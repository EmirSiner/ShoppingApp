package com.example.shoppingapp.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.mapper.ProductMapper
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.model.product.ProductDTO
import com.example.shoppingapp.data.repository.product.ProductsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductsRepository,
    private val productMapper: ProductMapper,
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : ViewModel() {
    val productEntity: MutableLiveData<List<ProductEntity>> = MutableLiveData()
    val productsList = productRepository.getAllProductEntities()
    private val _uiState = MutableStateFlow<HomeViewState>(HomeViewState.Success(mutableListOf()))
    val uiState: StateFlow<HomeViewState> = _uiState
    private var roomList: MutableLiveData<List<Product>> = MutableLiveData()

    init {
        /*  viewModelScope.launch {
    val itemCount = productRepository.getItemCount()
    if (itemCount == 0L){
        val products = productRepository.getAllProducts()
        if (products.isNotEmpty()){
            val productEntities = productMapper.toEntity(products)
            productRepository.insertAllProductEntities(productEntities)
        }
    }
  }*/

}

private fun getProductList(id: Int?): Flow<MutableList<String>?> = channelFlow {
val favoriteList = mutableListOf<String>()
val callBack =
  fireStore.collection("favoriteMovie").document(firebaseAuth.currentUser?.uid.toString())
      .collection("movie").document(id.toString()).get().addOnSuccessListener {
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
}

sealed class ProductViewEvent {
object NavigateToDetail : ProductViewEvent()
class ShowMessage(val message: String?) : ProductViewEvent()
class Success(val products: MutableList<Product>) : ProductViewEvent()
}

sealed class HomeViewState {
class Success(val popularMovies: MutableList<ProductDTO>?) : HomeViewState()
object Loading : HomeViewState()
}


