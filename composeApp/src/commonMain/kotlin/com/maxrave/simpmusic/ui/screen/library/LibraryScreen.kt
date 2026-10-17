package com.maxrave.simpmusic.ui.screen.library

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.maxrave.common.Config
import com.maxrave.domain.data.entities.AlbumEntity
import com.maxrave.domain.data.entities.ArtistEntity
import com.maxrave.domain.data.entities.LocalPlaylistEntity
import com.maxrave.domain.data.entities.PlaylistEntity
import com.maxrave.domain.data.entities.PodcastsEntity
import com.maxrave.domain.data.entities.SongEntity
import com.maxrave.domain.data.model.searchResult.playlists.PlaylistsResult
import com.maxrave.domain.data.type.ChartItem
import com.maxrave.domain.data.type.PlaylistType
import com.maxrave.domain.data.type.RecentlyType
import com.maxrave.domain.mediaservice.handler.PlaylistType as DomainPlaylistType
import com.maxrave.domain.mediaservice.handler.QueueData
import com.maxrave.domain.utils.toTrack
import com.maxrave.logger.Logger
import com.maxrave.simpmusic.ui.component.AddToPlaylistModalBottomSheet
import com.maxrave.simpmusic.ui.component.ArtistFullWidthItems
import com.maxrave.simpmusic.ui.component.NowPlayingBottomSheet
import com.maxrave.simpmusic.ui.component.PlaylistFullWidthItems
import com.maxrave.simpmusic.ui.component.ExpressiveLibraryButtonGroup
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCarousel
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCarouselItem
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCoverColors
import com.maxrave.simpmusic.ui.component.expressive.ExpressiveCreatePlaylistCard
import com.maxrave.simpmusic.ui.component.expressive.ExpressivePlaylistCard
import com.maxrave.simpmusic.ui.component.expressive.MaterialShapePreset
import com.maxrave.simpmusic.ui.component.expressive.PlaylistCoverCustomizerSheet
import com.maxrave.simpmusic.ui.component.expressive.QuickAccessGrid
import com.maxrave.simpmusic.ui.component.expressive.expressiveSpring
import com.maxrave.simpmusic.ui.component.expressive.pressFeedback
import kotlin.math.absoluteValue
import com.maxrave.simpmusic.ui.component.selection.SelectedSongsBottomSheet
import com.maxrave.simpmusic.ui.component.selection.SongSelectionTopAppBar
import com.maxrave.simpmusic.ui.component.selection.rememberSongSelectionState
import com.maxrave.simpmusic.ui.icon.Add
import com.maxrave.simpmusic.ui.icon.Groups
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.navigation.destination.home.ListenTogetherDestination
import com.maxrave.simpmusic.ui.navigation.destination.library.LibraryDynamicPlaylistDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.AlbumDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.ArtistDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.LocalPlaylistDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.PlaylistDestination
import com.maxrave.simpmusic.ui.navigation.destination.list.PodcastDestination
import com.maxrave.simpmusic.viewModel.LibraryDynamicPlaylistViewModel
import com.maxrave.simpmusic.viewModel.LibraryViewModel
import com.maxrave.simpmusic.viewModel.SharedViewModel
import com.maxrave.simpmusic.viewModel.SongSelectionViewModel
import com.maxrave.simpmusic.viewModel.UIEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import simpmusic.composeapp.generated.resources.Res
import simpmusic.composeapp.generated.resources.create
import simpmusic.composeapp.generated.resources.library
import simpmusic.composeapp.generated.resources.playlist_name
import simpmusic.composeapp.generated.resources.playlist_name_cannot_be_empty
import simpmusic.composeapp.generated.resources.playlists
import simpmusic.composeapp.generated.resources.recently_added
import simpmusic.composeapp.generated.resources.songs
import simpmusic.composeapp.generated.resources.your_library
import simpmusic.composeapp.generated.resources.your_playlists

