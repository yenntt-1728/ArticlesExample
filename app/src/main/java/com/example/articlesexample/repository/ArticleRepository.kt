package com.example.articlesexample.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.articlesexample.api.ArticleApi
import com.example.articlesexample.model.Article
import com.example.articlesexample.model.DataAttributes
import com.example.articlesexample.model.DataCategories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val articleApi: ArticleApi) {

    fun getArticleLiveData(): LiveData<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                initialLoadSize = 1,
                prefetchDistance = 2),
                pagingSourceFactory = { ArticleDataSource(articleApi)
            }
        ).liveData

    }

    suspend fun getCategories() : DataCategories{
        return articleApi.getCategories()
    }
}