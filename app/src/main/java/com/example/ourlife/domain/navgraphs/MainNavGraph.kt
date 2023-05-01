package com.example.ourlife.domain.navgraphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ourlife.ui.main.FeedContent
import com.example.ourlife.ui.main.HomeContent
import com.example.ourlife.ui.main.profile.ProfileContent
import com.example.ourlife.ui.main.profile.screens.AlbumsContent
import com.example.ourlife.ui.main.profile.screens.PhotosContent
import com.example.ourlife.ui.main.profile.screens.TodosContent

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Feed.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeContent(onSignOut = {
            })
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileContent()
        }
        composable(route = BottomBarScreen.Feed.route) {
            FeedContent()
        }
    }
}

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
        icon = Icons.Default.Person
    )
}

