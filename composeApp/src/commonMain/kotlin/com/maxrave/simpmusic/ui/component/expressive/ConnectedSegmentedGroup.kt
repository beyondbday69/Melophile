package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.SimpIcons

/**
 * Connected Segmented Group (Material 3 Expressive).
 *
 * Height 46dp, gap 4dp.
 * - Outer corners 23dp, inner corners 8dp.
 * - Selected: full pill (23dp) filled `primary`/`onPrimary` with leading check icon (18dp).
 * - Unselected: `surfaceContainerHigh`, `onSurfaceVariant`.
 * - Neighbor reaction: pressed segment weight x1.12, adjacent x0.94, total width constant.
 * - Pressed radius: 12dp.
 * - Pressed state min duration 130ms.
 */
@Composable
fun ConnectedSegmentedGroup(
    modifier: Modifier = Modifier,
    segments: List<String>,
    selectedIndex: Int,
    onSegmentSelected: (Int) -> Unit,
) {
    var pressedIndex by remember { mutableStateOf<Int?>(null) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(46.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        segments.forEachIndexed { i, title ->
            val isSelected = i == selectedIndex
            val isPressed = pressedIndex == i

            // Weight calculation for neighbor reaction
            val targetWeight = when {
                pressedIndex == null -> 1.0f
                i == pressedIndex -> 1.12f
                kotlin.math.abs(i - (pressedIndex ?: 0)) == 1 -> 0.94f
                else -> 1.0f
            }
            val animatedWeight by animateFloatAsState(
                targetValue = targetWeight,
                animationSpec = expressiveSpring(),
                label = "segWeight_$i",
            )

            // Shape calculation
            val (targetTopStart, targetTopEnd, targetBottomEnd, targetBottomStart) = when {
                isPressed -> listOf(12.dp, 12.dp, 12.dp, 12.dp)
                isSelected -> listOf(23.dp, 23.dp, 23.dp, 23.dp)
                i == 0 -> listOf(23.dp, 8.dp, 8.dp, 23.dp)
                i == segments.size - 1 -> listOf(8.dp, 23.dp, 23.dp, 8.dp)
                else -> listOf(8.dp, 8.dp, 8.dp, 8.dp)
            }

            val animatedTopStart by animateDpAsState(
                targetValue = targetTopStart,
                animationSpec = expressiveSpring(),
                label = "segTopStart_$i",
            )
            val animatedTopEnd by animateDpAsState(
                targetValue = targetTopEnd,
                animationSpec = expressiveSpring(),
                label = "segTopEnd_$i",
            )
            val animatedBottomEnd by animateDpAsState(
                targetValue = targetBottomEnd,
                animationSpec = expressiveSpring(),
                label = "segBottomEnd_$i",
            )
            val animatedBottomStart by animateDpAsState(
                targetValue = targetBottomStart,
                animationSpec = expressiveSpring(),
                label = "segBottomStart_$i",
            )

            val shape = RoundedCornerShape(
                topStart = animatedTopStart,
                topEnd = animatedTopEnd,
                bottomEnd = animatedBottomEnd,
                bottomStart = animatedBottomStart,
            )

            // Colors
            val targetContainerColor = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceContainerHigh
            }
            val targetContentColor = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }

            val containerColor by animateColorAsState(
                targetValue = targetContainerColor,
                animationSpec = tween(150),
                label = "segBgColor_$i",
            )
            val contentColor by animateColorAsState(
                targetValue = targetContentColor,
                animationSpec = tween(150),
                label = "segTextColor_$i",
            )

            Box(
                modifier = Modifier
                    .weight(animatedWeight)
                    .fillMaxHeight()
                    .semantics {
                        role = Role.Tab
                        selected = isSelected
                    }
                    .clip(shape)
                    .background(containerColor)
                    .pressFeedback(
                        minDurationMs = 130L,
                        onPressedChange = { pressed ->
                            pressedIndex = if (pressed) i else null
                        },
                        onClick = {
                            onSegmentSelected(i)
                        },
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(horizontal = 10.dp),
                ) {
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn(tween(150)),
                        exit = fadeOut(tween(150)),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = SimpIcons.Check,
                                contentDescription = null,
                                tint = contentColor,
                                modifier = Modifier.size(18.dp),
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                    }
                    Text(
                        text = title,
                        color = contentColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ConnectedSegmentedGroupPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            var selected by remember { mutableStateOf(0) }
            ConnectedSegmentedGroup(
                segments = listOf("Your library", "Charts", "Playlists"),
                selectedIndex = selected,
                onSegmentSelected = { selected = it },
            )
        }
    }
}
