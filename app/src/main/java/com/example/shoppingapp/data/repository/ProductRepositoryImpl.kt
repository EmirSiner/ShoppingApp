package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.local.ProductDatabase
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import com.example.shoppingapp.data.source.api.ApiService
import retrofit2.Response
import javax.inject.Inject

 class ProductRepositoryImpl @Inject constructor(
     private val apiService: ApiService,
     private val productDatabase: ProductDatabase,
 ) : ProductsRepository {
     //remote
     override suspend fun getAllProducts(): Response<List<ProductRecyclerViewItem.Product>>{
         return apiService.getAllProducts()
     }
     //Local

     override fun getPostById(id: Int): ProductEntity? {
        return productDatabase.productDao().getPostById(id.toString())
     }

     override fun insertAddCart(product: ProductEntity) {
         return productDatabase.productDao().insert(product)
     }

     override fun deleteAddCart(product: ProductEntity){
         return productDatabase.productDao().delete(product)
     }
 }



