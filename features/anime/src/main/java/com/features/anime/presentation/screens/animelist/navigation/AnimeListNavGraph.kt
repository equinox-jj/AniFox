package com.features.anime.presentation.screens.animelist.navigation

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.util.Routes
import com.core.util.Routes.ANIME_SCREEN
import com.features.anime.presentation.screens.animelist.AnimeListScreen
import com.features.anime.presentation.screens.animelist.AnimeListViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.animeListNavGraph() {
    composable(route = ANIME_SCREEN) {
        val viewModel = koinViewModel<AnimeListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        AnimeListScreen(
            state = state,
            event = viewModel::onEvent,
        )
    }
}