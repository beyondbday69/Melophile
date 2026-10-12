package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * Standard sizes for the Material 3 Expressive Slider tracks.
 */
enum class ExpressiveSliderSize(val trackHeight: Dp) {
    XS(16.dp),
    S(24.dp),
    M(40.dp),
    L(56.dp),
}

@Immutable
data class ExpressiveSliderColors(
    val activeTrackColor: Color,
    val inactiveTrackColor: Color,
    val handleColor: Color,
    val stopColor: Color,
    val inactiveStopColor: Color,
)

object ExpressiveSliderDefaults {
    val TrackHeightXS = 16.dp
    val TrackHeightS = 24.dp
    val TrackHeightM = 40.dp
    val TrackHeightL = 56.dp

    val HandleWidthDefault = 4.dp
    val HandleWidthPressed = 2.dp
    val Gap = 8.dp
    val InnerCornerRadius = 4.dp
    val StopDotDiameter = 4.dp
    val StopHandleExclusionRadius = 12.dp

    @Composable
    fun colors(
        activeTrackColor: Color = MaterialTheme.colorScheme.primary,
        inactiveTrackColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        handleColor: Color = MaterialTheme.colorScheme.primary,
        stopColor: Color = MaterialTheme.colorScheme.onPrimary,
        inactiveStopColor: Color = MaterialTheme.colorScheme.primary,
    ): ExpressiveSliderColors =
        ExpressiveSliderColors(
            activeTrackColor = activeTrackColor,
            inactiveTrackColor = inactiveTrackColor,
            handleColor = handleColor,
            stopColor = stopColor,
            inactiveStopColor = inactiveStopColor,
        )
}

/**
 * Custom Material 3 Expressive Slider built directly with Canvas and gesture handling.
 *
 * Implements the official M3 Expressive slider specification:
 * - Inactive & active track segments with pill outer corners (trackHeight / 2) and 4dp inner corners.
 * - Pill vertical handle that dynamically narrows from 4dp to 2dp upon interaction.
 * - Constant 8dp gap per side between handle and tracks to eliminate jitter during animation.
 * - Minimum 44dp hit/touch target height for accessibility.
 * - Discrete stops support with stop dots hidden near the handle.
 */
