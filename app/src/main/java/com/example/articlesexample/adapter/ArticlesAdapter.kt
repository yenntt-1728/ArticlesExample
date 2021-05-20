package com.example.articlesexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.articlesexample.databinding.ItemArticleBinding
import com.example.articlesexample.model.Article

class ArticlesAdapter(private val clicked: (String) -> Unit,
private val context: Context) :
    PagingDataAdapter<Article, ArticlesAdapter.ArticlesViewHolder>(
        ArticlesDiffCallback()
    ) {


    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {

        return ArticlesViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    inner class ArticlesViewHolder(
        private val binding: ItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Article?) {
            binding.let {
                data?.attributes?.apply {
                    it.description = this.description
                    it.name = this.name
                    it.releaseAt = this.released_at
                    Glide.with(context)
                        .load(this.card_artwork_url)
                        .into(it.ivArticle)
                }
            }
        }
    }

    private class ArticlesDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}