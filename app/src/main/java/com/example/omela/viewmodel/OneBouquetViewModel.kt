package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class OneBouquetViewModel(private val repository: Repository): ViewModel(),
    DefaultLifecycleObserver {


    val bouquet = MutableLiveData<BouquetItem>()
//    lateinit var bouquet: BouquetItem

    fun getBouquetById(id: Int) {
        viewModelScope.launch {
            val response = repository.getBouquetById(id)
            if (response.isSuccessful){
//                bouquet = response.body()!!
                bouquet.postValue(response.body())
            }
        }
    }
}
