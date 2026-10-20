package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.maxrave.domain.data.entities.SongEntity
import com.maxrave.simpmusic.expect.ui.photoPickerResult
import com.maxrave.simpmusic.ui.icon.Add
import com.maxrave.simpmusic.ui.icon.AddPhotoAlternate
import com.maxrave.simpmusic.ui.icon.Album
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.Cookie
import com.maxrave.simpmusic.ui.icon.MoreVert
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.Star
import com.maxrave.simpmusic.ui.icon.Tune
import kotlinx.coroutines.launch

/**
 * Material 3 Expressive Shape presets for playlist covers.
 */
enum class MaterialShapePreset(
    val id: String,
    val displayName: String,
    val subtitle: String,
) {
    COOKIE("cookie", "Cookie", "9-lobe"),
    FLOWER("flower", "Flower", "6-lobe"),
    STAR("star", "Star", "8-lobe"),
    CLOVER("clover", "Clover", "4-lobe"),
    SUNBURST("sunburst", "Sunburst", "12-lobe"),
    SQUIRCLE("squircle", "Squircle", "Smooth"),
    ARCH("arch", "Arch", "Pill"),
    CIRCLE("circle", "Circle", "Round"),
    DIAMOND("diamond", "Diamond", "Cut corner");

    val radiusTable: FloatArray
        get() = when (this) {
            COOKIE -> ShapeTables.CookieRadiusTable
            FLOWER -> ShapeTables.FlowerRadiusTable
            STAR -> ShapeTables.StarRadiusTable
            CLOVER -> ShapeTables.CloverRadiusTable
            SUNBURST -> ShapeTables.SunburstRadiusTable
            SQUIRCLE -> ShapeTables.SquircleRadiusTable
            ARCH -> ShapeTables.ArchRadiusTable
            CIRCLE -> ShapeTables.CircleRadiusTable
            DIAMOND -> ShapeTables.DiamondRadiusTable
        }

    fun getShape(sizeDp: Dp = 64.dp): Shape {
        return LobedShape(radiusTable)
    }

    companion object {
        fun fromId(id: String?): MaterialShapePreset {
            return entries.firstOrNull { it.id == id } ?: COOKIE
        }
    }
}

/**
 * Remembers a smooth, continuous morphing Shape that animates between Material 3 shape presets.
 * When [targetPreset] changes, the shape vertices smoothly flow and morph into the new shape
 * with spring physics rather than snapping instantly.
 */
@Composable
fun rememberMorphingShape(
    targetPreset: MaterialShapePreset,
    animationSpec: AnimationSpec<Float> = spring(
        // Critically damped: the shape settles into place without bouncing past
        // the target outline, which reads as a snap on a small cover.
        dampingRatio = 1f,
        stiffness = 380f,
    ),
): Shape {
    val progress = remember { Animatable(1f) }
    // The table we are morphing *from* and the preset we are morphing *to*.
    // Keeping the destination in state (instead of reading targetPreset
    // directly) means the frame in which the target changes still renders the
    // old shape; the animation can then start from it without a one-frame pop.
    var morphState by remember {
        mutableStateOf(
            ShapeMorphState(
                fromTable = targetPreset.radiusTable,
                toPreset = targetPreset,
            ),
        )
    }

    LaunchedEffect(targetPreset) {
        if (morphState.toPreset == targetPreset) return@LaunchedEffect

        val inFlight = progress.value
        // If another morph is already running, start the new one from the
        // exact outline currently on screen instead of from its destination.
        val fromTable =
            if (inFlight >= 1f) {
                morphState.toPreset.radiusTable
            } else {
                ShapeTables.lerpTables(
                    morphState.fromTable,
                    morphState.toPreset.radiusTable,
                    inFlight,
                )
            }

        // Both writes happen before animateTo suspends, so the first animated
        // frame is the old outline at progress 0 — no visible jump.
        progress.snapTo(0f)
        morphState = ShapeMorphState(fromTable = fromTable, toPreset = targetPreset)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = animationSpec,
        )
    }

    val state = morphState
    val animationProgress = progress.value
    return remember(state, animationProgress) {
        if (animationProgress >= 1f) {
            LobedShape(state.toPreset.radiusTable)
        } else {
            LobedShape(
                ShapeTables.lerpTables(
                    state.fromTable,
                    state.toPreset.radiusTable,
                    animationProgress,
                ),
            )
        }
    }
}

