package com.example.articlesexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.articlesexample.adapter.CategoryAdapter.CategoryViewHolder
import com.example.articlesexample.databinding.ItemArticleBinding
import com.example.articlesexample.databinding.ItemCategoryBinding
import com.example.articlesexample.model.DataCategories
import com.example.articlesexample.model.DataCategory

class CategoryAdapter constructor(private val listCategory : DataCategories) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listCategory.data?.size ?: 0
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        listCategory.data?.get(position)?.apply {
            holder.bind(this)
        }
    }

    inner class CategoryViewHolder(
        private val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataCategory: DataCategory){
            binding.tvCategory.text = dataCategory.attributes?.name
        }
    }

}