package com.example.omela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.R
import com.example.omela.databinding.FlowerListItemBinding
import com.example.omela.model.FlowersItem

class FlowersAdapter(): RecyclerView.Adapter<FlowersAdapter.FlowersViewHolder>() {

    private var list = mutableListOf<FlowersItem>()
    fun setList (list : MutableList<FlowersItem>){
        this.list = list
    }

    class FlowersViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = FlowerListItemBinding.bind(item)

        fun bind(flowers:FlowersItem) = with(binding) {
            listName.text = flowers.flower_name
            listPrice.text = flowers.flower_price.toString()
            listImage.setImageResource(flowers.flower_image)
            if(flowers.is_favorite) heart.setImageResource(R.drawable.ic_heart_red)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowersViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.flower_list_item, parent, false)
        return FlowersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowersViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}