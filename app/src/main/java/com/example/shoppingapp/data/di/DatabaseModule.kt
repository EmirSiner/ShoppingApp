package com.example.shoppingapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.shoppingapp.data.local.ProductDatabase
import com.example.shoppingapp.data.local.dao.CartDao
import com.example.shoppingapp.data.local.dao.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideShoppingDatabase(@ApplicationContext context:Context): ProductDatabase {
      return  Room.databaseBuilder(context, ProductDatabase::class.java, "shopping.dd")
            .build()

    }
    @Provides
    @Singleton
    fun provideProductDao(productDatabase: ProductDatabase): ProductsDao {
        return productDatabase.productDao()
    }

    @Provides
    @Singleton
    fun provideCartDao(productDatabase: ProductDatabase): CartDao {
        return productDatabase.cartDao()
    }
}