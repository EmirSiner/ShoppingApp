package com.example.shoppingapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.utils.Constants

@Entity(tableName= Constants.TABLE_POST_NAME)
class ProductEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "price")
    val price:Int?,
    @ColumnInfo(name = "description")
    val description:Int?,
    @ColumnInfo(name = "category")
    val category:Int?
)