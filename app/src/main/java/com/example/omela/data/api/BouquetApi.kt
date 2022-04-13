package com.example.omela.data.api

import com.example.omela.data.model.BouquetItem
import com.example.omela.data.model.CategoriesItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BouquetApi {

    @GET("categories?type=WITHOUT_REL")
    suspend fun getAllCategories(): Response<ArrayList<CategoriesItem>>

    @GET("bouquets")
    suspend fun getAllBouquets(): Response<Array<BouquetItem>>

    @GET("bouquets?discount=true")
    suspend fun getSaleBouquets(): Response<Array<BouquetItem>>

    @GET("bouquets/{id}")
    suspend fun getBouquetById(
        @Path ("id")
        id: Int?
    ): Response<BouquetItem>
}