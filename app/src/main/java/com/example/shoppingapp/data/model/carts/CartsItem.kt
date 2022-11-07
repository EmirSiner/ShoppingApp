package com.example.shoppingapp.data.model.carts

data class CartsItem(
    val date: String,
    val id: Int,
    val products: List<CartProduct>,
    val userId: Int
)