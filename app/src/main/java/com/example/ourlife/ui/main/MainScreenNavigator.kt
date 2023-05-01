package com.example.ourlife.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ourlife.domain.navgraphs.BottomBarScreen
import com.example.ourlife.domain.navgraphs.MainNavGraph
import com.example.ourlife.ui.theme.Primary
import com.example.ourlife.ui.theme.Secondary

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeContent(
    navController: NavHostController = rememberNavController(),
    onSignOut: () -> Unit
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
        topBar = { Toolbar(onSignOut = onSignOut) }
    ) {
        MainNavGraph(navController = navController)
    }
}

//TOP BAR NAVIGATION
@Composable
fun Toolbar(
    onSignOut: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = com.example.ourlife.R.drawable.ic_logo),
                contentDescription = "logo",
                Modifier
                    .padding(start = 16.dp)
                    .size(32.dp)
            )
            Text(
                text = "OurLife",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Primary,
                fontWeight = FontWeight.Bold
            )
        }
        Box(modifier = Modifier
            .padding(end = 16.dp)
            .clickable(onClick = {
                expanded = true
            })
        ) {
            Icon(
                painter = painterResource(id = com.example.ourlife.R.drawable.ic_more_vert),
                contentDescription = "More Vert",
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
            androidx.compose.material3.DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(120.dp)
                    .background(Color.White)
            ) {
                androidx.compose.material3.DropdownMenuItem(
                    onClick = {
                        expanded = false
                        //Firebase.auth.signOut()
                        onSignOut()
                    },
                    text = {
                        Text(
                            text = "Sign Out"
                        )
                    },
                    leadingIcon = {
                        Icon(painterResource(id = com.example.ourlife.R.drawable.ic_logout), contentDescription = "logout")
                    }
                )
            }
        }
    }
}

// ----- BOTTOM BAR NAVIGATION ------- //

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Feed,
        BottomBarScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Primary
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController)

            }

        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title, color = Color.White)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = Color.White
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = Secondary,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

