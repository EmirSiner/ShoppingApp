package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import retrofit2.Response

interface ProductsRepository {
    suspend fun getAllProducts():Response<List<ProductRecyclerViewItem.Product>>
    fun getPostById(id: Int): ProductEntity?
    fun insertAddCart(product: ProductEntity)
    fun deleteAddCart(product: ProductEntity)
}