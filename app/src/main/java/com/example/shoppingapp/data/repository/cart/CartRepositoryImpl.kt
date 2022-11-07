package com.example.shoppingapp.data.repository.cart

import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.example.shoppingapp.data.source.firebase.FirebaseCartDataSource
import com.example.shoppingapp.data.source.firebase.FirebaseState
import kotlinx.coroutines.flow.StateFlow

class CartRepositoryImpl constructor(private val firebaseCartDataSource: FirebaseCartDataSource) :CartRepository {

    override val firebaseCartFlow: StateFlow<FirebaseState> =  firebaseCartDataSource.firebaseStateFlow
    override fun getUserCart() {
      firebaseCartDataSource.getUserCart()
    }

    override fun addToUserCart(cartFirebaseModel: CartFirebaseModel) {
        firebaseCartDataSource.addToUserCart(cartFirebaseModel)
    }

    override fun deleteToUserCart(cartFirebaseModel: CartFirebaseModel) {
        firebaseCartDataSource.deleteToUserCart(cartFirebaseModel)
    }

    override fun updateToUserCartProduct(cartFirebaseModel: CartFirebaseModel) {
        firebaseCartDataSource.updateToUserCartProduct(cartFirebaseModel)
    }

    override fun clearUserCart() {
       firebaseCartDataSource.clearUserCart()
    }


}