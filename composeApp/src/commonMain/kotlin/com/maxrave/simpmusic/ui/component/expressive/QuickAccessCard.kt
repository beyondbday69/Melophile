package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.simpmusic.ui.icon.Downloading
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.PeopleAlt
import com.maxrave.simpmusic.ui.icon.SimpIcons

/**
 * Quick Access Grid (Material 3 Expressive).
 *
 * 2 columns with weight 1.12 : 1, 2 rows of 76dp with gap 8dp (total height 160dp).
 * - Favorites: Spans 2 rows, radius 32dp, primaryContainer/onPrimaryContainer,
 *   icon container 72dp (9-lobe cookie) top-left, text bottom.
 * - Followed: Row 1, radius 24dp, secondaryContainer, icon container 44dp (6-lobe flower) + text.
 * - Downloaded: Row 2, radius 24dp, tertiaryContainer, icon container 44dp (8-lobe) + text.
 * - On press: icon container rotates 40 degrees (spring), card radius morphs to 16dp with scale 0.97.
 */
@Composable
fun QuickAccessGrid(
    modifier: Modifier = Modifier,
    favoriteCount: String = "24 songs",
    followedCount: String = "9 artists",
    downloadedCount: String = "12 songs",
    onFavoritesClick: () -> Unit,
    onFollowedClick: () -> Unit,
    onDownloadedClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // Left column (1.12fr): Favorites spanning both rows
        FavoritesCard(
            modifier = Modifier
                .weight(1.12f)
                .fillMaxHeight(),
            count = favoriteCount,
            onClick = onFavoritesClick,
        )

        // Right column (1fr): Followed and Downloaded stacked
        Column(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            FollowedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                count = followedCount,
                onClick = onFollowedClick,
            )
            DownloadedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                count = downloadedCount,
                onClick = onDownloadedClick,
            )
        }
    }
}

@Composable
private fun FavoritesCard(
    modifier: Modifier = Modifier,
    count: String,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedRadius by animateDpAsState(
        targetValue = if (isPressed) 16.dp else 32.dp,
        animationSpec = expressiveSpring(),
        label = "favRadius",
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = expressiveSpring(),
        label = "favScale",
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (isPressed) 40f else 0f,
        animationSpec = expressiveSpring(),
        label = "favRotation",
    )

    val shape = remember(animatedRadius) { RoundedCornerShape(animatedRadius) }

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .clip(shape)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .semantics { role = Role.Button }
            .pressFeedback(
                minDurationMs = 130L,
                onPressedChange = { isPressed = it },
                onClick = onClick,
            )
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            ShapeIconContainer(
                icon = SimpIcons.Favorite,
                shape = LobedShape(9, 0.09f),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                size = 72.dp,
                iconSize = 34.dp,
                rotation = animatedRotation,
                contentDescription = "Favorites",
            )
            Column {
                Text(
                    text = "Favorites",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = count,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun FollowedCard(
    modifier: Modifier = Modifier,
    count: String,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedRadius by animateDpAsState(
        targetValue = if (isPressed) 16.dp else 24.dp,
        animationSpec = expressiveSpring(),
        label = "folRadius",
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = expressiveSpring(),
        label = "folScale",
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (isPressed) 40f else 0f,
        animationSpec = expressiveSpring(),
        label = "folRotation",
    )

    val shape = remember(animatedRadius) { RoundedCornerShape(animatedRadius) }

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .clip(shape)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .semantics { role = Role.Button }
            .pressFeedback(
                minDurationMs = 130L,
                onPressedChange = { isPressed = it },
                onClick = onClick,
            )
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ShapeIconContainer(
                icon = SimpIcons.PeopleAlt,
                shape = LobedShape(6, 0.14f),
                containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondaryContainer,
                size = 44.dp,
                iconSize = 26.dp,
                rotation = animatedRotation,
                contentDescription = "Followed",
            )
            Column {
                Text(
                    text = "Followed",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = count,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun DownloadedCard(
    modifier: Modifier = Modifier,
    count: String,
    onClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedRadius by animateDpAsState(
        targetValue = if (isPressed) 16.dp else 24.dp,
        animationSpec = expressiveSpring(),
        label = "dlRadius",
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1.0f,
        animationSpec = expressiveSpring(),
        label = "dlScale",
    )
    val animatedRotation by animateFloatAsState(
        targetValue = if (isPressed) 40f else 0f,
        animationSpec = expressiveSpring(),
        label = "dlRotation",
    )

    val shape = remember(animatedRadius) { RoundedCornerShape(animatedRadius) }

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = animatedScale
                scaleY = animatedScale
            }
            .clip(shape)
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .semantics { role = Role.Button }
            .pressFeedback(
                minDurationMs = 130L,
                onPressedChange = { isPressed = it },
                onClick = onClick,
            )
            .padding(horizontal = 14.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ShapeIconContainer(
                icon = SimpIcons.Downloading,
                shape = LobedShape(8, 0.12f),
                containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                contentColor = MaterialTheme.colorScheme.tertiaryContainer,
                size = 44.dp,
                iconSize = 26.dp,
                rotation = animatedRotation,
                contentDescription = "Downloaded",
            )
            Column {
                Text(
                    text = "Downloaded",
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = count,
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuickAccessGridPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            QuickAccessGrid(
                favoriteCount = "24 songs",
                followedCount = "9 artists",
                downloadedCount = "12 songs",
                onFavoritesClick = {},
                onFollowedClick = {},
                onDownloadedClick = {},
            )
        }
    }
}
