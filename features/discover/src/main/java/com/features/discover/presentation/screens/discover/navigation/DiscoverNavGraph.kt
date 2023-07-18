package com.features.discover.presentation.screens.discover.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.util.Routes.DISCOVER_SCREEN
import com.features.discover.presentation.screens.discover.DiscoverScreen

fun NavGraphBuilder.discoverNavGraph() {
    composable(route = DISCOVER_SCREEN) {
        DiscoverScreen()
    }
}