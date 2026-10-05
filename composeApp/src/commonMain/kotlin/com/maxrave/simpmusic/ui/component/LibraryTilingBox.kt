package com.maxrave.simpmusic.ui.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.maxrave.simpmusic.extension.NonLazyGrid
import com.maxrave.simpmusic.ui.icon.Downloading
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.Insights
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.TrendingUp
import com.maxrave.simpmusic.ui.navigation.destination.library.LibraryDynamicPlaylistDestination
import com.maxrave.simpmusic.ui.screen.library.LibraryDynamicPlaylistType
import com.maxrave.simpmusic.ui.theme.typo
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.downloaded
import simpmusic.composeapp.generated.resources.favorite
import simpmusic.composeapp.generated.resources.followed
import simpmusic.composeapp.generated.resources.most_played

/**
 * Material 3 Expressive Library Tiling Box:
 * Features organic asymmetrical corner variability ("randomens"), animated emotional icons
 * (heartbeat, spinning vinyl, bobbing download, pulsing radar), fluid spring overshoot physics,
 * and warm tonal gradients that make the user's music data feel alive and emotional.
 */
@Composable
fun LibraryTilingBox(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        LibraryTilingItemData(
            state = LibraryTilingState.Favorite,
            subtitle = "From the heart",
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 12.dp, bottomEnd = 24.dp, bottomStart = 16.dp),
            gradientColors = listOf(
                Color(0xFFFF5277).copy(alpha = 0.22f),
                MaterialTheme.colorScheme.surfaceContainerHigh,
            ),
            accentColor = Color(0xFFFF3366),
            animatedIcon = { ExpressiveHeartbeatIcon(size = 32.dp, tint = Color(0xFFFF3366)) },
        ),
        LibraryTilingItemData(
            state = LibraryTilingState.Followed,
            subtitle = "Curated artists",
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 26.dp, bottomEnd = 14.dp, bottomStart = 28.dp),
            gradientColors = listOf(
                Color(0xFFFFB74D).copy(alpha = 0.20f),
                MaterialTheme.colorScheme.surfaceContainerHigh,
            ),
            accentColor = Color(0xFFFF9800),
            animatedIcon = { ExpressiveRadarIcon(size = 32.dp, tint = Color(0xFFFF9800)) },
        ),
        LibraryTilingItemData(
            state = LibraryTilingState.MostPlayed,
            subtitle = "Heavy rotation",
            shape = RoundedCornerShape(topStart = 26.dp, topEnd = 14.dp, bottomEnd = 28.dp, bottomStart = 12.dp),
            gradientColors = listOf(
                Color(0xFF00E5FF).copy(alpha = 0.18f),
                MaterialTheme.colorScheme.surfaceContainerHigh,
            ),
            accentColor = Color(0xFF00B4D8),
            animatedIcon = {
                ExpressiveVinylIcon(
                    size = 32.dp,
                    labelColor = Color(0xFF00B4D8),
                )
            },
        ),
        LibraryTilingItemData(
            state = LibraryTilingState.Downloaded,
            subtitle = "Offline sanctuary",
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 28.dp, bottomEnd = 16.dp, bottomStart = 24.dp),
            gradientColors = listOf(
                Color(0xFF00E676).copy(alpha = 0.18f),
                MaterialTheme.colorScheme.surfaceContainerHigh,
            ),
            accentColor = Color(0xFF10B981),
            animatedIcon = { ExpressiveFloatingDownloadIcon(size = 32.dp, tint = Color(0xFF10B981)) },
        ),
    )

    NonLazyGrid(
        columns = 2,
        itemCount = 4,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 6.dp),
    ) { number ->
        Box(
            Modifier.padding(5.dp),
        ) {
            val item = items[number]
            ExpressiveLibraryTilingCard(
                data = item,
                onClick = {
                    when (item.state) {
                        LibraryTilingState.Favorite -> {
                            navController.navigate(
                                LibraryDynamicPlaylistDestination(
                                    type = LibraryDynamicPlaylistType.Favorite.toStringParams(),
                                ),
                            )
                        }

                        LibraryTilingState.Followed -> {
                            navController.navigate(
                                LibraryDynamicPlaylistDestination(
                                    type = LibraryDynamicPlaylistType.Followed.toStringParams(),
                                ),
                            )
                        }

                        LibraryTilingState.MostPlayed -> {
                            navController.navigate(
                                LibraryDynamicPlaylistDestination(
                                    type = LibraryDynamicPlaylistType.MostPlayed.toStringParams(),
                                ),
                            )
                        }

                        LibraryTilingState.Downloaded -> {
                            navController.navigate(
                                LibraryDynamicPlaylistDestination(
                                    type = LibraryDynamicPlaylistType.Downloaded.toStringParams(),
                                ),
                            )
                        }
                    }
                },
            )
        }
    }
}

private data class LibraryTilingItemData(
    val state: LibraryTilingState,
    val subtitle: String,
    val shape: Shape,
    val gradientColors: List<Color>,
    val accentColor: Color,
    val animatedIcon: @Composable () -> Unit,
)

@Composable
private fun ExpressiveLibraryTilingCard(
    data: LibraryTilingItemData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Tactile overshoot spring physics on touch
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.93f else 1f,
        animationSpec = spring(
            dampingRatio = 0.62f,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "tilingCardScale",
    )

    val title = stringResource(data.state.title)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(data.shape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        shape = data.shape,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp, pressedElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = data.gradientColors,
                    ),
                )
                .padding(14.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(data.accentColor.copy(alpha = 0.16f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        data.animatedIcon()
                    }

                    // Subtle organic pulse dot indicating live connectivity
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(RoundedCornerShape(50))
                            .background(data.accentColor.copy(alpha = 0.6f)),
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = title,
                    style = typo().titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.2).sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                )

                Text(
                    text = data.subtitle,
                    style = typo().bodySmall.copy(
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                )
            }
        }
    }
}

/**
 * Kept for binary/source compatibility with any legacy callers.
 */
@Composable
fun LibraryTilingItem(
    state: LibraryTilingState,
    onClick: () -> Unit = {},
) {
    val title = stringResource(state.title)
    Card(
        modifier =
            Modifier.fillMaxWidth().clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors =
            CardDefaults.elevatedCardColors().copy(
                containerColor = state.containerColor,
            ),
    ) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                state.icon,
                contentDescription = title,
                modifier =
                    Modifier
                        .size(50.dp)
                        .padding(10.dp),
                tint = state.iconColor,
            )
            Text(
                title,
                style = typo().titleSmall,
                color = Color.Black,
            )
        }
    }
}

data class LibraryTilingState(
    val title: StringResource,
    val containerColor: Color,
    val icon: ImageVector,
    val iconColor: Color,
) {
    companion object {
        val Favorite =
            LibraryTilingState(
                title = Res.string.favorite,
                containerColor = Color(0xffff99ae),
                icon = SimpIcons.Favorite,
                iconColor = Color(0xffD10000),
            )
        val Followed =
            LibraryTilingState(
                title = Res.string.followed,
                containerColor = Color(0xffFFEB3B),
                icon = SimpIcons.Insights,
                iconColor = Color.Black,
            )
        val MostPlayed =
            LibraryTilingState(
                title = Res.string.most_played,
                containerColor = Color(0xff00BCD4),
                icon = SimpIcons.TrendingUp,
                iconColor = Color.Black,
            )
        val Downloaded =
            LibraryTilingState(
                title = Res.string.downloaded,
                containerColor = Color(0xff4CAF50),
                icon = SimpIcons.Downloading,
                iconColor = Color.Black,
            )
    }
}