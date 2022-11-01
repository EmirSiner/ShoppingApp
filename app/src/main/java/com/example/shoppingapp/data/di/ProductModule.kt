package com.example.shoppingapp.data.di

import com.example.shoppingapp.data.source.api.ApiService
import com.example.shoppingapp.data.local.ProductDatabase
import com.example.shoppingapp.data.repository.ProductsRepository
import com.example.shoppingapp.data.repository.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ProductModule {

    @Provides
    fun providePostRepository(
        apiService: ApiService,
        productDatabase: ProductDatabase
    ): ProductsRepository {
        return ProductRepositoryImpl(apiService, productDatabase)
    }
}