package com.example.ourlife.domain.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.ourlife.ui.auth.LoginContent
import com.example.ourlife.ui.auth.RegisterContent

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
) {
    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginContent(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(Graph.MAIN)
                },
            ) {
                navController.navigate(AuthScreen.Register.route)
            }
        }
        composable(route = AuthScreen.Register.route){
            RegisterContent(
                onSuccessRegister = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object Register : AuthScreen(route = "REGISTER")
}