package com.maxrave.simpmusic.ui.screen.player.content.expressive

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maxrave.domain.mediaservice.handler.RepeatState
import com.maxrave.simpmusic.expect.ui.isPlatformCastAvailable
import com.maxrave.simpmusic.ui.component.CastSlot
import com.maxrave.simpmusic.ui.icon.Info
import com.maxrave.simpmusic.ui.icon.PlaylistAdd
import com.maxrave.simpmusic.ui.icon.QueueMusic
import com.maxrave.simpmusic.ui.icon.Repeat
import com.maxrave.simpmusic.ui.icon.RepeatOne
import com.maxrave.simpmusic.ui.icon.Shuffle
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.screen.player.content.NowPlayingContentActions
import com.maxrave.simpmusic.ui.screen.player.content.NowPlayingContentState
import com.maxrave.simpmusic.viewModel.UIEvent
import kotlinx.coroutines.launch

// Base weight for inactive slots
private const val BASE_SLOT_WEIGHT = 1.0f
// Active toggles (Shuffle / Repeat ON) gain prominent resting width
private const val ACTIVE_SLOT_WEIGHT = 1.25f
// Pressed slot expands dynamically, causing adjacent buttons to squish with spring overshoot
private const val PRESSED_SLOT_WEIGHT = 1.60f

/**
 * Material 3 Expressive Standard Button Group:
 * Features fluid interactions between adjacent buttons.
 *
 * When a button is pressed or active:
 * 1. Its width/weight expands with an expressive overshoot spring.
 * 2. Surrounding/adjacent buttons react by compressing and bouncing back.
 * 3. Its shape morphs into a full pill (24dp) with dynamic color transitions.
 * 4. Shuffle, Loop (Repeat), and Add to Playlist buttons feature dedicated animated icons and states.
 */
