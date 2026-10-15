package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

/** Standard M3 Expressive spring spec for shapes and sizes */
fun <T> expressiveSpring(
    dampingRatio: Float = 0.7f,
    stiffness: Float = 380f,
) = spring<T>(
    dampingRatio = dampingRatio,
    stiffness = stiffness,
)

/**
 * Ensures pressed state stays visible for at least [minDurationMs] (default 130ms),
 * and resets cleanly on pointer cancel.
 */
fun Modifier.pressFeedback(
    minDurationMs: Long = 130L,
    enabled: Boolean = true,
    onPressedChange: (Boolean) -> Unit,
    onClick: (() -> Unit)? = null,
): Modifier = composed {
    val coroutineScope = rememberCoroutineScope()
    if (!enabled) return@composed this

    pointerInput(Unit) {
        awaitEachGesture {
            val down = awaitFirstDown(requireUnconsumed = false)
            onPressedChange(true)
            val startTime = Clock.System.now().toEpochMilliseconds()
            val up = waitForUpOrCancellation()
            val elapsed = Clock.System.now().toEpochMilliseconds() - startTime
            val remaining = maxOf(0L, minDurationMs - elapsed)
            coroutineScope.launch {
                if (remaining > 0L) {
                    delay(remaining)
                }
                onPressedChange(false)
            }
            if (up != null) {
                onClick?.invoke()
            }
        }
    }
}
