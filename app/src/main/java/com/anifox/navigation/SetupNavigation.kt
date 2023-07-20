package com.anifox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.features.anime.presentation.screens.animedetail.navigation.animeDetailNavGraph
import com.features.anime.presentation.screens.animelist.navigation.animeListNavGraph
import com.features.discover.presentation.screens.discover.navigation.discoverNavGraph
import com.features.favorite.presentation.screens.favorite.navigation.favoriteNavGraph

@Composable
fun SetupNavigation(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        animeListNavGraph(
            navigateToDetail = {
                navController.navigate(it)
            }
        )
        animeDetailNavGraph()
        discoverNavGraph()
        favoriteNavGraph()
    }
}