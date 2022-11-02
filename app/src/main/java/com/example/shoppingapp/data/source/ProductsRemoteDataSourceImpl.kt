package com.example.shoppingapp.data.source

import com.example.shoppingapp.data.model.product.Categories
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.source.api.ApiService
import retrofit2.Response
import javax.inject.Inject

class ProductsRemoteDataSourceImpl @Inject constructor(
    private val productApiService: ApiService
) {

    suspend fun getProducts(): Response<List<Product>> {
        return productApiService.getAllProducts()
    }


    suspend fun getCategories(): Response<Categories> {
        return productApiService.getAllCategories()
    }



}