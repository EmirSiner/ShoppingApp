package com.example.shoppingapp.data.model.product

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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

@Parcelize
data class ProductDTO(
    val id: Long,
    val title: String?,
    val price: Float?,
    val category: String?,
    val description: String,
    val image: String?,
    val isAddToCard: Boolean
): Parcelable
