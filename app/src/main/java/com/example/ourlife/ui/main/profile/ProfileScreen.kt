package com.example.ourlife.ui.main.profile

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ourlife.R
import com.example.ourlife.data.model.users.AddressModel
import com.example.ourlife.data.model.users.CompanyModel
import com.example.ourlife.data.model.users.UsersItemModel
import com.example.ourlife.ui.main.profile.screens.ProfileTabsContent
import com.example.ourlife.ui.theme.BoxColor
import com.example.ourlife.ui.theme.DarkBackground
import com.example.ourlife.ui.theme.Primary
import com.example.ourlife.ui.theme.TextColor


@Composable
fun ProfileContent(
) {

    val viewModel = hiltViewModel<ProfileViewModel>()
    val user by viewModel.user.collectAsState()
    val userAddress by viewModel.userAddress.collectAsState()
    val userCompany by viewModel.userCompany.collectAsState()

    viewModel.getUserProfile(5)

    Scaffold(
        content = {
            Column(
                Modifier.background(DarkBackground)
            ) {
                ProfileInfoSection(user = user)
                AboutSection(user = user, userAddress = userAddress, userCompany = userCompany)
                ProfileBoxOverlay()
            }
        }
    )
}

@Composable
fun ProfileInfoSection(
    user: UsersItemModel
) {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.padding(top = 24.dp)
        ) {
            Text(
                text = user.name.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = user.username.toString(),
                color = Color.White
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = "https://randomuser.me/api/portraits/men/${user.id}.jpg"),
            contentDescription = "Profile Picture",
            Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun AboutSection(
    user: UsersItemModel,
    userAddress: AddressModel,
    userCompany: CompanyModel
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = CardDefaults.shape,
        colors = CardDefaults.cardColors(
            containerColor = DarkBackground
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable{
                    expandedState = !expandedState
                },
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "About",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_expand_arrow),
                        contentDescription = "Drop-Down Arrow",
                        tint = Color.White,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }
            if (expandedState) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                ) {
                    AboutTabSection(icon = R.drawable.ic_mail_2, text = "${user.email}")
                    DividerTab()
                    AboutTabSection(icon = R.drawable.ic_phone, text = "${user.phone}")
                    DividerTab()
                    AboutTabSection(icon = R.drawable.ic_internet, text = "${user.website}")
                    DividerTab()
                    AboutTabSection(icon = R.drawable.ic_address, text = "${userAddress.city}")
                    DividerTab()
                    AboutTabSection(icon = R.drawable.ic_company, text = "${userCompany.name}")
                }
            }
        }
    }
}

@Composable
fun AboutTabSection(
    icon: Int,
    text: String
) {
    Row() {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Primary)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 4.dp, start = 4.dp)
            )
        }
        Text(
            text = text,
            maxLines = 1,
            color = Color.White,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 4.dp, start = 4.dp)
        )
    }

}

@Composable
fun DividerTab(){
    Divider(
        thickness = 0.5.dp,
        color = TextColor,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .padding(start = 36.dp)
    )
}

@Composable
fun ProfileBoxOverlay(
) {
    Surface(
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(BoxColor)
        ) {
            ProfileTabsContent()
        }
    }
}


