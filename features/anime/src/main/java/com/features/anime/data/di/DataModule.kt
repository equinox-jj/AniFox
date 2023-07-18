package com.features.anime.data.di

import com.features.anime.data.repository.AnimeRepositoryImpl
import com.features.anime.domain.repository.AnimeRepository
import org.koin.dsl.module

val animeDataModule = module {
    single<AnimeRepository> { AnimeRepositoryImpl(get()) }
}