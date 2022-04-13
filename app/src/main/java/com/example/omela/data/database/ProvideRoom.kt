package com.example.omela.data.database

import android.content.Context
import androidx.room.Room
import com.example.omela.Constants.DATABASE_NAME

class ProvideRoom(context: Context) {

    private val applicationDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    fun provide(): MyDatabase{
        return applicationDatabase
    }
}