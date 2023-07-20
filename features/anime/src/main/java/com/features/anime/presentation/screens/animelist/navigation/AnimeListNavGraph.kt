package com.features.anime.presentation.screens.animelist.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core.util.Routes
import com.core.util.Routes.ANIME_SCREEN
import com.core.util.UiEvent
import com.features.anime.presentation.screens.animelist.AnimeListScreen
import com.features.anime.presentation.screens.animelist.AnimeListViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.animeListNavGraph(
    navigateToDetail: (String) -> Unit,
) {
    composable(route = ANIME_SCREEN) {
        val viewModel = koinViewModel<AnimeListViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(key1 = Unit) {
            viewModel.uiEvent.collect { uiEvent ->
                when (uiEvent) {
                    is UiEvent.OnNavigate -> {
                        navigateToDetail(uiEvent.route)
                    }

                    else -> {}
                }
            }
        }

        AnimeListScreen(
            state = state,
            event = viewModel::onEvent,
        )
    }
}