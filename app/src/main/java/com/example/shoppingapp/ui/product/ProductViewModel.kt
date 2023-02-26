package com.example.shoppingapp.ui.product

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.mapper.ProductMapper
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.repository.product.ProductsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductsRepository,
    private val productMapper: ProductMapper,
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
) : ViewModel() {
    val productsList = productRepository.getAllProductEntities()
    private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.Success(mutableListOf()))
    val uiState: StateFlow<HomeViewState> = _uiState
    private val _state = MutableStateFlow(ProductState())
    val state get() = _state.asStateFlow()
    private var roomList: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val itemCount = productRepository.getItemCount()
            Log.d("itemcount", itemCount.toString())
            if (itemCount == 0L) {
                val products = productRepository.getAllProducts()
                Log.d("itemcount", products.isEmpty().toString())
                if (products.isNotEmpty()) {
                    val productEntities = productMapper.toEntity(products)
                    productRepository.insertAllProductEntities(productEntities)
                }
            }
        }
        getProductList(id = null)
    }




private fun getProductList(id: Int?): Flow<MutableList<String>?> = channelFlow {
    val favoriteList = mutableListOf<String>()
    val callBack =
        fireStore.collection("productId").document(firebaseAuth.currentUser?.uid.toString())
            .collection("product").document(id.toString()).get().addOnSuccessListener {
                it.data?.values?.forEach { data ->
                    favoriteList.add(data.toString())
                }
                trySendBlocking(favoriteList)

            }.addOnFailureListener {
                trySendBlocking(mutableListOf())
            }
    awaitClose { callBack.isCanceled() }
}

    fun onBasketItem(data: ProductEntity) {
        viewModelScope.launch {
            val userId = firebaseAuth.currentUser?.uid
        }
    }

    fun addToCard() = viewModelScope.launch {
        state.value.product?.let {
            //  cartFirebaseModel.id
            //   cartFirebaseModel.quantity

        }
    }

}

sealed class ProductViewEvent {
object NavigateToDetail : ProductViewEvent()
class ShowMessage(val message: String?) : ProductViewEvent()
class Success(val products: MutableList<Product>) : ProductViewEvent()
}

sealed class HomeViewState {
    class Success(val productEntity: MutableList<ProductEntity>?) : HomeViewState()
object Loading : HomeViewState()
}

