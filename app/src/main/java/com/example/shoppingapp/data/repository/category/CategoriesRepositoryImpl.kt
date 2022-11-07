package com.example.shoppingapp.data.repository.category

import com.example.shoppingapp.data.local.dao.CategoryDao
import com.example.shoppingapp.data.local.entity.CategoryEntity
import com.example.shoppingapp.data.source.api.ApiService
import kotlinx.coroutines.flow.Flow

class CategoriesRepositoryImpl constructor(
    private val categoryDao: CategoryDao,
    private val apiService: ApiService
):CategoriesRepository {
    override suspend fun getAllCategories(): List<String> {
        return apiService.getAllCategories()
    }

    override suspend fun insertAllCategories(categories: List<CategoryEntity>) {
        categoryDao.insertAllCategories(categories)
    }

    override fun getAllCategoryEntities(): Flow<List<CategoryEntity>> {
        return categoryDao.getAllCategoryEntities()
    }

    override suspend fun getCategoriesItemCount(): Long {
       return categoryDao.getCategoriesItemCount()
    }

}