package com.maxrave.simpmusic.ui.screen.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.maxrave.common.Config
import com.maxrave.domain.data.entities.ArtistEntity
import com.maxrave.domain.data.entities.SongEntity
import com.maxrave.domain.extension.now
import com.maxrave.domain.mediaservice.handler.PlaylistType
import com.maxrave.domain.mediaservice.handler.QueueData
import com.maxrave.domain.utils.LocalResource
import com.maxrave.domain.utils.toArrayListTrack
import com.maxrave.domain.utils.toTrack
import com.maxrave.simpmusic.extension.getStringBlocking
import com.maxrave.simpmusic.extension.getScreenSizeInfo
import com.maxrave.simpmusic.expect.BackHandler
import com.maxrave.simpmusic.ui.component.SearchBarExit
import com.maxrave.simpmusic.ui.component.SearchBarEnter
import com.maxrave.simpmusic.ui.component.AddToPlaylistModalBottomSheet
import com.maxrave.simpmusic.ui.component.ArtistFullWidthItems
import com.maxrave.simpmusic.ui.component.EndOfPage
import com.maxrave.simpmusic.ui.component.NowPlayingBottomSheet
import com.maxrave.simpmusic.ui.component.PlaylistFullWidthItems
import com.maxrave.simpmusic.ui.component.RippleIconButton
import com.maxrave.simpmusic.ui.component.SongFullWidthItems
import com.maxrave.simpmusic.ui.component.expressive.rememberPlaylistBackgroundColor
import com.maxrave.simpmusic.ui.component.selection.SelectedSongsBottomSheet
import com.maxrave.simpmusic.ui.component.selection.SongSelectionTopAppBar
import com.maxrave.simpmusic.ui.component.selection.rememberSongSelectionState
import com.maxrave.simpmusic.ui.icon.ArrowBack
import com.maxrave.simpmusic.ui.icon.ArrowBackIosNew
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.DownloadForOffline
import com.maxrave.simpmusic.ui.icon.PlayArrow
import com.maxrave.simpmusic.ui.icon.PlayCircle
import com.maxrave.simpmusic.ui.icon.Search
import com.maxrave.simpmusic.ui.icon.Shuffle
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.navigation.destination.list.AlbumDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.ArtistDestination
import com.maxrave.simpmusic.ui.screen.home.analytics.monthFullName
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.viewModel.AnalyticsUiState
import com.maxrave.simpmusic.viewModel.AnalyticsViewModel
import com.maxrave.simpmusic.viewModel.LibraryDynamicPlaylistViewModel
import com.maxrave.simpmusic.viewModel.SongSelectionViewModel
import com.maxrave.simpmusic.viewModel.SharedViewModel
import com.maxrave.simpmusic.ui.screen.home.analytics.formatNumericSpan
import com.maxrave.simpmusic.ui.screen.home.analytics.labelRes
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.album_length
import simpmusic.composeapp.generated.resources.artists
import simpmusic.composeapp.generated.resources.available_offline
import simpmusic.composeapp.generated.resources.back_to_library
import simpmusic.composeapp.generated.resources.downloaded
import simpmusic.composeapp.generated.resources.favorite
import simpmusic.composeapp.generated.resources.followed
import simpmusic.composeapp.generated.resources.liked_songs
import simpmusic.composeapp.generated.resources.liked_songs_count
import simpmusic.composeapp.generated.resources.lower_plays
import simpmusic.composeapp.generated.resources.most_played
import simpmusic.composeapp.generated.resources.n_song
import simpmusic.composeapp.generated.resources.no_downloaded_songs
import simpmusic.composeapp.generated.resources.no_downloaded_songs_description
import simpmusic.composeapp.generated.resources.no_songs_match_search
import simpmusic.composeapp.generated.resources.no_songs_match_search_description
import simpmusic.composeapp.generated.resources.search
import simpmusic.composeapp.generated.resources.search_downloaded_songs
import simpmusic.composeapp.generated.resources.seconds
import simpmusic.composeapp.generated.resources.wrapped
import simpmusic.composeapp.generated.resources.wrapped_recap_month
import simpmusic.composeapp.generated.resources.wrapped_recap_month_year
import simpmusic.composeapp.generated.resources.wrapped_recap_subtitle
import simpmusic.composeapp.generated.resources.your_top_albums
import simpmusic.composeapp.generated.resources.your_top_artists
import simpmusic.composeapp.generated.resources.your_top_tracks

