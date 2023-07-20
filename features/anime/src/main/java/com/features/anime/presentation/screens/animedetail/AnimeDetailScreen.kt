package com.features.anime.presentation.screens.animedetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AnimeDetailScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        AnimeDetailContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it),
        )
    }
}

@Composable
fun AnimeDetailContent(
    modifier: Modifier = Modifier,
) {

}