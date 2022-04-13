package com.example.omela.view.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.omela.R
import com.example.omela.data.model.BasketItem
import com.example.omela.data.model.BouquetItem
import com.example.omela.databinding.FlowerListItemBinding
import com.example.omela.view.Delegates

class BouquetsAdapter(private val context: Context,
                      private val bouquetClicked: Delegates.BouquetClicked,
                      private val viewClicked: Delegates.ViewClicked
): RecyclerView.Adapter<BouquetsAdapter.ViewHolder>() {

    private var list = listOf<BouquetItem>()

    fun setData(newList: List<BouquetItem>) {
        this.list = newList
        notifyDataSetChanged()
    }


    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = FlowerListItemBinding.bind(item)
        @SuppressLint("SetTextI18n")
        fun bind(card: BouquetItem) = with(binding) {
            if(card.discount != 0) {
                flowerStatus.text = "- ${card.discount} %"
                flowerStatus.visibility = View.VISIBLE
            }
            Glide.with(itemView.context).load(card.photo).into(listImage)
            listName.text = card.name
            listPrice.text = card.price
            val item = BasketItem(card.id, card.name, card.photo, card.price, card.discount, card.discountResult)
            //logic to hide and show plus, minus and count
            plus.setOnClickListener {
                minus.visibility = View.VISIBLE
                count.visibility = View.VISIBLE
                plus.visibility = View.INVISIBLE
                count.text = "1"
                viewClicked.onItemClick("plus", item)
            }
            minus.setOnClickListener {
                plus.visibility = View.VISIBLE
                count.visibility = View.INVISIBLE
                minus.visibility = View.INVISIBLE
                count.text = ""
                viewClicked.onItemClick("minus", item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.flower_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BouquetsAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
        with(holder.binding) {
            layout.setOnClickListener {
                bouquetClicked.onItemClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