@Composable
@ExperimentalMaterial3Api
fun LibraryDynamicPlaylistScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    type: String,
    viewModel: LibraryDynamicPlaylistViewModel = koinViewModel(),
    analyticsViewModel: AnalyticsViewModel = koinViewModel(),
    sharedViewModel: SharedViewModel = koinInject(),
) {
    val nowPlayingVideoId by viewModel.nowPlayingVideoId.collectAsStateWithLifecycle()

    var chosenSong: SongEntity? by remember { mutableStateOf(null) }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var showSearchBar by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    val selectionState = rememberSongSelectionState()
    val selectionViewModel: SongSelectionViewModel = koinViewModel()
    var showSelectionSheet by rememberSaveable { mutableStateOf(false) }
    var showSelectionAddToPlaylist by rememberSaveable { mutableStateOf(false) }

    val favorite by viewModel.listFavoriteSong.collectAsStateWithLifecycle()
    var tempFavorite by remember { mutableStateOf(emptyList<SongEntity>()) }
    val followed by viewModel.listFollowedArtist.collectAsStateWithLifecycle()
    var tempFollowed by remember { mutableStateOf(emptyList<ArtistEntity>()) }
    val mostPlayed by viewModel.listMostPlayedSong.collectAsStateWithLifecycle()
    var tempMostPlayed by remember { mutableStateOf(emptyList<SongEntity>()) }
    val downloaded by viewModel.listDownloadedSong.collectAsStateWithLifecycle()
    var tempDownloaded by remember { mutableStateOf(emptyList<SongEntity>()) }
    val monthlyRecap by viewModel.listMonthlyRecapSong.collectAsStateWithLifecycle()
    var tempMonthlyRecap by remember { mutableStateOf(emptyList<SongEntity>()) }
    val artistLiked by viewModel.listArtistLikedSong.collectAsStateWithLifecycle()
    val artistLikedName by viewModel.artistLikedName.collectAsStateWithLifecycle()
    var tempArtistLiked by remember { mutableStateOf(emptyList<SongEntity>()) }
    val analyticsUIState by analyticsViewModel.analyticsUIState.collectAsStateWithLifecycle()
    var tempTopTracks by remember { mutableStateOf(analyticsUIState.topTracks.data ?: emptyList()) }
    var tempTopArtists by remember { mutableStateOf(analyticsUIState.topArtists.data ?: emptyList()) }
    var tempTopAlbums by remember { mutableStateOf(analyticsUIState.topAlbums.data ?: emptyList()) }
    val playlistType = LibraryDynamicPlaylistType.toType(type)
    val isDownloaded = playlistType == LibraryDynamicPlaylistType.Downloaded
    val screenInfo = getScreenSizeInfo()
    val isPortrait = screenInfo.wDP < screenInfo.hDP
    // Downloaded songs have no artwork, so the page pastel comes from the app's primary role.
    // Other dynamic lists keep the plain theme background.
    val downloadedPageBg = rememberPlaylistBackgroundColor(MaterialTheme.colorScheme.primary)
    val pageBg = if (isDownloaded) downloadedPageBg else MaterialTheme.colorScheme.background
    val lazyState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val collapseThreshold = with(LocalDensity.current) { 48.dp.roundToPx() }
    val isHeaderCollapsed by remember(collapseThreshold) {
        derivedStateOf {
            lazyState.firstVisibleItemIndex > 0 ||
                lazyState.firstVisibleItemScrollOffset > collapseThreshold
        }
    }
    val showCollapsedBar =
        isHeaderCollapsed &&
            !selectionState.isActive &&
            !(showSearchBar && lazyState.firstVisibleItemIndex == 0)
    val toggleSearch: () -> Unit = {
        if (showSearchBar) {
            showSearchBar = false
            query = ""
            keyboardController?.hide()
            coroutineScope.launch { lazyState.animateScrollToItem(0) }
        } else {
            showSearchBar = true
        }
    }
    val selectAllVisible: () -> Unit = {
        val visible =
            when (playlistType) {
                is LibraryDynamicPlaylistType.TopTracks ->
                    (
                        if (query.isNotEmpty() && showSearchBar) {
                            tempTopTracks
                        } else {
                            analyticsUIState.topTracks.data ?: emptyList()
                        }
                    ).map { it.second.videoId }

                LibraryDynamicPlaylistType.Downloaded ->
                    (if (query.isNotEmpty() && showSearchBar) tempDownloaded else downloaded)
                        .map { it.videoId }

                LibraryDynamicPlaylistType.Favorite ->
                    (if (query.isNotEmpty() && showSearchBar) tempFavorite else favorite)
                        .map { it.videoId }

                LibraryDynamicPlaylistType.MostPlayed ->
                    (if (query.isNotEmpty() && showSearchBar) tempMostPlayed else mostPlayed)
                        .map { it.videoId }

                is LibraryDynamicPlaylistType.ArtistLiked ->
                    (if (query.isNotEmpty() && showSearchBar) tempArtistLiked else artistLiked)
                        .map { it.videoId }

                else -> emptyList()
            }
        selectionState.toggleSelectAll(visible)
    }
    // Search is an overlay mode, not a destination: system back has to close it before the
    // route beneath the screen is allowed to pop.
    BackHandler(enabled = isDownloaded && showSearchBar) {
        showSearchBar = false
        query = ""
        keyboardController?.hide()
    }
    // Opening search from the collapsed bar has to return to the header first, otherwise the
    // field would be composed above the current scroll position.
    LaunchedEffect(showSearchBar) {
        if (isDownloaded && showSearchBar) {
            lazyState.animateScrollToItem(0)
        }
    }
    // The other lists are observed from the database and are already loaded by the time this
    // screen opens; a recap is one month's ranking, so it can only be fetched once the route says
    // which month. Keyed on the route argument rather than the parsed type so re-entering the same
    // month does not re-query.
    LaunchedEffect(type) {
        val parsed = LibraryDynamicPlaylistType.toType(type)
        if (parsed is LibraryDynamicPlaylistType.MonthlyRecap) {
            viewModel.getMonthlyRecapSong(parsed)
        }
        if (parsed is LibraryDynamicPlaylistType.ArtistLiked) {
            viewModel.getArtistLikedSong(parsed)
        }
        // A top list opened from an older period. This screen gets its own analytics view model,
        // which starts on the latest period, so without this every older period opened as the
        // newest one.
        parsed.topListRange()?.let { (start, end) -> analyticsViewModel.showRange(start, end) }
    }

    LaunchedEffect(query) {
        tempFavorite = favorite.filter { it.matches(query) }
        tempFollowed = followed.filter { it.name.contains(query, ignoreCase = true) }
        tempMostPlayed = mostPlayed.filter { it.matches(query) }
        tempDownloaded = downloaded.filter { it.matches(query) }
        tempMonthlyRecap = monthlyRecap.filter { it.matches(query) }
        tempArtistLiked = artistLiked.filter { it.matches(query) }
        tempTopTracks =
            analyticsUIState.topTracks.data
                ?.filter { it.second.matches(query) }
                ?: emptyList()
        tempTopArtists =
            analyticsUIState.topArtists.data
                ?.filter { it.second.name.contains(query, ignoreCase = true) }
                ?: emptyList()
        tempTopAlbums =
            analyticsUIState.topAlbums.data
                ?.filter { matchesQuery(it.second.title, it.second.artistName, query) }
                ?: emptyList()
    }

    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .background(pageBg),
        state = lazyState,
        contentPadding = innerPadding,
    ) {
        val type = playlistType
        if (isDownloaded) {
            item(
                key = "downloaded_header",
                contentType = "downloaded_header",
            ) {
                DownloadedHeader(
                    isPortrait = isPortrait,
                    count = downloaded.size,
                    isSearchActive = showSearchBar,
                    query = query,
                    onQueryChange = { query = it },
                    onToggleSearch = toggleSearch,
                    onBack = { navController.navigateUp() },
                    onPlayAll = { viewModel.playAll(playlistType) },
                    onShuffle = { viewModel.shuffle(playlistType) },
                )
            }
        } else {
            item {
                Spacer(Modifier.height(64.dp))
            }
            item {
                AnimatedVisibility(showSearchBar) {
                    Spacer(Modifier.height(55.dp))
                }
            }
        }
        if (type == LibraryDynamicPlaylistType.Followed) {
            items(
                if (query.isNotEmpty() && showSearchBar) {
                    tempFollowed
                } else {
                    followed
                },
                key = { it.channelId },
            ) { artist ->
                ArtistFullWidthItems(
                    artist,
                    onClickListener = {
                        navController.navigate(
                            ArtistDestination(
                                channelId = artist.channelId,
                            ),
                        )
                    },
                )
            }
        } else if (type is LibraryDynamicPlaylistType.TopArtists) {
            when (analyticsUIState.topArtists) {
                is LocalResource.Success if (!analyticsUIState.topArtists.data.isNullOrEmpty()) -> {
                    val data = analyticsUIState.topArtists.data ?: emptyList()
                    items(
                        if (query.isNotEmpty() && showSearchBar) {
                            tempTopArtists
                        } else {
                            data
                        },
                        key = { it.first.hashCode() },
                    ) { artist ->
                        ArtistFullWidthItems(
                            artist.second,
                            rightView = {
                                Box(Modifier.padding(horizontal = 8.dp)) {
                                    Text(
                                        text = "${artist.first.playCount} ${stringResource(Res.string.lower_plays)}",
                                        style = typo().bodySmall,
                                    )
                                }
                            },
                            onClickListener = {
                                navController.navigate(
                                    ArtistDestination(
                                        channelId = artist.second.channelId,
                                    ),
                                )
                            },
                        )
                    }
                }

                else -> {}
            }
        } else if (type is LibraryDynamicPlaylistType.TopAlbums) {
            when (analyticsUIState.topAlbums) {
                is LocalResource.Success if (!analyticsUIState.topAlbums.data.isNullOrEmpty()) -> {
                    val data = analyticsUIState.topAlbums.data ?: emptyList()
                    items(
                        if (query.isNotEmpty() && showSearchBar) {
                            tempTopAlbums
                        } else {
                            data
                        },
                        key = { it.first.hashCode() },
                    ) { album ->
                        PlaylistFullWidthItems(
                            album.second,
                            rightView = {
                                Box(Modifier.padding(horizontal = 8.dp)) {
                                    Text(
                                        text = "${album.first.playCount} ${stringResource(Res.string.lower_plays)}",
                                        style = typo().bodySmall,
                                    )
                                }
                            },
                            onClickListener = {
                                navController.navigate(
                                    AlbumDestination(
                                        browseId = album.second.browseId,
                                    ),
                                )
                            },
                        )
                    }
                }

                else -> {}
            }
        } else if (type is LibraryDynamicPlaylistType.TopTracks) {
            when (analyticsUIState.topTracks) {
                is LocalResource.Success if (!analyticsUIState.topTracks.data.isNullOrEmpty()) -> {
                    val data = analyticsUIState.topTracks.data ?: emptyList()
                    items(
                        if (query.isNotEmpty() && showSearchBar) {
                            tempTopTracks
                        } else {
                            data
                        },
                        key = { it.hashCode() },
                    ) { song ->
                        SongFullWidthItems(
                            songEntity = song.second,
                            isPlaying = song.second.videoId == nowPlayingVideoId,
                            modifier = Modifier.fillMaxWidth(),
                            onMoreClickListener = {
                                chosenSong = song.second
                                showBottomSheet = true
                            },
                            onClickListener = { videoId ->
                                val targetList = data.map { it.second }
                                val playTrack = song.second
                                with(sharedViewModel) {
                                    setQueueData(
                                        QueueData.Data(
                                            listTracks = targetList.toArrayListTrack(),
                                            firstPlayedTrack = playTrack.toTrack(),
                                            playlistId = null,
                                            playlistName = getStringBlocking(Res.string.your_top_tracks),
                                            playlistType = PlaylistType.RADIO,
                                            continuation = null,
                                        ),
                                    )
                                    loadMediaItem(
                                        playTrack.toTrack(),
                                        Config.PLAYLIST_CLICK,
                                        targetList.indexOf(playTrack).coerceAtLeast(0),
                                    )
                                }
                            },
                            onAddToQueue = {
                                sharedViewModel.addListToQueue(
                                    arrayListOf(song.second.toTrack()),
                                )
                            },
                            selectionMode = selectionState.isActive,
                            isSelected = selectionState.isSelected(song.second.videoId),
                            onLongClick = { selectionState.start(it) },
                            onSelectToggle = { selectionState.toggle(it) },
                            rightView = {
                                Column(
                                    modifier = Modifier.wrapContentWidth(),
                                    verticalArrangement = Arrangement.spacedBy(4.dp),
                                ) {
                                    Text(
                                        text = "${song.first.totalListeningTime} ${stringResource(Res.string.seconds)}",
                                        style = typo().bodySmall,
                                    )
                                    Text(
                                        text = "${song.first.playCount} ${stringResource(Res.string.lower_plays)}",
                                        style = typo().bodySmall,
                                    )
                                }
                            },
                        )
                    }
                }

                else -> {}
            }
        } else {
            val songs =
                when (type) {
                    LibraryDynamicPlaylistType.Downloaded ->
                        if (query.isNotEmpty() && showSearchBar) {
                            tempDownloaded
                        } else {
                            downloaded
                        }

                    LibraryDynamicPlaylistType.Favorite ->
                        if (query.isNotEmpty() && showSearchBar) {
                            tempFavorite
                        } else {
                            favorite
                        }

                    LibraryDynamicPlaylistType.MostPlayed ->
                        if (query.isNotEmpty() && showSearchBar) {
                            tempMostPlayed
                        } else {
                            mostPlayed
                        }

                    // Kept as an explicit branch rather than an `else`: the three cases above are
                    // reachable only because the `if` chain around this block has already ruled
                    // out every other object, and an `else` here would silently swallow the next
                    // case someone adds to the sealed class.
                    is LibraryDynamicPlaylistType.MonthlyRecap ->
                        if (query.isNotEmpty() && showSearchBar) {
                            tempMonthlyRecap
                        } else {
                            monthlyRecap
                        }

                    is LibraryDynamicPlaylistType.ArtistLiked ->
                        if (query.isNotEmpty() && showSearchBar) {
                            tempArtistLiked
                        } else {
                            artistLiked
                        }

                    // Handled by the surrounding if chain; listed explicitly so the when stays
                    // exhaustive when a new list type is added.
                    LibraryDynamicPlaylistType.Followed,
                    is LibraryDynamicPlaylistType.TopTracks,
                    is LibraryDynamicPlaylistType.TopArtists,
                    is LibraryDynamicPlaylistType.TopAlbums -> emptyList()
                }
            if (isDownloaded && songs.isEmpty()) {
                item(
                    key = "downloaded_empty",
                    contentType = "downloaded_empty",
                ) {
                    DownloadedEmptyState(
                        isSearching = showSearchBar && query.isNotEmpty(),
                        onBack = { navController.navigateUp() },
                    )
                }
            } else {
                items(songs, key = { it.hashCode() }) { song ->
                    SongFullWidthItems(
                        songEntity = song,
                        isPlaying = song.videoId == nowPlayingVideoId,
                        modifier = Modifier.fillMaxWidth(),
                        onMoreClickListener = {
                            chosenSong = song
                            showBottomSheet = true
                        },
                        onClickListener = { videoId ->
                            viewModel.playSong(videoId, type = type)
                        },
                        onAddToQueue = {
                            sharedViewModel.addListToQueue(
                                arrayListOf(song.toTrack()),
                            )
                        },
                        selectionMode = selectionState.isActive,
                        isSelected = selectionState.isSelected(song.videoId),
                        onLongClick = { selectionState.start(it) },
                        onSelectToggle = { selectionState.toggle(it) },
                    )
                }
            }
        }
        if (!isDownloaded || downloaded.isNotEmpty()) {
            item {
                EndOfPage()
            }
        }
    }
    if (showSelectionSheet) {
        val selectedIds = selectionState.selected.toList()
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
    if (showBottomSheet) {
        NowPlayingBottomSheet(
            onDismiss = {
                showBottomSheet = false
                chosenSong = null
            },
            navController = navController,
            song = chosenSong ?: return,
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (!isDownloaded) {
            val type = playlistType
            val isSongType =
                type != LibraryDynamicPlaylistType.Followed &&
                    type !is LibraryDynamicPlaylistType.TopArtists &&
                    type !is LibraryDynamicPlaylistType.TopAlbums
            // Counts always come from the unfiltered lists, so the subtitle keeps reporting the
            // library total while the user is typing in the search bar.
            val subtitle =
                when (type) {
                    LibraryDynamicPlaylistType.Favorite ->
                        stringResource(Res.string.album_length, favorite.size.toString(), "")
                    LibraryDynamicPlaylistType.MostPlayed ->
                        stringResource(Res.string.album_length, mostPlayed.size.toString(), "")
                    LibraryDynamicPlaylistType.Downloaded ->
                        stringResource(Res.string.album_length, downloaded.size.toString(), "")
                    LibraryDynamicPlaylistType.Followed ->
                        "${followed.size} ${stringResource(Res.string.artists)}"
                    is LibraryDynamicPlaylistType.MonthlyRecap ->
                        stringResource(Res.string.wrapped_recap_subtitle)
                    is LibraryDynamicPlaylistType.ArtistLiked ->
                        listOfNotNull(
                            pluralStringResource(Res.plurals.liked_songs_count, artistLiked.size, artistLiked.size),
                            artistLikedName,
                        ).joinToString(" · ")
                    is LibraryDynamicPlaylistType.TopTracks,
                    is LibraryDynamicPlaylistType.TopArtists,
                    is LibraryDynamicPlaylistType.TopAlbums -> topListSubtitle(type)
                    else -> null
                }
            Box {
                TopAppBar(
                    title = {
                        Column {
                            Text(
                                text = type.title(),
                                style = typo().titleMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            if (subtitle != null) {
                                Text(
                                    text = subtitle,
                                    style = typo().bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    },
                    navigationIcon = {
                        Box(Modifier.padding(horizontal = 5.dp)) {
                            RippleIconButton(
                                SimpIcons.ArrowBackIosNew,
                                Modifier
                                    .size(32.dp),
                                true,
                                tint = MaterialTheme.colorScheme.onBackground,
                            ) {
                                navController.navigateUp()
                            }
                        }
                    },
                    actions = {
                        if (isSongType) {
                            RippleIconButton(
                                SimpIcons.PlayCircle,
                                Modifier
                                    .size(48.dp),
                                fillMaxSize = true,
                                tint = MaterialTheme.colorScheme.onBackground,
                            ) {
                                if (type is LibraryDynamicPlaylistType.TopTracks) {
                                    val data = analyticsUIState.topTracks.data
                                    if (!data.isNullOrEmpty()) {
                                        val first = data.first().second
                                        sharedViewModel.setQueueData(
                                            QueueData.Data(
                                                listTracks = data.map { it.second }.toArrayListTrack(),
                                                firstPlayedTrack = first.toTrack(),
                                                playlistId = null,
                                                playlistName = getStringBlocking(Res.string.your_top_tracks),
                                                playlistType = PlaylistType.RADIO,
                                                continuation = null,
                                            ),
                                        )
                                        sharedViewModel.loadMediaItem(
                                            first.toTrack(),
                                            Config.PLAYLIST_CLICK,
                                            0,
                                        )
                                    }
                                } else {
                                    viewModel.playAll(type)
                                }
                            }
                            RippleIconButton(
                                SimpIcons.Shuffle,
                                Modifier.size(32.dp),
                                true,
                                tint = MaterialTheme.colorScheme.onBackground,
                            ) {
                                if (type is LibraryDynamicPlaylistType.TopTracks) {
                                    val data = analyticsUIState.topTracks.data
                                    if (!data.isNullOrEmpty()) {
                                        val shuffled = data.shuffled()
                                        val first = shuffled.first().second
                                        sharedViewModel.setQueueData(
                                            QueueData.Data(
                                                listTracks = shuffled.map { it.second }.toArrayListTrack(),
                                                firstPlayedTrack = first.toTrack(),
                                                playlistId = null,
                                                playlistName = getStringBlocking(Res.string.your_top_tracks),
                                                playlistType = PlaylistType.RADIO,
                                                continuation = null,
                                            ),
                                        )
                                        sharedViewModel.loadMediaItem(
                                            first.toTrack(),
                                            Config.PLAYLIST_CLICK,
                                            0,
                                        )
                                    }
                                } else {
                                    viewModel.shuffle(type)
                                }
                            }
                        }
                        Box(Modifier.padding(horizontal = 5.dp)) {
                            RippleIconButton(
                                if (showSearchBar) SimpIcons.Close else SimpIcons.Search,
                                Modifier
                                    .size(32.dp),
                                true,
                                tint = MaterialTheme.colorScheme.onBackground,
                            ) {
                                showSearchBar = !showSearchBar
                            }
                        }
                    },
                    modifier =
                        Modifier
                            .background(MaterialTheme.colorScheme.background),
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                        ),
                )
                // Drawn last inside the same Box, with an opaque colour, so it covers the normal bar
                // instead of pushing it around — the search bar below keeps its position either way.
                if (selectionState.isActive) {
                    SongSelectionTopAppBar(
                        state = selectionState,
                        onSelectAll = selectAllVisible,
                        onOpenActions = { showSelectionSheet = true },
                        containerColor = Color.Black,
                    )
                }
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showSearchBar,
                enter = SearchBarEnter,
                exit = SearchBarExit,
            ) {
                SearchBar(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .padding(horizontal = 12.dp),
                    inputField = {
                        CompositionLocalProvider(LocalTextStyle provides typo().bodySmall) {
                            SearchBarDefaults.InputField(
                                query = query,
                                onQueryChange = { query = it },
                                onSearch = { showSearchBar = false },
                                expanded = showSearchBar,
                                onExpandedChange = { showSearchBar = it },
                                placeholder = {
                                    Text(
                                        stringResource(Res.string.search),
                                        style = typo().bodySmall,
                                    )
                                },
                                leadingIcon = { Icon(SimpIcons.Search, contentDescription = null) },
                            )
                        }
                    },
                    expanded = false,
                    onExpandedChange = {},
                    windowInsets = WindowInsets(0, 0, 0, 0),
                ) {
                }
            }
        } else {
            if (selectionState.isActive) {
                SongSelectionTopAppBar(
                    state = selectionState,
                    onSelectAll = selectAllVisible,
                    onOpenActions = { showSelectionSheet = true },
                    containerColor = pageBg,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                )
            } else {
                AnimatedVisibility(
                    visible = showCollapsedBar,
                    enter =
                        fadeIn(tween(160, easing = FastOutSlowInEasing)) +
                            slideInVertically(
                                animationSpec = tween(180, easing = FastOutSlowInEasing),
                            ) { -it / 3 },
                    exit =
                        fadeOut(tween(120, easing = FastOutSlowInEasing)) +
                            slideOutVertically(
                                animationSpec = tween(160, easing = FastOutSlowInEasing),
                            ) { -it / 3 },
                ) {
                    TopAppBar(
                        title = {
                            Text(
                                text = stringResource(Res.string.downloaded),
                                style = typo().titleMedium.copy(fontWeight = FontWeight.Bold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        navigationIcon = {
                            FilledTonalIconButton(
                                onClick = { navController.navigateUp() },
                                modifier =
                                    Modifier
                                        .padding(start = 6.dp)
                                        .size(40.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors =
                                    IconButtonDefaults.filledTonalIconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                    ),
                            ) {
                                Icon(
                                    imageVector = SimpIcons.ArrowBack,
                                    contentDescription = "Back",
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                        },
                        actions = {
                            FilledTonalIconButton(
                                onClick = toggleSearch,
                                modifier = Modifier.size(40.dp),
                                shape = RoundedCornerShape(14.dp),
                                colors =
                                    IconButtonDefaults.filledTonalIconButtonColors(
                                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                        contentColor = MaterialTheme.colorScheme.onSurface,
                                    ),
                            ) {
                                Icon(
                                    imageVector = if (showSearchBar) SimpIcons.Close else SimpIcons.Search,
                                    contentDescription =
                                        if (showSearchBar) {
                                            "Close search"
                                        } else {
                                            stringResource(Res.string.search_downloaded_songs)
                                        },
                                    modifier = Modifier.size(20.dp),
                                )
                            }
                            Spacer(Modifier.size(4.dp))
                        },
                        colors =
                            TopAppBarDefaults.topAppBarColors(
                                containerColor = pageBg,
                                scrolledContainerColor = pageBg,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                            ),
                    )
                }
            }
        }
    }
}

/**
 * Material 3 Expressive header for the Downloaded library list.
 *
 * The top row keeps navigation and search within thumb reach; below it the hero gives the list
 * one clear identity and its two primary actions. Search replaces the hero rather than opening a
 * second screen, so the results appear in the same scroll container and system back can close it.
 */
@Composable
private fun DownloadedHeader(
    isPortrait: Boolean,
    count: Int,
    isSearchActive: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    onToggleSearch: () -> Unit,
    onBack: () -> Unit,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FilledTonalIconButton(
                onClick = onBack,
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(18.dp),
                colors =
                    IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
            ) {
                Icon(
                    imageVector = SimpIcons.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.size(22.dp),
                )
            }
            FilledTonalIconButton(
                onClick = onToggleSearch,
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(18.dp),
                colors =
                    IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
            ) {
                Icon(
                    imageVector = if (isSearchActive) SimpIcons.Close else SimpIcons.Search,
                    contentDescription =
                        if (isSearchActive) {
                            "Close search"
                        } else {
                            stringResource(Res.string.search_downloaded_songs)
                        },
                    modifier = Modifier.size(22.dp),
                )
            }
        }
        if (isSearchActive) {
            DownloadedSearchField(
                query = query,
                onQueryChange = onQueryChange,
                modifier = Modifier.padding(bottom = 20.dp),
            )
        } else if (isPortrait) {
            DownloadedHeroPortrait(
                count = count,
                onPlayAll = onPlayAll,
                onShuffle = onShuffle,
            )
        } else {
            DownloadedHeroLandscape(
                count = count,
                onPlayAll = onPlayAll,
                onShuffle = onShuffle,
            )
        }
    }
}

@Composable
private fun DownloadedHeroPortrait(
    count: Int,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DownloadedHeroIcon(size = 132.dp)
        Spacer(Modifier.height(20.dp))
        Text(
            text = stringResource(Res.string.downloaded),
            style =
                MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.5).sp,
                ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(Modifier.height(12.dp))
        DownloadedMetaRow(count = count)
        if (count > 0) {
            Spacer(Modifier.height(20.dp))
            DownloadedActionRow(
                count = count,
                onPlayAll = onPlayAll,
                onShuffle = onShuffle,
            )
        }
    }
}

@Composable
private fun DownloadedHeroLandscape(
    count: Int,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(28.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DownloadedHeroIcon(size = 124.dp)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Text(
                text = stringResource(Res.string.downloaded),
                style =
                    MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.5).sp,
                    ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            DownloadedMetaRow(count = count)
            if (count > 0) {
                DownloadedActionRow(
                    count = count,
                    onPlayAll = onPlayAll,
                    onShuffle = onShuffle,
                )
            }
        }
    }
}

/**
 * The one expressive moment on this page: a large tonal squircle that gives the downloaded
 * library a recognisable mark without borrowing album art it does not have.
 */
@Composable
private fun DownloadedHeroIcon(size: Dp) {
    Surface(
        shape = RoundedCornerShape(size * 0.28f),
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        tonalElevation = 2.dp,
        modifier = Modifier.size(size),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = SimpIcons.DownloadForOffline,
                contentDescription = null,
                modifier = Modifier.size(size * 0.42f),
            )
        }
    }
}

@Composable
private fun DownloadedMetaRow(count: Int) {
    val countText = pluralStringResource(Res.plurals.n_song, count, count)
    Text(
        text =
            if (count > 0) {
                "$countText · ${stringResource(Res.string.available_offline)}"
            } else {
                countText
            },
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun DownloadedActionRow(
    count: Int,
    onPlayAll: () -> Unit,
    onShuffle: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = onPlayAll,
            enabled = count > 0,
            shape = RoundedCornerShape(28.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 14.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 0.dp),
        ) {
            Icon(
                imageVector = SimpIcons.PlayArrow,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.size(8.dp))
            Text(
                text = "Play",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        FilledTonalIconButton(
            onClick = onShuffle,
            enabled = count > 0,
            modifier = Modifier.size(52.dp),
            shape = CircleShape,
            colors =
                IconButtonDefaults.filledTonalIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
        ) {
            Icon(
                imageVector = SimpIcons.Shuffle,
                contentDescription = "Shuffle",
                modifier = Modifier.size(22.dp),
            )
        }
    }
}

@Composable
private fun DownloadedSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboard?.show()
    }
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier =
            modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = stringResource(Res.string.search_downloaded_songs),
                style = MaterialTheme.typography.bodyLarge,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = SimpIcons.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = SimpIcons.Close,
                        contentDescription = "Clear search",
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(28.dp),
        textStyle = MaterialTheme.typography.bodyLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { keyboard?.hide() }),
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
    )
}

@Composable
private fun DownloadedEmptyState(
    isSearching: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // The hero already supplies the large download mark in the empty-download case;
        // only the search variant needs its own icon.
        if (isSearching) {
            Surface(
                shape = RoundedCornerShape(28.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.size(88.dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = SimpIcons.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(40.dp),
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
        }
        Text(
            text =
                stringResource(
                    if (isSearching) Res.string.no_songs_match_search else Res.string.no_downloaded_songs,
                ),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
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
            FilledTonalButton(onClick = onBack) {
                Icon(
                    imageVector = SimpIcons.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.size(8.dp))
                Text(stringResource(Res.string.back_to_library))
            }
        }
    }
}

sealed class LibraryDynamicPlaylistType {
    data object Favorite : LibraryDynamicPlaylistType()

    data object Followed : LibraryDynamicPlaylistType()

    data object MostPlayed : LibraryDynamicPlaylistType()

    data object Downloaded : LibraryDynamicPlaylistType()

    /**
     * The three top lists carry the period they were opened for, so the list on screen is the one
     * the user was looking at rather than whatever period this screen's own view model starts on.
     *
     * Both ends null means no period was given — the deep link and anything else that names the
     * list without a range — and the list follows the navigator's current period, as it always has.
     * [dayRange] is only the NAME the Analytics dropdown had selected, kept for the header; the
     * dates alone decide what is loaded.
     */
    data class TopTracks(
        val start: LocalDate? = null,
        val end: LocalDate? = null,
        val dayRange: AnalyticsUiState.DayRange? = null,
    ) : LibraryDynamicPlaylistType()

    data class TopArtists(
        val start: LocalDate? = null,
        val end: LocalDate? = null,
        val dayRange: AnalyticsUiState.DayRange? = null,
    ) : LibraryDynamicPlaylistType()

    data class TopAlbums(
        val start: LocalDate? = null,
        val end: LocalDate? = null,
        val dayRange: AnalyticsUiState.DayRange? = null,
    ) : LibraryDynamicPlaylistType()

    /**
     * One calendar month's top songs — the Wrapped tab's "Recap January".
     *
     * The only case carrying data, which is the whole reason [title] exists: every other entry
     * names itself with a fixed [StringResource], and a month cannot.
     *
     * [month] is 1..12, matching `kotlinx.datetime.Month.number`, so the round trip stays readable
     * in a URL and needs no enum lookup to serialise.
     */
    data class MonthlyRecap(
        val year: Int,
        val month: Int,
    ) : LibraryDynamicPlaylistType()

    /**
     * Liked songs crediting one artist — opened from the "Liked songs" row on that artist's page
     * (issue #2524).
     *
     * Carries only the channel id: the header's artist name is looked up by the view model, since a
     * name can hold any character and would have to survive the route string.
     */
    data class ArtistLiked(
        val channelId: String,
    ) : LibraryDynamicPlaylistType()

    /**
     * The fixed name, for the callers that resolve a resource without composition.
     *
     * [MonthlyRecap] has no honest answer here — its name is assembled from a month and sometimes
     * a year — so it falls back to the tab it belongs to. Nothing renders that fallback as a
     * heading: the screen prints [title], which knows the month. It exists because the `when` must
     * cover the sealed hierarchy, and because
     * [com.maxrave.simpmusic.viewModel.LibraryDynamicPlaylistViewModel] still needs *some* answer
     * on the path where it names a queue — that view model resolves the recap's real name itself.
     */
    fun name(): StringResource =
        when (this) {
            Favorite -> Res.string.favorite
            Followed -> Res.string.followed
            MostPlayed -> Res.string.most_played
            Downloaded -> Res.string.downloaded
            is TopAlbums -> Res.string.your_top_albums
            is TopArtists -> Res.string.your_top_artists
            is TopTracks -> Res.string.your_top_tracks
            is MonthlyRecap -> Res.string.wrapped
            is ArtistLiked -> Res.string.liked_songs
        }

    /**
     * What the header actually shows.
     *
     * A `@Composable` rather than a [StringResource] because "Recap January" is a format string
     * plus an argument, and a resource reference cannot carry the argument. The year is printed
     * only when it is not the current one — two Januaries a year apart would otherwise be the same
     * playlist as far as the reader can tell.
     */
    @Composable
    fun title(): String =
        when (this) {
            is MonthlyRecap ->
                if (year == now().date.year) {
                    stringResource(Res.string.wrapped_recap_month, monthFullName(Month(month)))
                } else {
                    stringResource(
                        Res.string.wrapped_recap_month_year,
                        monthFullName(Month(month)),
                        year.toString(),
                    )
                }

            else -> stringResource(name())
        }

    /** The span a top list was opened for, or null when it follows the navigator's own period. */
    fun topListRange(): Pair<LocalDate, LocalDate>? {
        // Named apart from the properties on purpose: inside each branch `this` is smart-cast, so a
        // local called `start` would sit one character away from the property it is reading.
        val (rangeStart, rangeEnd) =
            when (this) {
                is TopTracks -> Pair(start, end)
                is TopArtists -> Pair(start, end)
                is TopAlbums -> Pair(start, end)
                else -> return null
            }
        return if (rangeStart != null && rangeEnd != null) Pair(rangeStart, rangeEnd) else null
    }

    /** The Analytics dropdown range a top list was opened from, or null. */
    fun topListDayRange(): AnalyticsUiState.DayRange? =
        when (this) {
            is TopTracks -> dayRange
            is TopArtists -> dayRange
            is TopAlbums -> dayRange
            else -> null
        }

    // `_LAST_30_DAYS_2026-09-01_2026-10-01`, or nothing. The dates go LAST because they contain no
    // underscore while the enum name does, which is what lets the parser read them off the end.
    private fun periodSuffix(): String {
        val (start, end) = topListRange() ?: return ""
        val range = topListDayRange()?.let { "_${it.name}" }.orEmpty()
        return "${range}_${start}_$end"
    }

    // For serialization and navigation
    fun toStringParams(): String =
        when (this) {
            Favorite -> "favorite"
            Followed -> "followed"
            MostPlayed -> "most_played"
            Downloaded -> "downloaded"
            is TopAlbums -> TOP_ALBUMS + periodSuffix()
            is TopArtists -> TOP_ARTISTS + periodSuffix()
            is TopTracks -> TOP_TRACKS + periodSuffix()
            // Zero-padded so the strings sort the way the months do, which makes a list of these
            // readable in a log or a deep link without parsing it back.
            is MonthlyRecap -> "${RECAP_PREFIX}${year}_${month.toString().padStart(2, '0')}"
            is ArtistLiked -> ARTIST_LIKED_PREFIX + channelId
        }

    companion object {
        private const val RECAP_PREFIX = "recap_"
        private const val ARTIST_LIKED_PREFIX = "artist_liked_"
        private const val TOP_TRACKS = "top_tracks"
        private const val TOP_ARTISTS = "top_artists"
        private const val TOP_ALBUMS = "top_albums"

        fun toType(input: String): LibraryDynamicPlaylistType =
            when (input) {
                "favorite" -> Favorite
                "followed" -> Followed
                "most_played" -> MostPlayed
                "downloaded" -> Downloaded
                TOP_ALBUMS -> TopAlbums()
                TOP_ARTISTS -> TopArtists()
                TOP_TRACKS -> TopTracks()
                else ->
                    parseTopList(input)
                        ?: parseMonthlyRecap(input)
                        ?: parseArtistLiked(input)
                        ?: throw IllegalArgumentException("Unknown type: $input")
            }

        /**
         * `artist_liked_UC…` back into an [ArtistLiked], or null. The remainder is taken whole —
         * channel ids contain `_` and `-` — and only has to be non-blank: an id matching no song
         * just opens an empty list.
         */
        private fun parseArtistLiked(input: String): ArtistLiked? =
            input
                .takeIf { it.startsWith(ARTIST_LIKED_PREFIX) }
                ?.removePrefix(ARTIST_LIKED_PREFIX)
                ?.takeIf { it.isNotBlank() }
                ?.let(::ArtistLiked)

        /**
         * `top_tracks_2026-08-01_2026-08-31` back into a top list with its period, or null.
         *
         * Validated for the same reason as [parseMonthlyRecap] — this can arrive through the deep
         * link from outside the app — and a start after its end is rejected rather than handed to a
         * query that would quietly return nothing.
         */
        private fun parseTopList(input: String): LibraryDynamicPlaylistType? {
            val key = listOf(TOP_TRACKS, TOP_ARTISTS, TOP_ALBUMS).firstOrNull { input.startsWith("${it}_") } ?: return null
            // Read from the RIGHT: the two dates are last and never contain an underscore, while the
            // range name before them is an enum name that does (`LAST_30_DAYS`).
            val parts = input.removePrefix("${key}_").split("_")
            if (parts.size < 2) return null
            val start = runCatching { LocalDate.parse(parts[parts.size - 2]) }.getOrNull() ?: return null
            val end = runCatching { LocalDate.parse(parts.last()) }.getOrNull() ?: return null
            if (start > end) return null
            val rangeName = parts.dropLast(2).joinToString("_")
            val dayRange =
                if (rangeName.isEmpty()) {
                    null
                } else {
                    runCatching { AnalyticsUiState.DayRange.valueOf(rangeName) }.getOrNull() ?: return null
                }
            return when (key) {
                TOP_TRACKS -> TopTracks(start, end, dayRange)
                TOP_ARTISTS -> TopArtists(start, end, dayRange)
                else -> TopAlbums(start, end, dayRange)
            }
        }

        /**
         * `recap_2026_01` back into a [MonthlyRecap], or null for anything else.
         *
         * Validated rather than trusted: this arrives from a persisted navigation argument and,
         * through the `simpmusic://library?type=` deep link, from outside the app entirely. A month
         * of 0 or 13 would reach `Month(month)` and throw somewhere far away from here.
         */
        private fun parseMonthlyRecap(input: String): MonthlyRecap? {
            if (!input.startsWith(RECAP_PREFIX)) return null
            val parts = input.removePrefix(RECAP_PREFIX).split("_")
            if (parts.size != 2) return null
            val year = parts[0].toIntOrNull() ?: return null
            val month = parts[1].toIntOrNull()?.takeIf { it in 1..12 } ?: return null
            return MonthlyRecap(year = year, month = month)
        }
    }
}

/**
 * `Last 30 days (1/9-1/10/2026)` — the period a top list was opened on, named exactly as the
 * Analytics dropdown names it so the two screens read the same. Null when the list names no period,
 * which keeps the header to a single line.
 */
@Composable
private fun topListSubtitle(type: LibraryDynamicPlaylistType): String? {
    val (start, end) = type.topListRange() ?: return null
    val span = formatNumericSpan(start, end)
    val dayRange = type.topListDayRange() ?: return span
    return "${stringResource(dayRange.labelRes())} ($span)"
}

/**
 * Whether a title or any of its artists contains [query].
 *
 * Artist names count as well as the title: people look for "everything by X" at least as often as
 * for one track, and typing an artist into a search box that only reads titles looks broken.
 *
 * [artists] is a converted `List<String>`, so every entry is checked — a featured artist matches
 * as readily as the lead.
 */
private fun matchesQuery(
    title: String,
    artists: List<String>?,
    query: String,
): Boolean =
    title.contains(query, ignoreCase = true) ||
        artists?.any { it.contains(query, ignoreCase = true) } == true

private fun SongEntity.matches(query: String): Boolean = matchesQuery(title, artistName, query)
