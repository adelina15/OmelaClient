package com.example.omela.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.omela.data.model.BasketItem

@Database(entities = [BasketItem::class], version = 6, exportSchema = false)
abstract class MyDatabase(): RoomDatabase() {

    abstract fun dao(): BouquetDao

}