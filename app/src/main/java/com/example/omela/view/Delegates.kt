package com.example.omela.view

import android.view.View
import com.example.omela.data.model.*

interface Delegates {
    interface ViewClicked{
        fun onItemClick(view: String, basketItem: BasketItem)
    }

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
