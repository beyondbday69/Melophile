package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.maxrave.simpmusic.ui.icon.AutoGraph
import com.maxrave.simpmusic.ui.icon.Home
import com.maxrave.simpmusic.ui.icon.LibraryMusic
import com.maxrave.simpmusic.ui.icon.Search
import com.maxrave.simpmusic.ui.icon.Sensors
import com.maxrave.simpmusic.ui.icon.SimpIcons
import kotlinx.coroutines.launch

/**
 * CompositionLocal indicating whether the enclosing navigation tab/dock item is currently selected.
 */
val LocalNavigationTabSelected = compositionLocalOf { false }

/**
 * Animated Material 3 Expressive Home icon.
 * Performs a vertical hop and squash-and-stretch spring bounce on selection.
 */
@Composable
fun AnimatedHomeIcon(
    selected: Boolean = LocalNavigationTabSelected.current,
    modifier: Modifier = Modifier,
) {
    val yOffset = remember { Animatable(0f) }
    val scaleX = remember { Animatable(1f) }
    val scaleY = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selected) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        if (selected) {
            launch {
                // Hop up and land with M3 spring settle
                yOffset.animateTo(-3.5f, tween(100, easing = FastOutSlowInEasing))
                yOffset.animateTo(0f, spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                // Horizontal compression during jump, expansion on landing, settle
                scaleX.animateTo(0.92f, tween(100, easing = FastOutSlowInEasing))
                scaleX.animateTo(1.10f, tween(120, easing = FastOutSlowInEasing))
                scaleX.animateTo(1f, spring(dampingRatio = 0.58f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                // Vertical stretch during jump, squash on landing impact, settle
                scaleY.animateTo(1.12f, tween(100, easing = FastOutSlowInEasing))
                scaleY.animateTo(0.90f, tween(120, easing = FastOutSlowInEasing))
                scaleY.animateTo(1f, spring(dampingRatio = 0.58f, stiffness = Spring.StiffnessMediumLow))
            }
        } else {
            launch {
                yOffset.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                scaleX.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                scaleY.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
        }
    }

    Icon(
        imageVector = SimpIcons.Home,
        contentDescription = null,
        modifier =
            modifier.graphicsLayer {
                translationY = yOffset.value * density
                this.scaleX = scaleX.value
                this.scaleY = scaleY.value
            },
    )
}

/**
 * Animated Material 3 Expressive Search (Magnifying Glass) icon.
 * Performs a lens zoom pulse and handle tilt/sweep on selection.
 */
@Composable
fun AnimatedSearchIcon(
    selected: Boolean = LocalNavigationTabSelected.current,
    modifier: Modifier = Modifier,
) {
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selected) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        if (selected) {
            launch {
                // Handle tilt: anticipation swing back, forward sweep, spring settle
                rotation.animateTo(-18f, tween(90, easing = FastOutSlowInEasing))
                rotation.animateTo(6f, tween(110, easing = FastOutSlowInEasing))
                rotation.animateTo(0f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                // Lens zoom pulse: zoom in inspection, slight rebound, settle
                scale.animateTo(1.22f, tween(110, easing = FastOutSlowInEasing))
                scale.animateTo(0.97f, tween(90, easing = FastOutSlowInEasing))
                scale.animateTo(1f, spring(dampingRatio = 0.58f, stiffness = Spring.StiffnessMediumLow))
            }
        } else {
            launch {
                rotation.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
        }
    }

    Icon(
        imageVector = SimpIcons.Search,
        contentDescription = null,
        modifier =
            modifier.graphicsLayer {
                rotationZ = rotation.value
                scaleX = scale.value
                scaleY = scale.value
            },
    )
}

/**
 * Animated Material 3 Expressive Library icon.
 * Performs a musical card/note lift and playful wobble tilt on selection.
 */
@Composable
fun AnimatedLibraryIcon(
    selected: Boolean = LocalNavigationTabSelected.current,
    modifier: Modifier = Modifier,
) {
    val rotation = remember { Animatable(0f) }
    val yOffset = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selected) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        if (selected) {
            launch {
                // Playful musical tilt wobble
                rotation.animateTo(-10f, tween(80, easing = FastOutSlowInEasing))
                rotation.animateTo(8f, tween(110, easing = FastOutSlowInEasing))
                rotation.animateTo(0f, spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                // Note/card lift
                yOffset.animateTo(-3.5f, tween(90, easing = FastOutSlowInEasing))
                yOffset.animateTo(0f, spring(dampingRatio = 0.50f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                // Scale punch
                scale.animateTo(1.18f, tween(100, easing = FastOutSlowInEasing))
                scale.animateTo(0.96f, tween(90, easing = FastOutSlowInEasing))
                scale.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
            }
        } else {
            launch {
                rotation.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                yOffset.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
        }
    }

    Icon(
        imageVector = SimpIcons.LibraryMusic,
        contentDescription = null,
        modifier =
            modifier.graphicsLayer {
                rotationZ = rotation.value
                translationY = yOffset.value * density
                scaleX = scale.value
                scaleY = scale.value
            },
    )
}

/**
 * Animated Material 3 Expressive Analytics icon.
 */
@Composable
fun AnimatedAnalyticsIcon(
    selected: Boolean = LocalNavigationTabSelected.current,
    modifier: Modifier = Modifier,
) {
    val yOffset = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selected) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        if (selected) {
            launch {
                yOffset.animateTo(-3f, tween(90, easing = FastOutSlowInEasing))
                yOffset.animateTo(0f, spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow))
            }
            launch {
                scale.animateTo(1.16f, tween(100, easing = FastOutSlowInEasing))
                scale.animateTo(0.96f, tween(90, easing = FastOutSlowInEasing))
                scale.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
            }
        } else {
            launch {
                yOffset.animateTo(0f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
            launch {
                scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
        }
    }

    Icon(
        imageVector = SimpIcons.AutoGraph,
        contentDescription = null,
        modifier =
            modifier.graphicsLayer {
                translationY = yOffset.value * density
                scaleX = scale.value
                scaleY = scale.value
            },
    )
}

/**
 * Animated Material 3 Expressive Mix For You (Sensors) icon.
 */
@Composable
fun AnimatedMixForYouIcon(
    selected: Boolean = LocalNavigationTabSelected.current,
    modifier: Modifier = Modifier,
) {
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(selected) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        if (selected) {
            launch {
                // Expanding sensor radio wave pulse
                scale.animateTo(1.20f, tween(110, easing = FastOutSlowInEasing))
                scale.animateTo(0.95f, tween(90, easing = FastOutSlowInEasing))
                scale.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
            }
        } else {
            launch {
                scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium))
            }
        }
    }

    Icon(
        imageVector = SimpIcons.Sensors,
        contentDescription = null,
        modifier =
            modifier.graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            },
    )
}
