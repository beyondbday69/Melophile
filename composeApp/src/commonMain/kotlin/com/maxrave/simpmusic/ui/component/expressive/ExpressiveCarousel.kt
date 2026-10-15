package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.maxrave.simpmusic.ui.icon.LibraryMusic
import com.maxrave.simpmusic.ui.icon.Pause
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.SimpIcons

/**
 * Data model for expressive carousel items.
 */
data class ExpressiveCarouselItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val imageUrl: String? = null,
    val gradientColors: Pair<Color, Color> = Color(0xFF4A8FB5) to Color(0xFF1B2C3A),
    val rawData: Any? = null,
)

/**
 * Expressive Carousel (Material 3 Expressive).
 *
 * - Height: 216dp, edge-to-edge.
 * - Hero width 214dp, adjacent 92dp, others 64dp.
 * - Hero is horizontally centered on screen.
 * - Widths and translation offset animate via spring(dampingRatio = 0.7f, stiffness = 380f).
 * - Hero shows bottom gradient scrim (title 21sp bold, subtitle 14sp) and top-right 44dp play button
 *   (radius 22dp paused, 14dp playing).
 * - Drag moves hero by one item per 46dp. Tap non-hero to center; tap hero to trigger action.
 * - Below: page indicator dots (8dp dots, active 24dp pill).
 */
@Composable
fun ExpressiveCarousel(
    modifier: Modifier = Modifier,
    items: List<ExpressiveCarouselItem>,
    activeHeroIndex: Int,
    isPlaying: Boolean = false,
    playingItemId: String? = null,
    onHeroIndexChange: (Int) -> Unit,
    onItemClick: (ExpressiveCarouselItem) -> Unit,
    onPlayPauseClick: (ExpressiveCarouselItem) -> Unit,
) {
    if (items.isEmpty()) return

    val density = LocalDensity.current
    val heroWidthDp = 214.dp
    val adjacentWidthDp = 92.dp
    val otherWidthDp = 64.dp
    val gapDp = 8.dp

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(216.dp),
        ) {
            val totalWidthDp = maxWidth

            // Horizontal drag gesture tracking
            var accumulatedDrag by remember { mutableStateOf(0f) }
            val dragThresholdPx = with(density) { 46.dp.toPx() }

            // Width of each item
            val itemWidths = items.mapIndexed { index, _ ->
                val targetWidth = when {
                    index == activeHeroIndex -> heroWidthDp
                    kotlin.math.abs(index - activeHeroIndex) == 1 -> adjacentWidthDp
                    else -> otherWidthDp
                }
                animateDpAsState(
                    targetValue = targetWidth,
                    animationSpec = expressiveSpring(),
                    label = "carouselItemWidth_$index",
                ).value
            }

            // Calculate offset of hero in strip and target translation to center hero
            var heroStartX = 0.dp
            for (i in 0 until activeHeroIndex) {
                heroStartX += itemWidths[i] + gapDp
            }
            val heroCenterInStrip = heroStartX + itemWidths[activeHeroIndex] / 2
            val targetTranslateX = (totalWidthDp / 2) - heroCenterInStrip

            val animatedTranslateX by animateFloatAsState(
                targetValue = with(density) { targetTranslateX.toPx() },
                animationSpec = expressiveSpring(),
                label = "carouselTranslateX",
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(items.size, activeHeroIndex) {
                        detectHorizontalDragGestures(
                            onDragStart = { accumulatedDrag = 0f },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                accumulatedDrag += dragAmount
                                if (accumulatedDrag > dragThresholdPx) {
                                    if (activeHeroIndex > 0) {
                                        onHeroIndexChange(activeHeroIndex - 1)
                                    }
                                    accumulatedDrag = 0f
                                } else if (accumulatedDrag < -dragThresholdPx) {
                                    if (activeHeroIndex < items.size - 1) {
                                        onHeroIndexChange(activeHeroIndex + 1)
                                    }
                                    accumulatedDrag = 0f
                                }
                            },
                            onDragEnd = { accumulatedDrag = 0f },
                            onDragCancel = { accumulatedDrag = 0f },
                        )
                    },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .graphicsLayer {
                            translationX = animatedTranslateX
                        },
                    horizontalArrangement = Arrangement.spacedBy(gapDp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items.forEachIndexed { i, item ->
                        val isHero = i == activeHeroIndex
                        val isItemPlaying = isPlaying && item.id == playingItemId
                        val width = itemWidths[i]

                        CarouselCard(
                            item = item,
                            width = width,
                            isHero = isHero,
                            isPlaying = isItemPlaying,
                            onClick = {
                                if (isHero) {
                                    onItemClick(item)
                                } else {
                                    onHeroIndexChange(i)
                                }
                            },
                            onPlayPauseClick = {
                                onPlayPauseClick(item)
                            },
                        )
                    }
                }
            }
        }

        // Page Indicator Dots
        Row(
            modifier = Modifier
                .padding(top = 14.dp)
                .height(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { i, _ ->
                val isActive = i == activeHeroIndex
                val dotWidth by animateDpAsState(
                    targetValue = if (isActive) 24.dp else 8.dp,
                    animationSpec = expressiveSpring(),
                    label = "dotWidth_$i",
                )
                val dotColor by animateColorAsState(
                    targetValue = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                    animationSpec = tween(150),
                    label = "dotColor_$i",
                )
                Box(
                    modifier = Modifier
                        .size(width = dotWidth, height = 8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(dotColor),
                )
            }
        }
    }
}

