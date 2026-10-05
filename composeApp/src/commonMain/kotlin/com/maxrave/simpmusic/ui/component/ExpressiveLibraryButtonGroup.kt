package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.common.LibraryChipType
import com.maxrave.simpmusic.ui.icon.AutoGraph
import com.maxrave.simpmusic.ui.icon.Download
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.GraphicEq
import com.maxrave.simpmusic.ui.icon.Insights
import com.maxrave.simpmusic.ui.icon.LibraryMusic
import com.maxrave.simpmusic.ui.icon.QueueMusic
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.TrendingUp
import com.maxrave.simpmusic.ui.theme.typo
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.downloaded_playlists
import simpmusic.composeapp.generated.resources.favorite_playlists
import simpmusic.composeapp.generated.resources.favorite_podcasts
import simpmusic.composeapp.generated.resources.simpmusic_charts
import simpmusic.composeapp.generated.resources.wrapped
import simpmusic.composeapp.generated.resources.your_library
import simpmusic.composeapp.generated.resources.your_playlists
import simpmusic.composeapp.generated.resources.your_youtube_playlists

data class LibraryCategoryMeta(
    val type: LibraryChipType,
    val titleRes: StringResource,
    val icon: ImageVector,
)

val AllLibraryCategories: List<LibraryCategoryMeta> = listOf(
    LibraryCategoryMeta(LibraryChipType.YOUR_LIBRARY, Res.string.your_library, SimpIcons.LibraryMusic),
    LibraryCategoryMeta(LibraryChipType.LOCAL_PLAYLIST, Res.string.your_playlists, SimpIcons.QueueMusic),
    LibraryCategoryMeta(LibraryChipType.FAVORITE_PLAYLIST, Res.string.favorite_playlists, SimpIcons.Favorite),
    LibraryCategoryMeta(LibraryChipType.DOWNLOADED_PLAYLIST, Res.string.downloaded_playlists, SimpIcons.Download),
    LibraryCategoryMeta(LibraryChipType.CHART, Res.string.simpmusic_charts, SimpIcons.TrendingUp),
    LibraryCategoryMeta(LibraryChipType.WRAPPED, Res.string.wrapped, SimpIcons.AutoGraph),
    LibraryCategoryMeta(LibraryChipType.FAVORITE_PODCAST, Res.string.favorite_podcasts, SimpIcons.GraphicEq),
    LibraryCategoryMeta(LibraryChipType.YOUTUBE_MUSIC_PLAYLIST, Res.string.your_youtube_playlists, SimpIcons.Insights),
)

/**
 * Material 3 Expressive Library Button Group:
 * Replaces old static chips with organic connected morphing pills, fluid spring overshoot physics,
 * Google Sans Flex typography, and emotional animated micro-interactions.
 */
@Composable
fun ExpressiveLibraryButtonGroup(
    currentFilter: LibraryChipType,
    onFilterSelected: (LibraryChipType) -> Unit,
    loggedIn: Boolean,
    localTrackingEnabled: Boolean,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    var currentlyPressedIndex by remember { mutableIntStateOf(-1) }

    val visibleCategories = remember(loggedIn, localTrackingEnabled) {
        AllLibraryCategories.filter { item ->
            when (item.type) {
                LibraryChipType.YOUTUBE_MIX_FOR_YOU -> false
                LibraryChipType.YOUTUBE_MUSIC_PLAYLIST -> loggedIn
                LibraryChipType.WRAPPED -> localTrackingEnabled
                else -> true
            }
        }
    }

    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        visibleCategories.forEachIndexed { index, category ->
            val isSelected = category.type == currentFilter
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            LaunchedEffect(isPressed) {
                currentlyPressedIndex = if (isPressed) index else -1
            }

            val isAdjacent = currentlyPressedIndex != -1 &&
                (index == currentlyPressedIndex - 1 || index == currentlyPressedIndex + 1)

            // Tactile overshoot spring physics
            val overshootSpring = spring<Float>(
                dampingRatio = 0.62f,
                stiffness = Spring.StiffnessMediumLow,
            )

            val targetScale = when {
                isPressed -> 0.94f
                isSelected -> 1.02f
                isAdjacent -> 0.98f
                else -> 1.0f
            }

            val scale by animateFloatAsState(
                targetValue = targetScale,
                animationSpec = overshootSpring,
                label = "pillScale_${category.type.name}",
            )

            // Dynamic morphing pill corner radius
            val cornerRadius by animateDpAsState(
                targetValue = when {
                    isSelected -> 24.dp
                    isPressed -> 18.dp
                    else -> 12.dp
                },
                animationSpec = spring(
                    dampingRatio = 0.72f,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                label = "pillCorner_${category.type.name}",
            )

            val containerColor by animateColorAsState(
                targetValue = when {
                    isSelected -> colorScheme.primaryContainer
                    isPressed -> colorScheme.surfaceContainerHighest
                    else -> colorScheme.surfaceContainer
                },
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "pillBg_${category.type.name}",
            )

            val contentColor by animateColorAsState(
                targetValue = when {
                    isSelected -> colorScheme.onPrimaryContainer
                    else -> colorScheme.onSurfaceVariant
                },
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "pillFg_${category.type.name}",
            )

            Surface(
                onClick = { onFilterSelected(category.type) },
                shape = RoundedCornerShape(cornerRadius),
                color = containerColor,
                contentColor = contentColor,
                interactionSource = interactionSource,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    },
                tonalElevation = if (isSelected) 3.dp else 0.dp,
            ) {
                Row(
                    modifier = Modifier
                        .height(38.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.size(18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isSelected && category.type == LibraryChipType.FAVORITE_PLAYLIST) {
                            ExpressiveHeartbeatIcon(
                                size = 18.dp,
                                tint = contentColor,
                            )
                        } else if (isSelected && category.type == LibraryChipType.YOUR_LIBRARY) {
                            ExpressiveLiveEqualizerBars(
                                barColor = contentColor,
                                height = 14.dp,
                            )
                        } else if (isSelected && category.type == LibraryChipType.DOWNLOADED_PLAYLIST) {
                            ExpressiveFloatingDownloadIcon(
                                size = 18.dp,
                                tint = contentColor,
                            )
                        } else {
                            AnimatedContent(
                                targetState = category.icon,
                                transitionSpec = {
                                    (fadeIn() + scaleIn(initialScale = 0.8f)).togetherWith(
                                        fadeOut() + scaleOut(targetScale = 0.8f),
                                    )
                                },
                                label = "iconTransition_${category.type.name}",
                            ) { iconVector ->
                                Icon(
                                    imageVector = iconVector,
                                    contentDescription = null,
                                    modifier = Modifier.size(17.dp),
                                    tint = contentColor,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(7.dp))

                    Text(
                        text = stringResource(category.titleRes),
                        style = typo().labelLarge.copy(
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            letterSpacing = 0.15.sp,
                        ),
                        color = contentColor,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}
