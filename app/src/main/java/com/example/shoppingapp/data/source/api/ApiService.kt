package com.example.shoppingapp.data.source.api

import com.example.shoppingapp.data.model.product.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(
    ): List<Product>
    @GET("products/categories")
    suspend fun getAllCategories(
    ):List<String>
}

