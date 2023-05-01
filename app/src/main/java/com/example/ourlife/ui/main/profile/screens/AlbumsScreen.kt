package com.example.ourlife.ui.main.profile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ourlife.data.model.albums.AlbumsItemModel
import com.example.ourlife.ui.main.profile.ProfileViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ourlife.ui.theme.Primary

@Composable
fun AlbumsContent(){
    val viewModel = hiltViewModel<ProfileViewModel>()
    val albums by viewModel.userAlbums.collectAsState()
    viewModel.getUserAlbums()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        Modifier
            .padding(bottom = 55.dp)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ){
        items(albums.size){item->
            ProfileAlbumsView(albums.get(item), item)
        }
    }
}

@Composable
fun ProfileAlbumsView(
    album: AlbumsItemModel,
    item: Int
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://source.unsplash.com/random/400x300?${item + 1}"),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Primary),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = album.title.toString(),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}