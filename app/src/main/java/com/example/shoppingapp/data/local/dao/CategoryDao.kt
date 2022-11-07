package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppingapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao  {

    @Query("SELECT * FROM categories")
    fun getAllCategoryEntities(): Flow<List<CategoryEntity>>

    @Insert
    suspend fun insertAllCategories(categories:List<CategoryEntity>)

    @Query("SELECT COUNT(*) FROM categories")
    suspend fun getCategoriesItemCount(): Long

}