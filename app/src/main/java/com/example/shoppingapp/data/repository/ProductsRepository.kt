package com.example.shoppingapp.data.repository

import com.example.patikaweek5.data.model.Product
import com.example.shoppingapp.data.local.entity.ProductEntity
import retrofit2.Call

interface ProductsRepository {
    fun getPosts(): Call<List<Product>>
    fun getPostById(id: Int): ProductEntity?
    fun insertAddCart(product: ProductEntity)
    fun deleteAddCart(id: String)
    fun getFavorites(): List<ProductEntity>
}