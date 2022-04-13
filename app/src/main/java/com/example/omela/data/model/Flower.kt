package com.example.omela.data.model

import java.io.Serializable

data class Flower(
    val id: Int,
    val name: String,
    val photo: String,
    val price: Int,
    val quantity: Int
): Serializable