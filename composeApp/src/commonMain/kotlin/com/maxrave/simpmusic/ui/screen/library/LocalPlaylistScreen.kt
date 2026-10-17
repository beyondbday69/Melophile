package com.maxrave.simpmusic.ui.screen.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.maxrave.simpmusic.ui.component.DownloadingIndicator
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCoverColors
import com.maxrave.simpmusic.ui.component.expressive.ExpressivePlaylistCover
import com.maxrave.simpmusic.ui.component.expressive.MaterialShapePreset
import com.maxrave.simpmusic.ui.component.expressive.PlaylistCoverCustomizerSheet
import com.maxrave.simpmusic.ui.icon.Search
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.Tune
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.LocalTextStyle
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.toBitmap
import com.kyant.backdrop.highlight.Highlight
import com.kmpalette.rememberPaletteState
import com.maxrave.common.LOCAL_PLAYLIST_ID
import com.maxrave.domain.data.entities.DownloadState
import com.maxrave.domain.data.entities.LocalPlaylistEntity
import com.maxrave.domain.data.entities.PairSongLocalPlaylist
import com.maxrave.domain.data.entities.SongEntity
import com.maxrave.domain.utils.FilterState
import com.maxrave.domain.utils.toTrack
import com.maxrave.logger.Logger
import com.maxrave.simpmusic.Platform
import com.maxrave.simpmusic.expect.ui.layerBackdrop
import com.maxrave.simpmusic.expect.ui.rememberBackdrop
import com.maxrave.simpmusic.expect.ui.toImageBitmap
import com.maxrave.simpmusic.extension.artworkScrimBrush
import com.maxrave.simpmusic.extension.displayNameRes
import com.maxrave.simpmusic.extension.getColorFromPalette
import com.maxrave.simpmusic.extension.getScreenSizeInfo
import com.maxrave.simpmusic.extension.toImmersiveBackground
import com.maxrave.simpmusic.getPlatform
import com.maxrave.simpmusic.ui.component.SearchBarExit
import com.maxrave.simpmusic.ui.component.SearchBarEnter
import com.maxrave.simpmusic.ui.component.AddToPlaylistModalBottomSheet
import com.maxrave.simpmusic.ui.component.CenterLoadingBox
import com.maxrave.simpmusic.ui.component.rememberSurfaceDarkColors
import com.maxrave.simpmusic.ui.component.DraggableItem
import com.maxrave.simpmusic.ui.component.EndOfPage
import com.maxrave.simpmusic.ui.component.LiquidGlassIconButton
import com.maxrave.simpmusic.ui.component.LoadingDialog
import com.maxrave.simpmusic.ui.component.LocalPlaylistBottomSheet
import com.maxrave.simpmusic.ui.component.NowPlayingBottomSheet
import com.maxrave.simpmusic.ui.component.RippleIconButton
import com.maxrave.simpmusic.ui.component.SongFullWidthItems
import com.maxrave.simpmusic.ui.component.selection.SelectedSongsBottomSheet
import com.maxrave.simpmusic.ui.component.selection.SongSelectionAction
import com.maxrave.simpmusic.ui.component.selection.SongSelectionTopAppBar
import com.maxrave.simpmusic.ui.component.selection.rememberSongSelectionState
import com.maxrave.simpmusic.ui.component.SortPlaylistBottomSheet
import com.maxrave.simpmusic.ui.component.SuggestItems
import com.maxrave.simpmusic.ui.component.liquidGlass
import com.maxrave.simpmusic.ui.component.painterPlaylistThumbnail
import com.maxrave.simpmusic.ui.component.playlistTitleGradient
import com.maxrave.simpmusic.ui.component.rememberDragDropState
import com.maxrave.simpmusic.ui.icon.ArrowBackIosNew
import com.maxrave.simpmusic.ui.icon.Delete
import com.maxrave.simpmusic.ui.icon.DownloadForOffline
import com.maxrave.simpmusic.ui.icon.MoreVert
import com.maxrave.simpmusic.ui.icon.Pause
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.Shuffle
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.Sort
import com.maxrave.simpmusic.ui.icon.TipsAndUpdates
import com.maxrave.simpmusic.ui.theme.LocalIsDarkTheme
import com.maxrave.simpmusic.ui.theme.seed
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.viewModel.LocalPlaylistUIEvent
import com.maxrave.simpmusic.viewModel.LocalPlaylistViewModel
import com.maxrave.simpmusic.viewModel.SharedViewModel
import com.maxrave.simpmusic.viewModel.SongSelectionViewModel
import com.maxrave.simpmusic.viewModel.UIEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.album_length
import simpmusic.composeapp.generated.resources.baseline_downloaded
import simpmusic.composeapp.generated.resources.cancel
import simpmusic.composeapp.generated.resources.created_at
import simpmusic.composeapp.generated.resources.downloaded
import simpmusic.composeapp.generated.resources.downloading
import simpmusic.composeapp.generated.resources.reload
import simpmusic.composeapp.generated.resources.remove_from_playlist
import simpmusic.composeapp.generated.resources.sort_by
import simpmusic.composeapp.generated.resources.suggest
import simpmusic.composeapp.generated.resources.sync_playlist_warning
import simpmusic.composeapp.generated.resources.synced_playlist_cannot_change_order
import simpmusic.composeapp.generated.resources.unsync_playlist_warning
import simpmusic.composeapp.generated.resources.warning
import simpmusic.composeapp.generated.resources.yes
import simpmusic.composeapp.generated.resources.your_playlist
import simpmusic.composeapp.generated.resources.search

