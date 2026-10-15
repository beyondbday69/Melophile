package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.maxrave.simpmusic.ui.icon.LibraryMusic
import com.maxrave.simpmusic.ui.icon.Pause
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.SimpIcons
import kotlinx.coroutines.launch
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

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

private fun lerpFloat(start: Float, stop: Float, fraction: Float): Float = start + (stop - start) * fraction

/**
 * Distance of an item's center from the hero center in screen coordinates.
 */
private fun getCenterOffset(diff: Float): Dp {
    val absDiff = kotlin.math.abs(diff)
    val sign = if (diff < 0) -1f else 1f
    val offset = when {
        absDiff <= 1f -> absDiff * 161f
        absDiff <= 2f -> 161f + (absDiff - 1f) * 86f
        absDiff <= 2.8f -> 247f + (absDiff - 2f) * 72f
        else -> 247f + 0.8f * 72f + (absDiff - 2.8f) * 60f
    }
    return (sign * offset).dp
}

/**
 * Item width based on its distance from the active hero position.
 * Hero is 214dp, adjacent items shrink to 92dp, outer items shrink to 64dp, fading edges shrink to 48dp.
 */
private fun getItemWidth(diff: Float): Dp {
    val absDiff = kotlin.math.abs(diff)
    return when {
        absDiff <= 1f -> lerpFloat(214f, 92f, absDiff).dp
        absDiff <= 2f -> lerpFloat(92f, 64f, absDiff - 1f).dp
        absDiff <= 2.8f -> lerpFloat(64f, 48f, (absDiff - 2f) / 0.8f).dp
        else -> 48.dp
    }
}

/**
 * Continuous edge-fade alpha: hero is 1.0f, adjacent items are dimmed to 0.48f,
 * outer items are 0.28f, and cards leaving the screen smoothly fade down to 0.0f
 * before exiting composition, eliminating abrupt culling or disappearing artifacts.
 */
private fun getCardAlpha(diff: Float): Float {
    val absDiff = kotlin.math.abs(diff)
    return when {
        absDiff <= 0.05f -> 1.0f
        absDiff <= 1.0f -> lerpFloat(1.0f, 0.48f, absDiff)
        absDiff <= 2.0f -> lerpFloat(0.48f, 0.28f, absDiff - 1f)
        absDiff <= 2.7f -> lerpFloat(0.28f, 0.0f, (absDiff - 2f) / 0.7f)
        else -> 0.0f
    }
}

