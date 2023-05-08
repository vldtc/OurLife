package com.example.ourlife.ui.main.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _posts = MutableStateFlow<List<PostsItemModel>>(emptyList())
    val posts: StateFlow<List<PostsItemModel>> = _posts

    fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable){
                val response = repository.getPosts()
                _posts.value = response
            }
        }

    }
}