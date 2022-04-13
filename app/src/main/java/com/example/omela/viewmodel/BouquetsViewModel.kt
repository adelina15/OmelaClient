package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.BouquetItem
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class BouquetsViewModel(private val repository: Repository): ViewModel(), DefaultLifecycleObserver {

    val bouquetsLiveData = MutableLiveData<Array<BouquetItem>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getAllBouquets()
    }

    private fun getAllBouquets(){
        viewModelScope.launch {
            val response = repository.getAllBouquets()
            if (response.isSuccessful){
                bouquetsLiveData.postValue(response.body())
            }
        }
    }
}
