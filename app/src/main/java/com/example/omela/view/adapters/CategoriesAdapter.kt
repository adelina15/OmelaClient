package com.example.omela.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.view.Delegates
import com.example.omela.R
import com.example.omela.databinding.CategoriesItemBinding
import com.example.omela.data.model.CategoriesItem

class CategoriesAdapter(private val categoryClicker: Delegates.CategoryClicked): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var list = listOf<CategoriesItem>()

    fun setList (list : List<CategoriesItem>){
        this.list = list
        notifyDataSetChanged()
    }

    class CategoryViewHolder(item: View): RecyclerView.ViewHolder(item)  {
        val binding = CategoriesItemBinding.bind(item)

        fun bind(category: CategoriesItem) = with(binding){
            categoriesText.text = category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.categories_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.layout.setOnClickListener {
            categoryClicker.onItemClick(list[position ])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}