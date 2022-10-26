package com.example.shoppingapp.data.di

import android.content.Context
import com.example.shoppingapp.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun     providePostsDatabase(@ApplicationContext appContext : Context): ProductDatabase {
        return ProductDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db : ProductDatabase) = db.productDao()
}