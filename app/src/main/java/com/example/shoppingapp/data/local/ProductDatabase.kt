package com.example.shoppingapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.local.converter.DaoConverter
import com.example.shoppingapp.data.local.dao.ProductsDao
import com.example.shoppingapp.data.local.entity.ProductEntity
import com.example.shoppingapp.utils.Constants

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class ProductDatabase :RoomDatabase() {
    abstract fun productDao(): ProductsDao

    companion object {
        private var instance: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, ProductDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }

}