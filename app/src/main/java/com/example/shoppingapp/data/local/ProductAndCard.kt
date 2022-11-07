package com.example.shoppingapp.data.local

import androidx.room.Query
import com.example.shoppingapp.data.model.CardProductInfoModel
import kotlinx.coroutines.flow.Flow

interface ProductAndCard {
    @Query("SELECT products.id as id, products.title as title, products.price as price, carts.quantity as quantity FROM products, carts")
    fun getCardProductInfo(): Flow<List<CardProductInfoModel>>
}