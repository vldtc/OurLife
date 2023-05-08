package com.example.ourlife.domain.navgraphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

//Nav Graphs
object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
    const val PROFILE = "profile_graph"
    const val POST = "post_graph"
}

//Auth Nav Screens
sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
}

//Main Nav Graph - Bottom Navigation
sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home_route",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile : BottomBarScreen(
        route = "profile_route",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Feed : BottomBarScreen(
        route = "feed_route",
        title = "Feed",
        icon = Icons.Default.List
    )
}

//Profile Screen Nav Graph
sealed class ProfileScreens(val route: String) {
    object ProfileNavigator: ProfileScreens(route = "NAVIGATOR")
    object Posts : ProfileScreens(route = "POSTS")
    object Albums : ProfileScreens(route = "ALBUMS")
    object Todos : ProfileScreens(route = "TODOS")
}

//Post Details Graph
sealed class PostDetailsScreen(val route: String){
    object PostScreen : PostDetailsScreen(route = "POST")
}