@Composable
fun ExpressiveFunctionButtonGroup(
    state: NowPlayingContentState,
    actions: NowPlayingContentActions,
    modifier: Modifier = Modifier,
) {
    val colorScheme = MaterialTheme.colorScheme
    val isCastAvailable = isPlatformCastAvailable()

    // Interaction sources to detect press states
    val infoInteraction = remember { MutableInteractionSource() }
    val castInteraction = remember { MutableInteractionSource() }
    val shuffleInteraction = remember { MutableInteractionSource() }
    val repeatInteraction = remember { MutableInteractionSource() }
    val playlistInteraction = remember { MutableInteractionSource() }
    val queueInteraction = remember { MutableInteractionSource() }

    val isInfoPressed by infoInteraction.collectIsPressedAsState()
    val isCastPressed by castInteraction.collectIsPressedAsState()
    val isShufflePressed by shuffleInteraction.collectIsPressedAsState()
    val isRepeatPressed by repeatInteraction.collectIsPressedAsState()
    val isPlaylistPressed by playlistInteraction.collectIsPressedAsState()
    val isQueuePressed by queueInteraction.collectIsPressedAsState()

    val isShuffleActive = state.controllerState.isShuffle
    val isRepeatActive = state.controllerState.repeatState !is RepeatState.None

    // Target weights calculation: pressed slot expands, active slot expands
    val targetInfoWeight = if (isInfoPressed) PRESSED_SLOT_WEIGHT else BASE_SLOT_WEIGHT
    val targetCastWeight = if (isCastPressed) PRESSED_SLOT_WEIGHT else BASE_SLOT_WEIGHT
    val targetShuffleWeight = when {
        isShufflePressed -> PRESSED_SLOT_WEIGHT
        isShuffleActive -> ACTIVE_SLOT_WEIGHT
        else -> BASE_SLOT_WEIGHT
    }
    val targetRepeatWeight = when {
        isRepeatPressed -> PRESSED_SLOT_WEIGHT
        isRepeatActive -> ACTIVE_SLOT_WEIGHT
        else -> BASE_SLOT_WEIGHT
    }
    val targetPlaylistWeight = if (isPlaylistPressed) PRESSED_SLOT_WEIGHT else BASE_SLOT_WEIGHT
    val targetQueueWeight = if (isQueuePressed) PRESSED_SLOT_WEIGHT else BASE_SLOT_WEIGHT

    // Material 3 Expressive spring physics with overshoot for fluid adjacent reaction
    val weightSpring = spring<Float>(
        dampingRatio = 0.55f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val infoWeight by animateFloatAsState(targetValue = targetInfoWeight, animationSpec = weightSpring, label = "infoWeight")
    val castWeight by animateFloatAsState(targetValue = targetCastWeight, animationSpec = weightSpring, label = "castWeight")
    val shuffleWeight by animateFloatAsState(targetValue = targetShuffleWeight, animationSpec = weightSpring, label = "shuffleWeight")
    val repeatWeight by animateFloatAsState(targetValue = targetRepeatWeight, animationSpec = weightSpring, label = "repeatWeight")
    val playlistWeight by animateFloatAsState(targetValue = targetPlaylistWeight, animationSpec = weightSpring, label = "playlistWeight")
    val queueWeight by animateFloatAsState(targetValue = targetQueueWeight, animationSpec = weightSpring, label = "queueWeight")

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(48.dp),
    ) {
        // 1. Info Button (Start cap morphs to pill)
        ExpressiveReactiveSlot(
            weight = infoWeight,
            isPressed = isInfoPressed,
            isActive = false,
            restingStartCorner = 24.dp,
            restingEndCorner = 8.dp,
            interactionSource = infoInteraction,
            onClick = { actions.onShowInfo() },
        ) {
            Icon(
                imageVector = SimpIcons.Info,
                contentDescription = "Song Info",
                tint = colorScheme.onSurfaceVariant,
                modifier = Modifier.size(22.dp),
            )
        }

        // 2. Cast Button
        if (isCastAvailable) {
            val castCorner by animateDpAsState(
                targetValue = if (isCastPressed || state.castState.isRemote) 24.dp else 8.dp,
                animationSpec = spring(dampingRatio = 0.62f, stiffness = Spring.StiffnessMediumLow),
                label = "castCorner",
            )
            val castContainerColor by animateColorAsState(
                targetValue = if (state.castState.isRemote) colorScheme.primaryContainer else colorScheme.surfaceContainerHigh,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "castContainerColor",
            )
            val castTint by animateColorAsState(
                targetValue = if (state.castState.isRemote) colorScheme.primary else colorScheme.onSurfaceVariant,
                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                label = "castTint",
            )
            CastSlot(
                tint = castTint,
                shape = RoundedCornerShape(castCorner),
                color = castContainerColor,
                iconSize = 22.dp,
                modifier = Modifier.weight(castWeight).fillMaxHeight(),
            )
        }

        // 3. Shuffle Button (with M3 animated icon & state indicator)
        val shuffleIconColor by animateColorAsState(
            targetValue = if (isShuffleActive) colorScheme.onPrimaryContainer else colorScheme.onSurfaceVariant,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            label = "shuffleIconColor",
        )
        ExpressiveReactiveSlot(
            weight = shuffleWeight,
            isPressed = isShufflePressed,
            isActive = isShuffleActive,
            restingStartCorner = 8.dp,
            restingEndCorner = 8.dp,
            interactionSource = shuffleInteraction,
            onClick = { actions.onUIEvent(UIEvent.Shuffle) },
        ) {
            ExpressiveShuffleIcon(
                active = isShuffleActive,
                color = shuffleIconColor,
            )
        }

        // 4. Repeat / Loop Button (with M3 animated icon & single/all indicator)
        val repeatIconColor by animateColorAsState(
            targetValue = if (isRepeatActive) colorScheme.onPrimaryContainer else colorScheme.onSurfaceVariant,
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            label = "repeatIconColor",
        )
        ExpressiveReactiveSlot(
            weight = repeatWeight,
            isPressed = isRepeatPressed,
            isActive = isRepeatActive,
            restingStartCorner = 8.dp,
            restingEndCorner = 8.dp,
            interactionSource = repeatInteraction,
            onClick = { actions.onUIEvent(UIEvent.Repeat) },
        ) {
            ExpressiveRepeatIcon(
                repeatState = state.controllerState.repeatState,
                color = repeatIconColor,
            )
        }

        // 5. Add to Playlist Button (with tactile spring punch)
        ExpressiveReactiveSlot(
            weight = playlistWeight,
            isPressed = isPlaylistPressed,
            isActive = false,
            restingStartCorner = 8.dp,
            restingEndCorner = 8.dp,
            interactionSource = playlistInteraction,
            onClick = { actions.onShowAddToPlaylist() },
        ) {
            ExpressivePlaylistAddIcon(
                isPressed = isPlaylistPressed,
                color = colorScheme.onSurfaceVariant,
            )
        }

        // 6. Queue Button (End cap morphs to pill)
        ExpressiveReactiveSlot(
            weight = queueWeight,
            isPressed = isQueuePressed,
            isActive = false,
            restingStartCorner = 8.dp,
            restingEndCorner = 24.dp,
            interactionSource = queueInteraction,
            onClick = { actions.onShowQueue() },
        ) {
            Icon(
                imageVector = SimpIcons.QueueMusic,
                contentDescription = "Queue",
                tint = colorScheme.onSurfaceVariant,
                modifier = Modifier.size(22.dp),
            )
        }
    }
}

