package com.example.omela.model

data class HistoryItem(
    val date: String,
    val image1: Int,
    val image2: Int? = null,
    val image3: Int? = null,
    val sum: Int
        )