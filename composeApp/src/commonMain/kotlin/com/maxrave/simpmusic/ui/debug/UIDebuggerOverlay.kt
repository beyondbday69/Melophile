package com.maxrave.simpmusic.ui.debug

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.maxrave.simpmusic.ui.icon.Close
import com.maxrave.simpmusic.ui.icon.SimpIcons
import kotlin.math.roundToInt

/**
 * Visual layout inspector modifier.
 * When Inspector Mode is turned on in UI Debugger, wraps the component with a clear debug outline.
 */
@Composable
fun Modifier.debugOutline(name: String = ""): Modifier {
    val config = LocalUIDebuggerConfig.current
    if (!config.appearance.showComponentOutlines) return this
    return this.drawWithContent {
        drawContent()
        drawRect(
            color = Color(0xFF00E5FF).copy(alpha = 0.8f),
            style = Stroke(width = 1.5.dp.toPx()),
        )
    }
}

/**
 * Top-level floating UI Debugger widget and live inspector overlay.
 */
@Composable
fun UIDebuggerOverlay(
    modifier: Modifier = Modifier,
) {
    val config by UIDebuggerManager.config.collectAsState()
    if (!config.enabled) return

    var offsetX by remember { mutableFloatStateOf(16f) }
    var offsetY by remember { mutableFloatStateOf(100f) }

    // Floating Draggable Trigger Button
    Box(
        modifier =
            modifier
                .fillMaxSize(),
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            shadowElevation = 8.dp,
            modifier =
                Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX = (offsetX + dragAmount.x).coerceIn(0f, 1000f)
                            offsetY = (offsetY + dragAmount.y).coerceIn(50f, 2200f)
                        }
                    }
                    .clickable { UIDebuggerManager.togglePanel() },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Text(
                    text = "🛠️",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 4.dp),
                )
                Text(
                    text = "UI Debug",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                if (config.colorOverrides.isNotEmpty()) {
                    Box(
                        modifier =
                            Modifier
                                .padding(start = 6.dp)
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary),
                    )
                }
            }
        }

        // Full UI Debugger Modal Sheet / Dialog
        if (config.isPanelOpen) {
            UIDebuggerPanelDialog(onDismiss = { UIDebuggerManager.closePanel() })
        }
    }
}

@Composable
private fun UIDebuggerPanelDialog(
    onDismiss: () -> Unit,
) {
    val config by UIDebuggerManager.config.collectAsState()
    var selectedTab by remember { mutableIntStateOf(config.activeTab) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Surface(
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 16.dp,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
        ) {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "🛠️ UI Debugger & Live Inspector",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "Live edit colors, shapes & styles across the app",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = SimpIcons.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                // Tabs
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        text = { Text("🎨 Colors", fontSize = 13.sp) },
                    )
                    Tab(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        text = { Text("📐 Shapes", fontSize = 13.sp) },
                    )
                    Tab(
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 },
                        text = { Text("✨ Appearance", fontSize = 13.sp) },
                    )
                    Tab(
                        selected = selectedTab == 3,
                        onClick = { selectedTab = 3 },
                        text = { Text("💾 Presets", fontSize = 13.sp) },
                    )
                }

                // Body content based on tab
                Box(
                    modifier =
                        Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                ) {
                    when (selectedTab) {
                        0 -> ColorsTabContent()
                        1 -> ShapesTabContent()
                        2 -> AppearanceTabContent()
                        3 -> PresetsTabContent()
                    }
                }
            }
        }
    }
}

// -------------------------------------------------------------
// TAB 0: COLORS
// -------------------------------------------------------------

private val COLOR_TOKENS =
    listOf(
        "primary" to "Primary (Brand)",
        "onPrimary" to "On Primary",
        "primaryContainer" to "Primary Container",
        "onPrimaryContainer" to "On Primary Container",
        "secondary" to "Secondary",
        "tertiary" to "Tertiary",
        "surface" to "Surface",
        "onSurface" to "On Surface",
        "surfaceVariant" to "Surface Variant",
        "onSurfaceVariant" to "On Surface Variant",
        "surfaceContainerLowest" to "Container Lowest",
        "surfaceContainerLow" to "Container Low",
        "surfaceContainer" to "Container (Default)",
        "surfaceContainerHigh" to "Container High",
        "surfaceContainerHighest" to "Container Highest",
        "background" to "App Background",
        "outline" to "Outline / Border",
    )

