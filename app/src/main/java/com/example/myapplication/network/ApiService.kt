package com.example.myapplication.network

import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): Product
}
