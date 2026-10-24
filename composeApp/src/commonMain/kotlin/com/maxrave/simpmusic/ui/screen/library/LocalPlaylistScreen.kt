package com.maxrave.simpmusic.ui.screen.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maxrave.common.LOCAL_PLAYLIST_ID
import com.maxrave.domain.data.entities.DownloadState
import com.maxrave.domain.data.entities.LocalPlaylistEntity
import com.maxrave.domain.data.entities.PairSongLocalPlaylist
import com.maxrave.domain.data.entities.SongEntity
import com.maxrave.domain.data.model.browse.album.Track
import com.maxrave.domain.utils.FilterState
import com.maxrave.domain.utils.toTrack
import com.maxrave.logger.Logger
import com.maxrave.simpmusic.Platform
import com.maxrave.simpmusic.expect.BackHandler
import com.maxrave.simpmusic.extension.displayNameRes
import com.maxrave.simpmusic.getPlatform
import com.maxrave.simpmusic.ui.component.AddToPlaylistModalBottomSheet
import com.maxrave.simpmusic.ui.component.CenterLoadingBox
import com.maxrave.simpmusic.ui.component.DownloadingIndicator
import com.maxrave.simpmusic.ui.component.DraggableItem
import com.maxrave.simpmusic.ui.component.EndOfPage
import com.maxrave.simpmusic.ui.component.LoadingDialog
import com.maxrave.simpmusic.ui.component.LocalPlaylistBottomSheet
import com.maxrave.simpmusic.ui.component.NowPlayingBottomSheet
import com.maxrave.simpmusic.ui.component.SearchBarEnter
import com.maxrave.simpmusic.ui.component.SearchBarExit
import com.maxrave.simpmusic.ui.component.SongFullWidthItems
import com.maxrave.simpmusic.ui.component.SortPlaylistBottomSheet
import com.maxrave.simpmusic.ui.component.SuggestItems
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCoverColors
import com.maxrave.simpmusic.ui.component.expressive.ExpressivePlaylistCover
import com.maxrave.simpmusic.ui.component.expressive.MaterialShapePreset
import com.maxrave.simpmusic.ui.component.expressive.PlaylistCoverCustomizerSheet
import com.maxrave.simpmusic.ui.component.expressive.expressiveSpring
import com.maxrave.simpmusic.ui.component.expressive.rememberPlaylistBackgroundColor
import com.maxrave.simpmusic.ui.component.rememberDragDropState
import com.maxrave.simpmusic.ui.component.rememberSurfaceDarkColors
import com.maxrave.simpmusic.ui.component.selection.SelectedSongsBottomSheet
import com.maxrave.simpmusic.ui.component.selection.SongSelectionAction
import com.maxrave.simpmusic.ui.component.selection.SongSelectionTopAppBar
import com.maxrave.simpmusic.ui.component.selection.rememberSongSelectionState
import com.maxrave.simpmusic.ui.icon.Album
import com.maxrave.simpmusic.ui.icon.ArrowBack
import com.maxrave.simpmusic.ui.icon.Check
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.Delete
import com.maxrave.simpmusic.ui.icon.DownloadForOffline
import com.maxrave.simpmusic.ui.icon.DragHandle
import com.maxrave.simpmusic.ui.icon.MoreVert
import com.maxrave.simpmusic.ui.icon.Pause
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.Search
import com.maxrave.simpmusic.ui.icon.Shuffle
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.Sort
import com.maxrave.simpmusic.ui.icon.Sync
import com.maxrave.simpmusic.ui.icon.TipsAndUpdates
import com.maxrave.simpmusic.ui.icon.Tune
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.baseline_downloaded
import simpmusic.composeapp.generated.resources.cancel
import simpmusic.composeapp.generated.resources.download
import simpmusic.composeapp.generated.resources.downloaded
import simpmusic.composeapp.generated.resources.downloading
import simpmusic.composeapp.generated.resources.n_song
import simpmusic.composeapp.generated.resources.more
import simpmusic.composeapp.generated.resources.no_results_found
import simpmusic.composeapp.generated.resources.no_songs_match_search
import simpmusic.composeapp.generated.resources.playlist_ai_suggestions
import simpmusic.composeapp.generated.resources.playlist_back
import simpmusic.composeapp.generated.resources.playlist_clear_search
import simpmusic.composeapp.generated.resources.playlist_close_search
import simpmusic.composeapp.generated.resources.playlist_customize_cover
import simpmusic.composeapp.generated.resources.playlist_edit_style
import simpmusic.composeapp.generated.resources.playlist_empty_description
import simpmusic.composeapp.generated.resources.playlist_empty_title
import simpmusic.composeapp.generated.resources.playlist_local
import simpmusic.composeapp.generated.resources.playlist_pause
import simpmusic.composeapp.generated.resources.playlist_play
import simpmusic.composeapp.generated.resources.playlist_reorder
import simpmusic.composeapp.generated.resources.playlist_reorder_done
import simpmusic.composeapp.generated.resources.playlist_search_in_playlist
import simpmusic.composeapp.generated.resources.playlist_syncing
import simpmusic.composeapp.generated.resources.playlist_youtube_synced
import simpmusic.composeapp.generated.resources.reload
import simpmusic.composeapp.generated.resources.remove_from_playlist
import simpmusic.composeapp.generated.resources.search
import simpmusic.composeapp.generated.resources.search_min_characters
import simpmusic.composeapp.generated.resources.search_songs_in_playlist
import simpmusic.composeapp.generated.resources.shuffle
import simpmusic.composeapp.generated.resources.songs
import simpmusic.composeapp.generated.resources.sort_by
import simpmusic.composeapp.generated.resources.suggest
import simpmusic.composeapp.generated.resources.sync_playlist_warning
import simpmusic.composeapp.generated.resources.unsync_playlist_warning
import simpmusic.composeapp.generated.resources.warning
import simpmusic.composeapp.generated.resources.yes