private val SWATCH_PALETTE =
    listOf(
        Color(0xFF00E5FF),
        Color(0xFF10B981),
        Color(0xFFFF007F),
        Color(0xFFFF6B6B),
        Color(0xFF8B5CF6),
        Color(0xFFF59E0B),
        Color(0xFF3B82F6),
        Color(0xFF14B8A6),
        Color(0xFF000000),
        Color(0xFF111827),
        Color(0xFF1E1E2E),
        Color(0xFFF8FAFC),
        Color(0xFFFAFAFA),
        Color(0xFFFFFFFF),
    )

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ColorsTabContent() {
    val config by UIDebuggerManager.config.collectAsState()
    var selectedTokenKey by remember { mutableStateOf("primary") }

    val currentTheme = MaterialTheme.colorScheme
    val currentColor =
        config.colorOverrides[selectedTokenKey]?.let { Color(it) }
            ?: when (selectedTokenKey) {
                "primary" -> currentTheme.primary
                "onPrimary" -> currentTheme.onPrimary
                "primaryContainer" -> currentTheme.primaryContainer
                "onPrimaryContainer" -> currentTheme.onPrimaryContainer
                "secondary" -> currentTheme.secondary
                "tertiary" -> currentTheme.tertiary
                "surface" -> currentTheme.surface
                "onSurface" -> currentTheme.onSurface
                "surfaceVariant" -> currentTheme.surfaceVariant
                "onSurfaceVariant" -> currentTheme.onSurfaceVariant
                "surfaceContainerLowest" -> currentTheme.surfaceContainerLowest
                "surfaceContainerLow" -> currentTheme.surfaceContainerLow
                "surfaceContainer" -> currentTheme.surfaceContainer
                "surfaceContainerHigh" -> currentTheme.surfaceContainerHigh
                "surfaceContainerHighest" -> currentTheme.surfaceContainerHighest
                "background" -> currentTheme.background
                "outline" -> currentTheme.outline
                else -> currentTheme.primary
            }

    var redVal by remember(selectedTokenKey, currentColor) { mutableFloatStateOf(currentColor.red * 255f) }
    var greenVal by remember(selectedTokenKey, currentColor) { mutableFloatStateOf(currentColor.green * 255f) }
    var blueVal by remember(selectedTokenKey, currentColor) { mutableFloatStateOf(currentColor.blue * 255f) }
    var alphaVal by remember(selectedTokenKey, currentColor) { mutableFloatStateOf(currentColor.alpha * 100f) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Text(
            text = "Select Token to Edit",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))

        // Tokens FlowRow
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            COLOR_TOKENS.forEach { (key, label) ->
                val isSelected = selectedTokenKey == key
                val hasOverride = config.colorOverrides.containsKey(key)
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedTokenKey = key },
                    label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (hasOverride) {
                                Box(
                                    modifier =
                                        Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.primary)
                                            .padding(end = 4.dp),
                                )
                                Spacer(Modifier.width(4.dp))
                            }
                            Text(label, fontSize = 11.5.sp)
                        }
                    },
                    colors =
                        FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Current Swatch Preview Card
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(14.dp),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(currentColor)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp)),
                )
                Spacer(Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = COLOR_TOKENS.firstOrNull { it.first == selectedTokenKey }?.second ?: selectedTokenKey,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    val hex =
                        "#" +
                            ((currentColor.alpha * 255).toInt().toString(16).padStart(2, '0')) +
                            ((currentColor.red * 255).toInt().toString(16).padStart(2, '0')) +
                            ((currentColor.green * 255).toInt().toString(16).padStart(2, '0')) +
                            ((currentColor.blue * 255).toInt().toString(16).padStart(2, '0')).uppercase()
                    Text(
                        text = hex,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                if (config.colorOverrides.containsKey(selectedTokenKey)) {
                    OutlinedButton(
                        onClick = { UIDebuggerManager.removeColorOverride(selectedTokenKey) },
                        contentPadding = ButtonDefaults.TextButtonContentPadding,
                    ) {
                        Text("Reset", fontSize = 12.sp)
                    }
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        // Quick Swatch Palette
        Text(
            text = "Quick Palette Swatches",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            SWATCH_PALETTE.forEach { swatch ->
                Box(
                    modifier =
                        Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(swatch)
                            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                            .clickable {
                                redVal = swatch.red * 255f
                                greenVal = swatch.green * 255f
                                blueVal = swatch.blue * 255f
                                alphaVal = swatch.alpha * 100f
                                UIDebuggerManager.setColorOverride(selectedTokenKey, swatch)
                            },
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // RGB & Alpha Sliders
        ColorChannelSlider(
            label = "Red",
            value = redVal,
            color = Color.Red,
            onValueChange = {
                redVal = it
                val updated = Color(redVal / 255f, greenVal / 255f, blueVal / 255f, alphaVal / 100f)
                UIDebuggerManager.setColorOverride(selectedTokenKey, updated)
            },
        )
        ColorChannelSlider(
            label = "Green",
            value = greenVal,
            color = Color.Green,
            onValueChange = {
                greenVal = it
                val updated = Color(redVal / 255f, greenVal / 255f, blueVal / 255f, alphaVal / 100f)
                UIDebuggerManager.setColorOverride(selectedTokenKey, updated)
            },
        )
        ColorChannelSlider(
            label = "Blue",
            value = blueVal,
            color = Color.Blue,
            onValueChange = {
                blueVal = it
                val updated = Color(redVal / 255f, greenVal / 255f, blueVal / 255f, alphaVal / 100f)
                UIDebuggerManager.setColorOverride(selectedTokenKey, updated)
            },
        )
        ColorChannelSlider(
            label = "Alpha",
            value = alphaVal,
            maxValue = 100f,
            unit = "%",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            onValueChange = {
                alphaVal = it
                val updated = Color(redVal / 255f, greenVal / 255f, blueVal / 255f, alphaVal / 100f)
                UIDebuggerManager.setColorOverride(selectedTokenKey, updated)
            },
        )
    }
}

@Composable
private fun ColorChannelSlider(
    label: String,
    value: Float,
    maxValue: Float = 255f,
    unit: String = "",
    color: Color,
    onValueChange: (Float) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = color,
            modifier = Modifier.width(55.dp),
        )
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..maxValue,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "${value.toInt()}$unit",
            fontFamily = FontFamily.Monospace,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.width(45.dp),
        )
    }
}

// -------------------------------------------------------------
// TAB 1: SHAPES
// -------------------------------------------------------------

@Composable
private fun ShapesTabContent() {
    val config by UIDebuggerManager.config.collectAsState()
    val shapes = config.shapes

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Text(
            text = "Corner Radius Dials (dp)",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(12.dp))

        ShapeRadiusSlider(
            label = "Small Radius (Chips, Tags, Badges)",
            value = shapes.smallCornerDp,
            max = 32f,
            onValueChange = { UIDebuggerManager.setShapeCorners(small = it) },
        )
        ShapeRadiusSlider(
            label = "Medium Radius (Song items, list tiles, cards)",
            value = shapes.mediumCornerDp,
            max = 40f,
            onValueChange = { UIDebuggerManager.setShapeCorners(medium = it) },
        )
        ShapeRadiusSlider(
            label = "Large Radius (Hero shelves, containers, dialogs)",
            value = shapes.largeCornerDp,
            max = 64f,
            onValueChange = { UIDebuggerManager.setShapeCorners(large = it) },
        )
        ShapeRadiusSlider(
            label = "Pill Radius (Capsules, connected groups)",
            value = shapes.pillCornerDp,
            max = 48f,
            onValueChange = { UIDebuggerManager.setShapeCorners(pill = it) },
        )
        ShapeRadiusSlider(
            label = "Button Radius (Action & standard buttons)",
            value = shapes.buttonCornerDp,
            max = 36f,
            onValueChange = { UIDebuggerManager.setShapeCorners(button = it) },
        )

        Spacer(Modifier.height(16.dp))

        // Visual Shape Previews Box
        Text(
            text = "Live Shape Hierarchy Preview",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Surface(
                shape = shapes.small,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.weight(1f).height(48.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Small", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Surface(
                shape = shapes.medium,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.weight(1f).height(48.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Medium", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Surface(
                shape = shapes.large,
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier = Modifier.weight(1f).height(48.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("Large", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Surface(
                shape = shapes.pill,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.weight(1f).height(48.dp),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        "Pill",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                }
            }
        }
    }
}

@Composable
private fun ShapeRadiusSlider(
    label: String,
    value: Float,
    max: Float,
    onValueChange: (Float) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "${value.roundToInt()} dp",
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..max,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

// -------------------------------------------------------------
// TAB 2: APPEARANCE & INSPECTOR
// -------------------------------------------------------------

@Composable
private fun AppearanceTabContent() {
    val config by UIDebuggerManager.config.collectAsState()
    val appearance = config.appearance

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        // Layout Outlines Inspector Mode
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Component Outlines (Inspector Mode)",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "Draws cyan bounds around UI elements for inspection",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Switch(
                    checked = appearance.showComponentOutlines,
                    onCheckedChange = { UIDebuggerManager.setAppearance(showComponentOutlines = it) },
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Content Padding Scale Slider
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Content Spacing / Padding Multiplier",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${((appearance.contentPaddingScale * 100).roundToInt()) / 100f}x",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Slider(
                value = appearance.contentPaddingScale,
                onValueChange = { UIDebuggerManager.setAppearance(contentPaddingScale = it) },
                valueRange = 0.7f..1.5f,
            )
        }

        // Font Scale Slider
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Typography Font Scale",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${((appearance.fontScale * 100).roundToInt()) / 100f}x",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Slider(
                value = appearance.fontScale,
                onValueChange = { UIDebuggerManager.setAppearance(fontScale = it) },
                valueRange = 0.8f..1.4f,
            )
        }

        // Liquid Glass Alpha Slider
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Liquid Glass Blur Opacity",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${(appearance.glassAlpha * 100).roundToInt()}%",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Slider(
                value = appearance.glassAlpha,
                onValueChange = { UIDebuggerManager.setAppearance(glassAlpha = it) },
                valueRange = 0f..1.0f,
            )
        }

        // Border Width Slider
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Global Component Border Width",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = "${appearance.borderWidthDp.roundToInt()} dp",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
            Slider(
                value = appearance.borderWidthDp,
                onValueChange = { UIDebuggerManager.setAppearance(borderWidth = it) },
                valueRange = 0f..4f,
            )
        }
    }
}

