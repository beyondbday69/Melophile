package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Shared Material 3 playlist background.
 *
 * A `surface` base with a soft radial accent glow anchored behind the hero area. The glow is
 * deliberately low-alpha: it reads as a tonal surface tint rather than as a colored page, so
 * text and controls keep their M3 contrast in both light and dark themes.
 *
 * The accent animates when the playlist's artwork or custom cover color changes, which makes
 * switching playlists feel continuous instead of snapping to a new background.
 */
@Composable
fun Modifier.playlistBackground(accent: Color): Modifier {
    val animatedAccent by animateColorAsState(
        targetValue = accent,
        animationSpec = tween(durationMillis = 350),
        label = "playlistBackgroundAccent",
    )
    val surface = MaterialTheme.colorScheme.surface
    return drawBehind {
        drawRect(surface)
        drawRect(
            brush =
                Brush.radialGradient(
                    colors =
                        listOf(
                            animatedAccent.copy(alpha = 0.20f),
                            animatedAccent.copy(alpha = 0.06f),
                            Color.Transparent,
                        ),
                    center = Offset(size.width / 2f, size.height * 0.16f),
                    radius = size.width.coerceAtLeast(size.height * 0.55f) * 0.9f,
                ),
        )
    }
}
