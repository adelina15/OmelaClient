package com.example.omela.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.data.model.BouquetItem
import com.example.omela.databinding.FlowerListItemBinding
import com.example.omela.data.model.FlowersItem

class FlowersAdapter(
    private val context: Context,
    private val flowerClicker: Delegates.FlowerClicked
) : RecyclerView.Adapter<FlowersAdapter.FlowersViewHolder>() {

    private var list = mutableListOf<FlowersItem>()
    fun setList(list: MutableList<FlowersItem>) {
        this.list = list
    }

    class FlowersViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = FlowerListItemBinding.bind(item)
        fun bind(flowers: FlowersItem, context: Context) = with(binding) {
            listName.text = flowers.flower_name
            listPrice.text = flowers.flower_price.toString()
            listImage.setImageResource(flowers.flower_image)
            if (flowers.is_favorite) heart.setImageResource(R.drawable.ic_heart_red)
            if (flowers.status != null) {
                flowerStatus.visibility = View.VISIBLE
                flowerStatus.text = flowers.status
            }
            //logic to hide and show plus, minus and count
            plus.setOnClickListener {
                Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show()
                minus.visibility = View.VISIBLE
                count.visibility = View.VISIBLE
                plus.visibility = View.INVISIBLE
                count.text = "1"
            }
            minus.setOnClickListener {
                plus.visibility = View.VISIBLE
                count.visibility = View.INVISIBLE
                minus.visibility = View.INVISIBLE
                count.text = ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowersViewHolder {
        val view =
            LayoutInflater.from((parent.context)).inflate(R.layout.flower_list_item, parent, false)
        return FlowersViewHolder(view)
    }
//"https://res.cloudinary.com/hoifu2mlw/image/upload/v1648229203/v4cblaflav0pgnjpgnk0.jpg"
    override fun onBindViewHolder(holder: FlowersViewHolder, position: Int) {
        holder.bind(list[position], context)
        holder.binding.layout.setOnClickListener {
            flowerClicker.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}