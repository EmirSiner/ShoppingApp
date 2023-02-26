package com.example.shoppingapp.data.model.carts

data class CartFirebaseModel  (
    val id:Int,
    val imageUrl:String,
    val price:Float,
    val quantity:Int,
    val title:String
)
