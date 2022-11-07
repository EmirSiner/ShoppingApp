package com.example.shoppingapp.data.repository.product

import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.Product
import com.example.shoppingapp.data.source.api.ApiService
import kotlinx.coroutines.flow.Flow

 class ProductRepositoryImpl constructor(
     private val apiService: ApiService,
     private val productDao: ProductsDao,
 ) : ProductsRepository {

     override suspend fun getAllProducts():List<Product> {
         return apiService.getAllProducts()
     }

     override fun getAllProductEntities(): Flow<List<ProductEntity>> {
         return productDao.getAllProducts()
     }

     override suspend fun insertAllProductEntities(productEntities: List<ProductEntity>) {
         productDao.insertAllProducts(productEntities)
     }

     override suspend fun getItemCount(): Long {
         return productDao.getProductItemCount()
     }

     override fun filterProductByName(query: String): Flow<List<ProductEntity>> {
         return productDao.filterProductByName(query)
     }

     override fun filterProductByCategory(category: String): Flow<List<ProductEntity>> {
        return productDao.filterProductByCategory(category)
     }
 }