/**
 * Expressive Carousel (Material 3 Expressive).
 *
 * - Height: 216dp, edge-to-edge.
 * - Hero item width 214dp, adjacent 92dp, outer 64dp.
 * - Real-time 1:1 finger tracking: cards follow user drag with zero deadzone and snap with smooth non-bouncy springs.
 * - Continuous edge fade: outer cards seamlessly fade to transparent before exiting the viewport.
 * - Keyed items: items retain state across reorders for butter-smooth morphing to #1 on play.
 * - Hero shows bottom gradient scrim (title 21sp bold, subtitle 14sp) and top-right 44dp play button.
 * - Below: page indicator dots (sliding 7-dot window when item count > 7, avoiding screen overflow).
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
    val coroutineScope = rememberCoroutineScope()
    val itemStepPx = with(density) { 161.dp.toPx() }

    // Non-bouncy spring specs (critically damped: zero overshoot, zero bouncy oscillation)
    val nonBouncySpringFloat = remember {
        spring<Float>(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        )
    }

    val animatableHeroFloat = remember { Animatable(activeHeroIndex.toFloat()) }

    // Synchronize animatable smoothly when activeHeroIndex changes from parent or selection
    LaunchedEffect(activeHeroIndex, items.size) {
        val maxIdx = (items.size - 1).coerceAtLeast(0).toFloat()
        val target = activeHeroIndex.toFloat().coerceIn(0f, maxIdx)
        if (animatableHeroFloat.targetValue != target) {
            animatableHeroFloat.animateTo(
                targetValue = target,
                animationSpec = nonBouncySpringFloat,
            )
        }
    }

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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(items.size) {
                        detectHorizontalDragGestures(
                            onDragStart = {
                                coroutineScope.launch { animatableHeroFloat.stop() }
                            },
                            onHorizontalDrag = { change, dragAmount ->
                                change.consume()
                                val deltaFloat = -dragAmount / itemStepPx
                                val currentVal = animatableHeroFloat.value
                                val maxIdx = (items.size - 1).coerceAtLeast(0).toFloat()
                                val newVal = (currentVal + deltaFloat).coerceIn(-0.35f, maxIdx + 0.35f)
                                coroutineScope.launch { animatableHeroFloat.snapTo(newVal) }
                            },
                            onDragEnd = {
                                val maxIdx = (items.size - 1).coerceAtLeast(0)
                                val targetIndex = animatableHeroFloat.value.roundToInt().coerceIn(0, maxIdx)
                                onHeroIndexChange(targetIndex)
                                coroutineScope.launch {
                                    animatableHeroFloat.animateTo(
                                        targetValue = targetIndex.toFloat(),
                                        animationSpec = nonBouncySpringFloat,
                                    )
                                }
                            },
                            onDragCancel = {
                                val maxIdx = (items.size - 1).coerceAtLeast(0)
                                val targetIndex = animatableHeroFloat.value.roundToInt().coerceIn(0, maxIdx)
                                onHeroIndexChange(targetIndex)
                                coroutineScope.launch {
                                    animatableHeroFloat.animateTo(
                                        targetValue = targetIndex.toFloat(),
                                        animationSpec = nonBouncySpringFloat,
                                    )
                                }
                            },
                        )
                    },
            ) {
                val currentFloatPos = animatableHeroFloat.value
                val minVisible = (floor(currentFloatPos) - 3).toInt().coerceAtLeast(0)
                val maxVisible = (ceil(currentFloatPos) + 3).toInt().coerceAtMost(items.size - 1)

                for (i in minVisible..maxVisible) {
                    val item = items[i]
                    key(item.id) {
                        val diff = i - currentFloatPos
                        val width = getItemWidth(diff)
                        val centerOffset = getCenterOffset(diff)
                        val cardCenterX = totalWidthDp / 2 + centerOffset
                        val cardStartX = cardCenterX - width / 2
                        val cardAlpha = getCardAlpha(diff)
                        val isHero = kotlin.math.abs(diff) < 0.5f
                        val isItemPlaying = isPlaying && item.id == playingItemId

                        Box(
                            modifier = Modifier
                                .offset(x = cardStartX)
                                .width(width)
                                .fillMaxHeight()
                                .zIndex(if (isHero) 2f else if (kotlin.math.abs(diff) <= 1f) 1f else 0f),
                        ) {
                            CarouselCard(
                                item = item,
                                cardAlpha = cardAlpha,
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
        }

        // Page Indicator Dots: sliding window when total items > 7 to keep layout within screen bounds
        if (items.size > 1) {
            val maxVisibleDots = 7
            val totalDots = items.size
            val showSlidingWindow = totalDots > maxVisibleDots
            val windowStart = if (showSlidingWindow) {
                (activeHeroIndex - 3).coerceIn(0, totalDots - maxVisibleDots)
            } else {
                0
            }
            val windowEnd = if (showSlidingWindow) windowStart + maxVisibleDots else totalDots

            val nonBouncyDotSpring = remember {
                spring<Dp>(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMediumLow,
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .height(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                for (i in windowStart until windowEnd) {
                    val isActive = i == activeHeroIndex
                    val isEdgeDot = showSlidingWindow && (
                        (i == windowStart && windowStart > 0) ||
                        (i == windowEnd - 1 && windowEnd < totalDots)
                    )
                    val targetDotWidth = when {
                        isActive -> 24.dp
                        isEdgeDot -> 4.dp
                        else -> 8.dp
                    }
                    val dotWidth by animateDpAsState(
                        targetValue = targetDotWidth,
                        animationSpec = nonBouncyDotSpring,
                        label = "dotWidth_$i",
                    )
                    val dotColor by animateColorAsState(
                        targetValue = if (isActive) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.outlineVariant
                        },
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
}

@Composable
private fun CarouselCard(
    item: ExpressiveCarouselItem,
    cardAlpha: Float,
    isHero: Boolean,
    isPlaying: Boolean,
    onClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
) {
    var isPressed by remember { mutableStateOf(false) }

    // Constant invariant rounded corners: 28dp across all states — does NOT morph when touched!
    val cardShape = remember { RoundedCornerShape(28.dp) }

    // Tactile press scale gives physical touch feedback without changing corner radius
    val pressScale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "pressScale",
    )


    val playBtnRadius by animateDpAsState(
        targetValue = if (isPlaying) 14.dp else 22.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "playBtnRadius",
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(cardShape)
            .graphicsLayer {
                alpha = cardAlpha
                scaleX = pressScale
                scaleY = pressScale
            }
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

        // Dimming overlay on side cards for enhanced focal depth and contrast
        if (!isHero) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.25f)),
            )
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
