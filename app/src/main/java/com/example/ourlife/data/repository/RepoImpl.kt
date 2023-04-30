package com.example.ourlife.data.repository

import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.data.remote.ApiRequest
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {
    override suspend fun getUserProfile(id: Int): UsersItemModel = apiRequest.getUserProfile(id)

}