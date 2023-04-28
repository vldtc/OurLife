package com.example.ourlife.data.repository

import com.example.ourlife.data.remote.ApiRequest
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {

}