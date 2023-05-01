package com.example.ourlife.domain.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ourlife.ui.main.HomeContent
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RootNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
        ){
            authNavGraph(navController = navController)
            composable(route = Graph.MAIN){
                HomeContent(onSignOut = {
                    Firebase.auth.signOut()
                    navController.popBackStack()
                    navController.navigate(Graph.AUTH)
                })
            }
    }
}

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}