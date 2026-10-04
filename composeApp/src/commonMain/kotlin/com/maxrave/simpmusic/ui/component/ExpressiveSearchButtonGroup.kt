package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
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
import kotlinx.coroutines.launch
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
 * Features connected pill layout, fluid spring overshoot physics, adjacent button reaction,
 * and animated icon-to-checkmark transitions.
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

            // Adjacent reaction: buttons next to the pressed slot compress horizontally with spring
            val isAdjacent = currentlyPressedIndex != -1 &&
                    (index == currentlyPressedIndex - 1 || index == currentlyPressedIndex + 1)

            // Expressive overshoot spring specs (same physics as M3 Expressive Now Playing button group)
            val overshootSpring = spring<Float>(
                dampingRatio = 0.52f, // Low damping gives physical overshoot bounce
                stiffness = Spring.StiffnessMediumLow,
            )

            val dpSpring = spring<androidx.compose.ui.unit.Dp>(
                dampingRatio = 0.55f,
                stiffness = Spring.StiffnessMediumLow,
            )

            val targetScaleX = when {
                isPressed -> 1.07f
                isAdjacent -> 0.94f // Adjacent buttons compress when pressed button expands
                isSelected -> 1.02f
                else -> 1.0f
            }

            val targetScaleY = when {
                isPressed -> 1.05f
                isAdjacent -> 0.96f
                isSelected -> 1.01f
                else -> 1.0f
            }

            val scaleX by animateFloatAsState(
                targetValue = targetScaleX,
                animationSpec = overshootSpring,
                label = "m3ButtonScaleX_${category.name}",
            )

            val scaleY by animateFloatAsState(
                targetValue = targetScaleY,
                animationSpec = overshootSpring,
                label = "m3ButtonScaleY_${category.name}",
            )

            // Dynamic horizontal padding: pressed and selected slots expand, adjacent slots compress
            val targetPaddingHorizontal = when {
                isPressed -> 18.dp
                isSelected -> 16.dp
                isAdjacent -> 10.dp
                else -> 13.dp
            }

            val horizontalPadding by animateDpAsState(
                targetValue = targetPaddingHorizontal,
                animationSpec = dpSpring,
                label = "m3ButtonPadding_${category.name}",
            )

            // Shape morph: connected corners at rest, morphing into full pill (24dp) on press or selection
            val cornerRadius by animateDpAsState(
                targetValue = if (isSelected || isPressed) 24.dp else 8.dp,
                animationSpec = spring(
                    dampingRatio = 0.55f,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                label = "m3ButtonCorner_${category.name}",
            )

            // Default shape based on position in connected group
            val isFirst = index == 0
            val isLast = index == SearchFilterCategories.lastIndex
            val defaultShape: Shape = when {
                isFirst -> RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp, topEnd = 8.dp, bottomEnd = 8.dp)
                isLast -> RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp, topEnd = 20.dp, bottomEnd = 20.dp)
                else -> RoundedCornerShape(8.dp)
            }

            val slotShape = if (isSelected || isPressed) RoundedCornerShape(cornerRadius) else defaultShape

            // Dynamic color transitions
            val containerColor by animateColorAsState(
                targetValue = when {
                    isSelected -> colorScheme.primaryContainer
                    isPressed -> colorScheme.surfaceContainerHighest
                    else -> colorScheme.surfaceContainerHigh
                },
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "m3ButtonContainerColor_${category.name}",
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colorScheme.onPrimaryContainer
                } else {
                    colorScheme.onSurfaceVariant
                },
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "m3ButtonContentColor_${category.name}",
            )

            // Animated icon bounce on selection change
            val iconScale = remember { Animatable(1f) }
            var isInitialComposition by remember { mutableStateOf(true) }

            LaunchedEffect(isSelected) {
                if (isInitialComposition) {
                    isInitialComposition = false
                    return@LaunchedEffect
                }
                if (isSelected) {
                    launch {
                        iconScale.animateTo(1.28f, tween(80, easing = FastOutSlowInEasing))
                        iconScale.animateTo(1.0f, spring(dampingRatio = 0.50f, stiffness = Spring.StiffnessMediumLow))
                    }
                }
            }

            Surface(
                onClick = { onSelectType(category) },
                shape = slotShape,
                color = containerColor,
                contentColor = contentColor,
                interactionSource = interactionSource,
                tonalElevation = if (isSelected) 3.dp else 0.dp,
                shadowElevation = if (isSelected) 1.dp else 0.dp,
                modifier = Modifier
                    .graphicsLayer {
                        this.scaleX = scaleX
                        this.scaleY = scaleY
                    }
                    .height(40.dp),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = horizontalPadding, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // Animated checkmark when selected
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn(spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow)) +
                                scaleIn(spring(dampingRatio = 0.50f, stiffness = Spring.StiffnessMediumLow), initialScale = 0.6f) +
                                expandHorizontally(spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow)),
                        exit = fadeOut(spring(stiffness = Spring.StiffnessMediumLow)) +
                                scaleOut(targetScale = 0.7f) +
                                shrinkHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = SimpIcons.Check,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(17.dp)
                                    .graphicsLayer {
                                        this.scaleX = iconScale.value
                                        this.scaleY = iconScale.value
                                    },
                                tint = contentColor,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }

                    // Category icon when unselected
                    AnimatedVisibility(
                        visible = !isSelected,
                        enter = fadeIn(spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow)) +
                                expandHorizontally(spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow)),
                        exit = fadeOut(spring(stiffness = Spring.StiffnessMediumLow)) +
                                shrinkHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = category.toIcon(),
                                contentDescription = null,
                                modifier = Modifier.size(17.dp),
                                tint = contentColor.copy(alpha = 0.85f),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }

                    Text(
                        text = stringResource(category.toStringRes()),
                        style = typo().labelMedium.copy(
                            fontSize = 13.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                        ),
                        color = contentColor,
                    )
                }
            }
        }
    }
}
