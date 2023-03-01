package com.example.shoppingapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.utils.Constants

@Entity(tableName= "products")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "price")
    val price:Float?,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "category")
    val category:String?,
    @ColumnInfo(name = "image")
    val image: String?
){
    val priceText:String get() = price.toString()
}
