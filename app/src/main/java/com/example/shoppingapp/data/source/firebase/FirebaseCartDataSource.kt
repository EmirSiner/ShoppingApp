package com.example.shoppingapp.data.source.firebase

import androidx.lifecycle.ViewModel
import com.example.shoppingapp.data.model.carts.CartFirebaseModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

// TODO: Use delete and update methods from called place(Bu metodlarda quantity kısımlarını çağrıldığı yerde kontrol edip ona göre ilgili metodu çağır)

@Singleton
class FirebaseCartDataSource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) {

    private val _firebaseStateFlow: MutableStateFlow<FirebaseState> = MutableStateFlow(InitialState)
    val firebaseStateFlow: StateFlow<FirebaseState> = _firebaseStateFlow
    private val userId = firebaseAuth.currentUser?.uid

    fun getUserCart() {
        val collectionReference = firebaseFirestore
            .collection(CARTS_PATH)
            .document(userId.orEmpty())
            .collection(PRODUCTS_PATH)

        collectionReference.addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                _firebaseStateFlow.value = ErrorState(error.message.orEmpty())
                return@addSnapshotListener
            }

            if (querySnapshot != null && !querySnapshot.isEmpty) {
                val cartProducts = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.toObject(CartFirebaseModel::class.java)
                }
                _firebaseStateFlow.value = SuccessState(cartProducts)
            }
        }
    }

    fun addToUserCart(cartFirebaseModel: CartFirebaseModel) {
        val collectionReference = firebaseFirestore
            .collection(CARTS_PATH)
            .document(userId.orEmpty())
            .collection(PRODUCTS_PATH)

        collectionReference.add(cartFirebaseModel)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

    fun deleteToUserCart(cartFirebaseModel: CartFirebaseModel) {
        val documentReference = firebaseFirestore
            .collection(CARTS_PATH)
            .document(userId.orEmpty())

        documentReference.update(PRODUCTS_PATH, FieldValue.arrayRemove(cartFirebaseModel))
    }

    fun updateToUserCartProduct(cartFirebaseModel: CartFirebaseModel) {
        val collectionReference = firebaseFirestore
            .collection(CARTS_PATH)
            .document(userId.orEmpty())
            .collection(PRODUCTS_PATH)
    }

    fun clearUserCart() {
        val collectionReference = firebaseFirestore
            .collection(CARTS_PATH)
            .document(userId.orEmpty())
            .collection(PRODUCTS_PATH)
    }

    companion object {
        private const val PRODUCTS_PATH = "products"
        private const val CARTS_PATH = "products"
    }
}

sealed class FirebaseState

object InitialState : FirebaseState()
data class ErrorState(val errorMessage: String) : FirebaseState()
data class SuccessState(val cartProducts: List<CartFirebaseModel?>) : FirebaseState()