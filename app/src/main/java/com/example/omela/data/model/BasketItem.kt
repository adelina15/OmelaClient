package com.example.omela.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basketitem")
data class BasketItem (
    @PrimaryKey
    val id: Int
    )