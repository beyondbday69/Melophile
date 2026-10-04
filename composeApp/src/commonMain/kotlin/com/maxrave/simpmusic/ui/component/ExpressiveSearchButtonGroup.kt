package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchFilterCategories.forEachIndexed { index, category ->
            val isSelected = category == selectedType
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            LaunchedEffect(isPressed) {
                currentlyPressedIndex = if (isPressed) index else -1
            }

            // Adjacent reaction: buttons next to the pressed slot subtly react
            val isAdjacent = currentlyPressedIndex != -1 &&
                    (index == currentlyPressedIndex - 1 || index == currentlyPressedIndex + 1)

            // Spring physics for overshoot scale
            val targetScale = when {
                isPressed -> 0.94f
                isAdjacent -> 0.97f
                isSelected -> 1.0f
                else -> 1.0f
            }

            val scale by animateFloatAsState(
                targetValue = targetScale,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                ),
                label = "m3ButtonScale_${category.name}",
            )

            // Shape morph: selected button morphs into full pill
            val cornerRadius by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 14.dp,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3ButtonCorner_${category.name}",
            )

            // Dynamic color transitions
            val containerColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colorScheme.primaryContainer
                } else {
                    colorScheme.surfaceContainerHigh
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3ButtonContainerColor_${category.name}",
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
                shape = RoundedCornerShape(cornerRadius),
                color = containerColor,
                contentColor = contentColor,
                interactionSource = interactionSource,
                tonalElevation = if (isSelected) 3.dp else 0.dp,
                shadowElevation = if (isSelected) 1.dp else 0.dp,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .height(38.dp),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    // Animated checkmark when selected
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn(spring(stiffness = Spring.StiffnessMediumLow)) +
                                expandHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                        exit = fadeOut(spring(stiffness = Spring.StiffnessMediumLow)) +
                                shrinkHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = SimpIcons.Check,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = contentColor,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }

                    // Category icon when unselected
                    AnimatedVisibility(
                        visible = !isSelected,
                        enter = fadeIn(spring(stiffness = Spring.StiffnessMediumLow)) +
                                expandHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                        exit = fadeOut(spring(stiffness = Spring.StiffnessMediumLow)) +
                                shrinkHorizontally(spring(stiffness = Spring.StiffnessMediumLow)),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = category.toIcon(),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = contentColor.copy(alpha = 0.8f),
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
