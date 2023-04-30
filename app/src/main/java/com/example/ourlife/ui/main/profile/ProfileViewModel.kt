package com.example.ourlife.ui.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourlife.data.model.users.UsersItemModel
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
class ProfileViewModel @Inject constructor(
    val repository: Repository
): ViewModel() {

    private val _user = MutableStateFlow(UsersItemModel())
    val user: StateFlow<UsersItemModel> = _user

    init{
        getUserProfile()
    }

    fun getUserProfile(){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable){
                val response = repository.getUserProfile(1)
                _user.value = response
            }
        }
    }
}