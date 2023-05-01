package com.example.ourlife.domain.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ourlife.ui.main.profile.screens.AlbumsContent
import com.example.ourlife.ui.main.profile.screens.PostsContent
import com.example.ourlife.ui.main.profile.screens.ProfileTabsContent
import com.example.ourlife.ui.main.profile.screens.TodosContent

@Composable
fun ProfileNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.PROFILE,
        startDestination = ProfileScreens.Posts.route
    ) {
        composable(route = ProfileScreens.ProfileNavigator.route) {
            ProfileTabsContent()
        }
        composable(route = ProfileScreens.Posts.route) {
            PostsContent()
        }
        composable(route = ProfileScreens.Albums.route) {
            AlbumsContent()
        }
        composable(route = ProfileScreens.Todos.route) {
            TodosContent()
        }
    }
}

sealed class ProfileScreens(val route: String) {
    object ProfileNavigator: ProfileScreens(route = "NAVIGATOR")
    object Posts : ProfileScreens(route = "POSTS")
    object Albums : ProfileScreens(route = "ALBUMS")
    object Todos : ProfileScreens(route = "TODOS")
}