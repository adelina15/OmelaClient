package com.example.omela.view

import com.example.omela.data.model.*

interface Delegates {
    interface FlowerClicked{
        fun onItemClick(flower: FlowersItem)
    }

    interface BouquetClicked{
        fun onItemClick(bouquet: BouquetItem)
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
