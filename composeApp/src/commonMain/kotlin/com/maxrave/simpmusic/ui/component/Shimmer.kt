package com.maxrave.simpmusic.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxrave.simpmusic.extension.shimmer
import com.maxrave.simpmusic.ui.theme.LocalAppColors

@Composable
fun HomeItemShimmer() {
    Column {
        Box(
            Modifier
                .width(150.dp)
                .height(36.dp)
                .padding(vertical = 8.dp)
                .background(
                    color = LocalAppColors.current.shimmerBackground,
                ).clip(RoundedCornerShape(10))
                .shimmer(),
        )
        LazyRow(userScrollEnabled = false) {
            items(10) {
                PlaylistShimmer()
            }
        }
    }
}

@Composable
fun PlaylistShimmer() {
    Column(
        Modifier
            .height(270.dp)
            .padding(10.dp),
    ) {
        Box(
            Modifier
                .size(160.dp)
                .clip(
                    RoundedCornerShape(10),
                ).background(
                    color = LocalAppColors.current.shimmerBackground,
                ).shimmer(),
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            Modifier
                .width(130.dp)
                .height(18.dp)
                .clip(
                    RoundedCornerShape(10),
                ).background(
                    color = LocalAppColors.current.shimmerBackground,
                ).shimmer(),
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            Modifier
                .width(130.dp)
                .height(18.dp)
                .clip(
                    RoundedCornerShape(10),
                ).background(
                    color = LocalAppColors.current.shimmerBackground,
                ).shimmer(),
        )
    }
}

@Composable
fun QuickPicksShimmerItem() {
    Row(
        Modifier
            .height(70.dp)
            .padding(10.dp),
    ) {
        Box(
            Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10))
                .background(LocalAppColors.current.shimmerBackground)
                .shimmer(),
        )
        Column(
            Modifier
                .padding(start = 10.dp)
                .wrapContentHeight(align = Alignment.CenterVertically)
                .align(Alignment.CenterVertically),
        ) {
            Box(
                Modifier
                    .width(300.dp)
                    .height(21.dp)
                    .clip(RoundedCornerShape(10))
                    .background(LocalAppColors.current.shimmerBackground)
                    .shimmer(),
            )
            Spacer(modifier = Modifier.height(3.dp))
            Box(
                Modifier
                    .width(260.dp)
                    .height(21.dp)
                    .clip(RoundedCornerShape(10))
                    .background(LocalAppColors.current.shimmerBackground)
                    .shimmer(),
            )
        }
    }
}

@Composable
fun QuickPicksShimmer() {
    Column {
        Box(
            Modifier
                .width(150.dp)
                .height(36.dp)
                .padding(vertical = 8.dp)
                .background(
                    color = LocalAppColors.current.shimmerBackground,
                ).clip(RoundedCornerShape(10))
                .shimmer(),
        )
        LazyColumn(userScrollEnabled = false) {
            items(4) {
                QuickPicksShimmerItem()
            }
        }
    }
}

@Composable
fun HomeShimmer() {
    Column(
        Modifier.padding(horizontal = 15.dp),
    ) {
        QuickPicksShimmer()
        LazyColumn(userScrollEnabled = false) {
            items(10) {
                HomeItemShimmer()
            }
        }
    }
}

@Composable
fun ShimmerSearchItem(
    index: Int = 0,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val shimmerColor = colorScheme.surfaceContainerHighest.copy(alpha = 0.6f)
    val cardBackground = colorScheme.surfaceContainerLow

    // Natural alternating widths so skeleton list looks organic and dynamic
    val titleWidthPercent = when (index % 4) {
        0 -> 0.72f
        1 -> 0.58f
        2 -> 0.85f
        else -> 0.65f
    }
    val subtitleWidthPercent = when (index % 3) {
        0 -> 0.45f
        1 -> 0.55f
        else -> 0.38f
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(cardBackground)
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Thumbnail shimmer (rounded 12dp or circle if artist-like)
        val isCircle = index % 5 == 3
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(if (isCircle) CircleShape else RoundedCornerShape(12.dp))
                .background(shimmerColor)
                .shimmer()
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Text & Badge skeleton content
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
        ) {
            // Title placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(titleWidthPercent)
                    .height(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(shimmerColor)
                    .shimmer()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth(subtitleWidthPercent)
                    .height(13.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(shimmerColor)
                    .shimmer()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Category badge placeholder pill
            Box(
                modifier = Modifier
                    .width(46.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(shimmerColor.copy(alpha = 0.5f))
                    .shimmer()
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Trailing placeholder action (Play / More)
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(shimmerColor.copy(alpha = 0.35f))
                .shimmer()
        )
    }
}

/**
 * Skeleton shimmer card for the Moods & Genres browse grid on the Search screen.
 * Perfectly mirrors [MoodCategoryCard] layout, ratio, and rotated thumbnail placeholder.
 */
@Composable
fun MoodCategoryCardShimmer(
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val shimmerColor = colorScheme.surfaceContainerHighest.copy(alpha = 0.55f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .clip(RoundedCornerShape(10.dp))
            .background(colorScheme.surfaceContainerLow)
            .shimmer(),
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .align(Alignment.TopStart),
        ) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(14.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(shimmerColor)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(12.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(shimmerColor.copy(alpha = 0.7f))
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 8.dp, y = 12.dp)
                .size(64.dp)
                .rotate(25f)
                .clip(RoundedCornerShape(4.dp))
                .background(shimmerColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShimmerSearchItemPreview() {
    ShimmerSearchItem()
}
