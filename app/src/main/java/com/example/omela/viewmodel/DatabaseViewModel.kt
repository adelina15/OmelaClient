package com.example.omela.viewmodel

import androidx.lifecycle.*
import com.example.omela.data.model.BasketItem
import com.example.omela.data.repository.Repository
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: Repository) : ViewModel(),
    DefaultLifecycleObserver {

    val productList: LiveData<List<BasketItem>> = repository.getAllFromRoom().asLiveData()

    fun insert(item: BasketItem) =
        viewModelScope.launch {
            repository.insert(item)
        }

    fun delete(item: BasketItem) =
        repository.delete(item)

    suspend fun clear() =
        viewModelScope.launch {
            repository.clear()
        }
}