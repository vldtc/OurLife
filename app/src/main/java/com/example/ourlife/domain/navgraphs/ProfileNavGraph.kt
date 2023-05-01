package com.example.ourlife.domain.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ourlife.ui.main.profile.screens.AlbumsContent
import com.example.ourlife.ui.main.profile.screens.PhotosContent
import com.example.ourlife.ui.main.profile.screens.ProfileTabsContent
import com.example.ourlife.ui.main.profile.screens.TodosContent

@Composable
fun ProfileNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.PROFILE,
        startDestination = ProfileScreens.Photos.route
    ) {
        composable(route = ProfileScreens.ProfileNavigator.route) {
            ProfileTabsContent()
        }
        composable(route = ProfileScreens.Photos.route) {
            PhotosContent()
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
    object Photos : ProfileScreens(route = "PHOTOS")
    object Albums : ProfileScreens(route = "ALBUMS")
    object Todos : ProfileScreens(route = "TODOS")
}