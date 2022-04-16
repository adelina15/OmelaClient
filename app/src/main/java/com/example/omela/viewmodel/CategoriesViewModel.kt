package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.CategoriesItem
import com.example.omela.data.model.CategoryBouquets
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class CategoriesViewModel(private val repository: Repository): ViewModel(), DefaultLifecycleObserver {

    val categoriesLiveData = MutableLiveData<ArrayList<CategoriesItem>>()
    val categoryBouquets = MutableLiveData<CategoryBouquets>()

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

    fun getBouquetsByCategory(id: Int){
        viewModelScope.launch {
            val response = repository.getBouquetByCategory(id)
            if (response.isSuccessful){
                categoryBouquets.postValue(response.body())
            }
        }
    }
}
