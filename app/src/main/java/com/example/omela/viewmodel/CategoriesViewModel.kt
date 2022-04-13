package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.CategoriesItem
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: Repository): ViewModel(), DefaultLifecycleObserver {

    val categoriesLiveData = MutableLiveData<ArrayList<CategoriesItem>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getAllBouquets()
    }

    private fun getAllBouquets(){
        viewModelScope.launch {
            val response = repository.getAllCategories()
            if (response.isSuccessful){
                categoriesLiveData.postValue(response.body())
            }
        }
    }
}
