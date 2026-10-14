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
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.simpmusic.ui.icon.Album
import com.maxrave.simpmusic.ui.icon.AutoGraph
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.GraphicEq
import com.maxrave.simpmusic.ui.icon.Movie
import com.maxrave.simpmusic.ui.icon.PeopleAlt
import com.maxrave.simpmusic.ui.icon.QueueMusic
import com.maxrave.simpmusic.ui.icon.RssFeed
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.viewModel.SearchType
import com.maxrave.simpmusic.viewModel.toStringRes
import org.jetbrains.compose.resources.stringResource

/**
 * Filter categories for search results in Material 3 Expressive Button Group.
 * Featured Playlists is excluded as requested.
 */
val SearchFilterCategories: List<SearchType> = listOf(
    SearchType.ALL,
    SearchType.SONGS,
    SearchType.VIDEOS,
    SearchType.ARTISTS,
    SearchType.ALBUMS,
    SearchType.PLAYLISTS,
    SearchType.PODCASTS,
)

/**
 * Maps each [SearchType] to its expressive icon.
 */
private fun SearchType.toIcon(): ImageVector = when (this) {
    SearchType.ALL -> SimpIcons.AutoGraph
    SearchType.SONGS -> SimpIcons.GraphicEq
    SearchType.VIDEOS -> SimpIcons.Movie
    SearchType.ARTISTS -> SimpIcons.PeopleAlt
    SearchType.ALBUMS -> SimpIcons.Album
    SearchType.PLAYLISTS -> SimpIcons.QueueMusic
    SearchType.PODCASTS -> SimpIcons.RssFeed
    SearchType.FEATURED_PLAYLISTS -> SimpIcons.QueueMusic
}

/**
 * Material 3 Expressive Search Button Group:
 * Features connected pill layout, fluid GPU-accelerated spring overshoot physics,
 * continuous corner morphing, and stable in-place icon crossfading.
 */
@Composable
fun ExpressiveSearchButtonGroup(
    selectedType: SearchType,
    onSelectType: (SearchType) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = null,
) {
    val colorScheme = MaterialTheme.colorScheme
    var currentlyPressedIndex by remember { mutableIntStateOf(-1) }

    val scrollModifier = if (scrollState != null) {
        Modifier.horizontalScroll(scrollState)
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .then(scrollModifier)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchFilterCategories.forEachIndexed { index, category ->
            val isSelected = category == selectedType
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            LaunchedEffect(isPressed) {
                currentlyPressedIndex = if (isPressed) index else -1
            }

            val isAdjacent = currentlyPressedIndex != -1 &&
                    (index == currentlyPressedIndex - 1 || index == currentlyPressedIndex + 1)

            // Tactile overshoot spring physics (RenderThread / graphicsLayer only - zero relayout jitter)
            val overshootSpring = spring<Float>(
                dampingRatio = 0.62f, // Gentle, satisfying physical overshoot without endless oscillation
                stiffness = Spring.StiffnessMediumLow,
            )

            val targetScale = when {
                isPressed -> 0.94f // Tactile compression on touch
                isAdjacent -> 0.98f // Subtle adjacent reaction
                isSelected -> 1.02f // Slight resting prominence for active filter
                else -> 1.0f
            }

            val scale by animateFloatAsState(
                targetValue = targetScale,
                animationSpec = overshootSpring,
                label = "m3ButtonScale_${category.name}",
            )

            // Smooth continuous shape morphing:
            // When resting in connected group: ends have 20dp outer caps and 8dp inner corners.
            // When selected or pressed: smoothly morphs into a standalone 20dp pill.
            // When unselected: smoothly animates back to resting corners without any abrupt popping.
            val isFirst = index == 0
            val isLast = index == SearchFilterCategories.lastIndex
            val restingStart = if (isFirst) 20.dp else 8.dp
            val restingEnd = if (isLast) 20.dp else 8.dp

            val startCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingStart,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3ButtonStartCorner_${category.name}",
            )

            val endCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingEnd,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3ButtonEndCorner_${category.name}",
            )

            val buttonShape = RoundedCornerShape(
                topStart = startCorner,
                bottomStart = startCorner,
                topEnd = endCorner,
                bottomEnd = endCorner,
            )

            // Smooth tonal container, border and content color transitions (transparent background)
            val containerColor by animateColorAsState(
                targetValue = when {
                    isSelected -> colorScheme.primaryContainer
                    isPressed -> colorScheme.surfaceContainerHighest.copy(alpha = 0.25f)
                    else -> Color.Transparent
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3ButtonContainerColor_${category.name}",
            )

            val borderColor by animateColorAsState(
                targetValue = when {
                    isSelected -> Color.Transparent
                    isPressed -> colorScheme.primary.copy(alpha = 0.5f)
                    else -> colorScheme.outlineVariant.copy(alpha = 0.5f)
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3ButtonBorderColor_${category.name}",
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colorScheme.onPrimaryContainer
                } else {
                    colorScheme.onSurfaceVariant
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3ButtonContentColor_${category.name}",
            )

            Surface(
                onClick = { onSelectType(category) },
                shape = buttonShape,
                color = containerColor,
                contentColor = contentColor,
                border = BorderStroke(1.dp, borderColor),
                interactionSource = interactionSource,
                tonalElevation = 0.dp,
                shadowElevation = 0.dp,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .height(40.dp),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // Stable in-place icon transition: preserves fixed width (18dp)
                    // so text never shifts or vibrates during selection transitions
                    Box(
                        modifier = Modifier.size(18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        AnimatedContent(
                            targetState = isSelected,
                            transitionSpec = {
                                (fadeIn(spring(dampingRatio = 0.65f, stiffness = Spring.StiffnessMedium)) +
                                        scaleIn(spring(dampingRatio = 0.62f, stiffness = Spring.StiffnessMediumLow), initialScale = 0.5f))
                                    .togetherWith(
                                        fadeOut(spring(stiffness = Spring.StiffnessMedium)) +
                                                scaleOut(spring(stiffness = Spring.StiffnessMedium), targetScale = 0.5f)
                                    )
                            },
                            label = "m3IconTransition_${category.name}",
                        ) { selected ->
                            if (selected) {
                                Icon(
                                    imageVector = SimpIcons.Check,
                                    contentDescription = null,
                                    modifier = Modifier.size(17.dp),
                                    tint = contentColor,
                                )
                            } else {
                                Icon(
                                    imageVector = category.toIcon(),
                                    contentDescription = null,
                                    modifier = Modifier.size(17.dp),
                                    tint = contentColor.copy(alpha = 0.85f),
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = stringResource(category.toStringRes()),
                        style = typo().labelMedium.copy(
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                        ),
                        color = contentColor,
                    )
                }
            }
        }
    }
}
