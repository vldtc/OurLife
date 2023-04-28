package com.example.ourlife.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ourlife.ui.main.HomeContent

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
                HomeContent()
            }
    }
}

object Graph{
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}