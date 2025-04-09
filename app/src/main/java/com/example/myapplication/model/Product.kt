package com.example.myapplication.model


data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)

data class ProductResponse(val products: List<Product>)
