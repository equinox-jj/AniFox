package com.features.anime.presentation.screens.animelist

sealed interface AnimeListEvent {
    object OnUpcomingAnimeFetched : AnimeListEvent
    object OnNowPlayingAnimeFetched : AnimeListEvent
    object OnPopularAnimeFetched : AnimeListEvent
    object OnTopRatedAnimeFetched : AnimeListEvent
    data class OnCarouselCardClicked(val route: String) : AnimeListEvent
    data class OnHorizontalCardClicked(val route: String) : AnimeListEvent
}