@Composable
fun ExpressiveSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: ExpressiveSliderColors = ExpressiveSliderDefaults.colors(),
    trackHeight: Dp = ExpressiveSliderDefaults.TrackHeightM,
) {
    var pressed by remember { mutableStateOf(false) }
    var widthPx by remember { mutableIntStateOf(0) }

    val handleWidth by animateDpAsState(
        targetValue = if (pressed) ExpressiveSliderDefaults.HandleWidthPressed else ExpressiveSliderDefaults.HandleWidthDefault,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing),
        label = "expressiveSliderHandleWidth",
    )

    val handleH = maxOf(44.dp, trackHeight + 8.dp)
    val rangeLength = (valueRange.endInclusive - valueRange.start).coerceAtLeast(1e-6f)
    val fraction = ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)

    val density = LocalDensity.current
    val trackHeightPx = with(density) { trackHeight.toPx() }
    val gapPx = with(density) { ExpressiveSliderDefaults.Gap.toPx() }
    val innerRadiusPx = with(density) { ExpressiveSliderDefaults.InnerCornerRadius.toPx() }
    val stopRadiusPx = with(density) { (ExpressiveSliderDefaults.StopDotDiameter / 2).toPx() }
    val stopExclusionPx = with(density) { ExpressiveSliderDefaults.StopHandleExclusionRadius.toPx() }

    fun quantize(f: Float): Float {
        val clamped = f.coerceIn(0f, 1f)
        return if (steps > 0) {
            val stepSize = 1f / (steps + 1)
            val roundedStep = (clamped / stepSize).roundToInt()
            valueRange.start + (roundedStep * stepSize) * rangeLength
        } else {
            valueRange.start + clamped * rangeLength
        }.coerceIn(valueRange.start, valueRange.endInclusive)
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(handleH)
                .onSizeChanged { widthPx = it.width }
                .semantics {
                    role = Role.Slider
                    progressBarRangeInfo =
                        ProgressBarRangeInfo(
                            current = value,
                            range = valueRange,
                            steps = steps,
                        )
                    setProgress { targetValue ->
                        val coerced = targetValue.coerceIn(valueRange.start, valueRange.endInclusive)
                        if (coerced != value) {
                            onValueChange(coerced)
                            onValueChangeFinished?.invoke()
                            true
                        } else {
                            false
                        }
                    }
                }.pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    detectTapGestures(
                        onPress = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                                tryAwaitRelease()
                                pressed = false
                                onValueChangeFinished?.invoke()
                            }
                        },
                    )
                }.pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                            }
                        },
                        onDragEnd = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onDragCancel = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onHorizontalDrag = { change, _ ->
                            change.consume()
                            if (widthPx > 0) {
                                val f = (change.position.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                            }
                        },
                    )
                },
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val totalW = size.width
            val totalH = size.height
            val x = fraction * totalW

            // 1. Active segment (from 0 to x)
            drawSegment(
                left = 0f,
                right = x,
                leftTouchesHandle = false,
                rightTouchesHandle = true,
                trackHeightPx = trackHeightPx,
                gapPx = gapPx,
                innerRadiusPx = innerRadiusPx,
                color = if (enabled) colors.activeTrackColor else colors.activeTrackColor.copy(alpha = 0.38f),
            )

            // 2. Inactive segment (from x to totalW)
            drawSegment(
                left = x,
                right = totalW,
                leftTouchesHandle = true,
                rightTouchesHandle = false,
                trackHeightPx = trackHeightPx,
                gapPx = gapPx,
                innerRadiusPx = innerRadiusPx,
                color = if (enabled) colors.inactiveTrackColor else colors.inactiveTrackColor.copy(alpha = 0.38f),
            )

            // 3. Discrete stops dots (if steps > 0)
            if (steps > 0) {
                val totalSteps = steps + 1
                for (i in 1..steps) {
                    val stopX = (i.toFloat() / totalSteps) * totalW
                    if (abs(stopX - x) >= stopExclusionPx) {
                        val stopColor =
                            if (stopX < x) {
                                colors.stopColor
                            } else {
                                colors.inactiveStopColor
                            }
                        drawCircle(
                            color = if (enabled) stopColor else stopColor.copy(alpha = 0.38f),
                            radius = stopRadiusPx,
                            center = Offset(stopX, totalH / 2f),
                        )
                    }
                }
            }

            // 4. Handle (thin vertical pill)
            val w = handleWidth.toPx()
            val handleTop = (totalH - handleH.toPx()) / 2f
            drawRoundRect(
                color = if (enabled) colors.handleColor else colors.handleColor.copy(alpha = 0.38f),
                topLeft = Offset(x - w / 2f, handleTop),
                size = Size(w, handleH.toPx()),
                cornerRadius = CornerRadius(w / 2f, w / 2f),
            )
        }
    }
}

/**
 * Centered Material 3 Expressive Slider.
 * Value expands symmetrically or towards the left/right starting from the center (min + max)/2.
 */
