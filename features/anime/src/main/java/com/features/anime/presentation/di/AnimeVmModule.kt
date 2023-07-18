package com.features.anime.presentation.di

import com.features.anime.presentation.screens.animelist.AnimeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val animeVmModule = module {
    viewModelOf(::AnimeListViewModel)
}