package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically

/**
 * How the search bar enters and leaves, shared by every screen that has one.
 *
 * The offset is `-it`, a full height rather than the default `-it / 2`: at half a height the bar
 * appears already halfway down and the first part of the movement is missing, which reads as a
 * jump rather than as something sliding in from above.
 *
 * Plain eased tweens, deliberately not springs: an under-damped spring overshoots past its
 * resting position, which makes the bar bounce as it settles.
 */
val SearchBarEnter: EnterTransition =
    fadeIn(tween(durationMillis = 180, easing = FastOutSlowInEasing)) +
        slideInVertically(
            animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        ) { -it }

/** Leaving is quicker than arriving: a control being dismissed should not hold the eye. */
val SearchBarExit: ExitTransition =
    fadeOut(tween(durationMillis = 140, easing = FastOutSlowInEasing)) +
        slideOutVertically(
            animationSpec = tween(durationMillis = 160, easing = FastOutSlowInEasing),
        ) { -it }
