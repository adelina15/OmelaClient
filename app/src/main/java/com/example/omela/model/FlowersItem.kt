package com.example.omela.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FlowersItem(
    val flower_name: String,
    val flower_image: Int,
    val flower_price: Int,
    val is_favorite: Boolean = false
    )
