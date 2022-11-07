package com.example.shoppingapp.data.repository.cart

import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.data.source.firebase.FirebaseState
import kotlinx.coroutines.flow.StateFlow

interface CartRepository{
    val firebaseCartFlow: StateFlow<FirebaseState>
    fun getUserCart()
    fun addToUserCart(cartFirebaseModel: CartFirebaseModel)
    fun deleteToUserCart(cartFirebaseModel: CartFirebaseModel)
    fun updateToUserCartProduct(cartFirebaseModel: CartFirebaseModel)
    fun clearUserCart()
}