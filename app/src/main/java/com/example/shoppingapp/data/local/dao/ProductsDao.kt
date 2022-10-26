package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.shoppingapp.data.local.base.BaseDao
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.utils.Constants

@Dao
interface ProductsDao : BaseDao<ProductEntity> {

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts():List<ProductEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE id = :productId")
    fun getPostById(productId: String): ProductEntity?

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME} WHERE id = :productId")
    fun deleteAddCartById(productId: String)



}