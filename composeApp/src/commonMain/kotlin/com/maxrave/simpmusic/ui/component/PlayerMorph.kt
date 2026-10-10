package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.lerp as lerpTextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.maxrave.simpmusic.expect.BackHandler
import com.maxrave.simpmusic.ui.icon.KeyboardArrowDown
import com.maxrave.simpmusic.ui.icon.SkipNext
import com.maxrave.simpmusic.ui.icon.SkipPrevious
import com.maxrave.simpmusic.ui.theme.SimpIcons
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * State controlling the shared-element morph between Mini Player and Now Playing.
 * Uses a single continuous progress value (0f = mini player, 1f = Now Playing).
 */
@Stable
class NowPlayingState(initialProgress: Float = 0f) {
    internal val anim = Animatable(initialProgress)

    /** 0f = mini player, 1f = Now Playing. */
    val progress: Float get() = anim.value
    val isExpanded: Boolean get() = anim.targetValue > 0.5f || anim.value > 0.5f

    suspend fun expand() { anim.animateTo(1f, SheetSpring) }
    suspend fun collapse() { anim.animateTo(0f, SheetSpring) }
    suspend fun snapTo(value: Float) { anim.snapTo(value.coerceIn(0f, 1f)) }
}

@Composable
fun rememberNowPlayingState(initialProgress: Float = 0f): NowPlayingState =
    remember { NowPlayingState(initialProgress) }

val SheetSpring = spring<Float>(dampingRatio = 0.9f, stiffness = 380f)

/**
 * Container transform and shared-element morph between Mini Player and Now Playing screen.
 */
