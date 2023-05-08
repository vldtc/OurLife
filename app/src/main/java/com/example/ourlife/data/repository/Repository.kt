package com.example.ourlife.data.repository

import com.example.ourlife.data.model.albums.AlbumsItemModel
import com.example.ourlife.data.model.comments.CommentsItemModel
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.model.todos.TodosItemModel
import com.example.ourlife.data.model.users.UsersItemModel

interface Repository {

    suspend fun getUserProfile(id: Int?): UsersItemModel

    suspend fun getUserPosts(id: Int?): List<PostsItemModel>

    suspend fun getUserAlbums(id: Int): List<AlbumsItemModel>

    suspend fun getUserTodos(id: Int): List<TodosItemModel>

    suspend fun getPostComments(id: Int?): List<CommentsItemModel>

    suspend fun getPosts(): List<PostsItemModel>

}