@Composable
private fun CarouselCard(
    item: ExpressiveCarouselItem,
    width: androidx.compose.ui.unit.Dp,
    isHero: Boolean,
    isPlaying: Boolean,
    onClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    val animatedRadius by animateDpAsState(
        targetValue = if (isPressed) 20.dp else 28.dp,
        animationSpec = expressiveSpring(),
        label = "cardRadius",
    )
    val cardShape = remember(animatedRadius) { RoundedCornerShape(animatedRadius) }

    val playBtnRadius by animateDpAsState(
        targetValue = if (isPlaying) 14.dp else 22.dp,
        animationSpec = expressiveSpring(),
        label = "playBtnRadius",
    )

    Box(
        modifier = Modifier
            .width(width)
            .fillMaxHeight()
            .clip(cardShape)
            .semantics { role = Role.Button }
            .pressFeedback(
                minDurationMs = 130L,
                onPressedChange = { isPressed = it },
                onClick = onClick,
            )
            .background(
                Brush.linearGradient(
                    colors = listOf(item.gradientColors.first, item.gradientColors.second),
                ),
            ),
    ) {
        // Thumbnail Image
        if (!item.imageUrl.isNullOrEmpty()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(item.imageUrl)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .diskCacheKey(item.imageUrl)
                    .build(),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = SimpIcons.LibraryMusic,
                    contentDescription = null,
                    tint = Color.White.copy(alpha = 0.35f),
                    modifier = Modifier.size(if (isHero) 56.dp else 28.dp),
                )
            }
        }

        // Overlay & controls only visible on hero
        AnimatedVisibility(
            visible = isHero,
            enter = fadeIn(tween(250)),
            exit = fadeOut(tween(150)),
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Top-right play/pause button
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(44.dp)
                        .clip(RoundedCornerShape(playBtnRadius))
                        .background(MaterialTheme.colorScheme.primary)
                        .pressFeedback(
                            minDurationMs = 130L,
                            onPressedChange = {},
                            onClick = onPlayPauseClick,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = if (isPlaying) SimpIcons.Pause else SimpIcons.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp),
                    )
                }

                // Bottom scrim with text
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xB8000000)),
                            ),
                        )
                        .padding(start = 16.dp, end = 16.dp, top = 44.dp, bottom = 14.dp),
                ) {
                    Column {
                        Text(
                            text = item.title,
                            color = Color.White,
                            fontSize = 21.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.3).sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = item.subtitle,
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ExpressiveCarouselPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            var activeIndex by remember { mutableStateOf(0) }
            val mockItems = listOf(
                ExpressiveCarouselItem(id = "1", title = "Let Down", subtitle = "Radiohead"),
                ExpressiveCarouselItem(id = "2", title = "Sailor Song", subtitle = "Gigi Perez"),
                ExpressiveCarouselItem(id = "3", title = "Beanie", subtitle = "Chezile"),
                ExpressiveCarouselItem(id = "4", title = "505", subtitle = "Arctic Monkeys"),
                ExpressiveCarouselItem(id = "5", title = "End of Beginning", subtitle = "Djo"),
            )
            ExpressiveCarousel(
                items = mockItems,
                activeHeroIndex = activeIndex,
                isPlaying = true,
                playingItemId = "1",
                onHeroIndexChange = { activeIndex = it },
                onItemClick = {},
                onPlayPauseClick = {},
            )
        }
    }
}