private const val TAG = "LocalPlaylistScreen"

/**
 * The hero block — cover, title, metadata, actions, suggestions and the sort row — is a
 * single leading item, so every song in the paged list sits this many slots further down
 * than its position in the playlist.
 *
 * [DraggableItem] takes a LazyList index and [rememberDragDropState] reports LazyList
 * indices, while `changeLocalPlaylistItemPosition` takes playlist positions. Both sides
 * of that conversion must use this constant. Adding or splitting a leading item without
 * updating it silently reorders the wrong songs.
 */
private const val PLAYLIST_LEADING_ITEMS = 1

/** Above this width the hero puts the cover beside the text instead of above it. */
private val HERO_WIDE_BREAKPOINT = 600.dp

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

    val lazyState = rememberLazyListState()

    val downloadState by viewModel.uiState.map { it.downloadState }.collectAsStateWithLifecycle(
        initialValue = DownloadState.STATE_NOT_DOWNLOADED,
    )
    var shouldShowSuggestions by rememberSaveable { mutableStateOf(false) }

    val selectionState = rememberSongSelectionState()
    val selectionViewModel: SongSelectionViewModel = koinViewModel()
    var showSelectionSheet by rememberSaveable { mutableStateOf(false) }
    var showSelectionAddToPlaylist by rememberSaveable { mutableStateOf(false) }
    var shouldShowSuggestButton by rememberSaveable { mutableStateOf(false) }

    val playingTrack by sharedViewModel.nowPlayingState
        .mapLatest {
            it?.songEntity
        }.collectAsStateWithLifecycle(initialValue = null)
    val isPlaying by sharedViewModel.controllerState.map { it.isPlaying }.collectAsStateWithLifecycle(initialValue = false)

    val queueData by sharedViewModel.getQueueDataState().collectAsStateWithLifecycle()
    val playingPlaylistId by remember {
        derivedStateOf {
            queueData?.data?.playlistId
        }
    }

    val suggestedTracks by viewModel.uiState.map { it.suggestions?.songs ?: emptyList() }.collectAsStateWithLifecycle(initialValue = emptyList())
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
        try {
            Color(hex.toLong(16).toULong())
        } catch (_: Exception) {
            null
        }
    } ?: defaultColor

    // Solid Material 3 pastel page background, softened from the playlist's cover accent.
    val playlistBg = rememberPlaylistBackgroundColor(currentCoverColor)

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

    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    val onPlaylistItemClick: (videoId: String) -> Unit = { videoId ->
        viewModel.onUIEvent(
            LocalPlaylistUIEvent.ItemClick(
                videoId = videoId,
            ),
        )
    }
    val onItemMoreClick: (videoId: String) -> Unit = { videoId ->
        // Search results are a database query, not a slice of the paged list, so the
        // searched song may not be in `itemSnapshotList` at all. Fall back to the
        // search result itself or the overflow sheet would silently refuse to open.
        currentItem =
            trackPagingItems.itemSnapshotList.findLast { it?.first?.videoId == videoId }?.first
                ?: searchResults.find { it.first.videoId == videoId }?.first
        if (currentItem != null) {
            itemBottomSheetShow = true
        }
    }
    val onPlaylistMoreClick: () -> Unit = {
        playlistBottomSheetShow = true
    }

    LaunchedEffect(key1 = shouldShowSuggestions) {
        // Only fetch suggestions when the panel is actually expanded/visible.
        if (shouldShowSuggestions && suggestedTracks.isEmpty() &&
            uiState.syncState != LocalPlaylistEntity.YouTubeSyncState.NotSynced
        ) {
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
                viewModel.changeLocalPlaylistItemPosition(
                    from - PLAYLIST_LEADING_ITEMS,
                    to - PLAYLIST_LEADING_ITEMS,
                )
                trackPagingItems.refresh()
            }
        }
    var overscrollJob by remember { mutableStateOf<Job?>(null) }
    var showSearchBar by rememberSaveable { mutableStateOf(false) }
    // Search is an overlay, not a destination: system back must close it before
    // it can pop the playlist.
    BackHandler(enabled = showSearchBar) {
        showSearchBar = false
        viewModel.setSearchQuery("")
    }

    // The bar title fades in as the hero's own title scrolls away. Driving it from the
    // scroll offset (rather than an animation) keeps it glued to the finger.
    val titleFadeDistance = with(LocalDensity.current) { 96.dp.toPx() }
    val barTitleProgress by remember(titleFadeDistance) {
        derivedStateOf {
            if (lazyState.firstVisibleItemIndex > 0) {
                1f
            } else {
                (lazyState.firstVisibleItemScrollOffset / titleFadeDistance).coerceIn(0f, 1f)
            }
        }
    }

    BoxWithConstraints(
        modifier =
            Modifier
                .fillMaxSize()
                .background(playlistBg),
    ) {
        val compactHero = maxWidth < HERO_WIDE_BREAKPOINT

        Column(modifier = Modifier.fillMaxSize()) {
            PlaylistTopBar(
                title = uiState.title,
                titleProgress = barTitleProgress,
                showSuggestionsAction = shouldShowSuggestButton,
                suggestionsExpanded = shouldShowSuggestions,
                onBack = { navController.navigateUp() },
                onToggleSuggestions = { shouldShowSuggestions = !shouldShowSuggestions },
                onSearch = { showSearchBar = true },
                onMore = onPlaylistMoreClick,
                background = playlistBg,
                accentPainter = aiPainter,
                accentProgress = progressAnimated,
            )

            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
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
                contentPadding = PaddingValues(top = 4.dp, bottom = 24.dp),
            ) {
                item(
                    key = "hero",
                    contentType = "header",
                ) {
                    Column(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                    ) {
                        PlaylistHero(
                            title = uiState.title,
                            trackCount = uiState.trackCount,
                            syncState = uiState.syncState,
                            thumbnail = uiState.thumbnail,
                            shape = currentShape,
                            coverColor = currentCoverColor,
                            isThisPlaylistPlaying = isPlaying && playingPlaylistId == LOCAL_PLAYLIST_ID + uiState.id,
                            downloadState = downloadState,
                            compact = compactHero,
                            onPlayPause = {
                                if (isPlaying && playingPlaylistId == LOCAL_PLAYLIST_ID + uiState.id) {
                                    sharedViewModel.onUIEvent(UIEvent.PlayPause)
                                } else {
                                    viewModel.onUIEvent(LocalPlaylistUIEvent.PlayClick)
                                }
                            },
                            onShuffle = { viewModel.onUIEvent(LocalPlaylistUIEvent.ShuffleClick) },
                            onDownload = {
                                if (downloadState == DownloadState.STATE_DOWNLOADED) {
                                    viewModel.makeToast(runBlocking { getString(Res.string.downloaded) })
                                } else if (downloadState == DownloadState.STATE_DOWNLOADING) {
                                    viewModel.makeToast(runBlocking { getString(Res.string.downloading) })
                                } else {
                                    viewModel.downloadFullPlaylist()
                                }
                            },
                            onCustomize = { showCoverCustomizer = true },
                        )

                        SuggestionsSection(
                            visible = shouldShowSuggestions,
                            loading = suggestionsLoading,
                            tracks = suggestedTracks,
                            playingVideoId = playingTrack?.videoId,
                            onReload = { viewModel.reloadSuggestion() },
                            onAdd = { viewModel.addSuggestTrackToListTrack(it) },
                            onPlay = { viewModel.onUIEvent(LocalPlaylistUIEvent.SuggestionsItemClick(it)) },
                        )

                        PlaylistControls(
                            filterState = uiState.filterState,
                            changingOrder = changingOrder,
                            onSortClick = { sortBottomSheetShow = true },
                            onToggleReorder = { changingOrder = !changingOrder },
                            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                        )
                    }
                }

                items(
                    count = trackPagingItems.itemCount,
                    key = { index -> trackPagingItems[index]?.second?.id ?: "placeholder_$index" },
                    contentType = { "song_row" },
                ) { index ->
                    val item = trackPagingItems[index]?.first
                    if (item != null) {
                        val isThisSongPlaying = playingTrack?.videoId == item.videoId && isPlaying
                        val rowModifier =
                            if (changingOrder) {
                                Modifier
                            } else {
                                Modifier.animateItem()
                            }
                        val row = @Composable { mod: Modifier ->
                            PlaylistSongRow(
                                song = item,
                                isPlaying = isThisSongPlaying,
                                showDragHandle = changingOrder,
                                selectionMode = selectionState.isActive,
                                isSelected = selectionState.isSelected(item.videoId),
                                // No long press while reordering: that gesture already
                                // belongs to drag.
                                onLongClick = if (changingOrder) null else ({ selectionState.start(it) }),
                                onSelectToggle = { selectionState.toggle(it) },
                                onClick = onPlaylistItemClick,
                                onMoreClick = onItemMoreClick,
                                onAddToQueue = { sharedViewModel.addListToQueue(arrayListOf(item.toTrack())) },
                                modifier = mod,
                                showDivider = index < trackPagingItems.itemCount - 1,
                            )
                        }
                        if (changingOrder) {
                            DraggableItem(
                                dragDropState,
                                index + PLAYLIST_LEADING_ITEMS,
                                Modifier.animateItem(),
                            ) {
                                row(Modifier)
                            }
                        } else {
                            row(rowModifier)
                        }
                    }
                }

                if (trackPagingItems.itemCount == 0 && trackPagingItems.loadState.refresh !is LoadState.Loading) {
                    item(
                        key = "empty",
                        contentType = "empty",
                    ) {
                        PlaylistEmptyState(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp, vertical = 32.dp),
                        )
                    }
                }

                if (trackPagingItems.loadState.refresh is LoadState.Loading ||
                    trackPagingItems.loadState.append is LoadState.Loading
                ) {
                    item(
                        key = "loading",
                        contentType = "loading",
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.height(15.dp))
                            CenterLoadingBox(modifier = Modifier.size(80.dp))
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }

                item(
                    key = "end",
                    contentType = "footer",
                ) {
                    EndOfPage()
                }
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
                    // peek() rather than get(): reading an item is what asks Paging to load
                    // its page, and select-all must not drag the whole playlist into memory.
                    selectionState.toggleSelectAll(
                        (0 until trackPagingItems.itemCount).mapNotNull {
                            trackPagingItems.peek(it)?.first?.videoId
                        },
                    )
                },
                onOpenActions = { showSelectionSheet = true },
                modifier =
                    Modifier.background(playlistBg),
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
            val track = currentItem ?: return@BoxWithConstraints
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
        // threading a second data source through it would put all of that at risk for a feature
        // that only needs a flat list. This draws over it instead and leaves it untouched.
        //
        // Results come from LocalPlaylistViewModel.searchResults, which queries the database —
        // filtering trackPagingItems would only ever search the pages already scrolled through.
        AnimatedVisibility(
            visible = showSearchBar,
            enter = SearchBarEnter,
            exit = SearchBarExit,
        ) {
            val searchFocusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current
            var searchSettled by remember { mutableStateOf(false) }
            var searchFocused by remember { mutableStateOf(false) }
            val searchBarColor by animateColorAsState(
                targetValue =
                    if (searchFocused) {
                        MaterialTheme.colorScheme.surfaceContainerHighest
                    } else {
                        MaterialTheme.colorScheme.surfaceContainerHigh
                    },
                animationSpec = spring(dampingRatio = 1f, stiffness = 900f),
                label = "playlistSearchBarColor",
            )

            // The view model debounces queries by 250 ms. Wait a little longer before
            // declaring "no results" so the empty state never flashes mid-query.
            LaunchedEffect(searchQuery) {
                val trimmed = searchQuery.trim()
                searchSettled = trimmed.length < 2
                if (trimmed.length >= 2) {
                    delay(500)
                    searchSettled = true
                }
            }
            LaunchedEffect(Unit) {
                delay(150)
                searchFocusRequester.requestFocus()
                keyboardController?.show()
            }

            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(playlistBg)
                        .imePadding(),
            ) {
                // Material 3 full-screen search: the back action sits inside the
                // field, so the bar uses the whole window width instead of being
                // squeezed next to a separate button.
                SearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = searchQuery,
                            onQueryChange = { viewModel.setSearchQuery(it) },
                            onSearch = { keyboardController?.hide() },
                            expanded = false,
                            onExpandedChange = {},
                            placeholder = {
                                Text(
                                    text = stringResource(Res.string.search),
                                    style = typo().bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            },
                            leadingIcon = {
                                IconButton(
                                    onClick = {
                                        showSearchBar = false
                                        viewModel.setSearchQuery("")
                                        keyboardController?.hide()
                                    },
                                ) {
                                    Icon(
                                        imageVector = SimpIcons.ArrowBack,
                                        contentDescription = stringResource(Res.string.playlist_close_search),
                                        tint = MaterialTheme.colorScheme.onSurface,
                                    )
                                }
                            },
                            trailingIcon = {
                                AnimatedVisibility(visible = searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                        Icon(
                                            imageVector = SimpIcons.Close,
                                            contentDescription = stringResource(Res.string.playlist_clear_search),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        )
                                    }
                                }
                            },
                        )
                    },
                    expanded = false,
                    onExpandedChange = {},
                    // No explicit height here: the collapsed M3 SearchBar is 64dp tall
                    // (a 56dp input field plus 8dp of internal top padding). Forcing
                    // 56dp clips the field.
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .windowInsetsPadding(WindowInsets.statusBars)
                            .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 10.dp)
                            .focusRequester(searchFocusRequester)
                            .onFocusChanged { searchFocused = it.isFocused },
                    shape = RoundedCornerShape(28.dp),
                    colors = SearchBarDefaults.colors(containerColor = searchBarColor),
                    windowInsets = WindowInsets(0, 0, 0, 0),
                    content = {},
                )

                val trimmedQuery = searchQuery.trim()
                when {
                    trimmedQuery.length < 2 ->
                        PlaylistSearchMessage(
                            icon = SimpIcons.Search,
                            title = stringResource(Res.string.search_songs_in_playlist),
                            subtitle = stringResource(Res.string.search_min_characters),
                            modifier = Modifier.weight(1f),
                        )

                    searchResults.isNotEmpty() ->
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(top = 4.dp, bottom = 24.dp),
                        ) {
                            item(
                                key = "search-header",
                                contentType = "header",
                            ) {
                                Text(
                                    text = stringResource(Res.string.songs),
                                    style = typo().labelLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier =
                                        Modifier.padding(
                                            start = 20.dp,
                                            end = 20.dp,
                                            top = 4.dp,
                                            bottom = 6.dp,
                                        ),
                                )
                            }
                            itemsIndexed(
                                searchResults,
                                key = { _, (song, pair) -> song.videoId + "_" + pair.position },
                                contentType = { _, _ -> "search-song" },
                            ) { index, (song, _) ->
                                PlaylistSongRow(
                                    song = song,
                                    isPlaying = playingTrack?.videoId == song.videoId && isPlaying,
                                    showDragHandle = false,
                                    selectionMode = selectionState.isActive,
                                    isSelected = selectionState.isSelected(song.videoId),
                                    onLongClick = { selectionState.start(it) },
                                    onSelectToggle = { selectionState.toggle(it) },
                                    onClick = {
                                        keyboardController?.hide()
                                        onPlaylistItemClick(it)
                                    },
                                    onMoreClick = {
                                        keyboardController?.hide()
                                        onItemMoreClick(it)
                                    },
                                    onAddToQueue = {
                                        sharedViewModel.addListToQueue(arrayListOf(song.toTrack()))
                                    },
                                    showDivider = index < searchResults.lastIndex,
                                )
                            }
                        }

                    !searchSettled ->
                        Box(
                            modifier = Modifier.weight(1f).fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(36.dp),
                                strokeWidth = 3.dp,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }

                    else ->
                        PlaylistSearchMessage(
                            icon = SimpIcons.Search,
                            title = stringResource(Res.string.no_results_found),
                            subtitle = stringResource(Res.string.no_songs_match_search),
                            modifier = Modifier.weight(1f),
                        )
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
}

