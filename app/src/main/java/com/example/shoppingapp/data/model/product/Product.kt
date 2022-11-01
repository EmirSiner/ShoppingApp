package com.example.shoppingapp.data.model.product
sealed class ProductRecyclerViewItem {
    data class Product(
        val id: Int,
        val title: String?,
        val price: Float?,
        val category: String?,
        val description: String?,
        val image: String?,
    )

    data class ProductDTO(
        val id: Int,
        val title: String?,
        val price: Float?,
        val category: String?,
        val description: String?,
        val image: String?,
        val isAddToCard: Boolean = false

    )
}