package com.example.ourlife.ui.main.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ourlife.data.model.posts.PostsItemModel
import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.data.model.users.usersList
import com.example.ourlife.ui.main.profile.ProfileViewModel
import com.example.ourlife.ui.theme.Primary
import com.example.ourlife.ui.theme.TextColor

@Composable
fun FeedContent(
    onPostClick: () -> Unit
) {
    val viewModel = hiltViewModel<FeedViewModel>()
    val posts by viewModel.posts.collectAsState()
    viewModel.getPosts()

    //Shuffled List
    val shuffledPosts = posts.shuffled()

    LazyColumn(
        Modifier.padding(bottom = 58.dp)
    ) {
        items(shuffledPosts.size) { item ->
            FeedPostView(post = shuffledPosts.get(item), onPostClick)
        }
    }
}

@Composable
fun FeedPostView(
    post: PostsItemModel,
    onPostClick: () -> Unit
) {
    val currentUser = usersList[post.userId?.minus(1)!!]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Box(){
            Column {
                Text(
                    text = currentUser.name,
                    Modifier
                        .padding(vertical = 6.dp)
                        .padding(start = 68.dp, end = 8.dp),
                    color = TextColor,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary)
                ){
                    Text(
                        text = post.title.toString(),
                        Modifier
                            .padding(vertical = 6.dp)
                            .padding(start = 68.dp, end = 8.dp),
                        color = Color.White,
                        maxLines = 1,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Image(
                painter = rememberAsyncImagePainter(model = "https://randomuser.me/api/portraits/men/${currentUser.id}.jpg"),
                contentDescription = "Profile Picture",
                Modifier
                    .size(60.dp)
                    .border(2.dp, Primary, CircleShape)
                    .clip(CircleShape)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onPostClick() },
            shape = RoundedCornerShape(0.dp),
            elevation = 10.dp
        ) {
            Column() {
                Text(
                    text = post.body.toString(),
                    Modifier
                        .padding(8.dp),
                    color = TextColor,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "View comments",
                    color = TextColor,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(
                            Alignment.End
                        )
                )

            }
        }
    }
}