/**
 * The one top bar this screen uses. It stays pinned above the list at every scroll
 * position and fades the playlist title in once the hero title scrolls out of view,
 * so the chrome never changes shape mid-scroll.
 */
@Composable
private fun PlaylistTopBar(
    title: String,
    titleProgress: Float,
    showSuggestionsAction: Boolean,
    suggestionsExpanded: Boolean,
    onBack: () -> Unit,
    onToggleSuggestions: () -> Unit,
    onSearch: () -> Unit,
    onMore: () -> Unit,
    background: Color,
    accentPainter: Painter,
    accentProgress: Float,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(background.copy(alpha = 0.97f * titleProgress))
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExpressiveTopBarIconButton(
            imageVector = SimpIcons.ArrowBack,
            contentDescription = stringResource(Res.string.playlist_back),
            onClick = onBack,
        )

        Text(
            text = title,
            style = typo().titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier =
                Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
                    .graphicsLayer {
                        alpha = titleProgress
                        translationY = (1f - titleProgress) * 6.dp.toPx()
                    },
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (showSuggestionsAction) {
                ExpressiveTopBarGroupButton(
                    contentDescription = stringResource(Res.string.playlist_ai_suggestions),
                    onClick = onToggleSuggestions,
                    isFirst = true,
                    isLast = false,
                    selected = suggestionsExpanded,
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(22.dp)
                                .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                                .drawWithContent {
                                    val width = size.width
                                    val height = size.height
                                    val offsetDraw = width * accentProgress
                                    val brush =
                                        Brush.linearGradient(
                                            colors = listOf(Color(0xFF4C82EF), Color(0xFFD96570)),
                                            start = Offset(offsetDraw, 0f),
                                            end = Offset(offsetDraw + width, height),
                                        )
                                    with(accentPainter) { draw(size = Size(width, height)) }
                                    drawRect(brush = brush, blendMode = BlendMode.SrcIn)
                                },
                    )
                }
            }

            ExpressiveTopBarGroupButton(
                contentDescription = stringResource(Res.string.playlist_search_in_playlist),
                onClick = onSearch,
                isFirst = !showSuggestionsAction,
                isLast = false,
            ) {
                Icon(
                    imageVector = SimpIcons.Search,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                )
            }

            ExpressiveTopBarGroupButton(
                contentDescription = stringResource(Res.string.more),
                onClick = onMore,
                isFirst = false,
                isLast = true,
            ) {
                Icon(
                    imageVector = SimpIcons.MoreVert,
                    contentDescription = null,
                    modifier = Modifier.size(22.dp),
                )
            }
        }
    }
}

