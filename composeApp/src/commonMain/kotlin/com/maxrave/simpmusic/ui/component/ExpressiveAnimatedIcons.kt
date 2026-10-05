package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maxrave.simpmusic.ui.icon.Download
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.Insights
import com.maxrave.simpmusic.ui.icon.SimpIcons
import kotlin.math.PI
import kotlin.math.sin

/**
 * Material 3 Expressive Animated Icons:
 * Giving UI elements life, heartbeat, and organic warmth ("data feeling emotional").
 */

/**
 * Emotional beating heart icon with physiological lub-dub rhythm.
 */
@Composable
fun ExpressiveHeartbeatIcon(
    modifier: Modifier = Modifier,
    size: Dp = 28.dp,
    tint: Color = Color(0xFFFF3366),
) {
    val transition = rememberInfiniteTransition(label = "heartbeat")
    // Double pulse heartbeat: 0 -> 1.22 -> 1.08 -> 1.28 -> 1.0 -> rest
    val scale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1200
                1.00f at 0
                1.24f at 140 using FastOutSlowInEasing // Lub
                1.06f at 260
                1.30f at 400 using FastOutSlowInEasing // Dub
                1.00f at 600
                1.00f at 1200 // Resting pause
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "heartbeatScale",
    )

    val auraAlpha by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1200
                0.0f at 0
                0.45f at 140
                0.15f at 260
                0.55f at 400
                0.0f at 650
                0.0f at 1200
            },
            repeatMode = RepeatMode.Restart,
        ),
        label = "heartAuraAlpha",
    )

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        // Soft emotional glow aura
        Canvas(modifier = Modifier.fillMaxSize()) {
            if (auraAlpha > 0.01f) {
                drawCircle(
                    color = tint.copy(alpha = auraAlpha),
                    radius = (this.size.minDimension / 2f) * scale * 0.95f,
                )
            }
        }
        Icon(
            imageVector = SimpIcons.Favorite,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(size * 0.75f)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
        )
    }
}

/**
 * Animated spinning vinyl record with micro-grooves, light reflection, and center label.
 */
@Composable
fun ExpressiveVinylIcon(
    modifier: Modifier = Modifier,
    size: Dp = 28.dp,
    labelColor: Color = MaterialTheme.colorScheme.primary,
    vinylColor: Color = Color(0xFF1E2024),
) {
    val transition = rememberInfiniteTransition(label = "vinylSpin")
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "vinylAngle",
    )

    Canvas(modifier = modifier.size(size)) {
        val center = Offset(this.size.width / 2f, this.size.height / 2f)
        val radius = this.size.minDimension / 2f

        rotate(angle, pivot = center) {
            // Main vinyl disc body
            drawCircle(
                color = vinylColor,
                radius = radius,
                center = center,
            )

            // Dynamic anisotropic reflection sheen
            drawCircle(
                brush = Brush.sweepGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.05f),
                        Color.White.copy(alpha = 0.22f),
                        Color.White.copy(alpha = 0.05f),
                        Color.White.copy(alpha = 0.22f),
                        Color.White.copy(alpha = 0.05f),
                    ),
                    center = center,
                ),
                radius = radius,
                center = center,
            )

            // Fine sound grooves
            val grooveRadii = floatArrayOf(0.88f, 0.76f, 0.64f, 0.52f)
            grooveRadii.forEach { factor ->
                drawCircle(
                    color = Color.White.copy(alpha = 0.12f),
                    radius = radius * factor,
                    center = center,
                    style = Stroke(width = 1.2.dp.toPx()),
                )
            }

            // Center label
            drawCircle(
                color = labelColor,
                radius = radius * 0.36f,
                center = center,
            )

            // Center spindle hole
            drawCircle(
                color = Color.Black,
                radius = radius * 0.12f,
                center = center,
            )
        }
    }
}

/**
 * Animated bobbing download icon with pulsing offline-ready halo.
 */
@Composable
fun ExpressiveFloatingDownloadIcon(
    modifier: Modifier = Modifier,
    size: Dp = 28.dp,
    tint: Color = MaterialTheme.colorScheme.secondary,
) {
    val transition = rememberInfiniteTransition(label = "downloadBob")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "downloadPhase",
    )

    // Smooth sinusoidal bobbing: translateY oscillates +/- 2.5dp
    val bobOffset = sin(phase * 2 * PI.toFloat()) * 3f

    val haloAlpha by transition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 900, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "haloAlpha",
    )

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = tint.copy(alpha = haloAlpha),
                radius = (this.size.minDimension / 2f) * 0.85f,
            )
        }
        Icon(
            imageVector = SimpIcons.Download,
            contentDescription = null,
            tint = tint,
            modifier = Modifier
                .size(size * 0.75f)
                .graphicsLayer {
                    translationY = bobOffset.dp.toPx()
                },
        )
    }
}

/**
 * Animated radar / sparkle ripple icon for followed creators & trends.
 */
@Composable
fun ExpressiveRadarIcon(
    modifier: Modifier = Modifier,
    size: Dp = 28.dp,
    tint: Color = MaterialTheme.colorScheme.tertiary,
) {
    val transition = rememberInfiniteTransition(label = "radarPulse")
    val pulseProgress by transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "radarProgress",
    )

    val pulseAlpha = (1f - pulseProgress).coerceIn(0f, 0.6f)

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(this.size.width / 2f, this.size.height / 2f)
            val maxRadius = this.size.minDimension / 2f
            drawCircle(
                color = tint.copy(alpha = pulseAlpha),
                radius = maxRadius * pulseProgress,
                center = center,
                style = Stroke(width = 2.dp.toPx()),
            )
        }
        Icon(
            imageVector = SimpIcons.Insights,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size * 0.72f),
        )
    }
}

/**
 * Lively 3-bar mini equalizer indicating active rhythm, soul, or playback.
 */
@Composable
fun ExpressiveLiveEqualizerBars(
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary,
    height: Dp = 16.dp,
) {
    val transition = rememberInfiniteTransition(label = "miniEq")
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "eqPhase",
    )

    Canvas(modifier = modifier.size(width = 16.dp, height = height)) {
        val strokeWidth = 2.5.dp.toPx()
        val capRadius = strokeWidth / 2f
        val centerY = size.height / 2f

        val h1 = 0.3f + 0.65f * (0.5f + 0.5f * sin(phase * 2 * PI.toFloat()))
        val h2 = 0.3f + 0.65f * (0.5f + 0.5f * sin((phase + 0.33f) * 2 * PI.toFloat()))
        val h3 = 0.3f + 0.65f * (0.5f + 0.5f * sin((phase + 0.66f) * 2 * PI.toFloat()))

        val xs = floatArrayOf(2.5.dp.toPx(), 8.dp.toPx(), 13.5.dp.toPx())
        val hs = floatArrayOf(h1, h2, h3)

        for (i in 0..2) {
            val half = (size.height * hs[i] / 2f - capRadius).coerceAtLeast(0f)
            drawLine(
                color = barColor,
                start = Offset(xs[i], centerY - half),
                end = Offset(xs[i], centerY + half),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round,
            )
        }
    }
}
