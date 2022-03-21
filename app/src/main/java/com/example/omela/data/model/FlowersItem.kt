package com.example.omela.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class FlowersItem(
    val flower_name: String,
    val flower_image: Int,
    val flower_price: Int,
    val is_favorite: Boolean = false,
    val status: String? = null
    ): Parcelable
