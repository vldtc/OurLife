package com.example.ourlife.ui.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ourlife.R
import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.ui.theme.Primary
import com.example.ourlife.ui.theme.Secondary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent() {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = { Toolbar() },
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
private fun Toolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo",
                Modifier
                    .padding(start = 16.dp)
                    .size(32.dp)
            )
            Text(
                text = "OurLife",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more_vert),
                contentDescription = "More Vert",
                tint = Primary
            )
        }
    }
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