private data class ShapeMorphState(
    val fromTable: FloatArray,
    val toPreset: MaterialShapePreset,
)

/**
 * Vibrant Material 3 Expressive color presets for playlist covers.
 */
val ExpressiveCoverColors = listOf(
    Color(0xFF6750A4), // M3 Primary Iris
    Color(0xFF1E88E5), // Cobalt Blue
    Color(0xFF00897B), // Rich Teal
    Color(0xFF43A047), // Jade Green
    Color(0xFFE53935), // Crimson Red
    Color(0xFFFB8C00), // Tangerine Amber
    Color(0xFFD81B60), // Vivid Pink
    Color(0xFF8E24AA), // Deep Violet
    Color(0xFF3949AB), // Indigo
    Color(0xFF00ACC1), // Cyan
)

/**
 * Expressive Playlist Cover: dynamically clips artwork (from song or photo) into a
 * Material 3 shape with background color/gradient framing.
 * Smoothly morphs shape and cross-fades container color when presets change.
 */
@Composable
fun ExpressivePlaylistCover(
    thumbnailUrl: String?,
    shapePreset: MaterialShapePreset,
    containerColor: Color,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    title: String = "",
) {
    val animatedShape = rememberMorphingShape(targetPreset = shapePreset)
    val animatedBgColor by animateColorAsState(
        targetValue = containerColor,
        animationSpec = tween(durationMillis = 300),
        label = "coverContainerColor",
    )
    ExpressivePlaylistCover(
        thumbnailUrl = thumbnailUrl,
        shape = animatedShape,
        containerColor = animatedBgColor,
        modifier = modifier,
        size = size,
        title = title,
    )
}

@Composable
fun ExpressivePlaylistCover(
    thumbnailUrl: String?,
    shape: Shape,
    containerColor: Color,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    title: String = "",
) {
    val platformContext = LocalPlatformContext.current
    // Rebuilding the request on every morph frame re-allocated it 60 times a
    // second and could make Coil re-evaluate the load mid-animation.
    val imageRequest =
        remember(platformContext, thumbnailUrl) {
            ImageRequest.Builder(platformContext)
                .data(thumbnailUrl)
                .diskCachePolicy(CachePolicy.ENABLED)
                .crossfade(true)
                .build()
        }
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(containerColor),
        contentAlignment = Alignment.Center,
    ) {
        if (!thumbnailUrl.isNullOrEmpty()) {
            AsyncImage(
                model = imageRequest,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                // The parent Box already clips every child to the animated
                // shape. Clipping again here created a second graphics layer
                // that had to be rebuilt on every morph frame.
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(
                                containerColor,
                                containerColor.copy(alpha = 0.65f),
                            ),
                        ),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                if (title.isNotBlank()) {
                    Text(
                        text = title.take(1).uppercase(),
                        color = Color.White,
                        fontSize = (size.value * 0.42f).sp,
                        fontWeight = FontWeight.Bold,
                    )
                } else {
                    Icon(
                        imageVector = SimpIcons.Album,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.size(size * 0.45f),
                    )
                }
            }
        }
    }
}