@Composable
fun ExpressiveCenteredSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = -1f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: ExpressiveSliderColors = ExpressiveSliderDefaults.colors(),
    trackHeight: Dp = ExpressiveSliderDefaults.TrackHeightM,
) {
    var pressed by remember { mutableStateOf(false) }
    var widthPx by remember { mutableIntStateOf(0) }

    val handleWidth by animateDpAsState(
        targetValue = if (pressed) ExpressiveSliderDefaults.HandleWidthPressed else ExpressiveSliderDefaults.HandleWidthDefault,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing),
        label = "expressiveCenteredSliderHandleWidth",
    )

    val handleH = maxOf(44.dp, trackHeight + 8.dp)
    val rangeLength = (valueRange.endInclusive - valueRange.start).coerceAtLeast(1e-6f)
    val fraction = ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)

    val density = LocalDensity.current
    val trackHeightPx = with(density) { trackHeight.toPx() }
    val gapPx = with(density) { ExpressiveSliderDefaults.Gap.toPx() }
    val innerRadiusPx = with(density) { ExpressiveSliderDefaults.InnerCornerRadius.toPx() }

    fun quantize(f: Float): Float {
        val clamped = f.coerceIn(0f, 1f)
        return if (steps > 0) {
            val stepSize = 1f / (steps + 1)
            val roundedStep = (clamped / stepSize).roundToInt()
            valueRange.start + (roundedStep * stepSize) * rangeLength
        } else {
            valueRange.start + clamped * rangeLength
        }.coerceIn(valueRange.start, valueRange.endInclusive)
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(handleH)
                .onSizeChanged { widthPx = it.width }
                .pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    detectTapGestures(
                        onPress = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                                tryAwaitRelease()
                                pressed = false
                                onValueChangeFinished?.invoke()
                            }
                        },
                    )
                }.pointerInput(enabled, valueRange, steps) {
                    if (!enabled) return@pointerInput
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                            }
                        },
                        onDragEnd = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onDragCancel = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onHorizontalDrag = { change, _ ->
                            change.consume()
                            if (widthPx > 0) {
                                val f = (change.position.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(quantize(f))
                            }
                        },
                    )
                },
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val totalW = size.width
            val totalH = size.height
            val c = totalW / 2f
            val x = fraction * totalW

            if (x >= c) {
                // Left inactive segment [0, c]
                drawSegment(0f, c, false, false, trackHeightPx, gapPx, innerRadiusPx, colors.inactiveTrackColor)
                // Center active segment [c, x] (touches handle on right)
                drawSegment(c, x, false, true, trackHeightPx, gapPx, innerRadiusPx, colors.activeTrackColor)
                // Right inactive segment [x, totalW] (touches handle on left)
                drawSegment(x, totalW, true, false, trackHeightPx, gapPx, innerRadiusPx, colors.inactiveTrackColor)
            } else {
                // Left inactive segment [0, x] (touches handle on right)
                drawSegment(0f, x, false, true, trackHeightPx, gapPx, innerRadiusPx, colors.inactiveTrackColor)
                // Center active segment [x, c] (touches handle on left)
                drawSegment(x, c, true, false, trackHeightPx, gapPx, innerRadiusPx, colors.activeTrackColor)
                // Right inactive segment [c, totalW]
                drawSegment(c, totalW, false, false, trackHeightPx, gapPx, innerRadiusPx, colors.inactiveTrackColor)
            }

            // Handle
            val w = handleWidth.toPx()
            val handleTop = (totalH - handleH.toPx()) / 2f
            drawRoundRect(
                color = colors.handleColor,
                topLeft = Offset(x - w / 2f, handleTop),
                size = Size(w, handleH.toPx()),
                cornerRadius = CornerRadius(w / 2f, w / 2f),
            )
        }
    }
}

/**
 * Custom Material 3 Expressive Wavy Slider.
 *
 * Implements Section 5 of the M3 Expressive Slider Build Guide:
 * - Active track segment is an animated sinusoidal wave flowing smoothly to the right when playing.
 * - Envelope ramps smooth amplitude over ends.
 * - Inactive track segment is a flat line with round caps.
 * - Pill vertical handle that narrows when pressed with a constant 8dp gap.
 */
