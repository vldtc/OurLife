package com.example.ourlife.data.remote

import com.example.ourlife.data.model.albums.AlbumsItemModel
import com.example.ourlife.data.model.comments.CommentsItemModel
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.model.todos.TodosItemModel
import com.example.ourlife.data.model.users.UsersItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET(ApiDetails.USER_PROFILE)
    suspend fun getUserProfile(@Path("id")id: Int): UsersItemModel

    @GET(ApiDetails.USER_POSTS)
    suspend fun getUserPosts(@Path("id")id: Int): List<PostsItemModel>

    @GET(ApiDetails.USER_ALBUMS)
    suspend fun getUserAlbums(@Path("id")id: Int): List<AlbumsItemModel>

    @GET(ApiDetails.USER_TODOS)
    suspend fun getUserTodos(@Path("id")id: Int): List<TodosItemModel>

    @GET(ApiDetails.POST_COMMENTS)
    suspend fun getPostComments(@Path("id") id: Int?): List<CommentsItemModel>

}