@Composable
fun MiniToNowPlaying(
    state: NowPlayingState,
    title: String,
    artist: String,
    isPlaying: Boolean,
    position: Float, // 0f..1f
    onPlayPause: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier,
    durationSec: Int = 215,
    miniBottomOffset: Dp = 0.dp,
    miniColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    fullColor: Color = MaterialTheme.colorScheme.surface,
    artwork: @Composable (Modifier) -> Unit = { DefaultArtwork(it) },
    expandedContent: (@Composable () -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    val colors = MaterialTheme.colorScheme
    val type = MaterialTheme.typography
    val interactive by remember { derivedStateOf { state.anim.value > 0.9f } }
    val prog: () -> Float = { state.anim.value }

    BackHandler(enabled = state.isExpanded) {
        scope.launch { state.collapse() }
    }

    BoxWithConstraints(modifier.fillMaxSize()) {
        val w = constraints.maxWidth.toFloat()
        val h = constraints.maxHeight.toFloat()
        val top = WindowInsets.statusBars.getTop(density).toFloat()
        val bottom = WindowInsets.navigationBars.getBottom(density).toFloat()
        val lift = with(density) { miniBottomOffset.toPx() }
        val g = remember(w, h, top, bottom, lift, density.density) {
            Geo(density.density, w, h, top, bottom, lift)
        }
        val travel by rememberUpdatedState((g.miniC.top - g.fullC.top).coerceAtLeast(1f))
        val noRipple = remember { MutableInteractionSource() }

        val dragState = rememberDraggableState { delta ->
            scope.launch(start = CoroutineStart.UNDISPATCHED) {
                state.anim.snapTo((state.anim.value - delta / travel).coerceIn(0f, 1f))
            }
        }

        // 1) Container: M3 Surface, mini card -> full screen
        SheetSurface(
            prog = prog,
            g = g,
            miniColor = miniColor,
            fullColor = fullColor,
            modifier = Modifier
                .draggable(
                    state = dragState,
                    orientation = Orientation.Vertical,
                    onDragStopped = { v ->
                        val target = when {
                            v < -700f -> 1f
                            v > 700f -> 0f
                            else -> if (state.anim.value > 0.5f) 1f else 0f
                        }
                        state.anim.animateTo(target, SheetSpring, initialVelocity = -v / travel)
                    },
                )
                .clickable(interactionSource = noRipple, indication = null) {
                    if (state.anim.value < 0.5f) scope.launch { state.expand() }
                },
        )

        // 2) Mini progress bar: fades out as sheet expands
        LinearProgressIndicator(
            progress = { position.coerceIn(0f, 1f) },
            modifier = Modifier
                .morph(prog, g.miniBar, g.miniBar)
                .graphicsLayer { alpha = (1f - prog() * 5f).coerceIn(0f, 1f) },
        )

        // Morph elements alpha fades out if expandedContent is presented at the very end
        val morphElementsAlpha = if (expandedContent != null) {
            (1f - stage(prog(), 0.88f, 0.98f)).coerceIn(0f, 1f)
        } else {
            1f
        }

        // 3) Artwork morph: mini 48x48 rounded 10dp -> full size rounded 28dp with shadow
        Box(
            Modifier
                .morph(prog, g.artMini, g.artFull)
                .graphicsLayer {
                    val t = prog().coerceIn(0f, 1f)
                    alpha = morphElementsAlpha
                    shape = RoundedCornerShape(lerpF(10.dp.toPx(), 28.dp.toPx(), t))
                    clip = true
                    shadowElevation = 22.dp.toPx() * stage(t, 0.3f, 1f)
                }
                .clickable(interactionSource = noRipple, indication = null) {
                    if (state.anim.value < 0.5f) scope.launch { state.expand() }
                },
        ) {
            artwork(Modifier.fillMaxSize())
        }

        // 4) Title + Artist (typography morph: titleMedium -> headlineSmall)
        Box(
            Modifier
                .morph(prog, g.titleMini, g.titleFull)
                .graphicsLayer { alpha = morphElementsAlpha }
                .clickable(interactionSource = noRipple, indication = null) {
                    if (state.anim.value < 0.5f) scope.launch { state.expand() }
                },
        ) {
            MorphText(title, prog, type.titleMedium, type.headlineSmall, colors.onSurface)
        }
        Box(
            Modifier
                .morph(prog, g.subMini, g.subFull)
                .graphicsLayer { alpha = morphElementsAlpha }
                .clickable(interactionSource = noRipple, indication = null) {
                    if (state.anim.value < 0.5f) scope.launch { state.expand() }
                },
        ) {
            MorphText(artist, prog, type.bodySmall, type.titleMedium, colors.onSurfaceVariant)
        }

        // 5) Shared buttons: play/pause (mini icon -> large filled circle) and next track
        Box(
            Modifier
                .morph(prog, g.playMini, g.playFull)
                .graphicsLayer { alpha = morphElementsAlpha }
                .drawBehind {
                    val a = stage(prog().coerceIn(0f, 1f), 0.35f, 1f)
                    drawCircle(colors.primary.copy(alpha = a))
                },
            contentAlignment = Alignment.Center,
        ) {
            IconButton(onClick = onPlayPause, modifier = Modifier.fillMaxSize()) {
                PlayPauseIcon(
                    playing = isPlaying,
                    tint = { lerp(colors.onSurface, colors.onPrimary, stage(prog().coerceIn(0f, 1f), 0.2f, 0.8f)) },
                    inset = { lerpF(0.2f, 0.28f, prog().coerceIn(0f, 1f)) },
                )
            }
        }
        Box(
            Modifier
                .morph(prog, g.nextMini, g.nextFull)
                .graphicsLayer { alpha = morphElementsAlpha },
        ) {
            IconButton(onClick = onNext, modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = SimpIcons.SkipNext,
                    contentDescription = "Next",
                    tint = colors.onSurface,
                    modifier = Modifier.fillMaxSize(0.62f),
                )
            }
        }

        // 6) Now Playing-only elements: previous button, top bar, seek bar, time row
        Box(
            Modifier
                .morph(prog, g.prevFull, g.prevFull)
                .graphicsLayer { alpha = stage(prog(), 0.6f, 1f) * morphElementsAlpha },
        ) {
            IconButton(onClick = onPrevious, enabled = interactive, modifier = Modifier.fillMaxSize()) {
                Icon(
                    imageVector = SimpIcons.SkipPrevious,
                    contentDescription = "Previous",
                    tint = colors.onSurface,
                    modifier = Modifier.fillMaxSize(0.62f),
                )
            }
        }

        Box(
            Modifier
                .morph(prog, g.topBar, g.topBar)
                .graphicsLayer { alpha = stage(prog(), 0.6f, 1f) * morphElementsAlpha }
                .draggable(
                    state = dragState,
                    orientation = Orientation.Vertical,
                    onDragStopped = { v ->
                        val target = when {
                            v > 600f -> 0f
                            else -> if (state.anim.value > 0.5f) 1f else 0f
                        }
                        state.anim.animateTo(target, SheetSpring, initialVelocity = -v / travel)
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text("Now playing", style = type.labelLarge, color = colors.onSurfaceVariant)
            IconButton(
                onClick = { scope.launch { state.collapse() } },
                enabled = interactive,
                modifier = Modifier.align(Alignment.CenterStart).size(48.dp),
            ) {
                Icon(
                    imageVector = SimpIcons.KeyboardArrowDown,
                    contentDescription = "Collapse",
                    tint = colors.onSurface,
                )
            }
        }

        Slider(
            value = position.coerceIn(0f, 1f),
            onValueChange = onSeek,
            enabled = interactive,
            modifier = Modifier
                .morph(prog, g.seek, g.seek)
                .graphicsLayer { alpha = stage(prog(), 0.7f, 1f) * morphElementsAlpha },
        )

        Row(
            Modifier
                .morph(prog, g.timeRow, g.timeRow)
                .graphicsLayer { alpha = stage(prog(), 0.7f, 1f) * morphElementsAlpha },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val sec = (position.coerceIn(0f, 1f) * durationSec).toInt()
            Text(fmt(sec), style = type.labelMedium, color = colors.onSurfaceVariant)
            Text("-" + fmt(durationSec - sec), style = type.labelMedium, color = colors.onSurfaceVariant)
        }

        // 7) Optional full content (e.g. lyrics, queue, tabs) cross-fading when fully expanded
        if (expandedContent != null) {
            Box(
                Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        alpha = stage(prog(), 0.88f, 1f)
                    },
            ) {
                if (state.anim.value > 0.82f) {
                    expandedContent()
                }
            }
        }
    }
}

/* ---------------------------------------------------------------- pieces */

@Composable
private fun SheetSurface(
    prog: () -> Float,
    g: Geo,
    miniColor: Color,
    fullColor: Color,
    modifier: Modifier,
) {
    val t = prog().coerceIn(0f, 1f)
    Surface(
        modifier = modifier.morph(prog, g.miniC, g.fullC),
        shape = RoundedCornerShape(lerpF(g.miniRadius, 0f, stage(t, 0f, 0.9f))),
        color = lerp(miniColor, fullColor, stage(t, 0f, 0.7f)),
        shadowElevation = lerpF(6f, 0f, t).dp,
        content = {},
    )
}

@Composable
private fun MorphText(
    text: String,
    progress: () -> Float,
    mini: TextStyle,
    full: TextStyle,
    color: Color,
) {
    Text(
        text = text,
        style = lerpTextStyle(mini, full, progress().coerceIn(0f, 1f)),
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

private fun Modifier.morph(progress: () -> Float, from: Rect, to: Rect): Modifier = this
    .offset {
        val r = mix(from, to, progress())
        IntOffset(r.left.roundToInt(), r.top.roundToInt())
    }
    .layout { measurable, _ ->
        val r = mix(from, to, progress())
        val wPx = r.width.roundToInt().coerceAtLeast(0)
        val hPx = r.height.roundToInt().coerceAtLeast(0)
        val placeable = measurable.measure(Constraints.fixed(wPx, hPx))
        layout(wPx, hPx) { placeable.place(0, 0) }
    }

fun lerpF(a: Float, b: Float, t: Float) = a + (b - a) * t
fun mix(a: Rect, b: Rect, t: Float) =
    Rect(lerpF(a.left, b.left, t), lerpF(a.top, b.top, t), lerpF(a.right, b.right, t), lerpF(a.bottom, b.bottom, t))

fun stage(p: Float, from: Float, to: Float): Float =
    FastOutSlowInEasing.transform(((p - from) / (to - from)).coerceIn(0f, 1f))

private fun fmt(sec: Int): String {
    val s = sec.coerceAtLeast(0)
    val m = s / 60
    val rem = s % 60
    val remStr = if (rem < 10) "0$rem" else "$rem"
    return "$m:$remStr"
}

private class Geo(density: Float, w: Float, h: Float, top: Float, bottom: Float, lift: Float) {
    private fun dp(v: Float) = v * density
    private fun box(l: Float, t: Float, wd: Float, ht: Float) = Rect(l, t, l + wd, t + ht)

    val fullC = Rect(0f, 0f, w, h)
    private val miniH = dp(64f)
    private val margin = dp(8f)
    private val miniBottom = h - bottom - lift - margin
    val miniC = Rect(margin, miniBottom - miniH, w - margin, miniBottom)
    val miniRadius = dp(20f)
    val miniBar = Rect(miniC.left + dp(16f), miniC.bottom - dp(5f), miniC.right - dp(16f), miniC.bottom - dp(2f))

    // mini
    private val btn = dp(40f)
    private val miniMidY = miniC.top + miniH / 2
    val artMini = box(miniC.left + dp(8f), miniC.top + (miniH - dp(48f)) / 2, dp(48f), dp(48f))
    val nextMini = box(miniC.right - dp(8f) - btn, miniMidY - btn / 2, btn, btn)
    val playMini = box(nextMini.left - dp(4f) - btn, miniMidY - btn / 2, btn, btn)
    val titleMini = Rect(artMini.right + dp(12f), miniC.top + dp(10f), playMini.left - dp(8f), miniC.top + dp(10f) + dp(24f))
    val subMini = Rect(titleMini.left, miniC.top + dp(34f), titleMini.right, miniC.top + dp(34f) + dp(20f))

    // full
    private val artSize = minOf(w - dp(64f), h * 0.36f)
    val artFull = box((w - artSize) / 2, top + dp(72f), artSize, artSize)
    val titleFull = Rect(dp(32f), artFull.bottom + dp(28f), w - dp(32f), artFull.bottom + dp(28f) + dp(34f))
    val subFull = Rect(dp(32f), titleFull.bottom + dp(2f), w - dp(32f), titleFull.bottom + dp(2f) + dp(24f))
    val seek = Rect(dp(24f), subFull.bottom + dp(18f), w - dp(24f), subFull.bottom + dp(18f) + dp(36f))
    val timeRow = Rect(dp(32f), seek.bottom, w - dp(32f), seek.bottom + dp(20f))
    private val playY = timeRow.bottom + dp(20f)
    val playFull = box(w / 2 - dp(36f), playY, dp(72f), dp(72f))
    val nextFull = box(w / 2 + dp(104f) - dp(28f), playY + dp(8f), dp(56f), dp(56f))
    val prevFull = box(w / 2 - dp(104f) - dp(28f), playY + dp(8f), dp(56f), dp(56f))
    val topBar = Rect(dp(8f), top + dp(8f), w - dp(8f), top + dp(56f))
}

/* ---------------------------------------------------------------- play/pause morph icon */

@Composable
fun PlayPauseIcon(playing: Boolean, tint: () -> Color, inset: () -> Float) {
    val t by animateFloatAsState(if (playing) 1f else 0f, tween(260), label = "playPause")
    Canvas(Modifier.fillMaxSize()) {
        val s = size.minDimension * (1f - 2f * inset())
        val ox = (size.width - s) / 2
        val oy = (size.height - s) / 2
        val color = tint()
        fun quad(play: FloatArray, pause: FloatArray) {
            val path = Path()
            for (i in 0 until 4) {
                val x = ox + lerpF(play[i * 2], pause[i * 2], t) * s
                val y = oy + lerpF(play[i * 2 + 1], pause[i * 2 + 1], t) * s
                if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
            }
            path.close()
            drawPath(path, color)
        }
        quad(PLAY_L, PAUSE_L)
        quad(PLAY_R, PAUSE_R)
    }
}

private val PLAY_L = floatArrayOf(.2f, .15f, .5f, .325f, .5f, .675f, .2f, .85f)
private val PAUSE_L = floatArrayOf(.2f, .15f, .4f, .15f, .4f, .85f, .2f, .85f)
private val PLAY_R = floatArrayOf(.5f, .325f, .8f, .5f, .8f, .5f, .5f, .675f)
private val PAUSE_R = floatArrayOf(.6f, .15f, .8f, .15f, .8f, .85f, .6f, .85f)

@Composable
fun DefaultArtwork(modifier: Modifier = Modifier) {
    val c = MaterialTheme.colorScheme
    Box(modifier.background(Brush.linearGradient(listOf(c.primaryContainer, c.tertiaryContainer))))
}
