package com.maxrave.simpmusic.ui.component.update

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maxrave.simpmusic.model.AppUpdateInfo
import com.maxrave.simpmusic.ui.icon.CheckCircle
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.Download
import com.maxrave.simpmusic.ui.icon.OpenInNew
import com.maxrave.simpmusic.ui.icon.SimpIcons
import com.maxrave.simpmusic.ui.icon.Sync
import com.maxrave.simpmusic.ui.icon.TipsAndUpdates
import com.maxrave.simpmusic.ui.theme.fontFamily
import com.maxrave.simpmusic.ui.theme.googleSansFlexFamily
import com.maxrave.simpmusic.ui.theme.typo
import com.maxrave.simpmusic.utils.VersionManager
import com.maxrave.simpmusic.viewModel.UpdateDownloadState
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.m3.markdownTypography
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import simpmusic.composeapp.generated.resources.*
import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.sin

/**
 * Modern Material 3 Expressive update dialog with an animated fluid wave progress
 * indicator while downloading.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppUpdateDialog(
    response: AppUpdateInfo,
    downloadState: UpdateDownloadState,
    onDismissRequest: () -> Unit,
    onStartDownload: () -> Unit,
    onCancelDownload: () -> Unit,
    onInstallUpdate: (filePath: String) -> Unit,
    onRetryDownload: () -> Unit,
    onOpenUrl: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isDownloading = downloadState is UpdateDownloadState.Downloading

    Dialog(
        onDismissRequest = {
            if (!isDownloading) {
                onDismissRequest()
            }
        },
        properties =
            DialogProperties(
                dismissOnBackPress = !isDownloading,
                dismissOnClickOutside = !isDownloading,
                usePlatformDefaultWidth = false,
            ),
    ) {
        Surface(
            modifier =
                modifier
                    .fillMaxWidth(0.92f)
                    .padding(vertical = 24.dp)
                    .heightIn(max = 680.dp),
            shape = RoundedCornerShape(32.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 8.dp,
            border =
                BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f),
                ),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Header Bar (App Icon + Status Badges + Close)
                UpdateHeaderBar(
                    downloadState = downloadState,
                    response = response,
                    isDownloading = isDownloading,
                    onClose = onDismissRequest,
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Dynamic Body Content based on state
                Box(
                    modifier =
                        Modifier
                            .weight(1f, fill = false)
                            .fillMaxWidth(),
                ) {
                    when (val state = downloadState) {
                        is UpdateDownloadState.Downloading -> {
                            UpdateDownloadingContent(
                                state = state,
                                response = response,
                                onCancel = onCancelDownload,
                            )
                        }

                        is UpdateDownloadState.Completed -> {
                            UpdateCompletedContent(
                                state = state,
                                response = response,
                                onInstall = { onInstallUpdate(state.filePath) },
                            )
                        }

                        is UpdateDownloadState.Error -> {
                            UpdateErrorContent(
                                state = state,
                                response = response,
                                onRetry = onRetryDownload,
                                onOpenUrl = onOpenUrl,
                            )
                        }

                        is UpdateDownloadState.Idle -> {
                            UpdateIdleContent(
                                response = response,
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Bottom Action Buttons
                UpdateActionRow(
                    downloadState = downloadState,
                    response = response,
                    isDownloading = isDownloading,
                    onStartDownload = onStartDownload,
                    onCancelDownload = onCancelDownload,
                    onInstallUpdate = onInstallUpdate,
                    onRetryDownload = onRetryDownload,
                    onDismiss = onDismissRequest,
                    onOpenUrl = onOpenUrl,
                )
            }
        }
    }
}

/**
 * Top bar displaying the app icon, title, version chips, and close button.
 */
@Composable
private fun UpdateHeaderBar(
    downloadState: UpdateDownloadState,
    response: AppUpdateInfo,
    isDownloading: Boolean,
    onClose: () -> Unit,
) {
    val currentVersion = VersionManager.getVersionName().trim()
    val newVersion = response.tagName.removePrefix("v").trim()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f),
        ) {
            // App Icon with tonal container
            Box(
                modifier =
                    Modifier
                        .size(54.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.circle_app_icon),
                    contentDescription = "Melophile Logo",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(38.dp),
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Text(
                    text =
                        when (downloadState) {
                            is UpdateDownloadState.Downloading -> "Downloading Update"
                            is UpdateDownloadState.Completed -> "Update Ready"
                            is UpdateDownloadState.Error -> "Download Interrupted"
                            else -> stringResource(Res.string.update_available)
                        },
                    style =
                        typo().titleLarge.copy(
                            fontFamily = googleSansFlexFamily(),
                            fontWeight = FontWeight.Bold,
                        ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Version comparison pills: v0.2.1 -> v0.3.0
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    // Current version
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    ) {
                        Text(
                            text = "v$currentVersion",
                            style = typo().labelSmall.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                        )
                    }

                    Text(
                        text = "➜",
                        style = typo().labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )

                    // Target version
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                    ) {
                        Text(
                            text = "v$newVersion",
                            style =
                                typo().labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                ),
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp),
                        )
                    }
                }
            }
        }

        // Close button (only shown when not actively downloading)
        if (!isDownloading) {
            IconButton(
                onClick = onClose,
                modifier =
                    Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest.copy(alpha = 0.6f)),
            ) {
                Icon(
                    imageVector = SimpIcons.Close,
                    contentDescription = "Close",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp),
                )
            }
        }
    }
}

