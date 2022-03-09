package com.example.omela

import com.example.omela.model.CategoriesItem
import com.example.omela.model.FlowersItem
import com.example.omela.model.HistoryItem

interface Delegates {
    interface FlowerClicked{
        fun onItemClick(flower: FlowersItem)
    }

    interface CategoryClicked{
        fun onItemClick(category: CategoriesItem)
    }

    interface OrderClicked{
        fun onItemClick(historyItem: HistoryItem)
    }
}
