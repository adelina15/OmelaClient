package com.example.omela

import com.example.omela.model.CategoriesItem
import com.example.omela.model.FlowersItem

interface Delegates {
    interface FlowerClicked{
        fun onItemClick(flower: FlowersItem)
    }

    interface CategoryClicked{
        fun onItemClick(category: CategoriesItem)
    }
}