@file:OptIn(ExperimentalFoundationApi::class)

package com.features.anime.presentation.screens.animelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.core.util.Routes
import com.core.util.Routes.DETAIL_SCREEN
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
            onCarouselClicked = { event(AnimeListEvent.OnCarouselCardClicked(DETAIL_SCREEN)) },
            modifier = Modifier.padding(paddingValues = it),
        )
    }
}

@Composable
fun AnimeListContent(
    state: AnimeListState,
    onCarouselClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        item {
            HorizontalPager(
                pageCount = state.upcomingAnimeList.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ),
                key = { state.upcomingAnimeList[it].id }
            ) { index ->
                val data = state.upcomingAnimeList[index]

                AnimeCarouselCard(
                    data = data,
                    onCarouselClicked = onCarouselClicked,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(18.dp))
        }
        item {
            AnimeSubHeading(
                title = "Airing Today",
                icon = Icons.TwoTone.ArrowForward,
                onSubHeadingClicked = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            )
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
        item {
            AnimeSubHeading(
                title = "Most Popular Anime",
                icon = Icons.TwoTone.ArrowForward,
                onSubHeadingClicked = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            )
        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(
                    items = state.popularAnime,
                    key = { it.id }
                ) { data ->
                    AnimeHorizontalCard(
                        data = data,
                        modifier = Modifier.width(250.dp)
                    )
                }
            }
        }
        item {
            AnimeSubHeading(
                title = "Top Rated Anime",
                icon = Icons.TwoTone.ArrowForward,
                onSubHeadingClicked = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
            )
        }
        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items(
                    items = state.topRatedAnime,
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

@Composable
private fun AnimeSubHeading(
    title: String,
    icon: ImageVector,
    onSubHeadingClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = title)
        Icon(
            imageVector = icon,
            contentDescription = "View All Icon",
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onSubHeadingClicked() },
        )
    }
}