/**
 * Hero content shown when an update is available to review and download.
 */
@Composable
private fun UpdateIdleContent(
    response: AppUpdateInfo,
) {
    val releaseDateText =
        remember(response.releaseTime) {
            response.releaseTime?.let { input ->
                try {
                    val instant = Instant.parse(input)
                    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
                    dateTime.format(
                        LocalDateTime.Format {
                            day()
                            char(' ')
                            monthName(MonthNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            year()
                        },
                    )
                } catch (e: Exception) {
                    null
                }
            }
        }

    val apkSizeMb =
        remember(response.apkSize) {
            if (response.apkSize != null && response.apkSize > 0) {
                "${response.apkSize / 1048576}.${(response.apkSize % 1048576) * 10 / 1048576} MB"
            } else {
                null
            }
        }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Metadata Pill Row (Release date + Download Size)
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (releaseDateText != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f)),
                ) {
                    Text(
                        text = "📅 $releaseDateText",
                        style = typo().labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                }
            }

            if (apkSizeMb != null) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
                ) {
                    Text(
                        text = "📦 $apkSizeMb",
                        style =
                            typo().labelMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                            ),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                }
            }
        }

        // Changelog container with rounded inner surface
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = 10.dp),
                ) {
                    Icon(
                        imageVector = SimpIcons.TipsAndUpdates,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp),
                    )
                    Text(
                        text = "What's New in this Release",
                        style =
                            typo().titleSmall.copy(
                                fontFamily = googleSansFlexFamily(),
                                fontWeight = FontWeight.Bold,
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                // Scrollable Markdown Changelog
                Box(
                    modifier =
                        Modifier
                            .heightIn(min = 120.dp, max = 280.dp)
                            .verticalScroll(rememberScrollState()),
                ) {
                    Markdown(
                        response.body.ifBlank { "Performance improvements and bug fixes." },
                        typography =
                            markdownTypography(
                                h1 = typo().labelLarge.copy(fontWeight = FontWeight.Bold),
                                h2 = typo().labelMedium.copy(fontWeight = FontWeight.SemiBold),
                                h3 = typo().labelSmall.copy(fontWeight = FontWeight.SemiBold),
                                text = typo().bodySmall,
                                bullet = typo().bodySmall,
                                paragraph = typo().bodySmall,
                                textLink =
                                    TextLinkStyles(
                                        SpanStyle(
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontFamily = fontFamily(),
                                            textDecoration = TextDecoration.Underline,
                                            color = MaterialTheme.colorScheme.primary,
                                        ),
                                    ),
                            ),
                    )
                }
            }
        }
    }
}

/**
 * Expressive Hero content during active download featuring a live fluid wave
 * water visualizer and M3 LinearWavyProgressIndicator.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UpdateDownloadingContent(
    state: UpdateDownloadState.Downloading,
    response: AppUpdateInfo,
    onCancel: () -> Unit,
) {
    val mbDownloaded =
        "${state.bytesDownloaded / 1048576}.${(state.bytesDownloaded % 1048576) * 10 / 1048576}"
    val totalMb =
        if (state.totalBytes > 0) {
            "${state.totalBytes / 1048576}.${(state.totalBytes % 1048576) * 10 / 1048576}"
        } else {
            null
        }

    val progressValue = if (state.progress >= 0f) state.progress.coerceIn(0f, 1f) else 0.45f
    val animatedProgress by animateFloatAsState(
        targetValue = progressValue,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = 400f),
        label = "waveProgress",
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Fluid Wave Hero Visualizer Card
        FluidWaveProgressHero(
            progress = animatedProgress,
            mbDownloaded = mbDownloaded,
            totalMb = totalMb,
            isIndeterminate = state.progress < 0f,
            modifier = Modifier.fillMaxWidth().height(150.dp),
        )

        // Material 3 Expressive Linear Wavy Progress Line
        val density = LocalDensity.current
        val customStroke =
            remember(density) {
                Stroke(
                    width = with(density) { 8.dp.toPx() },
                    cap = StrokeCap.Round,
                )
            }

        if (state.progress >= 0f) {
            LinearWavyProgressIndicator(
                progress = { animatedProgress },
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                stroke = customStroke,
                trackStroke = customStroke,
                amplitude = { p -> if (p > 0f) 0.8f else 0.1f },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
            )
        } else {
            LinearWavyProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                stroke = customStroke,
                trackStroke = customStroke,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
            )
        }

        // Live download subtitle hint
        Text(
            text = "Downloading Melophile update package… Please keep the app open.",
            style = typo().bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

/**
 * Animated liquid wave tank / card showing dual-layer flowing waves
 * rising to reflect the current download percentage.
 */
