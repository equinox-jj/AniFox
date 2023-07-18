package com.features.anime.data.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.apollographql.apollo3.exception.DefaultApolloException
import com.core.util.Resource
import com.features.anime.domain.repository.AnimeRepository
import com.schema.AnimeQuery
import com.schema.type.MediaSort
import com.schema.type.MediaStatus
import com.schema.type.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AnimeRepositoryImpl(private val apolloClient: ApolloClient) : AnimeRepository {
    override fun getUpcomingAnime(): Flow<Resource<List<AnimeQuery.Medium>>> = flow {
        emit(Resource.Loading)
        val response = apolloClient
            .query(
                AnimeQuery(
                    page = Optional.presentIfNotNull(1),
                    perPage = Optional.presentIfNotNull(30),
                    sort = Optional.presentIfNotNull(listOf(MediaSort.POPULARITY_DESC)),
                    status = Optional.presentIfNotNull(MediaStatus.NOT_YET_RELEASED),
                    type = Optional.presentIfNotNull(MediaType.ANIME),
                    isAdult = Optional.presentIfNotNull(false),
                )
            )
            .execute()
        val data = response.data
        if (data != null) {
            val result = data.Page.media.filterNotNull()

            emit(Resource.Success(result))
        } else {
            when (response.exception) {
                is ApolloHttpException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is ApolloNetworkException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is DefaultApolloException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                else -> {}
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getNowPlayingAnime(): Flow<Resource<List<AnimeQuery.Medium>>> = flow {
        emit(Resource.Loading)
        val response = apolloClient
            .query(
                AnimeQuery(
                    page = Optional.presentIfNotNull(1),
                    perPage = Optional.presentIfNotNull(30),
                    sort = Optional.presentIfNotNull(listOf(MediaSort.POPULARITY_DESC)),
                    status = Optional.presentIfNotNull(MediaStatus.RELEASING),
                    type = Optional.presentIfNotNull(MediaType.ANIME),
                    isAdult = Optional.presentIfNotNull(false)
                )
            )
            .execute()
        val data = response.data
        if (data != null) {
            val result = data.Page.media.filterNotNull()

            emit(Resource.Success(result))
        } else {
            when (response.exception) {
                is ApolloHttpException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is ApolloNetworkException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is DefaultApolloException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                else -> {}
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getPopularAnime(): Flow<Resource<List<AnimeQuery.Medium>>> = flow {
        emit(Resource.Loading)
        val response = apolloClient
            .query(
                AnimeQuery(
                    page = Optional.presentIfNotNull(1),
                    perPage = Optional.presentIfNotNull(30),
                    sort = Optional.presentIfNotNull(listOf(MediaSort.POPULARITY_DESC)),
                    type = Optional.presentIfNotNull(MediaType.ANIME),
                    isAdult = Optional.presentIfNotNull(false),
                )
            )
            .execute()
        val data = response.data
        if (data != null) {
            val result = data.Page.media.filterNotNull()

            emit(Resource.Success(result))
        } else {
            when (response.exception) {
                is ApolloHttpException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is ApolloNetworkException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is DefaultApolloException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                else -> {}
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getTopRatedAnime(): Flow<Resource<List<AnimeQuery.Medium>>> = flow {
        emit(Resource.Loading)
        val response = apolloClient
            .query(
                AnimeQuery(
                    page = Optional.presentIfNotNull(1),
                    perPage = Optional.presentIfNotNull(30),
                    sort = Optional.presentIfNotNull(
                        listOf(
                            MediaSort.SCORE_DESC,
                            MediaSort.FAVOURITES_DESC,
                        )
                    ),
                    type = Optional.presentIfNotNull(MediaType.ANIME),
                    isAdult = Optional.presentIfNotNull(false),
                )
            )
            .execute()
        val data = response.data
        if (data != null) {
            val result = data.Page.media.filterNotNull()

            emit(Resource.Success(result))
        } else {
            when (response.exception) {
                is ApolloHttpException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is ApolloNetworkException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                is DefaultApolloException -> emit(Resource.Error(error = response.exception?.localizedMessage))
                else -> {}
            }
        }
    }.flowOn(Dispatchers.IO)

}