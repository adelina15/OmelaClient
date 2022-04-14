package com.example.omela.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class BouquetItem(
//    val comments: String,
    val description: String,
    val discount: Int,
    val discountResult: Int,
    val id: Int,
    val name: String,
    val flowers: List<Flower>,
    val photo: String,
    val price: String,
    val size: Size,
    var quantity: Int = 0
): Serializable