/**
 * Hero block: shaped cover, title, metadata and the action group. Stateless on purpose —
 * the compact and wide arrangements share one implementation, so they cannot drift.
 */
@Composable
private fun PlaylistHero(
    title: String,
    trackCount: Int,
    syncState: Int,
    thumbnail: String?,
    shape: MaterialShapePreset,
    coverColor: Color,
    isThisPlaylistPlaying: Boolean,
    downloadState: Int,
    compact: Boolean,
    onPlayPause: () -> Unit,
    onShuffle: () -> Unit,
    onDownload: () -> Unit,
    onCustomize: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val editStyleLabel = stringResource(Res.string.playlist_edit_style)
    val customizeLabel = stringResource(Res.string.playlist_customize_cover)
    val coverSize = if (compact) 184.dp else 208.dp

    val cover =
        @Composable {
            Box(
                contentAlignment = Alignment.BottomEnd,
                modifier =
                    Modifier
                        .semantics { contentDescription = customizeLabel }
                        .clickable(onClickLabel = editStyleLabel) { onCustomize() },
            ) {
                ExpressivePlaylistCover(
                    thumbnailUrl = thumbnail,
                    title = title,
                    shapePreset = shape,
                    containerColor = coverColor,
                    size = coverSize,
                )
                // Discoverability for the customizer without a second, competing button
                // in the action group. Decorative: the whole cover is the tap target.
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.94f),
                    modifier = Modifier.padding(6.dp).size(32.dp),
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = SimpIcons.Tune,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp),
                        )
                    }
                }
            }
        }

    val actions =
        @Composable {
            PlaylistActionGroup(
                isPlaying = isThisPlaylistPlaying,
                downloadState = downloadState,
                onPlayPause = onPlayPause,
                onShuffle = onShuffle,
                onDownload = onDownload,
            )
        }

    if (compact) {
        Column(
            modifier = modifier.fillMaxWidth().padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            cover()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style =
                    MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp,
                    ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 12.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            PlaylistMetadata(trackCount = trackCount, syncState = syncState)
            Spacer(modifier = Modifier.height(18.dp))
            actions()
            Spacer(modifier = Modifier.height(4.dp))
        }
    } else {
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            cover()
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Text(
                    text = title,
                    style =
                        MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.5).sp,
                        ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                PlaylistMetadata(trackCount = trackCount, syncState = syncState)
                Spacer(modifier = Modifier.height(4.dp))
                actions()
            }
        }
    }
}

