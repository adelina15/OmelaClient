package com.example.omela.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem(
    @PrimaryKey var bouquetId: Int,
    val name: String,
    val photo: String,
    val price: String,
    val discount: Int? = null,
    val discountResult: Int,
//    @PrimaryKey(autoGenerate = true) var id: Int
    )