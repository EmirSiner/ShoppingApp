package com.example.shoppingapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.data.repository.cart.CartRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel  @Inject constructor(
    private val cartRepository: CartRepository,
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): ViewModel() {
    init {
        getCartList(id = null)
    }
    val basketList: MutableLiveData<List<ProductEntity>> = MutableLiveData()

    fun addToUserCart(cartFirebaseModel: CartFirebaseModel) {
       viewModelScope.launch{ cartRepository.addToUserCart(cartFirebaseModel)}
    }

    fun deleteProduct(cartFirebaseModel: CartFirebaseModel) {
        cartRepository.deleteToUserCart(cartFirebaseModel)
    }

    fun updateToUserCartProduct(cartFirebaseModel: CartFirebaseModel) {
        cartRepository.updateToUserCartProduct(cartFirebaseModel)
    }

    fun clearUserCart() {
        cartRepository.clearUserCart()
    }

    private fun getCartList(id: Int?): Flow<MutableList<String>?> = channelFlow {
        val favoriteList = mutableListOf<String>()
        val callBack =
            fireStore.collection("productId").document(auth.currentUser?.uid.toString())
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
}