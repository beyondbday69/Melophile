package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick as semanticsOnClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxrave.simpmusic.ui.icon.ArrowBack
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.DownloadForOffline
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.Search
import com.maxrave.simpmusic.ui.icon.Shuffle
import com.maxrave.simpmusic.ui.icon.SimpIcons
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.available_offline
import simpmusic.composeapp.generated.resources.back_to_library
import simpmusic.composeapp.generated.resources.downloaded
import simpmusic.composeapp.generated.resources.n_song
import simpmusic.composeapp.generated.resources.no_downloaded_songs
import simpmusic.composeapp.generated.resources.no_downloaded_songs_description
import simpmusic.composeapp.generated.resources.no_songs_match_search
import simpmusic.composeapp.generated.resources.no_songs_match_search_description
import simpmusic.composeapp.generated.resources.search_downloaded_songs

/**
 * Downloaded-library header built with the m3-morph vocabulary: solid tonal surfaces, a lobed
 * offline badge, a neighbor-reacting Play/Shuffle pair, and spring press feedback.
 */
@Composable
fun DownloadedMorphHeader(
    count: Int,
    isSearchActive: Boolean,
    autoFocusSearch: Boolean,
    onSearchFocusHandled: () -> Unit,
    query: String,
    onQueryChange: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onBack: () -> Unit,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MorphIconButton(
                icon = SimpIcons.ArrowBack,
                contentDescription = "Back",
                onClick = onBack,
            )
            MorphIconButton(
                icon = if (isSearchActive) SimpIcons.Close else SimpIcons.Search,
                contentDescription =
                    if (isSearchActive) {
                        "Close search"
                    } else {
                        stringResource(Res.string.search_downloaded_songs)
                    },
                onClick = onToggleSearch,
            )
        }

        when {
            isSearchActive ->
                DownloadedMorphSearchField(
                    query = query,
                    onQueryChange = onQueryChange,
                    autoFocus = autoFocusSearch,
                    onFocusHandled = onSearchFocusHandled,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp, end = 16.dp, bottom = 20.dp),
                )

            count > 0 ->
                DownloadedHero(
                    count = count,
                    onPlayAll = onPlayAll,
                    onShuffle = onShuffle,
                )

            else -> DownloadedCompactTitle(count = count)
        }
    }
}

@Composable
private fun DownloadedHero(
    count: Int,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(20.dp),
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ShapeIconContainer(
                    icon = SimpIcons.DownloadForOffline,
                    shape = LobedShape(8, 0.12f),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    size = 64.dp,
                    iconSize = 30.dp,
                )
                Spacer(Modifier.size(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(Res.string.downloaded),
                        style =
                            MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-0.5).sp,
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(Modifier.height(6.dp))
                    DownloadedStatusChip(count = count)
                }
            }
            Spacer(Modifier.height(18.dp))
            DownloadedActionGroup(
                count = count,
                onPlayAll = onPlayAll,
                onShuffle = onShuffle,
            )
        }
    }
}

