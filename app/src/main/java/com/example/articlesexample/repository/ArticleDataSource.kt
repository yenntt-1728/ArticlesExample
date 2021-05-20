package com.example.articlesexample.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.articlesexample.api.ArticleApi
import com.example.articlesexample.model.Article
import retrofit2.HttpException
import java.io.IOException

class ArticleDataSource (private val articleApi: ArticleApi)
    : PagingSource<Int, Article>(){
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val response = articleApi.getArticles(params.key)
            val articles = response.data
            val currentId = params.key
            val repos = response.data
            val nextId = if (repos.isNullOrEmpty()) null else repos.last().id
            val nextKey = if (repos.isNullOrEmpty() || nextId == currentId) {
                null
            } else {
                nextId
            }
            LoadResult.Page(
                data = articles,
                prevKey = currentId?.minus(1),
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}