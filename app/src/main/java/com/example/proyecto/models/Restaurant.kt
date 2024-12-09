package com.example.proyecto.models

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val latitude: String,
    val longitude: String,
    val logo: String,
    val products: List<Product> // Lista de productos
)

