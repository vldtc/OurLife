package com.example.ourlife.data.model.users


import com.google.gson.annotations.SerializedName

data class GeoModel(
    @SerializedName("lat")
    val lat: String? = "",
    @SerializedName("lng")
    val lng: String? = ""
)