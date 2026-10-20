package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance

/**
 * Solid Material 3 pastel background for playlist pages.
 *
 * The playlist accent (custom cover colour for local playlists, artwork palette for YouTube
 * playlists) is softened toward white in light theme and toward the dark surface in dark theme.
 * The result is a flat, opaque pastel: no gradient, no artwork tint showing through, and enough
 * contrast for `onSurface` / `onSurfaceVariant` text in both themes.
 *
 * The accent animates when the playlist changes, so the page fades between pastels instead of
 * snapping.
 */
@Composable
fun rememberPlaylistBackgroundColor(accent: Color): Color {
    val animatedAccent by animateColorAsState(
        targetValue = accent,
        animationSpec = tween(durationMillis = 350),
        label = "playlistBackgroundAccent",
    )
    val scheme = MaterialTheme.colorScheme
    val pastelBase = if (scheme.surface.luminance() > 0.5f) Color.White else scheme.surface
    return lerp(animatedAccent, pastelBase, 0.80f)
}
