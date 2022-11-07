package com.example.shoppingapp.data.di

import com.example.shoppingapp.data.local.dao.CategoryDao
import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.repository.cart.CartRepository
import com.example.shoppingapp.data.repository.cart.CartRepositoryImpl
import com.example.shoppingapp.data.repository.category.CategoriesRepository
import com.example.shoppingapp.data.repository.category.CategoriesRepositoryImpl
import com.example.shoppingapp.data.repository.product.ProductRepositoryImpl
import com.example.shoppingapp.data.repository.product.ProductsRepository
import com.example.shoppingapp.data.source.api.ApiService
import com.example.shoppingapp.data.source.firebase.FirebaseCartDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        apiService: ApiService,
        productDao: ProductsDao
    ): ProductsRepository {
        return ProductRepositoryImpl(apiService, productDao)
    }

    @Singleton
    @Provides
    fun provideCartRepository(
       firebaseCartDataSource: FirebaseCartDataSource
    ): CartRepository {
        return CartRepositoryImpl(firebaseCartDataSource)
    }

    @Singleton
    @Provides
    fun provideCategoriesRepository(
        apiService: ApiService,
        categoryDao: CategoryDao
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(categoryDao,apiService)
    }

}