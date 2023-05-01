package com.example.ourlife.ui.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ourlife.data.model.users.UsersItemModel


@Composable
fun ProfileContent() {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val user by viewModel.user.collectAsState()

    Scaffold(
        content = {
            Column(
                Modifier.background(Color.Black)
            ) {
                ProfileUI(user = user)
            }
        }
    )
}

@Composable
fun ProfileUI(
    user: UsersItemModel
) {
    Column(
        Modifier
            .padding(top = 36.dp)
            .fillMaxWidth()
    ) {
        Text(text = user.name.toString())
        Text(text = user.username.toString())
        Image(
            painter = rememberAsyncImagePainter(model = "https://randomuser.me/api/portraits/men/${user.id}0.jpg"),
            contentDescription = "Profile Picture",
            Modifier.size(200.dp)
        )
    }
}