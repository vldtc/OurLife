package com.example.ourlife.ui.main.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.ui.main.profile.ProfileViewModel
import com.example.ourlife.ui.theme.Primary

@Composable
fun PostsContent(
    onPostClick: () -> Unit
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val posts by viewModel.userPosts.collectAsState()
    viewModel.getUserPosts()

    LazyColumn(
        Modifier.padding(bottom = 58.dp)
    ) {
        items(posts.size) { item ->
            ProfilePostView(post = posts.get(item), onPostClick)
        }
    }
}

@Composable
fun ProfilePostView(
    post: PostsItemModel,
    onPostClick: () -> Unit
    ) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val comments by viewModel.postComments.collectAsState()

    viewModel.getPostComments(post.id)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPostClick() },
            shape = RoundedCornerShape(0.dp),
            elevation = 10.dp
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Primary)
                ) {
                    Text(
                        text = post.title.toString(),
                        Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        color = Color.White,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(text = post.body.toString(), Modifier.padding(8.dp))
                Text(
                    text = "${comments.size} comments",
                    Modifier
                        .padding(8.dp)
                        .align(
                            Alignment.End
                        )
                )

            }
        }

    }
}