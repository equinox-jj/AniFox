package com.features.anime.presentation.screens.animedetail.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.util.Routes.DETAIL_SCREEN
import com.features.anime.presentation.screens.animedetail.AnimeDetailScreen

fun NavGraphBuilder.animeDetailNavGraph() {
    composable(route = DETAIL_SCREEN) {
        AnimeDetailScreen()
    }
}