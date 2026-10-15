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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.GraphicEq
import com.maxrave.simpmusic.ui.icon.LibraryMusic
import com.maxrave.simpmusic.ui.icon.QueueMusic
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.theme.typo
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.playlists
import simpmusic.composeapp.generated.resources.songs
import simpmusic.composeapp.generated.resources.your_library

data class LibraryTabItem(
    val titleRes: StringResource,
    val icon: ImageVector,
)

val LibraryTabItems = listOf(
    LibraryTabItem(Res.string.your_library, SimpIcons.LibraryMusic),
    LibraryTabItem(Res.string.songs, SimpIcons.GraphicEq),
    LibraryTabItem(Res.string.playlists, SimpIcons.QueueMusic),
)

/**
 * Material 3 Expressive Library Button Group:
 * Uses the exact same button group style as Home and Search screens:
 * Connected pill layout, fluid GPU-accelerated spring overshoot physics,
 * continuous corner morphing, tonal elevations, and in-place icon crossfade transitions.
 */
@Composable
fun ExpressiveLibraryButtonGroup(
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState? = rememberScrollState(),
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
        LibraryTabItems.forEachIndexed { index, tab ->
            val isSelected = index == selectedTab
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
                label = "m3LibBtnScale_$index",
            )

            // Smooth continuous shape morphing:
            // When resting in connected group: ends have 20dp outer caps and 8dp inner corners.
            // When selected or pressed: smoothly morphs into a standalone 20dp pill.
            // When unselected: smoothly animates back to resting corners without any abrupt popping.
            val isFirst = index == 0
            val isLast = index == LibraryTabItems.lastIndex
            val restingStart = if (isFirst) 20.dp else 8.dp
            val restingEnd = if (isLast) 20.dp else 8.dp

            val startCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingStart,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3LibBtnStartCorner_$index",
            )

            val endCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingEnd,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3LibBtnEndCorner_$index",
            )

            val buttonShape = RoundedCornerShape(
                topStart = startCorner,
                bottomStart = startCorner,
                topEnd = endCorner,
                bottomEnd = endCorner,
            )

            // Smooth tonal container and content color transitions
            val containerColor by animateColorAsState(
                targetValue = when {
                    isSelected -> colorScheme.primaryContainer
                    isPressed -> colorScheme.surfaceContainerHighest
                    else -> colorScheme.surfaceContainerHigh
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3LibBtnContainer_$index",
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colorScheme.onPrimaryContainer
                } else {
                    colorScheme.onSurfaceVariant
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3LibBtnContent_$index",
            )

            val tonalElevation by animateDpAsState(
                targetValue = if (isSelected) 3.dp else 0.dp,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3LibBtnTonal_$index",
            )

            val shadowElevation by animateDpAsState(
                targetValue = if (isSelected) 1.dp else 0.dp,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3LibBtnShadow_$index",
            )

            Surface(
                onClick = { onSelectTab(index) },
                shape = buttonShape,
                color = containerColor,
                contentColor = contentColor,
                interactionSource = interactionSource,
                tonalElevation = tonalElevation,
                shadowElevation = shadowElevation,
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
                        label = "m3LibIconTransition_$index",
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
                                imageVector = tab.icon,
                                contentDescription = null,
                                modifier = Modifier.size(17.dp),
                                tint = contentColor.copy(alpha = 0.85f),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = stringResource(tab.titleRes),
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
