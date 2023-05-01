package com.example.ourlife.ui.main.profile.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ourlife.domain.navgraphs.ProfileNavGraph
import com.example.ourlife.domain.navgraphs.ProfileScreens
import androidx.compose.ui.Modifier
import com.example.ourlife.ui.theme.Primary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileTabsContent(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = { ToolbarProfile(navController = navController) }
    ) {
        ProfileNavGraph(navController = navController)
    }
}

//TOP BAR NAVIGATION
@Composable
fun ToolbarProfile(
    navController: NavHostController
) {
    val screens = listOf(
        ProfileScreens.Photos,
        ProfileScreens.Albums,
        ProfileScreens.Todos
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val toolbarDestination = screens.any{ it.route == currentDestination?.route}
    if(toolbarDestination) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            screens.forEach{ screen ->
                AddItem(
                    screen = screen,
                    navController = navController
                )

            }
        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: ProfileScreens,
    navController: NavHostController
) {
    Button(
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 2.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Primary
        )
    ){
        Text(
            text = screen.route,
            color = Color.White
        )
    }
}

