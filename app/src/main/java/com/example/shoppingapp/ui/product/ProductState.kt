package com.example.shoppingapp.ui.product

import com.example.shoppingapp.data.model.product.Product

data class ProductState (
    val product:List<Product?> = emptyList(),
    val amount: Int = 1,
    val isLoading: Boolean = true,
    val error: String? = null
)