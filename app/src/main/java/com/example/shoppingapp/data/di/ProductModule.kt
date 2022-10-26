package com.example.shoppingapp.data.di

import com.example.shoppingapp.data.api.ApiService
import com.example.shoppingapp.data.local.ProductDatabase
import com.example.shoppingapp.data.repository.ProductsRepository
import com.example.shoppingapp.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ProductModule {
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePostRepository(apiService: ApiService, productDatabase: ProductDatabase): ProductsRepository {
        return RepositoryImpl(apiService, productDatabase)
    }
}