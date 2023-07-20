package com.features.anime.presentation.screens.animelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.util.Resource
import com.core.util.Routes
import com.core.util.Routes.DETAIL_SCREEN
import com.core.util.UiEvent
import com.features.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(private val animeRepository: AnimeRepository) : ViewModel() {

    private val _state = MutableStateFlow(AnimeListState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(AnimeListEvent.OnUpcomingAnimeFetched)
        onEvent(AnimeListEvent.OnNowPlayingAnimeFetched)
        onEvent(AnimeListEvent.OnPopularAnimeFetched)
        onEvent(AnimeListEvent.OnTopRatedAnimeFetched)
    }

    fun onEvent(event: AnimeListEvent) {
        when (event) {
            AnimeListEvent.OnNowPlayingAnimeFetched -> getNowPlayingAnime()
            AnimeListEvent.OnPopularAnimeFetched -> getPopularAnime()
            AnimeListEvent.OnTopRatedAnimeFetched -> getTopRatedAnime()
            AnimeListEvent.OnUpcomingAnimeFetched -> getUpcomingAnime()
            is AnimeListEvent.OnCarouselCardClicked -> {
                setUiEvent(UiEvent.OnNavigate(route = event.route))
            }

            is AnimeListEvent.OnHorizontalCardClicked -> {
                setUiEvent(UiEvent.OnNavigate(route = event.route))
            }
        }
    }

    private fun setUiEvent(uiEvent: UiEvent) = viewModelScope.launch {
        _uiEvent.send(uiEvent)
    }

    private fun getUpcomingAnime() {
        viewModelScope.launch {
            animeRepository.getUpcomingAnime().collect { response ->
                when (response) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                upcomingAnimeList = response.data,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = response.error,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getNowPlayingAnime() {
        viewModelScope.launch {
            animeRepository.getNowPlayingAnime().collect { response ->
                when (response) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                nowPlayingAnime = response.data,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = response.error,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPopularAnime() {
        viewModelScope.launch {
            animeRepository.getPopularAnime().collect { response ->
                when (response) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                popularAnime = response.data,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = response.error,
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getTopRatedAnime() {
        viewModelScope.launch {
            animeRepository.getTopRatedAnime().collect { response ->
                when (response) {
                    Resource.Loading -> {
                        _state.update {
                            it.copy(loading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                topRatedAnime = response.data,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                errorMessage = response.error,
                            )
                        }
                    }
                }
            }
        }
    }

}