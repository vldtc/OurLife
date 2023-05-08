package com.example.ourlife.domain.navgraphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ourlife.ui.main.feed.FeedContent
import com.example.ourlife.ui.main.HomeContent
import com.example.ourlife.ui.main.details.PostDetailsContent
import com.example.ourlife.ui.main.profile.ProfileContent

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
            FeedContent(
                onPostClick = {
                    navController.navigate(Graph.POST)
                }
            )
        }
        postDetailsGraph(navController = navController)
    }
}

fun NavGraphBuilder.postDetailsGraph(navController: NavHostController){
    navigation(
        route = Graph.POST,
        startDestination = PostDetailsScreen.PostScreen.route
    ){
        composable(route = PostDetailsScreen.PostScreen.route){
            PostDetailsContent()
        }
    }
}

