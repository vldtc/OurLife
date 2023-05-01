package com.example.ourlife.data.repository

import com.example.ourlife.data.model.albums.AlbumsItemModel
import com.example.ourlife.data.model.comments.CommentsItemModel
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.model.todos.TodosItemModel
import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.data.remote.ApiRequest
import javax.inject.Inject

class RepoImpl @Inject constructor(
    val apiRequest: ApiRequest
): Repository {
    override suspend fun getUserProfile(id: Int): UsersItemModel = apiRequest.getUserProfile(id)
    override suspend fun getUserPosts(id: Int): List<PostsItemModel> = apiRequest.getUserPosts(id)
    override suspend fun getUserAlbums(id: Int): List<AlbumsItemModel> = apiRequest.getUserAlbums(id)

    override suspend fun getUserTodos(id: Int): List<TodosItemModel> = apiRequest.getUserTodos(id)
    override suspend fun getPostComments(id: Int?): List<CommentsItemModel> = apiRequest.getPostComments(id)

}