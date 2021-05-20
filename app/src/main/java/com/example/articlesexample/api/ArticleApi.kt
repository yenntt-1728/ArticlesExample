package com.example.articlesexample.api

import com.example.articlesexample.model.Articles
import com.example.articlesexample.model.DataCategories
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {
    @GET("contents")
    suspend fun getArticles(@Query("content_id") id: Int?) : Articles

    @GET("categories")
    suspend fun getCategories() : DataCategories
}