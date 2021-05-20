package com.example.articlesexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articlesexample.Utils.RecyclerViewItemDecoration
import com.example.articlesexample.adapter.ArticlesAdapter
import com.example.articlesexample.adapter.ArticlesLoadingStateAdapter
import com.example.articlesexample.categories.CategoriesActivity
import com.example.articlesexample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ArticlesListener {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private var job: Job? = null
    private val adapter =
        ArticlesAdapter({onClickItem()}, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.listener = this
        setUpAdapter()
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.getArticleLiveData().observe(this@MainActivity, {
                adapter.submitData(this@MainActivity.lifecycle, it)
            })

        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun setUpAdapter() {
        binding.allProductRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addItemDecoration(RecyclerViewItemDecoration())
        }
        binding.allProductRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = ArticlesLoadingStateAdapter { retry() }
        )

        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {

                if (adapter.snapshot().isEmpty()) {
                    binding.progress.visibility = View.VISIBLE
                }
                binding.errorTxt.visibility = View.GONE

            } else {
                binding.progress.visibility = View.GONE
                binding.swipeRefreshLayout.isRefreshing = false


                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (adapter.snapshot().isEmpty()) {
                        binding.errorTxt.visibility = View.VISIBLE
                        binding.errorTxt.text = it.error.localizedMessage
                    }

                }

            }
        }

    }

    private fun retry() {
        adapter.retry()
    }

    private fun onClickItem(){

    }

    override fun goToCategories() {
        val intent = Intent(this, CategoriesActivity::class.java)
        startActivity(intent)
    }
}