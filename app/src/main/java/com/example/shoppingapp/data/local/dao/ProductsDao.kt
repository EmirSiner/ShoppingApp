package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.shoppingapp.data.local.base.BaseDao
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import com.example.shoppingapp.utils.Constants

@Dao
interface ProductsDao : BaseDao<ProductEntity> {

    @Query("SELECT * FROM ${Constants.TABLE_PRODUCT_NAME}")
    fun getAllPosts(): List<ProductRecyclerViewItem.Product>

    @Query("DELETE FROM ${Constants.TABLE_PRODUCT_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_PRODUCT_NAME} WHERE id = :productId")
    fun getPostById(productId: String): ProductEntity?

    @Query("DELETE FROM ${Constants.TABLE_PRODUCT_NAME} WHERE id = :productId")
    suspend fun deleteAddCartById(productId: Int)



}