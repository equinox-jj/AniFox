@file:OptIn(ExperimentalFoundationApi::class)

package com.features.anime.presentation.screens.animelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.features.anime.presentation.component.AnimeCarouselCard
import com.features.anime.presentation.component.AnimeHorizontalCard

@Composable
fun AnimeListScreen(
    state: AnimeListState,
    event: (AnimeListEvent) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        AnimeListContent(
            state = state,
            modifier = Modifier.padding(paddingValues = it)
        )
    }
}

@Composable
fun AnimeListContent(
    state: AnimeListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            HorizontalPager(
                pageCount = state.upcomingAnimeList.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 50.dp,
                            bottomEnd = 50.dp
                        )
                    ),
                key = { state.upcomingAnimeList[it].id }
            ) { index ->
                val data = state.upcomingAnimeList[index]

                AnimeCarouselCard(
                    data = data,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(18.dp))
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Now Playing")
                Icon(imageVector = Icons.TwoTone.ArrowForward, contentDescription = "View All Icon")
            }
        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(
                    items = state.nowPlayingAnime,
                    key = { it.id }
                ) { data ->
                    AnimeHorizontalCard(
                        data = data,
                        modifier = Modifier.width(250.dp)
                    )
                }
            }
        }
    }
}