private val gradientPresets = listOf(
    Color(0xFF4A8FB5) to Color(0xFF1B2C3A),
    Color(0xFFE8E8E8) to Color(0xFF7A7A7A),
    Color(0xFF9A9A8A) to Color(0xFF3A3A33),
    Color(0xFFC9A24A) to Color(0xFF2A2118),
    Color(0xFF4AD0C0) to Color(0xFF10302E),
    Color(0xFF3A4A8A) to Color(0xFF0E1330),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    innerPadding: PaddingValues,
    viewModel: LibraryViewModel = koinViewModel(),
    dynamicPlaylistViewModel: LibraryDynamicPlaylistViewModel = koinViewModel(),
    sharedViewModel: SharedViewModel = koinInject(),
    navController: NavController,
    onScrolling: (onTop: Boolean) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val nowPlaying by viewModel.nowPlayingVideoId.collectAsStateWithLifecycle()
    val recentlyAdded by viewModel.recentlyAdded.collectAsStateWithLifecycle()
    val recentSongs by viewModel.recentSongs.collectAsStateWithLifecycle()
    val yourLocalPlaylist by viewModel.yourLocalPlaylist.collectAsStateWithLifecycle()
    val youTubePlaylist by viewModel.youTubePlaylist.collectAsStateWithLifecycle()

    val favoriteSongs by dynamicPlaylistViewModel.listFavoriteSong.collectAsStateWithLifecycle()
    val followedArtists by dynamicPlaylistViewModel.listFollowedArtist.collectAsStateWithLifecycle()
    val downloadedSongs by dynamicPlaylistViewModel.listDownloadedSong.collectAsStateWithLifecycle()

    val controllerState by sharedViewModel.controllerState.collectAsStateWithLifecycle()
    val isMediaPlaying = controllerState.isPlaying

    val selectionState = rememberSongSelectionState()
    val selectionViewModel: SongSelectionViewModel = koinViewModel()
    var showSelectionSheet by rememberSaveable { mutableStateOf(false) }
    var showSelectionAddToPlaylist by rememberSaveable { mutableStateOf(false) }
    var showAddSheet by remember { mutableStateOf(false) }
    var songEntityForBottomSheet by remember { mutableStateOf<SongEntity?>(null) }
    var showSongBottomSheet by remember { mutableStateOf(false) }
    var customizingPlaylist by remember { mutableStateOf<LocalPlaylistEntity?>(null) }
    var showCoverCustomizer by remember { mutableStateOf(false) }

    var selectedTab by rememberSaveable { mutableStateOf(0) }
    var activeHeroIndex by rememberSaveable { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.getRecentlyAdded()
        viewModel.getRecentSongs()
        viewModel.getLocalPlaylist()
        viewModel.getYouTubePlaylist()
    }

    LaunchedEffect(nowPlaying) {
        Logger.w("LibraryScreen", "Check nowPlaying: $nowPlaying")
        viewModel.getRecentlyAdded()
        viewModel.getRecentSongs()
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }
            .collect {
                onScrolling(it <= 20)
            }
    }

    // Build carousel items based on selectedTab
    val carouselItems = remember(
        selectedTab,
        recentlyAdded.data,
        recentSongs.data,
        yourLocalPlaylist.data,
        youTubePlaylist.data,
    ) {
        when (selectedTab) {
            0 -> {
                val list = (recentlyAdded.data ?: emptyList()).filterIsInstance<RecentlyType>()
                list.mapIndexed { idx, item ->
                    val gradient = gradientPresets[idx % gradientPresets.size]
                    when (item.objectType()) {
                        RecentlyType.Type.SONG -> {
                            val song = item as SongEntity
                            ExpressiveCarouselItem(
                                id = song.videoId,
                                title = song.title,
                                subtitle = song.artistName?.joinToString(", ") ?: "Song",
                                imageUrl = song.thumbnails,
                                gradientColors = gradient,
                                rawData = song,
                            )
                        }
                        RecentlyType.Type.ARTIST -> {
                            val artist = item as ArtistEntity
                            ExpressiveCarouselItem(
                                id = artist.channelId,
                                title = artist.name,
                                subtitle = "Artist",
                                imageUrl = artist.thumbnails,
                                gradientColors = gradient,
                                rawData = artist,
                            )
                        }
                        RecentlyType.Type.ALBUM -> {
                            val album = item as AlbumEntity
                            ExpressiveCarouselItem(
                                id = album.browseId,
                                title = album.title,
                                subtitle = album.artistName?.joinToString(", ") ?: "Album",
                                imageUrl = album.thumbnails,
                                gradientColors = gradient,
                                rawData = album,
                            )
                        }
                        RecentlyType.Type.PLAYLIST -> {
                            val playlist = item as PlaylistEntity
                            ExpressiveCarouselItem(
                                id = playlist.id,
                                title = playlist.title,
                                subtitle = playlist.author ?: "Playlist",
                                imageUrl = playlist.thumbnails,
                                gradientColors = gradient,
                                rawData = playlist,
                            )
                        }
                    }
                }
            }
            1 -> {
                val list = recentSongs.data ?: emptyList()
                list.mapIndexed { idx, song ->
                    val gradient = gradientPresets[idx % gradientPresets.size]
                    ExpressiveCarouselItem(
                        id = song.videoId,
                        title = song.title,
                        subtitle = song.artistName?.joinToString(", ") ?: "Song",
                        imageUrl = song.thumbnails,
                        gradientColors = gradient,
                        rawData = song,
                    )
                }
            }
            2 -> {
                // Carousel removed from Playlists tab: dedicated Material 3 Expressive Playlists UI
                emptyList()
            }
            else -> emptyList()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = innerPadding.calculateTopPadding(), bottom = 180.dp),
        ) {
            // Selection Top Bar if active
            AnimatedVisibility(visible = selectionState.isActive) {
                SongSelectionTopAppBar(
                    state = selectionState,
                    windowInsets = WindowInsets(0),
                    onSelectAll = {
                        val ids = when (selectedTab) {
                            1 -> (recentSongs.data ?: emptyList()).map { it.videoId }
                            else -> (recentlyAdded.data ?: emptyList())
                                .filterIsInstance<SongEntity>()
                                .map { it.videoId }
                        }
                        selectionState.toggleSelectAll(ids)
                    },
                    onOpenActions = { showSelectionSheet = true },
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.onBackground,
                )
            }

            // 1. Header (height 56dp, padding horizontal 16dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(Res.string.library),
                    style = TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-1.0).sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                var isCommunityPressed by remember { mutableStateOf(false) }
                val communityRadius by animateDpAsState(
                    targetValue = if (isCommunityPressed) 14.dp else 24.dp,
                    animationSpec = expressiveSpring(),
                    label = "commRadius",
                )
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(communityRadius))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .semantics { role = Role.Button }
                        .pressFeedback(
                            minDurationMs = 130L,
                            onPressedChange = { isCommunityPressed = it },
                            onClick = { navController.navigate(ListenTogetherDestination) },
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = SimpIcons.Groups,
                        contentDescription = "Community",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(26.dp),
                    )
                }
            }

            // 2. Expressive Button Group (same style as Home & Search screens)
            Spacer(modifier = Modifier.height(10.dp))
            ExpressiveLibraryButtonGroup(
                selectedTab = selectedTab,
                onSelectTab = { newIndex ->
                    selectedTab = newIndex
                    activeHeroIndex = 0
                    when (newIndex) {
                        0 -> viewModel.getRecentlyAdded()
                        1 -> viewModel.getRecentSongs()
                        2 -> {
                            viewModel.getLocalPlaylist()
                            viewModel.getYouTubePlaylist()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 3. Quick Access Grid (2 columns 1.12 : 1, 2 rows of 76dp, gap 8dp, padding horizontal 16dp)
            val favCountText = "${favoriteSongs.size} songs"
            val folCountText = "${followedArtists.size} artists"
            val dlCountText = "${downloadedSongs.size} songs"

            QuickAccessGrid(
                modifier = Modifier.padding(horizontal = 16.dp),
                favoriteCount = favCountText,
                followedCount = folCountText,
                downloadedCount = dlCountText,
                onFavoritesClick = {
                    navController.navigate(
                        LibraryDynamicPlaylistDestination(
                            type = LibraryDynamicPlaylistType.Favorite.toStringParams(),
                        ),
                    )
                },
                onFollowedClick = {
                    navController.navigate(
                        LibraryDynamicPlaylistDestination(
                            type = LibraryDynamicPlaylistType.Followed.toStringParams(),
                        ),
                    )
                },
                onDownloadedClick = {
                    navController.navigate(
                        LibraryDynamicPlaylistDestination(
                            type = LibraryDynamicPlaylistType.Downloaded.toStringParams(),
                        ),
                    )
                },
            )

            // 4. Section Header and Carousel (Only for Tab 0 and Tab 1)
            if (selectedTab != 2 && carouselItems.isNotEmpty()) {
                val sectionTitle = when (selectedTab) {
                    0 -> stringResource(Res.string.recently_added)
                    else -> stringResource(Res.string.songs)
                }
                val totalCount = carouselItems.size
                val displayHeroIndex = if (totalCount == 0) 0 else (activeHeroIndex + 1).coerceAtMost(totalCount)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 22.dp, bottom = 10.dp)
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = sectionTitle,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.4).sp,
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "$displayHeroIndex / $totalCount",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                // 5. Expressive Carousel (height 216dp, edge-to-edge)
                ExpressiveCarousel(
                    items = carouselItems,
                    activeHeroIndex = activeHeroIndex.coerceIn(0, (carouselItems.size - 1).coerceAtLeast(0)),
                    isPlaying = isMediaPlaying,
                    playingItemId = nowPlaying,
                    onHeroIndexChange = { activeHeroIndex = it },
                    onItemClick = { item ->
                        when (val raw = item.rawData) {
                            is SongEntity -> {
                                activeHeroIndex = 0
                                viewModel.setQueueData(
                                    QueueData.Data(
                                        listTracks = arrayListOf(raw.toTrack()),
                                        firstPlayedTrack = raw.toTrack(),
                                        playlistId = "RDAMVM${raw.videoId}",
                                        playlistName = raw.title,
                                        playlistType = DomainPlaylistType.RADIO,
                                        continuation = null,
                                    ),
                                )
                                viewModel.loadMediaItem(raw, type = Config.SONG_CLICK, index = 0)
                            }
                            is ArtistEntity -> {
                                navController.navigate(ArtistDestination(channelId = raw.channelId))
                            }
                            is AlbumEntity -> {
                                navController.navigate(AlbumDestination(raw.browseId))
                            }
                            is PlaylistEntity -> {
                                navController.navigate(PlaylistDestination(raw.id))
                            }
                            is ChartItem -> {
                                navController.navigate(PlaylistDestination(raw.ytPlaylistId))
                            }
                            is LocalPlaylistEntity -> {
                                navController.navigate(LocalPlaylistDestination(raw.id))
                            }
                            is PlaylistsResult -> {
                                navController.navigate(PlaylistDestination(raw.browseId))
                            }
                        }
                    },
                    onPlayPauseClick = { item ->
                        when (val raw = item.rawData) {
                            is SongEntity -> {
                                if (nowPlaying == raw.videoId) {
                                    sharedViewModel.onUIEvent(UIEvent.PlayPause)
                                } else {
                                    activeHeroIndex = 0
                                    viewModel.setQueueData(
                                        QueueData.Data(
                                            listTracks = arrayListOf(raw.toTrack()),
                                            firstPlayedTrack = raw.toTrack(),
                                            playlistId = "RDAMVM${raw.videoId}",
                                            playlistName = raw.title,
                                            playlistType = DomainPlaylistType.RADIO,
                                            continuation = null,
                                        ),
                                    )
                                    viewModel.loadMediaItem(raw, type = Config.SONG_CLICK, index = 0)
                                }
                            }
                            is LocalPlaylistEntity -> {
                                navController.navigate(LocalPlaylistDestination(raw.id))
                            }
                            is PlaylistEntity -> {
                                navController.navigate(PlaylistDestination(raw.id))
                            }
                            is AlbumEntity -> {
                                navController.navigate(AlbumDestination(raw.browseId))
                            }
                            is ChartItem -> {
                                navController.navigate(PlaylistDestination(raw.ytPlaylistId))
                            }
                            is PlaylistsResult -> {
                                navController.navigate(PlaylistDestination(raw.browseId))
                            }
                        }
                    },
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTab) {
                0 -> {
                    val list = (recentlyAdded.data ?: emptyList())
                        .filterIsInstance<RecentlyType>()
                        .filter { it.objectType() != RecentlyType.Type.SONG }
                    if (list.isNotEmpty()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                        ) {
                            list.forEach { item ->
                                when (item.objectType()) {
                                    RecentlyType.Type.ARTIST -> {
                                        val artist = item as ArtistEntity
                                        ArtistFullWidthItems(
                                            data = artist,
                                            onClickListener = {
                                                navController.navigate(
                                                    ArtistDestination(channelId = artist.channelId),
                                                )
                                            },
                                        )
                                    }
                                    else -> {
                                        if (item is PlaylistType) {
                                            PlaylistFullWidthItems(
                                                data = item,
                                                onClickListener = {
                                                    when (item) {
                                                        is AlbumEntity -> {
                                                            navController.navigate(AlbumDestination(item.browseId))
                                                        }
                                                        is PlaylistEntity -> {
                                                            navController.navigate(PlaylistDestination(item.id))
                                                        }
                                                        is PodcastsEntity -> {
                                                            navController.navigate(PodcastDestination(item.podcastId))
                                                        }
                                                    }
                                                },
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Recent songs are displayed in the carousel above; no duplicate list below
                }
                2 -> {
                    val localList = yourLocalPlaylist.data ?: emptyList()
                    val ytList = youTubePlaylist.data ?: emptyList()
                    var playlistFilterIndex by rememberSaveable { mutableStateOf(0) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        // Material 3 Expressive Header: Title + Count Badge
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = stringResource(Res.string.your_playlists),
                                style = TextStyle(
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-0.6).sp,
                                ),
                                color = MaterialTheme.colorScheme.onSurface,
                            )

                            Surface(
                                shape = RoundedCornerShape(50),
                                color = MaterialTheme.colorScheme.primaryContainer,
                            ) {
                                Text(
                                    text = "${localList.size + ytList.size} playlists",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                )
                            }
                        }

                        // Material 3 Filter Chips Row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            FilterChip(
                                selected = playlistFilterIndex == 0,
                                onClick = { playlistFilterIndex = 0 },
                                label = { Text("All (${localList.size + ytList.size})") },
                                shape = RoundedCornerShape(12.dp),
                            )
                            FilterChip(
                                selected = playlistFilterIndex == 1,
                                onClick = { playlistFilterIndex = 1 },
                                label = { Text("Local (${localList.size})") },
                                shape = RoundedCornerShape(12.dp),
                            )
                            FilterChip(
                                selected = playlistFilterIndex == 2,
                                onClick = { playlistFilterIndex = 2 },
                                label = { Text("YouTube (${ytList.size})") },
                                shape = RoundedCornerShape(12.dp),
                            )
                        }

                        // Create Playlist Hero Card
                        ExpressiveCreatePlaylistCard(
                            onClick = { showAddSheet = true },
                        )

                        // Local Playlists with Material 3 Expressive Shapes
                        if (playlistFilterIndex == 0 || playlistFilterIndex == 1) {
                            localList.forEachIndexed { idx, pl ->
                                val shapeIdFlow = remember(pl.id) { viewModel.getPlaylistShapeFlow(pl.id.toString()) }
                                val colorHexFlow = remember(pl.id) { viewModel.getPlaylistColorFlow(pl.id.toString()) }
                                val savedShapeId by shapeIdFlow.collectAsStateWithLifecycle(null)
                                val savedColorHex by colorHexFlow.collectAsStateWithLifecycle(null)

                                val defaultShape = MaterialShapePreset.entries[idx % MaterialShapePreset.entries.size]
                                val defaultColor = ExpressiveCoverColors[idx % ExpressiveCoverColors.size]

                                val shapePreset = savedShapeId?.let { MaterialShapePreset.fromId(it) } ?: defaultShape
                                val coverColor = savedColorHex?.let { hex ->
                                    try { Color(hex.toLong(16).toULong()) } catch (_: Exception) { null }
                                } ?: defaultColor

                                ExpressivePlaylistCard(
                                    title = pl.title,
                                    subtitle = "${pl.tracks?.size ?: 0} tracks",
                                    tag = "Local",
                                    thumbnailUrl = pl.thumbnail,
                                    shapePreset = shapePreset,
                                    containerColor = coverColor,
                                    onClick = {
                                        navController.navigate(LocalPlaylistDestination(pl.id))
                                    },
                                    onCustomize = {
                                        customizingPlaylist = pl
                                        showCoverCustomizer = true
                                    },
                                    onPlay = {
                                        coroutineScope.launch {
                                            val tracks = viewModel.getPlaylistTracks(pl.id)
                                            if (tracks.isNotEmpty()) {
                                                val firstSong = tracks.first()
                                                viewModel.setQueueData(
                                                    QueueData.Data(
                                                        listTracks = ArrayList(tracks.map { it.toTrack() }),
                                                        firstPlayedTrack = firstSong.toTrack(),
                                                        playlistId = "local_${pl.id}",
                                                        playlistName = pl.title,
                                                        playlistType = DomainPlaylistType.PLAYLIST,
                                                        continuation = null,
                                                    ),
                                                )
                                                viewModel.loadMediaItem(firstSong, type = Config.SONG_CLICK, index = 0)
                                            }
                                        }
                                    },
                                )
                            }
                        }

                        // YouTube Playlists with Material 3 Expressive Shapes
                        if (playlistFilterIndex == 0 || playlistFilterIndex == 2) {
                            ytList.forEachIndexed { idx, yt ->
                                val defaultShape = MaterialShapePreset.entries[(idx + localList.size) % MaterialShapePreset.entries.size]
                                val defaultColor = ExpressiveCoverColors[(idx + localList.size) % ExpressiveCoverColors.size]

                                ExpressivePlaylistCard(
                                    title = yt.title,
                                    subtitle = yt.author ?: "YouTube Playlist",
                                    tag = "YouTube",
                                    thumbnailUrl = yt.thumbnails?.firstOrNull()?.url,
                                    shapePreset = defaultShape,
                                    containerColor = defaultColor,
                                    onClick = {
                                        navController.navigate(PlaylistDestination(yt.browseId))
                                    },
                                    onCustomize = null,
                                    onPlay = {
                                        navController.navigate(PlaylistDestination(yt.browseId))
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }

        // Create Playlist Dialog / BottomSheet
        if (showAddSheet) {
            var newTitle by remember { mutableStateOf("") }
            val showAddSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            val hideEditTitleBottomSheet: () -> Unit = {
                coroutineScope.launch {
                    showAddSheetState.hide()
                    showAddSheet = false
                }
            }
            ModalBottomSheet(
                onDismissRequest = { showAddSheet = false },
                sheetState = showAddSheetState,
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
                dragHandle = null,
                scrimColor = Color.Black.copy(alpha = .5f),
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                    colors = CardDefaults.cardColors().copy(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Card(
                            modifier = Modifier
                                .width(60.dp)
                                .height(4.dp),
                            colors = CardDefaults.cardColors().copy(
                                containerColor = MaterialTheme.colorScheme.outline,
                            ),
                            shape = RoundedCornerShape(50),
                        ) {}
                        Spacer(modifier = Modifier.height(5.dp))
                        OutlinedTextField(
                            value = newTitle,
                            onValueChange = { s -> newTitle = s },
                            label = { Text(text = stringResource(Res.string.playlist_name)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        TextButton(
                            onClick = {
                                if (newTitle.isBlank()) {
                                    viewModel.makeToast(runBlocking { getString(Res.string.playlist_name_cannot_be_empty) })
                                } else {
                                    viewModel.createPlaylist(newTitle)
                                    hideEditTitleBottomSheet()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Text(text = stringResource(Res.string.create))
                        }
                    }
                }
            }
        }

        // Selection Bottom Sheet
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

        // Add to playlist Sheet
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

        // Song Options Bottom Sheet
        if (showSongBottomSheet && songEntityForBottomSheet != null) {
            NowPlayingBottomSheet(
                onDismiss = {
                    showSongBottomSheet = false
                    songEntityForBottomSheet = null
                },
                navController = navController,
                song = songEntityForBottomSheet,
                onLibraryDelete = {
                    songEntityForBottomSheet?.videoId?.let { viewModel.deleteSong(it) }
                },
            )
        }

        // Playlist Cover Studio Customizer Sheet
        customizingPlaylist?.let { pl ->
            if (showCoverCustomizer) {
                val shapeIdFlow = remember(pl.id) { viewModel.getPlaylistShapeFlow(pl.id.toString()) }
                val colorHexFlow = remember(pl.id) { viewModel.getPlaylistColorFlow(pl.id.toString()) }
                val savedShapeId by shapeIdFlow.collectAsStateWithLifecycle(null)
                val savedColorHex by colorHexFlow.collectAsStateWithLifecycle(null)

                val defaultShape = MaterialShapePreset.entries[(pl.id.toInt().absoluteValue) % MaterialShapePreset.entries.size]
                val defaultColor = ExpressiveCoverColors[(pl.id.toInt().absoluteValue) % ExpressiveCoverColors.size]

                val initialShape = savedShapeId?.let { MaterialShapePreset.fromId(it) } ?: defaultShape
                val initialColor = savedColorHex?.let { hex ->
                    try { Color(hex.toLong(16).toULong()) } catch (_: Exception) { null }
                } ?: defaultColor

                PlaylistCoverCustomizerSheet(
                    playlistTitle = pl.title,
                    currentThumbnail = pl.thumbnail,
                    initialShape = initialShape,
                    initialColor = initialColor,
                    onDismiss = {
                        showCoverCustomizer = false
                        customizingPlaylist = null
                    },
                    getTracks = { viewModel.getPlaylistTracks(pl.id) },
                    onSave = { shapeId, colorHex, newThumbnail ->
                        viewModel.savePlaylistShape(pl.id.toString(), shapeId)
                        viewModel.savePlaylistColor(pl.id.toString(), colorHex)
                        if (newThumbnail != pl.thumbnail) {
                            viewModel.updatePlaylistThumbnail(pl.id, newThumbnail ?: "")
                        }
                        showCoverCustomizer = false
                        customizingPlaylist = null
                    },
                )
            }
        }
    }
}