/** `12 songs · YouTube Synced` — a metadata line, not two tappable-looking chips. */
@Composable
private fun PlaylistMetadata(
    trackCount: Int,
    syncState: Int,
    modifier: Modifier = Modifier,
) {
    val syncLabel =
        when (syncState) {
            LocalPlaylistEntity.YouTubeSyncState.Synced -> stringResource(Res.string.playlist_youtube_synced)
            LocalPlaylistEntity.YouTubeSyncState.Syncing -> stringResource(Res.string.playlist_syncing)
            else -> stringResource(Res.string.playlist_local)
        }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = pluralStringResource(Res.plurals.n_song, trackCount, trackCount),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = " · ",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
        )
        if (syncState == LocalPlaylistEntity.YouTubeSyncState.Synced) {
            Icon(
                imageVector = SimpIcons.Sync,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(end = 4.dp).size(13.dp),
            )
        }
        Text(
            text = syncLabel,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Play / shuffle / download as one connected object instead of one filled pill floating
 * beside three identical circles. Same outer-20 / inner-8 corner language as the top bar.
 */
@Composable
private fun PlaylistActionGroup(
    isPlaying: Boolean,
    downloadState: Int,
    onPlayPause: () -> Unit,
    onShuffle: () -> Unit,
    onDownload: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val playLabel = stringResource(if (isPlaying) Res.string.playlist_pause else Res.string.playlist_play)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExpressiveActionSegment(
            onClick = onPlayPause,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            startCorner = 26.dp,
            endCorner = 8.dp,
            contentDescription = playLabel,
            contentPadding = PaddingValues(horizontal = 22.dp),
        ) {
            Icon(
                imageVector = if (isPlaying) SimpIcons.Pause else SimpIcons.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = playLabel,
                style = typo().titleSmall.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
            )
        }

        ExpressiveActionSegment(
            onClick = onShuffle,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurface,
            startCorner = 8.dp,
            endCorner = 8.dp,
            contentDescription = stringResource(Res.string.shuffle),
            fixedWidth = 52.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                imageVector = SimpIcons.Shuffle,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
            )
        }

        ExpressiveActionSegment(
            onClick = onDownload,
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurface,
            startCorner = 8.dp,
            endCorner = 26.dp,
            contentDescription =
                when (downloadState) {
                    DownloadState.STATE_DOWNLOADED -> stringResource(Res.string.downloaded)
                    DownloadState.STATE_DOWNLOADING -> stringResource(Res.string.downloading)
                    else -> stringResource(Res.string.download)
                },
            fixedWidth = 52.dp,
            contentPadding = PaddingValues(0.dp),
        ) {
            when (downloadState) {
                DownloadState.STATE_DOWNLOADED ->
                    Icon(
                        painter = painterResource(Res.drawable.baseline_downloaded),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp),
                    )

                DownloadState.STATE_DOWNLOADING ->
                    DownloadingIndicator(modifier = Modifier.size(28.dp))

                else ->
                    Icon(
                        imageVector = SimpIcons.DownloadForOffline,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                    )
            }
        }
    }
}