/**
 * Individual reactive slot that morphs shape, expands width, and smoothly tints
 * based on press and active selection states.
 */
@Composable
private fun RowScope.ExpressiveReactiveSlot(
    weight: Float,
    isPressed: Boolean,
    isActive: Boolean,
    restingStartCorner: Dp = 8.dp,
    restingEndCorner: Dp = 8.dp,
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    val colorScheme = MaterialTheme.colorScheme

    // Morph corner radius to full pill (24dp) on press or when active,
    // and spring smoothly back to resting corners without snapping on release or deactivation
    val cornerSpring = spring<Dp>(
        dampingRatio = 0.62f,
        stiffness = Spring.StiffnessMediumLow,
    )
    val startCorner by animateDpAsState(
        targetValue = if (isPressed || isActive) 24.dp else restingStartCorner,
        animationSpec = cornerSpring,
        label = "slotStartCorner",
    )
    val endCorner by animateDpAsState(
        targetValue = if (isPressed || isActive) 24.dp else restingEndCorner,
        animationSpec = cornerSpring,
        label = "slotEndCorner",
    )
    val slotShape = RoundedCornerShape(
        topStart = startCorner,
        bottomStart = startCorner,
        topEnd = endCorner,
        bottomEnd = endCorner,
    )

    // Dynamic M3 tonal container colors
    val containerColor by animateColorAsState(
        targetValue = when {
            isActive -> colorScheme.primaryContainer
            isPressed -> colorScheme.surfaceContainerHighest
            else -> colorScheme.surfaceContainerHigh
        },
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "slotContainerColor",
    )

    Surface(
        onClick = onClick,
        shape = slotShape,
        color = containerColor,
        interactionSource = interactionSource,
        modifier =
            Modifier
                .weight(weight)
                .fillMaxHeight(),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}

/**
 * Animated Material 3 Expressive Shuffle Icon.
 * Features a playful rotational spring twist and active state dot indicator.
 */
@Composable
private fun ExpressiveShuffleIcon(
    active: Boolean,
    color: Color,
) {
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(active) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        // Playful shuffle crossing twist animation on toggle
        launch {
            rotation.animateTo(if (active) -18f else 18f, tween(90, easing = FastOutSlowInEasing))
            rotation.animateTo(0f, spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow))
        }
        launch {
            scale.animateTo(1.22f, tween(90, easing = FastOutSlowInEasing))
            scale.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
        }
    }

    Icon(
        imageVector = SimpIcons.Shuffle,
        contentDescription = "Shuffle",
        tint = color,
        modifier =
            Modifier
                .size(22.dp)
                .graphicsLayer {
                    rotationZ = rotation.value
                    scaleX = scale.value
                    scaleY = scale.value
                },
    )
}

/**
 * Animated Material 3 Expressive Loop/Repeat Icon.
 * Features fluid 180° rotation on state changes and distinct RepeatOne badge.
 */
@Composable
private fun ExpressiveRepeatIcon(
    repeatState: RepeatState,
    color: Color,
) {
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    var isFirstComposition by remember { mutableStateOf(true) }

    LaunchedEffect(repeatState) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        // Smooth tilt on state change without abrupt snapping
        launch {
            rotation.animateTo(if (repeatState !is RepeatState.None) -40f else 40f, tween(90, easing = FastOutSlowInEasing))
            rotation.animateTo(0f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
        }
        launch {
            scale.animateTo(1.20f, tween(90, easing = FastOutSlowInEasing))
            scale.animateTo(1f, spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMediumLow))
        }
    }

    val icon = if (repeatState is RepeatState.One) SimpIcons.RepeatOne else SimpIcons.Repeat
    Icon(
        imageVector = icon,
        contentDescription = "Repeat",
        tint = color,
        modifier =
            Modifier
                .size(22.dp)
                .graphicsLayer {
                    rotationZ = rotation.value
                    scaleX = scale.value
                    scaleY = scale.value
                },
    )
}

/**
 * Animated Material 3 Expressive Add to Playlist Icon.
 * Features tactile spring scaling and punch feedback on press.
 */
@Composable
private fun ExpressivePlaylistAddIcon(
    isPressed: Boolean,
    color: Color,
) {
    val iconScale by animateFloatAsState(
        targetValue = if (isPressed) 1.25f else 1.0f,
        animationSpec = spring(dampingRatio = 0.52f, stiffness = Spring.StiffnessMediumLow),
        label = "playlistIconScale",
    )

    Icon(
        imageVector = SimpIcons.PlaylistAdd,
        contentDescription = "Add to Playlist",
        tint = color,
        modifier =
            Modifier
                .size(22.dp)
                .graphicsLayer {
                    scaleX = iconScale
                    scaleY = iconScale
                },
    )
}
