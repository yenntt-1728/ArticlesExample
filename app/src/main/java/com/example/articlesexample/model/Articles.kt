package com.example.articlesexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Articles(
    val data : List<Article>
)