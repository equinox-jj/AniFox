package com.features.anime.domain.repository

import com.core.util.Resource
import com.schema.AnimeQuery
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getUpcomingAnime(): Flow<Resource<List<AnimeQuery.Medium>>>
    fun getNowPlayingAnime(): Flow<Resource<List<AnimeQuery.Medium>>>
    fun getPopularAnime(): Flow<Resource<List<AnimeQuery.Medium>>>
    fun getTopRatedAnime(): Flow<Resource<List<AnimeQuery.Medium>>>
}