@Composable
fun ExpressiveWavySlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: ExpressiveSliderColors = ExpressiveSliderDefaults.colors(),
    trackHeight: Dp = ExpressiveSliderDefaults.TrackHeightM,
) {
    var pressed by remember { mutableStateOf(false) }
    var widthPx by remember { mutableIntStateOf(0) }
    var wavePhase by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            var lastTime = 0L
            while (true) {
                withInfiniteAnimationFrameMillis { frameTime ->
                    if (lastTime != 0L) {
                        val dt = (frameTime - lastTime) / 1000f
                        wavePhase = (wavePhase + dt * 3.1f) % (2f * PI.toFloat())
                    }
                    lastTime = frameTime
                }
            }
        }
    }

    val handleWidth by animateDpAsState(
        targetValue = if (pressed) ExpressiveSliderDefaults.HandleWidthPressed else ExpressiveSliderDefaults.HandleWidthDefault,
        animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing),
        label = "expressiveWavySliderHandleWidth",
    )

    val handleH = maxOf(44.dp, trackHeight + 8.dp)
    val rangeLength = (valueRange.endInclusive - valueRange.start).coerceAtLeast(1e-6f)
    val fraction = ((value - valueRange.start) / rangeLength).coerceIn(0f, 1f)

    val density = LocalDensity.current
    val gapPx = with(density) { ExpressiveSliderDefaults.Gap.toPx() }
    val trackStrokePx = with(density) { 8.dp.toPx() }
    val wavelengthPx = with(density) { 40.dp.toPx() }
    val amplitudePx = with(density) { (if (isPlaying && !pressed) 4.dp.toPx() else 0f) }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(handleH)
                .onSizeChanged { widthPx = it.width }
                .pointerInput(enabled, valueRange) {
                    if (!enabled) return@pointerInput
                    detectTapGestures(
                        onPress = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(valueRange.start + f * rangeLength)
                                tryAwaitRelease()
                                pressed = false
                                onValueChangeFinished?.invoke()
                            }
                        },
                    )
                }.pointerInput(enabled, valueRange) {
                    if (!enabled) return@pointerInput
                    detectHorizontalDragGestures(
                        onDragStart = { offset ->
                            if (widthPx > 0) {
                                pressed = true
                                val f = (offset.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(valueRange.start + f * rangeLength)
                            }
                        },
                        onDragEnd = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onDragCancel = {
                            pressed = false
                            onValueChangeFinished?.invoke()
                        },
                        onHorizontalDrag = { change, _ ->
                            change.consume()
                            if (widthPx > 0) {
                                val f = (change.position.x / widthPx).coerceIn(0f, 1f)
                                onValueChange(valueRange.start + f * rangeLength)
                            }
                        },
                    )
                },
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val totalW = size.width
            val totalH = size.height
            val centerY = totalH / 2f
            val x = fraction * totalW

            // 1. Active Wavy Segment
            val startX = 6f
            val endX = (x - gapPx).coerceAtLeast(startX)
            if (endX > startX + 1f) {
                val wavePath = Path()
                val totalLength = endX - startX
                var currX = startX
                val stepX = 2f

                var first = true
                while (currX <= endX) {
                    val progress = (currX - startX) / totalLength
                    // Envelope function: ramp up in first 10%, ramp down in last 5%
                    val env = when {
                        progress < 0.10f -> {
                            val t = progress / 0.10f
                            t * t * (3f - 2f * t)
                        }
                        progress > 0.95f -> {
                            val t = (1f - progress) / 0.05f
                            t * t * (3f - 2f * t)
                        }
                        else -> 1f
                    }
                    val a = amplitudePx * env
                    val y = centerY + a * sin((2f * PI.toFloat() * (currX - startX) / wavelengthPx) - wavePhase)
                    if (first) {
                        wavePath.moveTo(currX, y)
                        first = false
                    } else {
                        wavePath.lineTo(currX, y)
                    }
                    currX += stepX
                }

                drawPath(
                    path = wavePath,
                    color = colors.activeTrackColor,
                    style = Stroke(width = trackStrokePx, cap = StrokeCap.Round),
                )
            }

            // 2. Inactive flat segment with round caps
            val inactiveStartX = (x + gapPx).coerceAtMost(totalW)
            if (totalW - inactiveStartX > 1f) {
                drawLine(
                    color = colors.inactiveTrackColor,
                    start = Offset(inactiveStartX, centerY),
                    end = Offset(totalW - 4f, centerY),
                    strokeWidth = trackStrokePx,
                    cap = StrokeCap.Round,
                )
            }

            // 3. Handle
            val w = handleWidth.toPx()
            val handleTop = (totalH - handleH.toPx()) / 2f
            drawRoundRect(
                color = colors.handleColor,
                topLeft = Offset(x - w / 2f, handleTop),
                size = Size(w, handleH.toPx()),
                cornerRadius = CornerRadius(w / 2f, w / 2f),
            )
        }
    }
}

/**
 * Internal helper to draw a track segment with pill outer corners and 4dp inner corners.
 */
private fun DrawScope.drawSegment(
    left: Float,
    right: Float,
    leftTouchesHandle: Boolean,
    rightTouchesHandle: Boolean,
    trackHeightPx: Float,
    gapPx: Float,
    innerRadiusPx: Float,
    color: Color,
) {
    val a = if (leftTouchesHandle) left + gapPx else left
    val b = if (rightTouchesHandle) right - gapPx else right
    if (b - a < 1f) return

    val rl = if (left <= 0.5f) trackHeightPx / 2f else innerRadiusPx
    val rr = if (right >= size.width - 0.5f) trackHeightPx / 2f else innerRadiusPx
    val top = (size.height - trackHeightPx) / 2f

    val path = Path().apply {
        addRoundRect(
            RoundRect(
                rect = Rect(a, top, b, top + trackHeightPx),
                topLeft = CornerRadius(rl, rl),
                bottomLeft = CornerRadius(rl, rl),
                topRight = CornerRadius(rr, rr),
                bottomRight = CornerRadius(rr, rr),
            ),
        )
    }
    drawPath(path, color)
}
