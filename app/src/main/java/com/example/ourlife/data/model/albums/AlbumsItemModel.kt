package com.example.ourlife.data.model.albums


import com.google.gson.annotations.SerializedName

data class AlbumsItemModel(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
)