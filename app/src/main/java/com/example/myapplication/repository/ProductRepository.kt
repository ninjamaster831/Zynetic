package com.example.myapplication.repository

import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductResponse
import com.example.myapplication.network.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProductById(id: Int): Product {
        return apiService.getProductDetail(id)
    }

    suspend fun getAllProducts(): ProductResponse {
        return apiService.getProducts()
    }
}
