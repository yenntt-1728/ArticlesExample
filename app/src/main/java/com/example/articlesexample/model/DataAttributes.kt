package com.example.articlesexample.model

import com.google.gson.annotations.SerializedName

data class DataAttributes(
    @SerializedName("name")
    val name : String?,
    @SerializedName("uri")
    val uri : String?,
    @SerializedName("ordinal")
    val ordinal : Int?
)