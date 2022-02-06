package com.example.omela.model

data class SaleItem(
    val flower_name: String,
    val flower_image: Int,
    val flower_price: Int,
    val discount_rate: String,
    val is_favorite: Boolean = false
)