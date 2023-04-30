package com.example.ourlife.data.remote

import com.example.ourlife.data.model.users.UsersItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET(ApiDetails.USER_PROFILE)
    suspend fun getUserProfile(@Path("id")id: Int): UsersItemModel

}