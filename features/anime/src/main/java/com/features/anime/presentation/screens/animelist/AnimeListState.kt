package com.features.anime.presentation.screens.animelist

import androidx.compose.runtime.Immutable
import com.schema.AnimeQuery

@Immutable
data class AnimeListState(
    val loading: Boolean = false,
    val upcomingAnimeList: List<AnimeQuery.Medium> = emptyList(),
    val nowPlayingAnime: List<AnimeQuery.Medium> = emptyList(),
    val popularAnime: List<AnimeQuery.Medium> = emptyList(),
    val topRatedAnime: List<AnimeQuery.Medium> = emptyList(),
    val errorMessage: String? = null,
)