@Composable
private fun DownloadedCompactTitle(count: Int) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 8.dp, end = 20.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = stringResource(Res.string.downloaded),
            style =
                MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.25).sp,
                ),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = pluralStringResource(Res.plurals.n_song, count, count),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun DownloadedStatusChip(count: Int) {
    val countText = pluralStringResource(Res.plurals.n_song, count, count)
    val label =
        if (count > 0) {
            "$countText · ${stringResource(Res.string.available_offline)}"
        } else {
            countText
        }
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun DownloadedActionGroup(
    count: Int,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
) {
    if (count <= 0) return

    var pressedIndex by remember { mutableStateOf<Int?>(null) }
    val weightSpec = spring<Float>(dampingRatio = 0.62f, stiffness = 500f)
    val playWeight by
        animateFloatAsState(
            targetValue =
                when (pressedIndex) {
                    0 -> 1.15f
                    1 -> 0.92f
                    else -> 1f
                },
            animationSpec = weightSpec,
            label = "downloadedPlayWeight",
        )
    val shuffleWeight by
        animateFloatAsState(
            targetValue =
                when (pressedIndex) {
                    1 -> 1.15f
                    0 -> 0.92f
                    else -> 1f
                },
            animationSpec = weightSpec,
            label = "downloadedShuffleWeight",
        )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MorphActionSurface(
            label = "Play",
            pressed = pressedIndex == 0,
            onPress = { pressedIndex = 0 },
            onRelease = { if (pressedIndex == 0) pressedIndex = null },
            onClick = onPlayAll,
            modifier = Modifier.weight(playWeight),
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            Icon(
                imageVector = SimpIcons.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(20.dp),
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Play",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary,
                maxLines = 1,
            )
        }
        MorphActionSurface(
            label = "Shuffle",
            pressed = pressedIndex == 1,
            onPress = { pressedIndex = 1 },
            onRelease = { if (pressedIndex == 1) pressedIndex = null },
            onClick = onShuffle,
            modifier = Modifier.weight(shuffleWeight),
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ) {
            Icon(
                imageVector = SimpIcons.Shuffle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(20.dp),
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Shuffle",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun DownloadedMorphSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    autoFocus: Boolean,
    onFocusHandled: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(autoFocus) {
        if (autoFocus) {
            focusRequester.requestFocus()
            keyboard?.show()
            onFocusHandled()
        }
    }

    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier =
            modifier
                .fillMaxWidth()
                .height(60.dp)
                .focusRequester(focusRequester),
        textStyle =
            MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { keyboard?.hide() }),
        decorationBox = { innerTextField ->
            Row(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(30.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                        .padding(start = 18.dp, end = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = SimpIcons.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(Modifier.size(12.dp))
                Box(modifier = Modifier.weight(1f)) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(Res.string.search_downloaded_songs),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    innerTextField()
                }
                if (query.isNotEmpty()) {
                    MorphIconButton(
                        icon = SimpIcons.Close,
                        contentDescription = "Clear search",
                        onClick = { onQueryChange("") },
                        size = 44.dp,
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        pressedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        pressedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        },
    )
}

@Composable
fun DownloadedCollapsedBar(
    count: Int,
    isSearchActive: Boolean,
    onBack: () -> Unit,
    onToggleSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .shadow(8.dp, RoundedCornerShape(32.dp))
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .padding(horizontal = 6.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        MorphIconButton(
            icon = SimpIcons.ArrowBack,
            contentDescription = "Back",
            onClick = onBack,
            size = 44.dp,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            pressedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            pressedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Spacer(Modifier.size(8.dp))
        ShapeIconContainer(
            icon = SimpIcons.DownloadForOffline,
            shape = LobedShape(8, 0.12f),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            size = 36.dp,
            iconSize = 20.dp,
        )
        Spacer(Modifier.size(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(Res.string.downloaded),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = pluralStringResource(Res.plurals.n_song, count, count),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
            )
        }
        MorphIconButton(
            icon = if (isSearchActive) SimpIcons.Close else SimpIcons.Search,
            contentDescription =
                if (isSearchActive) {
                    "Close search"
                } else {
                    stringResource(Res.string.search_downloaded_songs)
                },
            onClick = onToggleSearch,
            size = 44.dp,
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
            pressedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            pressedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Composable
fun DownloadedMorphEmptyState(
    isSearching: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ShapeIconContainer(
            icon = if (isSearching) SimpIcons.Search else SimpIcons.DownloadForOffline,
            shape = if (isSearching) LobedShape(9, 0.09f) else LobedShape(8, 0.12f),
            containerColor =
                if (isSearching) {
                    MaterialTheme.colorScheme.secondaryContainer
                } else {
                    MaterialTheme.colorScheme.primary
                },
            contentColor =
                if (isSearching) {
                    MaterialTheme.colorScheme.onSecondaryContainer
                } else {
                    MaterialTheme.colorScheme.onPrimary
                },
            size = 80.dp,
            iconSize = 36.dp,
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text =
                stringResource(
                    if (isSearching) Res.string.no_songs_match_search else Res.string.no_downloaded_songs,
                ),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text =
                stringResource(
                    if (isSearching) {
                        Res.string.no_songs_match_search_description
                    } else {
                        Res.string.no_downloaded_songs_description
                    },
                ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 320.dp),
        )
        if (!isSearching) {
            Spacer(Modifier.height(24.dp))
            MorphPillButton(
                label = stringResource(Res.string.back_to_library),
                icon = SimpIcons.ArrowBack,
                onClick = onBack,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun MorphPillButton(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
) {
    var pressed by remember { mutableStateOf(false) }
    MorphActionSurface(
        label = label,
        pressed = pressed,
        onPress = { pressed = true },
        onRelease = { pressed = false },
        onClick = onClick,
        modifier = modifier,
        containerColor = containerColor,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = contentColor,
            maxLines = 1,
        )
    }
}

@Composable
private fun MorphActionSurface(
    label: String,
    pressed: Boolean,
    onPress: () -> Unit,
    onRelease: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color,
    content: @Composable RowScope.() -> Unit,
) {
    val shapeRadius by
        animateDpAsState(
            targetValue = if (pressed) 14.dp else 26.dp,
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 480f),
            label = "downloadedActionRadius",
        )
    val scale by
        animateFloatAsState(
            targetValue = if (pressed) 0.96f else 1f,
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 480f),
            label = "downloadedActionScale",
        )
    val shape = RoundedCornerShape(shapeRadius)

    Row(
        modifier =
            modifier
                .height(52.dp)
                .scale(scale)
                .clip(shape)
                .background(containerColor)
                .morphPressable(
                    onPress = onPress,
                    onRelease = onRelease,
                    onClick = onClick,
                ).semantics {
                    role = Role.Button
                    semanticsOnClick(label = label) {
                        onClick()
                        true
                    }
                }.padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        content = content,
    )
}

@Composable
private fun MorphIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    pressedContainerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    pressedContentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
) {
    var pressed by remember { mutableStateOf(false) }
    val radius by
        animateDpAsState(
            targetValue = if (pressed) 12.dp else size / 2f,
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 500f),
            label = "morphIconRadius",
        )
    val scale by
        animateFloatAsState(
            targetValue = if (pressed) 0.9f else 1f,
            animationSpec = spring(dampingRatio = 0.7f, stiffness = 500f),
            label = "morphIconScale",
        )
    val background by
        animateColorAsState(
            targetValue = if (pressed) pressedContainerColor else containerColor,
            animationSpec = tween(150),
            label = "morphIconBackground",
        )
    val tint by
        animateColorAsState(
            targetValue = if (pressed) pressedContentColor else contentColor,
            animationSpec = tween(150),
            label = "morphIconTint",
        )

    Box(
        modifier =
            modifier
                .size(size)
                .scale(scale)
                .clip(RoundedCornerShape(radius))
                .background(background)
                .morphPressable(
                    onPress = { pressed = true },
                    onRelease = { pressed = false },
                    onClick = onClick,
                ).semantics {
                    role = Role.Button
                    semanticsOnClick(label = contentDescription) {
                        onClick()
                        true
                    }
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(size * 0.46f),
        )
    }
}

private fun Modifier.morphPressable(
    onPress: () -> Unit,
    onRelease: () -> Unit,
    onClick: () -> Unit,
): Modifier =
    this
        .onKeyEvent { event ->
            if (event.type == KeyEventType.KeyUp && (event.key == Key.Enter || event.key == Key.Spacebar)) {
                onClick()
                true
            } else {
                false
            }
        }.focusable()
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    onPress()
                    tryAwaitRelease()
                    delay(130L)
                    onRelease()
                },
                onTap = { onClick() },
            )
        }
