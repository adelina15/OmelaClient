package com.example.omela.view

import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.CategoriesItem
import com.example.omela.data.model.FlowersItem
import com.example.omela.data.model.HistoryItem

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

    interface BasketClicked{
        fun onItemClick(basketItem: BasketItem)
    }

}
