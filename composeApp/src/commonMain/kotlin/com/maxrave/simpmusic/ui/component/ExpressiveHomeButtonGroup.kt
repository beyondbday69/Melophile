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
import androidx.compose.material3.CircularProgressIndicator
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
import com.maxrave.simpmusic.ui.icon.AccessAlarm
import com.maxrave.simpmusic.ui.icon.AutoGraph
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.GraphicEq
import com.maxrave.simpmusic.ui.icon.Insights
import com.maxrave.simpmusic.ui.icon.QueueMusic
import com.maxrave.simpmusic.ui.icon.Sensors
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.Speed
import com.maxrave.simpmusic.ui.icon.ThumbDown
import com.maxrave.simpmusic.ui.icon.ThumbUp
import com.maxrave.simpmusic.ui.icon.TrendingUp
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_COMMUTE
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_ENERGIZE
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_FEEL_GOOD
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_FOCUS
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_PARTY
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_RELAX
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_ROMANCE
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_SAD
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_SLEEP
import com.maxrave.simpmusic.viewModel.HomeViewModel.Companion.HOME_PARAMS_WORKOUT
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.all
import simpmusic.composeapp.generated.resources.commute
import simpmusic.composeapp.generated.resources.energize
import simpmusic.composeapp.generated.resources.feel_good
import simpmusic.composeapp.generated.resources.focus
import simpmusic.composeapp.generated.resources.party
import simpmusic.composeapp.generated.resources.relax
import simpmusic.composeapp.generated.resources.romance
import simpmusic.composeapp.generated.resources.sad
import simpmusic.composeapp.generated.resources.sleep
import simpmusic.composeapp.generated.resources.workout

/**
 * Mood / Moment category configuration for Material 3 Expressive Home Button Group.
 */
data class HomeMoodCategory(
    val id: String,
    val stringRes: StringResource,
    val param: String?,
    val icon: ImageVector,
)

val HomeMoodCategories: List<HomeMoodCategory> = listOf(
    HomeMoodCategory("all", Res.string.all, null, SimpIcons.AutoGraph),
    HomeMoodCategory("relax", Res.string.relax, HOME_PARAMS_RELAX, SimpIcons.GraphicEq),
    HomeMoodCategory("sleep", Res.string.sleep, HOME_PARAMS_SLEEP, SimpIcons.AccessAlarm),
    HomeMoodCategory("energize", Res.string.energize, HOME_PARAMS_ENERGIZE, SimpIcons.Speed),
    HomeMoodCategory("sad", Res.string.sad, HOME_PARAMS_SAD, SimpIcons.ThumbDown),
    HomeMoodCategory("romance", Res.string.romance, HOME_PARAMS_ROMANCE, SimpIcons.Favorite),
    HomeMoodCategory("feel_good", Res.string.feel_good, HOME_PARAMS_FEEL_GOOD, SimpIcons.ThumbUp),
    HomeMoodCategory("workout", Res.string.workout, HOME_PARAMS_WORKOUT, SimpIcons.TrendingUp),
    HomeMoodCategory("party", Res.string.party, HOME_PARAMS_PARTY, SimpIcons.QueueMusic),
    HomeMoodCategory("commute", Res.string.commute, HOME_PARAMS_COMMUTE, SimpIcons.Sensors),
    HomeMoodCategory("focus", Res.string.focus, HOME_PARAMS_FOCUS, SimpIcons.Insights),
)

/**
 * Material 3 Expressive Home Button Group:
 * Features connected pill layout, fluid GPU-accelerated spring overshoot physics,
 * continuous corner morphing, stable in-place icon transitions, and loading feedback.
 */
@Composable
fun ExpressiveHomeButtonGroup(
    selectedParam: String?,
    isLoading: Boolean,
    onSelectParam: (String?) -> Unit,
    scrollState: ScrollState,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    var currentlyPressedIndex by remember { mutableIntStateOf(-1) }

    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HomeMoodCategories.forEachIndexed { index, category ->
            val isSelected = category.param == selectedParam
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
                label = "m3HomeBtnScale_${category.id}",
            )

            // Smooth continuous shape morphing:
            // When resting in connected group: ends have 20dp outer caps and 8dp inner corners.
            // When selected or pressed: smoothly morphs into a standalone 20dp pill.
            // When unselected: smoothly animates back to resting corners without any abrupt popping.
            val isFirst = index == 0
            val isLast = index == HomeMoodCategories.lastIndex
            val restingStart = if (isFirst) 20.dp else 8.dp
            val restingEnd = if (isLast) 20.dp else 8.dp

            val startCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingStart,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3HomeBtnStartCorner_${category.id}",
            )

            val endCorner by animateDpAsState(
                targetValue = if (isSelected || isPressed) 20.dp else restingEnd,
                animationSpec = spring(
                    dampingRatio = 0.65f,
                    stiffness = Spring.StiffnessMedium,
                ),
                label = "m3HomeBtnEndCorner_${category.id}",
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
                label = "m3HomeBtnContainer_${category.id}",
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) {
                    colorScheme.onPrimaryContainer
                } else {
                    colorScheme.onSurfaceVariant
                },
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3HomeBtnContent_${category.id}",
            )

            val tonalElevation by animateDpAsState(
                targetValue = if (isSelected) 3.dp else 0.dp,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3HomeBtnTonal_${category.id}",
            )

            val shadowElevation by animateDpAsState(
                targetValue = if (isSelected) 1.dp else 0.dp,
                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                label = "m3HomeBtnShadow_${category.id}",
            )

            Surface(
                onClick = {
                    val newParam = if (isSelected && category.param != null) null else category.param
                    onSelectParam(newParam)
                },
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
                    // Stable in-place icon transition: preserves fixed width (18dp)
                    // so text never shifts or vibrates during selection transitions
                    Box(
                        modifier = Modifier.size(18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (isSelected && isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(13.dp),
                                color = contentColor,
                                strokeWidth = 2.dp,
                            )
                        } else {
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
                                label = "m3HomeIconTransition_${category.id}",
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
                                        imageVector = category.icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp),
                                        tint = contentColor.copy(alpha = 0.85f),
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = stringResource(category.stringRes),
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