private const val TAG = "LocalPlaylistScreen"

@ExperimentalFoundationApi
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalCoroutinesApi::class,
)
@Composable
fun LocalPlaylistScreen(
    id: Long,
    sharedViewModel: SharedViewModel = koinInject(),
    viewModel: LocalPlaylistViewModel = koinViewModel(),
    navController: NavController,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val aiPainter = rememberVectorPainter(SimpIcons.TipsAndUpdates)
    val limit = 1.5f
    val transition = rememberInfiniteTransition(label = "shimmer")
    val progressAnimated by transition.animateFloat(
        initialValue = -limit,
        targetValue = limit,
        animationSpec =
            infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "shimmer",
    )
    val infiniteTransition = rememberInfiniteTransition(label = "rotation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        label = "rotation",
    )

    val lazyState = rememberLazyListState()
    val firstItemVisible by remember {
        derivedStateOf {
            lazyState.firstVisibleItemIndex == 0
        }
    }
    val downloadState by viewModel.uiState.map { it.downloadState }.collectAsState(
        initial = DownloadState.STATE_NOT_DOWNLOADED,
    )
    var shouldHideTopBar by rememberSaveable { mutableStateOf(false) }
    var shouldShowSuggestions by rememberSaveable { mutableStateOf(false) }

    val selectionState = rememberSongSelectionState()
    val selectionViewModel: SongSelectionViewModel = koinViewModel()
    var showSelectionSheet by rememberSaveable { mutableStateOf(false) }
    var showSelectionAddToPlaylist by rememberSaveable { mutableStateOf(false) }
    var shouldShowSuggestButton by rememberSaveable { mutableStateOf(false) }

    val playingTrack by sharedViewModel.nowPlayingState
        .mapLatest {
            it?.songEntity
        }.collectAsState(initial = null)
    val isPlaying by sharedViewModel.controllerState.map { it.isPlaying }.collectAsState(initial = false)

    val queueData by sharedViewModel.getQueueDataState().collectAsStateWithLifecycle()
    val playingPlaylistId by remember {
        derivedStateOf {
            queueData?.data?.playlistId
        }
    }

    val suggestedTracks by viewModel.uiState.map { it.suggestions?.songs ?: emptyList() }.collectAsState(initial = emptyList())
    val suggestionsLoading by viewModel.loading.collectAsStateWithLifecycle()
    var showSyncAlertDialog by rememberSaveable { mutableStateOf(false) }
    var showUnsyncAlertDialog by rememberSaveable { mutableStateOf(false) }
    var firstTimeGetLocalPlaylist by rememberSaveable {
        mutableStateOf(false)
    }

    var showCoverCustomizer by rememberSaveable { mutableStateOf(false) }
    val shapeIdFlow = remember(uiState.id) { viewModel.getPlaylistShapeFlow(uiState.id.toString()) }
    val colorHexFlow = remember(uiState.id) { viewModel.getPlaylistColorFlow(uiState.id.toString()) }
    val savedShapeId by shapeIdFlow.collectAsStateWithLifecycle(null)
    val savedColorHex by colorHexFlow.collectAsStateWithLifecycle(null)

    val defaultShape = remember(uiState.id) {
        val idx = (uiState.id % MaterialShapePreset.entries.size).toInt().coerceAtLeast(0)
        MaterialShapePreset.entries[idx]
    }
    val defaultColor = remember(uiState.id) {
        val idx = (uiState.id % ExpressiveCoverColors.size).toInt().coerceAtLeast(0)
        ExpressiveCoverColors[idx]
    }
    val currentShape = savedShapeId?.let { MaterialShapePreset.fromId(it) } ?: defaultShape
    val currentCoverColor = savedColorHex?.let { hex ->
        try { Color(hex.toLong(16).toULong()) } catch (_: Exception) { null }
    } ?: defaultColor

    var currentItem by remember {
        mutableStateOf<SongEntity?>(null)
    }

    var itemBottomSheetShow by remember {
        mutableStateOf(false)
    }
    var playlistBottomSheetShow by remember {
        mutableStateOf(false)
    }

    var sortBottomSheetShow by remember {
        mutableStateOf(false)
    }

    var changingOrder by remember {
        mutableStateOf(false)
    }

    val trackPagingItems: LazyPagingItems<Pair<SongEntity, PairSongLocalPlaylist>> = viewModel.tracksPagingState.collectAsLazyPagingItems()
    LaunchedEffect(Unit) {
        snapshotFlow {
            trackPagingItems.loadState
        }.collectLatest {
            Logger.d("PlaylistScreen", "loadState: ${trackPagingItems.loadState}")
            viewModel.setLazyTrackPagingItems(trackPagingItems)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            firstTimeGetLocalPlaylist = true
        }
    }

    val onPlaylistItemClick: (videoId: String) -> Unit = { videoId ->
        viewModel.onUIEvent(
            LocalPlaylistUIEvent.ItemClick(
                videoId = videoId,
            ),
        )
    }
    val onItemMoreClick: (videoId: String) -> Unit = { videoId ->
        currentItem = trackPagingItems.itemSnapshotList.findLast { it?.first?.videoId == videoId }?.first
        if (currentItem != null) {
            itemBottomSheetShow = true
        }
    }
    val onPlaylistMoreClick: () -> Unit = {
        playlistBottomSheetShow = true
    }

    LaunchedEffect(key1 = shouldShowSuggestions) {
        if (suggestedTracks.isEmpty() && uiState.syncState != LocalPlaylistEntity.YouTubeSyncState.NotSynced) {
            viewModel.getSuggestions(uiState.id)
        }
    }

    LaunchedEffect(key1 = id) {
        if (id != uiState.id) {
            Logger.w("PlaylistScreen", "new id: $id")
            viewModel.setOffset(0)
            viewModel.removeListSuggestion()
            viewModel.updatePlaylistState(id, true)
            delay(100)
            firstTimeGetLocalPlaylist = true
        }
    }
    LaunchedEffect(key1 = uiState) {
        shouldShowSuggestButton =
            !uiState.ytPlaylistId.isNullOrEmpty() &&
            uiState.syncState == LocalPlaylistEntity.YouTubeSyncState.Synced
    }
    LaunchedEffect(key1 = firstItemVisible) {
        shouldHideTopBar = !firstItemVisible
    }
    val paletteState = rememberPaletteState()
    var bitmap by remember {
        mutableStateOf<ImageBitmap?>(null)
    }
    // Track which thumbnail we've already extracted a palette from.
    // Prevents palette flash when LazyColumn recycles the header item on scroll —
    // AsyncImage re-mount fires onSuccess again, but we skip the regenerate.
    var paletteGeneratedFor by remember {
        mutableStateOf<String?>(null)
    }
    val currentThumbnail = uiState.thumbnail

    LaunchedEffect(bitmap) {
        val bm = bitmap
        if (bm != null && paletteGeneratedFor != currentThumbnail) {
            paletteState.generate(bm)
            paletteGeneratedFor = currentThumbnail
        }
    }

    LaunchedEffect(Unit) {
        snapshotFlow { paletteState.palette }
            .distinctUntilChanged()
            .collectLatest {
                viewModel.setBrush(listOf(it.getColorFromPalette(), Color.Black))
            }
    }

    // Apple Music-inspired immersive treatment. Which header is used depends on the window's
    // aspect ratio alone, not on the platform: a portrait window (a phone held upright, or a
    // narrow desktop window) gets the edge-to-edge artwork header, a landscape one gets the
    // side-by-side header. Everything else on the page — the palette background, the row
    // dividers, the blurred top bar — is shared by both.
    val screenInfo = getScreenSizeInfo()
    val isPortrait = screenInfo.wDP < screenInfo.hDP
    // Apple Music-style page background. When the playlist has real artwork we pick a vivid
    // swatch from its palette (getColorFromPalette). Many local playlists have NO artwork —
    // AsyncImage shows the title placeholder and onSuccess never fires, so the palette stays
    // null; in that case we fall back to the SAME deterministic title gradient the placeholder
    // draws, so the background still matches the thumbnail. Darkened slightly for readability.
    val mutedPaletteBg =
        run {
            val palette = paletteState.palette
            if (palette != null) {
                palette.toImmersiveBackground()
            } else {
                val titleColors = playlistTitleGradient(uiState.title)
                val base =
                    if (titleColors.size >= 2) {
                        lerp(titleColors[0], titleColors[1], 0.5f)
                    } else {
                        titleColors.firstOrNull() ?: Color.Black
                    }
                lerp(base, Color.Black, 0.3f)
            }
        }

    // Loading dialog
    val showLoadingDialog by viewModel.showLoadingDialog.collectAsStateWithLifecycle()
    if (showLoadingDialog.first) {
        LoadingDialog(
            true,
            showLoadingDialog.second,
        )
    }
    val coroutineScope = rememberCoroutineScope()
    val dragDropState =
        rememberDragDropState(lazyState) { from, to ->
            coroutineScope.launch {
                Logger.d(TAG, "onMove from $from to $to")
                viewModel.changeLocalPlaylistItemPosition(from - 1, to - 1)
                trackPagingItems.refresh()
            }
        }
    var overscrollJob by remember { mutableStateOf<Job?>(null) }
    var showSearchBar by rememberSaveable { mutableStateOf(false) }
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
//    Box {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(mutedPaletteBg)
                
                .pointerInput(changingOrder) {
                    if (!changingOrder) return@pointerInput
                    val onDrag: (change: androidx.compose.ui.input.pointer.PointerInputChange, offset: Offset) -> Unit =
                        { change, offset ->
                            Logger.d(TAG, "onDrag $offset")
                            change.consume()
                            dragDropState.onDrag(offset = offset)

                            if (overscrollJob?.isActive != true) {
                                dragDropState
                                    .checkForOverScroll()
                                    .takeIf { it != 0f }
                                    ?.let {
                                        overscrollJob =
                                            coroutineScope.launch {
                                                dragDropState.state.animateScrollBy(
                                                    it * 1.3f,
                                                    tween(easing = FastOutLinearInEasing),
                                                )
                                            }
                                    }
                                    ?: run { overscrollJob?.cancel() }
                            }
                        }
                    val onDragStart: (Offset) -> Unit = { offset ->
                        Logger.d(TAG, "onDragStart $offset")
                        dragDropState.onDragStart(offset)
                    }
                    val onDragEnd: () -> Unit = {
                        Logger.d(TAG, "onDragEnd")
                        dragDropState.onDragInterrupted(true)
                        overscrollJob?.cancel()
                    }
                    val onDragCancel: () -> Unit = {
                        Logger.d(TAG, "onDragCancel")
                        dragDropState.onDragInterrupted()
                        overscrollJob?.cancel()
                    }
                    if (getPlatform() == Platform.Desktop) {
                        // Desktop: normal drag (click-and-drag), no long press needed
                        detectDragGestures(
                            onDrag = onDrag,
                            onDragStart = onDragStart,
                            onDragEnd = onDragEnd,
                            onDragCancel = onDragCancel,
                        )
                    } else {
                        // Android: long press then drag (touch-friendly)
                        detectDragGesturesAfterLongPress(
                            onDrag = onDrag,
                            onDragStart = onDragStart,
                            onDragEnd = onDragEnd,
                            onDragCancel = onDragCancel,
                        )
                    }
                },
        state = lazyState,
    ) {
        item(contentType = "header") {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .windowInsetsPadding(WindowInsets.statusBars),
            ) {
                // 1. Material 3 Top Navigation Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier.size(40.dp),
                    ) {
                        Icon(
                            imageVector = SimpIcons.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp),
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // AI Suggestion button (when synced with YouTube)
                        if (shouldShowSuggestButton) {
                            IconButton(
                                onClick = { shouldShowSuggestions = !shouldShowSuggestions },
                                modifier = Modifier.size(40.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                                        .drawWithContent {
                                            val width = size.width
                                            val height = size.height
                                            val offsetDraw = width * progressAnimated
                                            val brush = Brush.linearGradient(
                                                colors = listOf(Color(0xFF4C82EF), Color(0xFFD96570)),
                                                start = Offset(offsetDraw, 0f),
                                                end = Offset(offsetDraw + width, height),
                                            )
                                            with(aiPainter) { draw(size = Size(width, height)) }
                                            drawRect(brush = brush, blendMode = BlendMode.SrcIn)
                                        },
                                )
                            }
                        }

                        // Search in playlist
                        IconButton(
                            onClick = { showSearchBar = true },
                            modifier = Modifier.size(40.dp),
                        ) {
                            Icon(
                                imageVector = SimpIcons.Search,
                                contentDescription = "Search in playlist",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp),
                            )
                        }

                        // Customize Cover
                        IconButton(
                            onClick = { showCoverCustomizer = true },
                            modifier = Modifier.size(40.dp),
                        ) {
                            Icon(
                                imageVector = SimpIcons.Tune,
                                contentDescription = "Customize Cover",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp),
                            )
                        }

                        // More options
                        IconButton(
                            onClick = onPlaylistMoreClick,
                            modifier = Modifier.size(40.dp),
                        ) {
                            Icon(
                                imageVector = SimpIcons.MoreVert,
                                contentDescription = "More",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp),
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 2. Material 3 Hero Section: Shaped Cover + Title + Metadata + Actions
                if (isPortrait) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        // Material 3 Shaped Cover (tappable to customize!)
                        Box(
                            contentAlignment = Alignment.BottomEnd,
                            modifier = Modifier.clickable { showCoverCustomizer = true },
                        ) {
                            ExpressivePlaylistCover(
                                thumbnailUrl = uiState.thumbnail,
                                title = uiState.title,
                                shape = currentShape.getShape(200.dp),
                                containerColor = currentCoverColor,
                                size = 200.dp,
                            )
                            // Subtle edit badge
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.9f),
                                tonalElevation = 4.dp,
                                shadowElevation = 3.dp,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(36.dp),
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = SimpIcons.Tune,
                                        contentDescription = "Customize",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.size(18.dp),
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Playlist Title
                        Text(
                            text = uiState.title,
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = (-0.6).sp,
                            ),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            modifier = Modifier.padding(horizontal = 8.dp),
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Badges Row (Tracks, Type)
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Surface(
                                shape = RoundedCornerShape(50),
                                color = MaterialTheme.colorScheme.primaryContainer,
                            ) {
                                Text(
                                    text = "${uiState.trackCount} tracks",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                )
                            }

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = Color.White.copy(alpha = 0.12f),
                            ) {
                                Text(
                                    text = if (uiState.syncState == LocalPlaylistEntity.YouTubeSyncState.Synced) "Synced with YouTube" else "Local",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.White.copy(alpha = 0.85f),
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Expressive Action Row: [Play Button] [Shuffle] [Download] [Customize]
                        val isThisPlaying = isPlaying && playingPlaylistId == LOCAL_PLAYLIST_ID + uiState.id
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Primary Play Pill Button
                            Button(
                                onClick = {
                                    if (isThisPlaying) {
                                        sharedViewModel.onUIEvent(UIEvent.PlayPause)
                                    } else {
                                        viewModel.onUIEvent(LocalPlaylistUIEvent.PlayClick)
                                    }
                                },
                                shape = RoundedCornerShape(28.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary,
                                ),
                                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                            ) {
                                Icon(
                                    imageVector = if (isThisPlaying) SimpIcons.Pause else SimpIcons.PlayArrow,
                                    contentDescription = if (isThisPlaying) "Pause" else "Play",
                                    modifier = Modifier.size(24.dp),
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isThisPlaying) "Pause" else "Play",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                )
                            }

                            // Shuffle Button
                            FilledTonalIconButton(
                                onClick = { viewModel.onUIEvent(LocalPlaylistUIEvent.ShuffleClick) },
                                modifier = Modifier.size(48.dp),
                                shape = CircleShape,
                            ) {
                                Icon(
                                    imageVector = SimpIcons.Shuffle,
                                    contentDescription = "Shuffle",
                                    modifier = Modifier.size(22.dp),
                                )
                            }

                            // Download Button
                            FilledTonalIconButton(
                                onClick = {
                                    if (downloadState == DownloadState.STATE_DOWNLOADED) {
                                        viewModel.makeToast(runBlocking { getString(Res.string.downloaded) })
                                    } else if (downloadState == DownloadState.STATE_DOWNLOADING) {
                                        viewModel.makeToast(runBlocking { getString(Res.string.downloading) })
                                    } else {
                                        viewModel.downloadFullPlaylist()
                                    }
                                },
                                modifier = Modifier.size(48.dp),
                                shape = CircleShape,
                            ) {
                                when (downloadState) {
                                    DownloadState.STATE_DOWNLOADED -> {
                                        Icon(
                                            painter = painterResource(Res.drawable.baseline_downloaded),
                                            tint = Color(0xFF00A0CB),
                                            contentDescription = "Downloaded",
                                            modifier = Modifier.size(22.dp),
                                        )
                                    }
                                    DownloadState.STATE_DOWNLOADING -> {
                                        DownloadingIndicator(modifier = Modifier.size(28.dp))
                                    }
                                    else -> {
                                        Icon(
                                            imageVector = SimpIcons.DownloadForOffline,
                                            contentDescription = "Download",
                                            modifier = Modifier.size(22.dp),
                                        )
                                    }
                                }
                            }

                            // Customize Button
                            FilledTonalIconButton(
                                onClick = { showCoverCustomizer = true },
                                modifier = Modifier.size(48.dp),
                                shape = CircleShape,
                            ) {
                                Icon(
                                    imageVector = SimpIcons.Tune,
                                    contentDescription = "Customize Cover",
                                    modifier = Modifier.size(22.dp),
                                )
                            }
                        }
                    }
                } else {
                    // Landscape / Desktop wide header
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(28.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            contentAlignment = Alignment.BottomEnd,
                            modifier = Modifier.clickable { showCoverCustomizer = true },
                        ) {
                            ExpressivePlaylistCover(
                                thumbnailUrl = uiState.thumbnail,
                                title = uiState.title,
                                shape = currentShape.getShape(200.dp),
                                containerColor = currentCoverColor,
                                size = 200.dp,
                            )
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.9f),
                                tonalElevation = 4.dp,
                                modifier = Modifier.padding(4.dp).size(36.dp),
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = SimpIcons.Tune,
                                        contentDescription = "Customize",
                                        tint = MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.size(18.dp),
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = uiState.title,
                                style = TextStyle(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-0.6).sp,
                                ),
                                color = Color.White,
                                maxLines = 2,
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = MaterialTheme.colorScheme.primaryContainer,
                                ) {
                                    Text(
                                        text = "${uiState.trackCount} tracks",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    )
                                }

                                Surface(
                                    shape = RoundedCornerShape(50),
                                    color = Color.White.copy(alpha = 0.12f),
                                ) {
                                    Text(
                                        text = if (uiState.syncState == LocalPlaylistEntity.YouTubeSyncState.Synced) "Synced with YouTube" else "Local",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color.White.copy(alpha = 0.85f),
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            val isThisPlaying = isPlaying && playingPlaylistId == LOCAL_PLAYLIST_ID + uiState.id
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Button(
                                    onClick = {
                                        if (isThisPlaying) {
                                            sharedViewModel.onUIEvent(UIEvent.PlayPause)
                                        } else {
                                            viewModel.onUIEvent(LocalPlaylistUIEvent.PlayClick)
                                        }
                                    },
                                    shape = RoundedCornerShape(28.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        contentColor = MaterialTheme.colorScheme.onPrimary,
                                    ),
                                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                                ) {
                                    Icon(
                                        imageVector = if (isThisPlaying) SimpIcons.Pause else SimpIcons.PlayArrow,
                                        contentDescription = if (isThisPlaying) "Pause" else "Play",
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isThisPlaying) "Pause" else "Play",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    )
                                }

                                FilledTonalIconButton(
                                    onClick = { viewModel.onUIEvent(LocalPlaylistUIEvent.ShuffleClick) },
                                    modifier = Modifier.size(48.dp),
                                    shape = CircleShape,
                                ) {
                                    Icon(
                                        imageVector = SimpIcons.Shuffle,
                                        contentDescription = "Shuffle",
                                        modifier = Modifier.size(22.dp),
                                    )
                                }

                                FilledTonalIconButton(
                                    onClick = {
                                        if (downloadState == DownloadState.STATE_DOWNLOADED) {
                                            viewModel.makeToast(runBlocking { getString(Res.string.downloaded) })
                                        } else if (downloadState == DownloadState.STATE_DOWNLOADING) {
                                            viewModel.makeToast(runBlocking { getString(Res.string.downloading) })
                                        } else {
                                            viewModel.downloadFullPlaylist()
                                        }
                                    },
                                    modifier = Modifier.size(48.dp),
                                    shape = CircleShape,
                                ) {
                                    when (downloadState) {
                                        DownloadState.STATE_DOWNLOADED -> {
                                            Icon(
                                                painter = painterResource(Res.drawable.baseline_downloaded),
                                                tint = Color(0xFF00A0CB),
                                                contentDescription = "Downloaded",
                                                modifier = Modifier.size(22.dp),
                                            )
                                        }
                                        DownloadState.STATE_DOWNLOADING -> {
                                            DownloadingIndicator(modifier = Modifier.size(28.dp))
                                        }
                                        else -> {
                                            Icon(
                                                imageVector = SimpIcons.DownloadForOffline,
                                                contentDescription = "Download",
                                                modifier = Modifier.size(22.dp),
                                            )
                                        }
                                    }
                                }

                                FilledTonalIconButton(
                                    onClick = { showCoverCustomizer = true },
                                    modifier = Modifier.size(48.dp),
                                    shape = CircleShape,
                                ) {
                                    Icon(
                                        imageVector = SimpIcons.Tune,
                                        contentDescription = "Customize Cover",
                                        modifier = Modifier.size(22.dp),
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 3. Suggestions Section (when opened)
                AnimatedVisibility(visible = shouldShowSuggestions) {
                    Column(
                        modifier = Modifier.animateContentSize(),
                    ) {
                        Spacer(modifier = Modifier.size(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(Res.string.suggest),
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .weight(1f),
                            )
                            TextButton(
                                onClick = { viewModel.reloadSuggestion() },
                                modifier = Modifier.padding(horizontal = 8.dp),
                            ) {
                                Text(
                                    text = stringResource(Res.string.reload),
                                    color = MaterialTheme.colorScheme.primary,
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Crossfade(targetState = suggestionsLoading) {
                            if (it) {
                                CenterLoadingBox(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                        .align(Alignment.CenterHorizontally),
                                )
                            } else {
                                Column {
                                    suggestedTracks.forEachIndexed { index, track ->
                                        SuggestItems(
                                            forceDark = true,
                                            track = track,
                                            isPlaying = playingTrack?.videoId == track.videoId,
                                            onAddClickListener = {
                                                viewModel.addSuggestTrackToListTrack(track)
                                            },
                                            onClickListener = {
                                                viewModel.onUIEvent(LocalPlaylistUIEvent.SuggestionsItemClick(track.videoId))
                                            },
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                    }
                }

                // 4. Sort and Order Controls Row
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    FilterChip(
                        selected = false,
                        onClick = { sortBottomSheetShow = true },
                        label = {
                            Text(
                                text = stringResource(Res.string.sort_by) + ": " + stringResource(uiState.filterState.displayNameRes()),
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = SimpIcons.Sort,
                                contentDescription = "Sort",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp),
                            )
                        },
                        shape = RoundedCornerShape(12.dp),
                    )

                    AnimatedVisibility(
                        visible = uiState.filterState == FilterState.CustomOrder,
                        enter = fadeIn(),
                        exit = fadeOut(),
                    ) {
                        TextButton(
                            onClick = { changingOrder = !changingOrder },
                        ) {
                            Text(
                                text = if (changingOrder) "Done" else "Change order",
                                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                                color = Color.White,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
        }
        items(count = trackPagingItems.itemCount, key = { index ->
            val item = trackPagingItems[index]
            (item?.first?.videoId ?: "") + "item_$index" + item?.second?.inPlaylist + item?.second?.position
        }) { index ->
            val item = trackPagingItems[index]?.first
            if (item != null) {
                val content = @Composable { mod: Modifier ->
                    if (playingTrack?.videoId == item.videoId && isPlaying) {
                        SongFullWidthItems(
                            forceDark = true,
                            isPlaying = true,
                            shouldShowDragHandle = changingOrder,
                            songEntity = item,
                            onMoreClickListener = { onItemMoreClick(it) },
                            onClickListener = {
                                Logger.w("PlaylistScreen", "index: $index")
                                onPlaylistItemClick(it)
                            },
                            onAddToQueue = {
                                sharedViewModel.addListToQueue(
                                    arrayListOf(item.toTrack()),
                                )
                            },
                            selectionMode = selectionState.isActive,
                            isSelected = selectionState.isSelected(item.videoId),
                            // No long press while reordering: that gesture already belongs to drag.
                            onLongClick = if (changingOrder) null else ({ selectionState.start(it) }),
                            onSelectToggle = { selectionState.toggle(it) },
                            modifier = mod,
                        )
                    } else {
                        SongFullWidthItems(
                            forceDark = true,
                            isPlaying = false,
                            shouldShowDragHandle = changingOrder,
                            songEntity = item,
                            onMoreClickListener = { onItemMoreClick(it) },
                            onClickListener = {
                                Logger.w("PlaylistScreen", "index: $index")
                                onPlaylistItemClick(it)
                            },
                            onAddToQueue = {
                                sharedViewModel.addListToQueue(
                                    arrayListOf(item.toTrack()),
                                )
                            },
                            selectionMode = selectionState.isActive,
                            isSelected = selectionState.isSelected(item.videoId),
                            // No long press while reordering: that gesture already belongs to drag.
                            onLongClick = if (changingOrder) null else ({ selectionState.start(it) }),
                            onSelectToggle = { selectionState.toggle(it) },
                            modifier = mod,
                        )
                    }
                }
                if (changingOrder) {
                    DraggableItem(
                        dragDropState,
                        index + 1,
                        Modifier.animateItem(),
                    ) {
                        content(Modifier)
                    }
                } else {
                    Column(modifier = Modifier.animateItem()) {
                        content(Modifier)
                        if (index < trackPagingItems.itemCount - 1) {
                            HorizontalDivider(
                                color = androidx.compose.ui.graphics.Color.Transparent, 
                                modifier = Modifier.padding(start = 72.dp, end = 16.dp),
                                thickness = 0.5.dp,
                            )
                        }
                    }
                }
            }
        }
        trackPagingItems.apply {
            item {
                AnimatedVisibility(loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(15.dp))
                        CenterLoadingBox(modifier = Modifier.size(80.dp))
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
        item {
            EndOfPage()
        }
    }
    AnimatedVisibility(
        visible = selectionState.isActive,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        SongSelectionTopAppBar(
            state = selectionState,
            onSelectAll = {
                // peek() rather than get(): reading an item is what asks Paging to load its page,
                // and select-all must not drag the whole playlist into memory.
                selectionState.toggleSelectAll(
                    (0 until trackPagingItems.itemCount).mapNotNull {
                        trackPagingItems.peek(it)?.first?.videoId
                    },
                )
            },
            onOpenActions = { showSelectionSheet = true },
            modifier =
                Modifier.background(mutedPaletteBg),
        )
    }
    if (showSelectionSheet) {
        val selectedIds = selectionState.selected.toList()
        val removeLabel = stringResource(Res.string.remove_from_playlist)
        SelectedSongsBottomSheet(
            count = selectedIds.size,
            onDismiss = { showSelectionSheet = false },
            onPlayNext = {
                selectionViewModel.playNext(selectedIds)
                selectionState.exit()
            },
            onAddToQueue = {
                selectionViewModel.addToQueue(selectedIds)
                selectionState.exit()
            },
            onAddToPlaylist = { showSelectionAddToPlaylist = true },
            onDownload = {
                selectionViewModel.download(selectedIds)
                selectionState.exit()
            },
            onAddToFavorite = {
                selectionViewModel.addToFavorite(selectedIds)
                selectionState.exit()
            },
            extraActions =
                listOf(
                    SongSelectionAction(
                        icon = SimpIcons.Delete,
                        label = removeLabel,
                        tint = Color.Red,
                    ) {
                        selectionViewModel.removeFromLocalPlaylist(uiState.id, selectedIds)
                        selectionState.exit()
                    },
                ),
        )
    }
    if (showSelectionAddToPlaylist) {
        val selectedIds = selectionState.selected.toList()
        val localPlaylists by selectionViewModel.listLocalPlaylist.collectAsStateWithLifecycle()
        AddToPlaylistModalBottomSheet(
            isBottomSheetVisible = true,
            listLocalPlaylist = localPlaylists,
            listYouTubePlaylist = emptyList(),
            onDismiss = { showSelectionAddToPlaylist = false },
            onClick = { playlist ->
                selectionViewModel.addToPlaylist(playlist.id, selectedIds)
                selectionState.exit()
            },
            onYTPlaylistClick = {},
        )
    }
    if (itemBottomSheetShow && currentItem != null) {
        val track = currentItem ?: return
        NowPlayingBottomSheet(
            onDelete = { viewModel.deleteItem(uiState.id, track) },
            onDismiss = {
                itemBottomSheetShow = false
                currentItem = null
            },
            navController = navController,
            song = track,
        )
    }
    if (playlistBottomSheetShow) {
        Logger.w("PlaylistScreen", "PlaylistBottomSheet")
        LocalPlaylistBottomSheet(
            isBottomSheetVisible = playlistBottomSheetShow,
            onDismiss = { playlistBottomSheetShow = false },
            title = uiState.title,
            ytPlaylistId = uiState.ytPlaylistId,
            onEditTitle =
                { newTitle ->
                    viewModel.updatePlaylistTitle(newTitle, uiState.id)
                },
            onEditThumbnail =
                { thumbUri ->
                    viewModel.updatePlaylistThumbnail(thumbUri, uiState.id)
                },
            onAddToQueue = {
                viewModel.addAllToQueue()
            },
            onSync = {
                if (uiState.syncState == LocalPlaylistEntity.YouTubeSyncState.Synced) {
                    showUnsyncAlertDialog = true
                } else {
                    showSyncAlertDialog = true
                }
            },
            onUpdatePlaylist = {
                viewModel.updateListTrackSynced(uiState.id)
            },
            onDelete = {
                viewModel.deletePlaylist(uiState.id)
                navController.navigateUp()
            },
        )
    }
    if (showSyncAlertDialog) {
        AlertDialog(
            containerColor = rememberSurfaceDarkColors().container,
            titleContentColor = rememberSurfaceDarkColors().content,
            textContentColor = rememberSurfaceDarkColors().content,
            title = { Text(text = stringResource(Res.string.warning)) },
            text = { Text(text = stringResource(Res.string.sync_playlist_warning)) },
            onDismissRequest = { showSyncAlertDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.syncPlaylistWithYouTubePlaylist(uiState.id)
                    showSyncAlertDialog = false
                }) {
                    Text(text = stringResource(Res.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showSyncAlertDialog = false
                }) {
                    Text(text = stringResource(Res.string.cancel))
                }
            },
        )
    }
    if (showUnsyncAlertDialog) {
        AlertDialog(
            containerColor = rememberSurfaceDarkColors().container,
            titleContentColor = rememberSurfaceDarkColors().content,
            textContentColor = rememberSurfaceDarkColors().content,
            title = { Text(text = stringResource(Res.string.warning)) },
            text = { Text(text = stringResource(Res.string.unsync_playlist_warning)) },
            onDismissRequest = { showUnsyncAlertDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.unsyncPlaylistWithYouTubePlaylist(uiState.id)
                    showUnsyncAlertDialog = false
                }) {
                    Text(text = stringResource(Res.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showUnsyncAlertDialog = false
                }) {
                    Text(text = stringResource(Res.string.cancel))
                }
            },
        )
    }
    // A sibling of the paged list, not a branch inside it. The paged reader carries drag
    // reordering, in-place removal and its own scroll state; searching has none of that, and
    // threading a second data source through it would put all of that at risk for a feature that
    // only needs a flat list. This draws over it instead and leaves it untouched.
    //
    // Results come from LocalPlaylistViewModel.searchResults, which queries the database —
    // filtering trackPagingItems would only ever search the pages already scrolled through.
    AnimatedVisibility(
        visible = showSearchBar,
        enter = SearchBarEnter,
        exit = SearchBarExit,
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(mutedPaletteBg),
        ) {
            // Same bar as the one on Playlist and Album: blurred over the content behind it,
            // back on the left, clear on the right.
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(mutedPaletteBg),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .windowInsetsPadding(WindowInsets.statusBars),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RippleIconButton(
                        imageVector = SimpIcons.ArrowBackIosNew,
                    ) {
                        navController.navigateUp()
                    }
                    SearchBar(
                        modifier =
                            Modifier
                                .height(50.dp)
                                .padding(horizontal = 12.dp)
                                .weight(1f),
                        colors =
                            SearchBarDefaults.colors().copy(
                                containerColor = Color.Transparent,
                            ),
                        inputField = {
                            CompositionLocalProvider(LocalTextStyle provides typo().bodySmall) {
                                SearchBarDefaults.InputField(
                                    query = searchQuery,
                                    onQueryChange = { viewModel.setSearchQuery(it) },
                                    onSearch = {},
                                    expanded = showSearchBar,
                                    onExpandedChange = { showSearchBar = it },
                                    placeholder = {
                                        Text(
                                            stringResource(Res.string.search),
                                            style = typo().bodyMedium,
                                        )
                                    },
                                )
                            }
                        },
                        expanded = false,
                        onExpandedChange = {},
                        windowInsets = WindowInsets(0, 0, 0, 0),
                    ) {
                    }
                    IconButton(
                        onClick = {
                            showSearchBar = false
                            viewModel.setSearchQuery("")
                        },
                    ) {
                        Icon(SimpIcons.Close, null, tint = Color.White)
                    }
                }
            }
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(
                    searchResults,
                    key = { (song, pair) -> song.videoId + "_" + pair.position },
                ) { (song, _) ->
                    SongFullWidthItems(
                        forceDark = true,
                        isPlaying = playingTrack?.videoId == song.videoId && isPlaying,
                        songEntity = song,
                        onMoreClickListener = { onItemMoreClick(it) },
                        onClickListener = { onPlaylistItemClick(it) },
                        onAddToQueue = { sharedViewModel.addListToQueue(arrayListOf(song.toTrack())) },
                        selectionMode = selectionState.isActive,
                        isSelected = selectionState.isSelected(song.videoId),
                        onLongClick = { selectionState.start(it) },
                        onSelectToggle = { selectionState.toggle(it) },
                        // Required, no default — the paged list passes it down through `mod`.
                        modifier = Modifier,
                    )
                    HorizontalDivider(color = androidx.compose.ui.graphics.Color.Transparent, modifier = Modifier.padding(start = 72.dp, end = 16.dp))
                }
            }
        }
    }
    if (sortBottomSheetShow) {
        SortPlaylistBottomSheet(
            selectedState = uiState.filterState,
            onDismiss = { sortBottomSheetShow = false },
            onSortChanged = {
                viewModel.onUIEvent(LocalPlaylistUIEvent.ChangeFilter(it))
                sortBottomSheetShow = false
            },
        )
    }
    AnimatedVisibility(
        visible = shouldHideTopBar && !selectionState.isActive,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        TopAppBar(
            windowInsets =
                TopAppBarDefaults.windowInsets.exclude(
                    TopAppBarDefaults.windowInsets.only(WindowInsetsSides.Start),
                ),
            title = {
                Text(
                    text = uiState.title,
                    style = typo().titleMedium,
                )
            },
            navigationIcon = {
                Box(Modifier.padding(horizontal = 5.dp)) {
                    RippleIconButton(
                        SimpIcons.ArrowBackIosNew,
                        Modifier
                            .size(32.dp),
                        true,
                    ) {
                        navController.navigateUp()
                    }
                }
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
            modifier =
                Modifier.background(mutedPaletteBg),
        )
    }

    if (showCoverCustomizer) {
        PlaylistCoverCustomizerSheet(
            playlistTitle = uiState.title,
            currentThumbnail = uiState.thumbnail,
            initialShape = currentShape,
            initialColor = currentCoverColor,
            onDismiss = { showCoverCustomizer = false },
            getTracks = { viewModel.getPlaylistTracks(uiState.id) },
            onSave = { shapeId, colorHex, newThumbnail ->
                viewModel.savePlaylistShape(uiState.id.toString(), shapeId)
                viewModel.savePlaylistColor(uiState.id.toString(), colorHex)
                if (newThumbnail != null && newThumbnail != uiState.thumbnail) {
                    viewModel.updatePlaylistThumbnail(newThumbnail, uiState.id)
                }
                showCoverCustomizer = false
            },
        )
    }
}