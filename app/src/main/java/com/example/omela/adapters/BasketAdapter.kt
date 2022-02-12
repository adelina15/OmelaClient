package com.example.omela.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.R
import com.example.omela.databinding.BasketItemBinding
import com.example.omela.model.BasketItem
import com.example.omela.model.FlowersItem

class BasketAdapter(): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var list = mutableListOf<BasketItem>()
    fun setList (list : MutableList<BasketItem>){
        this.list = list
    }

    class BasketViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = BasketItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(flowers: BasketItem) = with(binding) {
            basketName.text = flowers.name
            basketDiscount.text = (if (flowers.discount == null) {""} else {"-${flowers.discount}%"})
            basketImage.setImageResource(flowers.image)
            basketPrice.text = "${flowers.price} с"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.basket_item, parent, false)
        return BasketAdapter.BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}