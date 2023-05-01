package com.example.ourlife.ui.main.profile

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourlife.data.model.albums.AlbumsItemModel
import com.example.ourlife.data.model.comments.CommentsItemModel
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.model.todos.TodosItemModel
import com.example.ourlife.data.model.users.AddressModel
import com.example.ourlife.data.model.users.CompanyModel
import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.data.repository.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    val id = 5

    private val _user = MutableStateFlow(UsersItemModel())
    val user: StateFlow<UsersItemModel> = _user

    private val _userAddress = MutableStateFlow(AddressModel())
    val userAddress: StateFlow<AddressModel> = _userAddress

    private val _userCompany = MutableStateFlow(CompanyModel())
    val userCompany: StateFlow<CompanyModel> = _userCompany

    private val _userPosts = MutableStateFlow<List<PostsItemModel>>(emptyList())
    val userPosts: StateFlow<List<PostsItemModel>> = _userPosts

    private val _postComments = MutableStateFlow<List<CommentsItemModel>>(emptyList())
    val postComments: StateFlow<List<CommentsItemModel>> = _postComments

    private val _userAlbums = MutableStateFlow<List<AlbumsItemModel>>(emptyList())
    val userAlbums: StateFlow<List<AlbumsItemModel>> = _userAlbums

    private val _userTodos = MutableStateFlow<List<TodosItemModel>>(emptyList())
    val userTodos: StateFlow<List<TodosItemModel>> = _userTodos

    fun getUserProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable){
                val response = repository.getUserProfile(id)
                _user.value = response
                _userAddress.value = response.address!!
                _userCompany.value = response.company!!
            }
        }
    }

    fun getUserPosts(){
        viewModelScope.launch (Dispatchers.IO){
            withContext(NonCancellable){
                val response = repository.getUserPosts(id)
                _userPosts.value = response
            }
        }
    }

    fun getUserAlbums(){
        viewModelScope.launch (Dispatchers.IO){
            withContext(NonCancellable){
                val response = repository.getUserAlbums(id)
                _userAlbums.value = response
            }
        }
    }

    fun getUserTodos(){
        viewModelScope.launch (Dispatchers.IO){
            withContext(NonCancellable){
                val response = repository.getUserTodos(id)
                _userTodos.value = response
            }
        }
    }

    fun getPostComments(id: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                val response = repository.getPostComments(id)
                _postComments.value = response
            }
        }
    }
}