@Composable
private fun FluidWaveProgressHero(
    progress: Float,
    mbDownloaded: String,
    totalMb: String?,
    isIndeterminate: Boolean,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waveAnimation")

    // Continuous wave phase motion (left to right)
    val wavePhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 2200, easing = LinearEasing),
                repeatMode = RepeatMode.Restart,
            ),
        label = "wavePhase",
    )

    // Gentle vertical bobbing for secondary wave
    val waveBob by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 3f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 1600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label = "waveBob",
    )

    val primaryColor = MaterialTheme.colorScheme.primary
    val primaryContainer = MaterialTheme.colorScheme.primaryContainer
    val tertiaryColor = MaterialTheme.colorScheme.tertiary
    val cardBackground = MaterialTheme.colorScheme.surfaceContainerLow

    Box(
        modifier =
            modifier
                .clip(RoundedCornerShape(26.dp))
                .background(cardBackground)
                .border(
                    BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    ),
                    RoundedCornerShape(26.dp),
                ),
        contentAlignment = Alignment.Center,
    ) {
        // Dual Sine Wave Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Calculate base liquid height from progress
            val effectiveProgress = progress.coerceIn(0.04f, 0.96f)
            val baseWaterY = height * (1f - effectiveProgress)

            // Scaled wave amplitude (smoother near boundaries, lively mid-way)
            val amplitude = 8.dp.toPx() * (1f - (effectiveProgress - 0.5f).absoluteValue * 0.4f)
            val waveLength = width * 0.75f

            // 1. Back Wave (secondary layer with subtle transparency)
            val backPath =
                Path().apply {
                    moveTo(0f, height)
                    lineTo(0f, baseWaterY)
                    var x = 0f
                    val step = 4f
                    while (x <= width) {
                        val radians = (x / waveLength) * 2 * PI + wavePhase + 1.25f
                        val y = baseWaterY + (sin(radians.toDouble()) * amplitude * 0.8f).toFloat() + waveBob
                        lineTo(x, y)
                        x += step
                    }
                    lineTo(width, height)
                    close()
                }

            drawPath(
                path = backPath,
                color = primaryColor.copy(alpha = 0.30f),
            )

            // 2. Front Wave (rich vibrant gradient)
            val frontPath =
                Path().apply {
                    moveTo(0f, height)
                    lineTo(0f, baseWaterY)
                    var x = 0f
                    val step = 4f
                    while (x <= width) {
                        val radians = (x / waveLength) * 2 * PI + wavePhase
                        val y = baseWaterY + (sin(radians.toDouble()) * amplitude).toFloat()
                        lineTo(x, y)
                        x += step
                    }
                    lineTo(width, height)
                    close()
                }

            drawPath(
                path = frontPath,
                brush =
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                primaryColor.copy(alpha = 0.85f),
                                tertiaryColor.copy(alpha = 0.90f),
                                primaryContainer.copy(alpha = 0.98f),
                            ),
                        startY = baseWaterY - amplitude,
                        endY = height,
                    ),
            )

            // Ambient rising bubbles on canvas
            val bubble1X = (width * 0.28f + (wavePhase * 15f)) % width
            val bubble1Y = baseWaterY + ((height - baseWaterY) * 0.5f) + waveBob * 2
            if (bubble1Y < height && bubble1Y > baseWaterY) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.35f),
                    radius = 3.5.dp.toPx(),
                    center = Offset(bubble1X, bubble1Y),
                )
            }

            val bubble2X = (width * 0.72f - (wavePhase * 10f) + width) % width
            val bubble2Y = baseWaterY + ((height - baseWaterY) * 0.3f) - waveBob * 2
            if (bubble2Y < height && bubble2Y > baseWaterY) {
                drawCircle(
                    color = Color.White.copy(alpha = 0.40f),
                    radius = 4.5.dp.toPx(),
                    center = Offset(bubble2X, bubble2Y),
                )
            }
        }

        // Center Content Overlay Pill
        Surface(
            shape = RoundedCornerShape(18.dp),
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.78f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.35f)),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Icon(
                        imageVector = SimpIcons.Download,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp),
                    )

                    Text(
                        text = if (!isIndeterminate) "${(progress * 100).toInt()}%" else "…",
                        style =
                            typo().headlineMedium.copy(
                                fontFamily = googleSansFlexFamily(),
                                fontWeight = FontWeight.ExtraBold,
                            ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text =
                        if (totalMb != null) {
                            "$mbDownloaded MB / $totalMb MB"
                        } else {
                            "$mbDownloaded MB downloaded"
                        },
                    style = typo().labelMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

/**
 * Content shown when update package has completed downloading and is ready to install.
 */
@Composable
private fun UpdateCompletedContent(
    state: UpdateDownloadState.Completed,
    response: AppUpdateInfo,
    onInstall: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Animated Checkmark Badge
        Box(
            modifier =
                Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = SimpIcons.CheckCircle,
                contentDescription = "Success",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(46.dp),
            )
        }

        Text(
            text = "Ready to Install!",
            style =
                typo().headlineSmall.copy(
                    fontFamily = googleSansFlexFamily(),
                    fontWeight = FontWeight.Bold,
                ),
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "Melophile ${response.tagName} has been downloaded.\nTap Install below to complete the update.",
            style = typo().bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
        )
    }
}

