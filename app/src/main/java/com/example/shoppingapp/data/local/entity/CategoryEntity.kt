package com.example.shoppingapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shoppingapp.utils.Constants

@Entity(tableName= "categories")
data class CategoryEntity(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String?
    )