package com.example.articlesexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @SerializedName("id")
    val id : Int?,
    @SerializedName("type")
    val type : String?,
    val attributes: Attributes?
)