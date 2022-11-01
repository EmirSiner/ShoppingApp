package com.example.shoppingapp.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.utils.Constants

@Dao
interface CartDao {
    @Insert
    suspend fun save(product: ProductEntity)
    @Update
    suspend fun update(product: ProductEntity)

    @Query("DELETE FROM ${Constants.TABLE_PRODUCT_NAME} WHERE id=:productId")
    suspend fun deleteProduct(productId: Int)

    @Query("SELECT * FROM ${Constants.TABLE_PRODUCT_NAME}")
    fun getAllProducts(): List<ProductEntity>
}