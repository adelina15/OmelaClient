package com.example.omela.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.data.model.BasketItem
import com.example.omela.databinding.BasketItemBinding
import com.example.omela.data.model.BouquetItem

class BasketAdapter(val basketClicked: Delegates.BasketClicked): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    private var list = listOf<BasketItem>()
    fun setList (list : List<BasketItem>){
        this.list = list
        notifyDataSetChanged()
    }

    class BasketViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = BasketItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(flowers: BasketItem) = with(binding) {
            basketName.text = flowers.name
            basketDiscount.text = (if (flowers.discount == null) {""} else {"-${flowers.discount}%"})
            Glide.with(itemView.context).load(flowers.photo).into(basketImage)
            basketPrice.text = "${flowers.price} с"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.basket_item, parent, false)
        return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.delete.setOnClickListener {
            basketClicked.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

//fun ImageView.loadWithGlide(url: String){
//    Glide.with(this)
//        .load(url)
//        .placeholder() //if there is no image
//        .into(this)
//}