@Composable
private fun ExpressiveActionSegment(
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    startCorner: Dp,
    endCorner: Dp,
    contentDescription: String,
    modifier: Modifier = Modifier,
    fixedWidth: Dp? = null,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val restingStart = startCorner
    val restingEnd = endCorner
    val start by animateDpAsState(
        targetValue = if (isPressed) 26.dp else restingStart,
        animationSpec = expressiveSpring(dampingRatio = 0.85f, stiffness = 600f),
        label = "playlistActionStartCorner",
    )
    val end by animateDpAsState(
        targetValue = if (isPressed) 26.dp else restingEnd,
        animationSpec = expressiveSpring(dampingRatio = 0.85f, stiffness = 600f),
        label = "playlistActionEndCorner",
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = expressiveSpring(dampingRatio = 0.75f, stiffness = 500f),
        label = "playlistActionScale",
    )
    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        shape =
            RoundedCornerShape(
                topStart = start,
                bottomStart = start,
                topEnd = end,
                bottomEnd = end,
            ),
        color = containerColor,
        contentColor = contentColor,
        modifier =
            modifier
                .then(if (fixedWidth != null) Modifier.size(fixedWidth, 52.dp) else Modifier.height(52.dp))
                .semantics { this.contentDescription = contentDescription }
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
    ) {
        Row(
            modifier = Modifier.fillMaxHeight().padding(contentPadding),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Composable
private fun SuggestionsSection(
    visible: Boolean,
    loading: Boolean,
    tracks: List<Track>,
    playingVideoId: String?,
    onReload: () -> Unit,
    onAdd: (Track) -> Unit,
    onPlay: (String) -> Unit,
) {
    AnimatedVisibility(visible = visible) {
        Column(
            // A spring here would re-measure the lazy list while it is still settling.
            modifier = Modifier.animateContentSize(animationSpec = tween(220, easing = FastOutSlowInEasing)),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(Res.string.suggest),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 8.dp).weight(1f),
                )
                TextButton(
                    onClick = onReload,
                    modifier = Modifier.padding(horizontal = 8.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.reload),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Crossfade(targetState = loading) {
                if (it) {
                    CenterLoadingBox(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .align(Alignment.CenterHorizontally),
                    )
                } else {
                    Column {
                        tracks.forEach { track ->
                            SuggestItems(
                                forceDark = false,
                                track = track,
                                isPlaying = playingVideoId == track.videoId,
                                onAddClickListener = { onAdd(track) },
                                onClickListener = { onPlay(track.videoId) },
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

/**
 * Sort is an action that opens a sheet, not a filter applied in place — so it is a plain
 * pill button, not a [androidx.compose.material3.FilterChip]. The row keeps a fixed height
 * because the reorder toggle mounts and unmounts inside it.
 */
@Composable
private fun PlaylistControls(
    filterState: FilterState,
    changingOrder: Boolean,
    onSortClick: () -> Unit,
    onToggleReorder: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth().height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExpressivePillButton(
            onClick = onSortClick,
            modifier = Modifier.weight(1f, fill = false),
        ) {
            Icon(
                imageVector = SimpIcons.Sort,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = stringResource(Res.string.sort_by) + ": " + stringResource(filterState.displayNameRes()),
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        AnimatedVisibility(
            visible = filterState == FilterState.CustomOrder,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ExpressivePillButton(
                onClick = onToggleReorder,
                containerColor = if (changingOrder) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainerHigh,
                contentColor = if (changingOrder) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
            ) {
                Icon(
                    imageVector = if (changingOrder) SimpIcons.Check else SimpIcons.DragHandle,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text =
                        stringResource(
                            if (changingOrder) Res.string.playlist_reorder_done else Res.string.playlist_reorder,
                        ),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                )
            }
        }
    }
}

@Composable
private fun ExpressivePillButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = expressiveSpring(dampingRatio = 0.75f, stiffness = 500f),
        label = "playlistPillScale",
    )
    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(20.dp),
        color = containerColor,
        contentColor = contentColor,
        modifier =
            modifier
                .height(48.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
    ) {
        Row(
            modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

/**
 * One song row, shared by the paged playlist and the search results so the two lists can
 * never render differently. [modifier] is applied to the outer column: it carries the drag
 * offset while reordering and [Modifier.animateItem] otherwise.
 */
@Composable
private fun PlaylistSongRow(
    song: SongEntity,
    isPlaying: Boolean,
    showDragHandle: Boolean,
    selectionMode: Boolean,
    isSelected: Boolean,
    onLongClick: ((String) -> Unit)?,
    onSelectToggle: (String) -> Unit,
    onClick: (String) -> Unit,
    onMoreClick: (String) -> Unit,
    onAddToQueue: (String) -> Unit,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
) {
    Column(modifier = modifier) {
        Surface(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 2.dp),
            shape = RoundedCornerShape(16.dp),
            color =
                if (isPlaying) {
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                } else {
                    Color.Transparent
                },
        ) {
            SongFullWidthItems(
                modifier = Modifier,
                forceDark = false,
                isPlaying = isPlaying,
                shouldShowDragHandle = showDragHandle,
                songEntity = song,
                onMoreClickListener = onMoreClick,
                onClickListener = onClick,
                onAddToQueue = onAddToQueue,
                selectionMode = selectionMode,
                isSelected = isSelected,
                onLongClick = onLongClick,
                onSelectToggle = onSelectToggle,
            )
        }
        if (showDivider) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.2f),
                modifier = Modifier.padding(start = 72.dp, end = 20.dp),
                thickness = 0.5.dp,
            )
        }
    }
}

@Composable
private fun PlaylistEmptyState(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                modifier = Modifier.size(64.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = SimpIcons.Album,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.playlist_empty_title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(Res.string.playlist_empty_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
    }
}

/**
 * Tonal squircle action used by the playlist header. The corner radius and scale
 * react to press, matching the expressive controls on the artist screen.
 */
@Composable
private fun ExpressiveTopBarIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = expressiveSpring(dampingRatio = 0.75f, stiffness = 500f),
        label = "playlistTopBarBackScale",
    )
    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        contentColor = MaterialTheme.colorScheme.onSurface,
        modifier =
            modifier
                .size(48.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier.size(22.dp),
            )
        }
    }
}

/**
 * One segment of the connected tonal action group. Outer segments keep a 20.dp
 * cap on their outer edge and an 8.dp inner corner; pressing morphs the segment
 * into a standalone 20.dp pill, matching [ExpressiveLibraryButtonGroup].
 */
@Composable
private fun ExpressiveTopBarGroupButton(
    contentDescription: String,
    onClick: () -> Unit,
    isFirst: Boolean,
    isLast: Boolean,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val restingStart = if (isFirst) 20.dp else 8.dp
    val restingEnd = if (isLast) 20.dp else 8.dp
    val startCorner by animateDpAsState(
        targetValue = if (isPressed) 20.dp else restingStart,
        animationSpec = expressiveSpring(dampingRatio = 0.85f, stiffness = 600f),
        label = "playlistTopBarStartCorner",
    )
    val endCorner by animateDpAsState(
        targetValue = if (isPressed) 20.dp else restingEnd,
        animationSpec = expressiveSpring(dampingRatio = 0.85f, stiffness = 600f),
        label = "playlistTopBarEndCorner",
    )
    val containerColor by animateColorAsState(
        targetValue =
            when {
                selected -> MaterialTheme.colorScheme.secondaryContainer
                isPressed -> MaterialTheme.colorScheme.surfaceContainerHighest
                else -> MaterialTheme.colorScheme.surfaceContainerHigh
            },
        animationSpec = spring(dampingRatio = 1f, stiffness = 900f),
        label = "playlistTopBarGroupColor",
    )
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = expressiveSpring(dampingRatio = 0.75f, stiffness = 500f),
        label = "playlistTopBarGroupScale",
    )
    Surface(
        onClick = onClick,
        interactionSource = interactionSource,
        shape =
            RoundedCornerShape(
                topStart = startCorner,
                bottomStart = startCorner,
                topEnd = endCorner,
                bottomEnd = endCorner,
            ),
        color = containerColor,
        contentColor =
            if (selected) {
                MaterialTheme.colorScheme.onSecondaryContainer
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
        modifier =
            modifier
                .size(48.dp)
                .semantics { this.contentDescription = contentDescription }
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            content()
        }
    }
}

/**
 * Centered M3 hint / empty state for the in-playlist search overlay.
 */
@Composable
private fun PlaylistSearchMessage(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.size(72.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style = typo().titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = subtitle,
                    style = typo().bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 280.dp),
                )
            }
        }
    }
}
