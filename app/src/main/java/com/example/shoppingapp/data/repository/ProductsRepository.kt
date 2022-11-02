package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import retrofit2.Response


interface ProductsRepository {
    suspend fun getAllProducts():Response<List<Product>>
    fun getPostById(id: Long): ProductEntity?
    suspend fun addProduct(product: ProductEntity)
    fun insertAddCart(product: ProductEntity)
    fun deleteAddCart(product: ProductEntity)
}