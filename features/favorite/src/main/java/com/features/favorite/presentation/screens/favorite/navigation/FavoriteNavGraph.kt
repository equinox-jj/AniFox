package com.features.favorite.presentation.screens.favorite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.util.Routes.FAVORITE_SCREEN
import com.features.favorite.presentation.screens.favorite.FavoriteScreen

fun NavGraphBuilder.favoriteNavGraph() {
    composable(route = FAVORITE_SCREEN) {
        FavoriteScreen()
    }
}