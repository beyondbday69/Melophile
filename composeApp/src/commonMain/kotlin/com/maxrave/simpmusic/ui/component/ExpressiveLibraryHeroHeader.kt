package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.simpmusic.ui.theme.typo

/**
 * Material 3 Expressive Library Hero Header:
 * Elevates the library from a cold file listing to an emotional "Sonic Sanctuary"
 * with Google Sans Flex typography, living pulse badge, and organic asymmetric curvature.
 */
@Composable
fun ExpressiveLibraryHeroHeader(
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = 0.62f,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "heroHeaderScale",
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 16.dp, bottomEnd = 24.dp, bottomStart = 20.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 2.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.35f),
                            MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.20f),
                            MaterialTheme.colorScheme.surfaceContainer,
                        ),
                    ),
                )
                .padding(horizontal = 18.dp, vertical = 16.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // Emotional living status pill
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.75f),
                        modifier = Modifier.clip(CircleShape),
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ExpressiveLiveEqualizerBars(
                                barColor = MaterialTheme.colorScheme.primary,
                                height = 12.dp,
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Sonic Sanctuary",
                                style = typo().labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    letterSpacing = 0.3.sp,
                                ),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }

                    // Playful beating heart micro-accent
                    ExpressiveHeartbeatIcon(
                        size = 20.dp,
                        tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Soundtrack of Your Journey",
                    style = typo().headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "Your memories, favorites, and heavy rotation — feeling alive in harmony.",
                    style = typo().bodyMedium.copy(
                        fontSize = 12.5.sp,
                        lineHeight = 17.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
