package com.example.articlesexample.model

import com.google.gson.annotations.SerializedName

class DataCategory (
    @SerializedName("id")
    val id : Int?,
    @SerializedName("type")
    val type : String?,
    @SerializedName("attributes")
    val attributes: DataAttributes?
)