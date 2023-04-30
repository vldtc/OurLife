package com.example.ourlife.data.repository

import com.example.ourlife.data.model.users.UsersItemModel

interface Repository {

    suspend fun getUserProfile(id: Int): UsersItemModel

}