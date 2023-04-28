package com.example.ourlife.navgraphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ourlife.ui.main.HomeContent
import com.example.ourlife.ui.main.ProfileContent

@Composable
fun MainNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeContent()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileContent()
        }
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home_route",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Profile: BottomBarScreen(
        route = "profile_route",
        title = "Profile",
        icon = Icons.Default.Person
    )
}