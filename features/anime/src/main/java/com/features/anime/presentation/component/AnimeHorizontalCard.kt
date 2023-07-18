@file:OptIn(ExperimentalMaterial3Api::class)

package com.features.anime.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.schema.AnimeQuery

@Composable
fun AnimeHorizontalCard(
    data: AnimeQuery.Medium,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Card(
        onClick = { },
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp,
                    )
                ),
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(data.coverImage.extraLarge)
                    .crossfade(1000)
                    .build(),
                contentDescription = "Anime Cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = data.format?.name.orEmpty(),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.8f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .align(Alignment.TopCenter),
                fontWeight = FontWeight.Black,
                maxLines = 1,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = data.title.romaji ?: data.title.english ?: "Untitled",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.padding(horizontal = 24.dp), thickness = 4.dp)
        Spacer(modifier = Modifier.height(8.dp))
        if (data.duration != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Info,
                    contentDescription = "Duration Icon",
                    modifier = Modifier.size(18.dp),
                )
                Text(text = "${data.duration} Min")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

        }
        Text(
            text = data.status?.name.orEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
        )
    }
}