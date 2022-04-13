package com.example.omela.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.omela.data.model.BasketItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BouquetDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(basketItem: BasketItem)

    @Delete
    suspend fun delete(basketItem: BasketItem)

    @Query("SELECT * FROM basketitem")
    fun getAll(): Flow<List<BasketItem>>

    @Query("DELETE FROM basketitem")
    suspend fun clear()
}