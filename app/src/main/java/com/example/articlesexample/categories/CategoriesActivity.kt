package com.example.articlesexample.categories

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.articlesexample.R
import com.example.articlesexample.adapter.CategoryAdapter
import com.example.articlesexample.databinding.ActivityCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesActivity : AppCompatActivity(){
    private val viewModel : CategoriesViewModel by viewModels()
    lateinit var binding : ActivityCategoriesBinding
    lateinit var adapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categories)
        viewModel.getCategories()
        handleObservable()
    }

    private fun handleObservable(){
        viewModel.dataCategories.observe(this, Observer {
            adapter = CategoryAdapter(it)
            binding.recyclerCategory.adapter = adapter
        })
    }
}