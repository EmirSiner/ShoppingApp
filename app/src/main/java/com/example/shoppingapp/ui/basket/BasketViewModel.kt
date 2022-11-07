package com.example.shoppingapp.ui.basket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.data.repository.cart.CartRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel  @Inject constructor(
    private val cartRepository: CartRepository,
    private val auth: FirebaseAuth
): ViewModel() {
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
}