package com.example.omela.data.repository

import androidx.lifecycle.LiveData
import com.example.omela.data.api.BouquetApi
import com.example.omela.data.database.BouquetDao
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.data.model.CategoriesItem
import com.example.omela.data.model.CategoryBouquets
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

class Repository(private val api: BouquetApi, private val dao: BouquetDao) {

    suspend fun getAllBouquets(): Response<Array<BouquetItem>> {
        return api.getAllBouquets()
    }

    suspend fun getBouquetById(id: Int): Response<BouquetItem> {
        return api.getBouquetById(id)
    }

    suspend fun getBouquetByCategory(id: Int): Response<CategoryBouquets> {
        return api.getBouquetsInCategory(id)
    }

    suspend fun getAllCategories(): Response<ArrayList<CategoriesItem>> {
        return api.getAllCategories()
    }

    suspend fun getSaleBouquets(): Response<Array<BouquetItem>> {
        return api.getSaleBouquets()
    }

    fun getAllFromRoom(): Flow<List<BasketItem>> {
        return dao.getAll()
    }

    suspend fun insert(item: BasketItem) {
        dao.insert(item)
    }

    fun delete(item: BasketItem) {
        dao.delete(item)
    }

    suspend fun clear() {
        dao.clear()
    }
}