package com.example.omela.koin


import androidx.room.Dao
import com.example.omela.Constants
import com.example.omela.data.api.BouquetApi
import com.example.omela.data.database.BouquetDao
import com.example.omela.data.database.ProvideRoom
import com.example.omela.data.repository.Repository
import com.example.omela.viewmodel.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    single { getOkHttp() }
    single { getRetrofitInstance(okHttpClient = get()) }
    single { getBouquetApi(retrofit = get()) }
    single { ProvideRoom(context = androidContext()) }
    factory { Repository(api = get(), dao = get()) }
    single { getProductDao(database = get()) }
}

val viewModules = module {
    viewModel { BouquetsViewModel(repository = get()) }
    viewModel { CategoriesViewModel(repository = get()) }
    viewModel { SaleBouquetsViewModel(repository = get()) }
    viewModel { OneBouquetViewModel(repository = get()) }
    viewModel { DatabaseViewModel(repository = get()) }
}

fun getProductDao(database: ProvideRoom): BouquetDao {
    return database.provide().dao()
}

fun getOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun getBouquetApi(retrofit: Retrofit): BouquetApi {
    return retrofit.create(BouquetApi::class.java)
}

fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}