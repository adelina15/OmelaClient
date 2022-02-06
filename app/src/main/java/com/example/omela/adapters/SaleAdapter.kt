package com.example.omela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.R
import com.example.omela.databinding.SaleItemBinding
import com.example.omela.model.SaleItem

class SaleAdapter(): RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    private var list = mutableListOf<SaleItem>()
    fun setList (list : MutableList<SaleItem>){
        this.list = list
    }

    class SaleViewHolder(item: View): RecyclerView.ViewHolder(item)   {
        val binding = SaleItemBinding.bind(item)

        fun bind(flowers: SaleItem) = with(binding) {
            listName.text = flowers.flower_name
            listPrice.text = flowers.flower_price.toString()
            listImage.setImageResource(flowers.flower_image)
            discountRate.text = flowers.discount_rate
            if(flowers.is_favorite) heart.setImageResource(R.drawable.ic_heart_red)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.sale_item, parent, false)
        return SaleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}