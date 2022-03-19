package com.example.omela.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.omela.Delegates
import com.example.omela.R
import com.example.omela.databinding.CategoriesItemBinding
import com.example.omela.model.CategoriesItem
import com.example.omela.model.FlowersItem

class CategoriesAdapter(private val categoryClicker: Delegates.CategoryClicked): RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var list = mutableListOf<CategoriesItem>()
    fun setList (list : MutableList<CategoriesItem>){
        this.list = list
    }

    class CategoryViewHolder(item: View): RecyclerView.ViewHolder(item)  {
        val binding = CategoriesItemBinding.bind(item)

        fun bind(category: CategoriesItem) = with(binding){
            categoriesText.text = category.category_name
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