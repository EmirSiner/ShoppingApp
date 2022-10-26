package com.example.shoppingapp.data.repository

import com.example.patikaweek5.data.model.Product
import com.example.shoppingapp.data.api.ApiService
import com.example.shoppingapp.data.local.ProductDatabase
import com.example.shoppingapp.data.local.entity.ProductEntity
import retrofit2.Call

class RepositoryImpl constructor(
    private val apiService: ApiService,
    private val productDatabase: ProductDatabase
) : ProductsRepository{
    override fun getPosts(): Call<List<Product>> {
        return apiService.getProducts()
    }

    override fun getPostById(id: Int): ProductEntity? {
        return productDatabase.productDao().getPostById(id.toString())
    }

    override fun insertAddCart(product: ProductEntity) {
        return productDatabase.productDao().insert(product)
    }

    override fun deleteAddCart(id: String) {
        return productDatabase.productDao().deleteAddCartById(id)
    }

    override fun getFavorites(): List<ProductEntity> {
        return productDatabase.productDao().getAllPosts()
    }

}