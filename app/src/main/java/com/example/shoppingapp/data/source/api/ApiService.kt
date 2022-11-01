package com.example.shoppingapp.data.source.api

import com.example.shoppingapp.data.model.carts.Carts
import com.example.shoppingapp.data.model.carts.CartsItem
import com.example.shoppingapp.data.model.product.Categories
import com.example.shoppingapp.data.model.product.ProductRecyclerViewItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("products")
    suspend fun getAllProducts(
    ): Response<List<ProductRecyclerViewItem.Product>>

    @GET("products/categories")
    suspend fun getAllCategories(
    ):Response<Categories>

    @GET("carts")
    suspend fun getAllCarts(
    ):Response<Carts>

    @GET("carts/productId")
    suspend fun getAllCartsId(
    ):Response<CartsItem>

}

// response  isteğin durumunu verir. (200 400 )
//call isteğin sucsess durumunu verir