/**
 * Expressive Playlist Card for the Library screen.
 * Displays the customized Material 3 shape cover, title, metadata, and quick actions.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpressivePlaylistCard(
    title: String,
    subtitle: String,
    thumbnailUrl: String?,
    shapePreset: MaterialShapePreset,
    containerColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    coverModifier: Modifier = Modifier,
    tag: String? = null,
    onCustomize: (() -> Unit)? = null,
    onPlay: (() -> Unit)? = null,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onCustomize,
            ),
        shape = RoundedCornerShape(22.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        tonalElevation = 2.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            ExpressivePlaylistCover(
                thumbnailUrl = thumbnailUrl,
                shapePreset = shapePreset,
                containerColor = containerColor,
                modifier = coverModifier,
                size = 72.dp,
                title = title,
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.2).sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    if (tag != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(
                                    if (tag == "Local") MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f)
                                    else MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp),
                        ) {
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = if (tag == "Local") MaterialTheme.colorScheme.onPrimaryContainer
                                else MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 10.sp,
                            )
                        }
                    }
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (onCustomize != null) {
                    IconButton(
                        onClick = onCustomize,
                        modifier = Modifier.size(38.dp),
                    ) {
                        Icon(
                            imageVector = SimpIcons.Tune,
                            contentDescription = "Customize playlist cover",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }

                if (onPlay != null) {
                    IconButton(
                        onClick = onPlay,
                        modifier = Modifier.size(38.dp),
                    ) {
                        Icon(
                            imageVector = SimpIcons.PlayArrow,
                            contentDescription = "Play playlist",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }
            }
        }
    }
}

/**
 * Expressive Card for creating a new playlist.
 */
