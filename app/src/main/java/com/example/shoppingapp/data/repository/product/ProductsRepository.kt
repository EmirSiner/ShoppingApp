package com.example.shoppingapp.data.repository.product

import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface ProductsRepository {
    suspend fun getAllProducts():List<Product>
    fun getAllProductEntities(): Flow<List<ProductEntity>>
    suspend fun insertAllProductEntities(productEntities:List<ProductEntity>)
    suspend fun getItemCount(): Long
    fun filterProductByName(query:String) : Flow<List<ProductEntity>>
    fun filterProductByCategory(category:String) : Flow<List<ProductEntity>>
}
