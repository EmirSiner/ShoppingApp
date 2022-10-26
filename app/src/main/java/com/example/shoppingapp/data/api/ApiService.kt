package com.example.shoppingapp.data.api

import com.example.patikaweek5.data.model.Product
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>

    @DELETE("products/{id}")
    fun deletePost(@Path("{id}") id:String) : Call<Product>
}