package com.example.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.local.converter.DaoConverter
import com.example.shoppingapp.data.local.dao.CategoryDao
import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.local.entity.CategoryEntity
import com.example.shoppingapp.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class,CategoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao(): ProductsDao
    abstract fun categoryDao(): CategoryDao
}