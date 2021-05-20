package com.example.articlesexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Attributes (
        @SerializedName("name")
        val name : String?,
        @SerializedName("uri")
        val uri : String?,
        @SerializedName("ordinal")
        val ordinal : Int?,
        @SerializedName("description")
        val description : String?,
        @SerializedName("released_at")
        val released_at : String?,
        @SerializedName("card_artwork_url")
        val card_artwork_url : String?
)