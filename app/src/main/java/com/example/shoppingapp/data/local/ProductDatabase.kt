package com.example.shoppingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.local.converter.DaoConverter
import com.example.shoppingapp.data.local.dao.CartDao
import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.local.entity.CartEntity
import com.example.shoppingapp.data.local.entity.ProductEntity

@Database(entities = [ProductEntity::class,CartEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao(): ProductsDao
    abstract fun cartDao():CartDao

}