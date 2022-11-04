package com.example.shoppingapp.data.di

import com.example.shoppingapp.data.local.dao.CartDao
import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.repository.cart.CartRepository
import com.example.shoppingapp.data.repository.cart.CartRepositoryImpl
import com.example.shoppingapp.data.repository.product.ProductRepositoryImpl
import com.example.shoppingapp.data.repository.product.ProductsRepository
import com.example.shoppingapp.data.source.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
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
        cartDao: CartDao
    ): CartRepository {
        return CartRepositoryImpl(cartDao)
    }

}