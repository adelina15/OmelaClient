package com.example.omela.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.R
import com.example.omela.databinding.HistoryItemBinding
import com.example.omela.model.HistoryItem

class OrderHistoryAdapter: RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryHolder>() {

    private var list = mutableListOf<HistoryItem>()
    fun setList (list : MutableList<HistoryItem>){
        this.list = list
    }

    class OrderHistoryHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = HistoryItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(historyItem: HistoryItem) = with(binding) {
            date.text = historyItem.date
            image1.setImageResource(historyItem.image1)
            historyItem.image2?.let { image2.setImageResource(it) }
            historyItem.image3?.let { image3.setImageResource(it) }
            sumOfOrder.text = "${historyItem.sum} c"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.history_item, parent, false)
        return OrderHistoryHolder(view)
    }

    override fun onBindViewHolder(holder: OrderHistoryHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}