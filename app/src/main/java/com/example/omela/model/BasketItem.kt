package com.example.omela.model

data class BasketItem (
    val name: String,
    val image: Int,
    val price: Int,
    val discount: Int? = null,
    )