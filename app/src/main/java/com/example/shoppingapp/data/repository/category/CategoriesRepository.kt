package com.example.shoppingapp.data.repository.category

import com.example.shoppingapp.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getAllCategories():List<String>
    suspend fun insertAllCategories(categories:List<CategoryEntity>)
    fun getAllCategoryEntities(): Flow<List<CategoryEntity>>
    suspend fun getCategoriesItemCount():Long
}