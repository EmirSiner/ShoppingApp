package com.example.shoppingapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "carts")
class CartEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    val productId:Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)