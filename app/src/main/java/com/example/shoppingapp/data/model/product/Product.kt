package com.example.shoppingapp.data.model.product


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
        val id: Long,
    @SerializedName("title")
        val title: String?,
    @SerializedName("price")
    val price: Float?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String?,
    )