@Composable
fun ExpressiveCreatePlaylistCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        tonalElevation = 1.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialShapePreset.CLOVER.getShape(56.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = SimpIcons.Add,
                    contentDescription = "Create playlist",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(28.dp),
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Create Playlist",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = "New list with Material 3 shape cover",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/**
 * Playlist Cover Customizer Sheet.
 * Provides a live interactive studio to configure:
 * 1. Material 3 Shape (Cookie, Flower, Star, Clover, Squircle, Arch, Circle, Diamond)
 * 2. Color Tone Preset
 * 3. Cover Image (Pick from songs in playlist, custom photo, or reset to gradient)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistCoverCustomizerSheet(
    playlistTitle: String,
    currentThumbnail: String?,
    initialShape: MaterialShapePreset,
    initialColor: Color,
    onDismiss: () -> Unit,
    getTracks: suspend () -> List<SongEntity>,
    onSave: (shapeId: String, colorHex: String, newThumbnail: String?) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    var selectedShape by remember { mutableStateOf(initialShape) }
    var selectedColor by remember { mutableStateOf(initialColor) }
    var selectedThumbnail by remember { mutableStateOf(currentThumbnail) }

    var playlistSongs by remember { mutableStateOf<List<SongEntity>>(emptyList()) }
    var isLoadingSongs by remember { mutableStateOf(true) }

    val photoPickerLauncher = photoPickerResult { uri ->
        if (uri != null) {
            selectedThumbnail = uri
        }
    }

    LaunchedEffect(Unit) {
        isLoadingSongs = true
        try {
            playlistSongs = getTracks()
        } catch (_: Exception) {
            playlistSongs = emptyList()
        } finally {
            isLoadingSongs = false
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Drag handle pill
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.outlineVariant),
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Sheet Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Customize Cover",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onDismiss()
                        }
                    },
                    modifier = Modifier.size(32.dp),
                ) {
                    Icon(
                        imageVector = SimpIcons.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 1. Live Preview Hero (Material 3 Shape + Image + Color)
            Card(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(28.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    ExpressivePlaylistCover(
                        thumbnailUrl = selectedThumbnail,
                        shapePreset = selectedShape,
                        containerColor = selectedColor,
                        size = 120.dp,
                        title = playlistTitle,
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = playlistTitle,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(18.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Section: Material 3 Shapes
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Material 3 Shape",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp),
                        ) {
                            items(MaterialShapePreset.entries) { preset ->
                                val isSelected = preset == selectedShape
                                Surface(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(14.dp))
                                        .clickable { selectedShape = preset },
                                    shape = RoundedCornerShape(14.dp),
                                    color = if (isSelected) {
                                        MaterialTheme.colorScheme.primaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.surfaceContainer
                                    },
                                    border = if (isSelected) {
                                        androidx.compose.foundation.BorderStroke(
                                            2.dp,
                                            MaterialTheme.colorScheme.primary,
                                        )
                                    } else null,
                                ) {
                                    Column(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .clip(preset.getShape(36.dp))
                                                .background(
                                                    if (isSelected) {
                                                        MaterialTheme.colorScheme.primary
                                                    } else {
                                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                                    },
                                                ),
                                        )
                                        Text(
                                            text = preset.displayName,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (isSelected) {
                                                MaterialTheme.colorScheme.onPrimaryContainer
                                            } else {
                                                MaterialTheme.colorScheme.onSurface
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Section: Color Palette
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = "Accent Color",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary,
                        )
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            contentPadding = PaddingValues(horizontal = 2.dp),
                        ) {
                            items(ExpressiveCoverColors) { color ->
                                val isSelected = color == selectedColor
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .clickable { selectedColor = color }
                                        .then(
                                            if (isSelected) {
                                                Modifier.border(2.dp, MaterialTheme.colorScheme.onSurface, CircleShape)
                                            } else Modifier,
                                        ),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    if (isSelected) {
                                        Icon(
                                            imageVector = SimpIcons.Check,
                                            contentDescription = "Selected",
                                            tint = Color.White,
                                            modifier = Modifier.size(20.dp),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Section: Cover Artwork from Song
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Cover Image (From Songs)",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary,
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                TextButton(
                                    onClick = { photoPickerLauncher.launch() },
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                ) {
                                    Icon(
                                        imageVector = SimpIcons.AddPhotoAlternate,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Photo", style = MaterialTheme.typography.labelSmall)
                                }

                                if (!selectedThumbnail.isNullOrEmpty()) {
                                    TextButton(
                                        onClick = { selectedThumbnail = null },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                    ) {
                                        Text("Reset", style = MaterialTheme.typography.labelSmall)
                                    }
                                }
                            }
                        }

                        if (isLoadingSongs) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp))
                            }
                        } else if (playlistSongs.isEmpty()) {
                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.surfaceContainer,
                            ) {
                                Text(
                                    text = "No songs in this playlist yet. Add songs to set their artwork as the cover, or choose a photo above.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(12.dp),
                                )
                            }
                        } else {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(horizontal = 2.dp),
                            ) {
                                items(playlistSongs) { song ->
                                    val isSelectedSong = song.thumbnails == selectedThumbnail
                                    val platformContext = LocalPlatformContext.current
                                    val songImageRequest =
                                        remember(platformContext, song.thumbnails) {
                                            ImageRequest.Builder(platformContext)
                                                .data(song.thumbnails)
                                                .diskCachePolicy(CachePolicy.ENABLED)
                                                .build()
                                        }
                                    Surface(
                                        modifier = Modifier
                                            .width(110.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable { selectedThumbnail = song.thumbnails },
                                        shape = RoundedCornerShape(12.dp),
                                        color = if (isSelectedSong) {
                                            MaterialTheme.colorScheme.primaryContainer
                                        } else {
                                            MaterialTheme.colorScheme.surfaceContainer
                                        },
                                        border = if (isSelectedSong) {
                                            androidx.compose.foundation.BorderStroke(
                                                2.dp,
                                                MaterialTheme.colorScheme.primary,
                                            )
                                        } else null,
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(8.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(54.dp)
                                                    .clip(RoundedCornerShape(8.dp)),
                                                contentAlignment = Alignment.Center,
                                            ) {
                                                AsyncImage(
                                                    model = songImageRequest,
                                                    contentDescription = null,
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier.fillMaxSize(),
                                                )
                                                if (isSelectedSong) {
                                                    Box(
                                                        modifier = Modifier
                                                            .fillMaxSize()
                                                            .background(Color.Black.copy(alpha = 0.35f)),
                                                        contentAlignment = Alignment.Center,
                                                    ) {
                                                        Icon(
                                                            imageVector = SimpIcons.Check,
                                                            contentDescription = "Selected",
                                                            tint = Color.White,
                                                            modifier = Modifier.size(24.dp),
                                                        )
                                                    }
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(4.dp))
                                            Text(
                                                text = song.title,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                OutlinedButton(
                    onClick = {
                        coroutineScope.launch {
                            sheetState.hide()
                            onDismiss()
                        }
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            val colorHex = selectedColor.value.toLong().toString(16)
                            onSave(selectedShape.id, colorHex, selectedThumbnail)
                            sheetState.hide()
                        }
                    },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Save Cover")
                }
            }
        }
    }
}