/**
 * Content shown when an error occurs during download.
 */
@Composable
private fun UpdateErrorContent(
    state: UpdateDownloadState.Error,
    response: AppUpdateInfo,
    onRetry: () -> Unit,
    onOpenUrl: (String) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.7f),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Download Failed",
                    style =
                        typo().titleMedium.copy(
                            fontFamily = googleSansFlexFamily(),
                            fontWeight = FontWeight.Bold,
                        ),
                    color = MaterialTheme.colorScheme.onErrorContainer,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = state.message,
                    style = typo().bodySmall,
                    color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.9f),
                    textAlign = TextAlign.Center,
                )
            }
        }

        if (response.downloadUrl != null) {
            TextButton(
                onClick = { onOpenUrl(response.downloadUrl) },
            ) {
                Icon(
                    imageVector = SimpIcons.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Download manually from browser",
                    style = typo().labelMedium,
                )
            }
        }
    }
}

/**
 * Bottom action button row reacting dynamically to the download state.
 */
@Composable
private fun UpdateActionRow(
    downloadState: UpdateDownloadState,
    response: AppUpdateInfo,
    isDownloading: Boolean,
    onStartDownload: () -> Unit,
    onCancelDownload: () -> Unit,
    onInstallUpdate: (filePath: String) -> Unit,
    onRetryDownload: () -> Unit,
    onDismiss: () -> Unit,
    onOpenUrl: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        when (val state = downloadState) {
            is UpdateDownloadState.Idle -> {
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.cancel),
                        style = typo().labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {
                        if (response.downloadUrl != null) {
                            onStartDownload()
                        } else {
                            onOpenUrl("https://github.com/beyondbday69/SimpMusic/releases")
                        }
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                ) {
                    Icon(
                        imageVector = SimpIcons.Download,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(Res.string.download),
                        style =
                            typo().labelLarge.copy(
                                fontFamily = googleSansFlexFamily(),
                                fontWeight = FontWeight.Bold,
                            ),
                    )
                }
            }

            is UpdateDownloadState.Downloading -> {
                OutlinedButton(
                    onClick = onCancelDownload,
                    shape = RoundedCornerShape(18.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                ) {
                    Icon(
                        imageVector = SimpIcons.Close,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Cancel",
                        style = typo().labelLarge,
                    )
                }
            }

            is UpdateDownloadState.Completed -> {
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(
                        text = "Later",
                        style = typo().labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { onInstallUpdate(state.filePath) },
                    shape = RoundedCornerShape(18.dp),
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                ) {
                    Icon(
                        imageVector = SimpIcons.CheckCircle,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Install Now",
                        style =
                            typo().labelLarge.copy(
                                fontFamily = googleSansFlexFamily(),
                                fontWeight = FontWeight.Bold,
                            ),
                    )
                }
            }

            is UpdateDownloadState.Error -> {
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Text(
                        text = stringResource(Res.string.cancel),
                        style = typo().labelLarge,
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = onRetryDownload,
                    shape = RoundedCornerShape(18.dp),
                ) {
                    Icon(
                        imageVector = SimpIcons.Sync,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Retry",
                        style = typo().labelLarge.copy(fontWeight = FontWeight.Bold),
                    )
                }
            }
        }
    }
}