// -------------------------------------------------------------
// TAB 3: PRESETS & EXPORT
// -------------------------------------------------------------

@Composable
private fun PresetsTabContent() {
    var showExportJson by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Text(
            text = "Color Palette Presets",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))

        UIDebuggerPresets.colorPresets.forEach { preset ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .clickable { UIDebuggerManager.applyColorPreset(preset) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                ) {
                    Text(
                        text = preset.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                    )
                    preset.overrides["primary"]?.let {
                        Box(
                            modifier =
                                Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(Color(it)),
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Shape Geometry Presets",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(8.dp))

        UIDebuggerPresets.shapePresets.forEach { preset ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .clickable { UIDebuggerManager.applyShapePreset(preset) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                ) {
                    Text(
                        text = preset.name,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        text = "${preset.shapes.largeCornerDp.toInt()}dp",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Reset All Button
        Button(
            onClick = { UIDebuggerManager.resetAll() },
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("🔄 Reset All Customizations to Default")
        }

        Spacer(Modifier.height(8.dp))

        // Export Config JSON
        OutlinedButton(
            onClick = { showExportJson = !showExportJson },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (showExportJson) "Hide Exported JSON" else "📋 Export Configuration (JSON)")
        }

        AnimatedVisibility(
            visible = showExportJson,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            val json = remember { UIDebuggerManager.exportConfigJson() }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            ) {
                Text(
                    text = json,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(12.dp),
                )
            }
        }
    }
}
