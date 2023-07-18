package com.anifox

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.anifox.navigation.BottomNavRoutes
import com.anifox.navigation.SetupNavigation

@Composable
fun MainScreen(navController: NavHostController, startDestination: String) {
    Scaffold(
        bottomBar = { MyBottomBar(navController = navController) },
    ) {
        SetupNavigation(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues = it)
        )
    }
}

@Composable
fun MyBottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavRoutes.ANIME,
        BottomNavRoutes.DISCOVER,
        BottomNavRoutes.FAVORITE,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomNav = screens.any { it.route == currentDestination?.route }

    if (showBottomNav) {
        BottomAppBar() {
            BottomNavItem(
                navController = navController,
                navBackStackEntry = navBackStackEntry,
                screens = screens,
            )
        }
    }
}

@Composable
fun RowScope.BottomNavItem(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    screens: List<BottomNavRoutes>,
) {
    screens.forEach { screen ->
        val selected =
            navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true

        NavigationBarItem(
            selected = selected,
            onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    imageVector = if (selected) screen.selectedIcon else screen.unselectedIcon,
                    contentDescription = "Bottom Nav Icon",
                )
            },
            modifier = Modifier,
            label = {
                Text(text = screen.title)
            },
            alwaysShowLabel = selected
        )
    }
}