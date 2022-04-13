package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.BouquetItem
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class SaleBouquetsViewModel (private val repository: Repository): ViewModel(),
    DefaultLifecycleObserver {

    val saleBouquetsLiveData = MutableLiveData<Array<BouquetItem>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getSaleBouquets()
    }

    private fun getSaleBouquets() {
        viewModelScope.launch {
            val response = repository.getSaleBouquets()
            if (response.isSuccessful) {
                saleBouquetsLiveData.postValue(response.body())
            }
        }
    }
}