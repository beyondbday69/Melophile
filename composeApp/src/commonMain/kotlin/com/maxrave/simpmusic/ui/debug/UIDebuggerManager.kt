package com.maxrave.simpmusic.ui.debug

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class UIDebuggerShapes(
    val smallCornerDp: Float = 8f,
    val mediumCornerDp: Float = 16f,
    val largeCornerDp: Float = 24f,
    val pillCornerDp: Float = 24f,
    val buttonCornerDp: Float = 20f,
) {
    val small: CornerBasedShape get() = RoundedCornerShape(smallCornerDp.dp)
    val medium: CornerBasedShape get() = RoundedCornerShape(mediumCornerDp.dp)
    val large: CornerBasedShape get() = RoundedCornerShape(largeCornerDp.dp)
    val pill: CornerBasedShape get() = RoundedCornerShape(pillCornerDp.dp)
    val button: CornerBasedShape get() = RoundedCornerShape(buttonCornerDp.dp)

    fun toMaterialShapes(): Shapes =
        Shapes(
            extraSmall = RoundedCornerShape((smallCornerDp / 2f).coerceAtLeast(0f).dp),
            small = small,
            medium = medium,
            large = large,
            extraLarge = RoundedCornerShape((largeCornerDp * 1.25f).coerceAtLeast(0f).dp),
        )
}

@Immutable
data class UIDebuggerAppearance(
    val borderWidthDp: Float = 0f,
    val borderColorArgb: ULong = 0xFF777777UL,
    val contentPaddingScale: Float = 1.0f,
    val fontScale: Float = 1.0f,
    val showComponentOutlines: Boolean = false,
    val glassAlpha: Float = 1.0f,
)

@Immutable
data class UIDebuggerConfig(
    val enabled: Boolean = true,
    val isPanelOpen: Boolean = false,
    val activeTab: Int = 0, // 0: Colors, 1: Shapes, 2: Appearance, 3: Presets
    val colorOverrides: Map<String, ULong> = emptyMap(),
    val shapes: UIDebuggerShapes = UIDebuggerShapes(),
    val appearance: UIDebuggerAppearance = UIDebuggerAppearance(),
) {
    fun applyToColorScheme(base: ColorScheme): ColorScheme {
        if (colorOverrides.isEmpty()) return base
        var scheme = base
        colorOverrides["primary"]?.let { scheme = scheme.copy(primary = Color(it)) }
        colorOverrides["onPrimary"]?.let { scheme = scheme.copy(onPrimary = Color(it)) }
        colorOverrides["primaryContainer"]?.let { scheme = scheme.copy(primaryContainer = Color(it)) }
        colorOverrides["onPrimaryContainer"]?.let { scheme = scheme.copy(onPrimaryContainer = Color(it)) }
        colorOverrides["secondary"]?.let { scheme = scheme.copy(secondary = Color(it)) }
        colorOverrides["onSecondary"]?.let { scheme = scheme.copy(onSecondary = Color(it)) }
        colorOverrides["secondaryContainer"]?.let { scheme = scheme.copy(secondaryContainer = Color(it)) }
        colorOverrides["onSecondaryContainer"]?.let { scheme = scheme.copy(onSecondaryContainer = Color(it)) }
        colorOverrides["tertiary"]?.let { scheme = scheme.copy(tertiary = Color(it)) }
        colorOverrides["onTertiary"]?.let { scheme = scheme.copy(onTertiary = Color(it)) }
        colorOverrides["background"]?.let { scheme = scheme.copy(background = Color(it)) }
        colorOverrides["onBackground"]?.let { scheme = scheme.copy(onBackground = Color(it)) }
        colorOverrides["surface"]?.let { scheme = scheme.copy(surface = Color(it)) }
        colorOverrides["onSurface"]?.let { scheme = scheme.copy(onSurface = Color(it)) }
        colorOverrides["surfaceVariant"]?.let { scheme = scheme.copy(surfaceVariant = Color(it)) }
        colorOverrides["onSurfaceVariant"]?.let { scheme = scheme.copy(onSurfaceVariant = Color(it)) }
        colorOverrides["surfaceContainerLowest"]?.let { scheme = scheme.copy(surfaceContainerLowest = Color(it)) }
        colorOverrides["surfaceContainerLow"]?.let { scheme = scheme.copy(surfaceContainerLow = Color(it)) }
        colorOverrides["surfaceContainer"]?.let { scheme = scheme.copy(surfaceContainer = Color(it)) }
        colorOverrides["surfaceContainerHigh"]?.let { scheme = scheme.copy(surfaceContainerHigh = Color(it)) }
        colorOverrides["surfaceContainerHighest"]?.let { scheme = scheme.copy(surfaceContainerHighest = Color(it)) }
        colorOverrides["outline"]?.let { scheme = scheme.copy(outline = Color(it)) }
        colorOverrides["outlineVariant"]?.let { scheme = scheme.copy(outlineVariant = Color(it)) }
        return scheme
    }
}

val LocalUIDebuggerConfig = staticCompositionLocalOf { UIDebuggerConfig() }
val LocalUIDebuggerShapes = staticCompositionLocalOf { UIDebuggerShapes() }

object UIDebuggerManager {
    private val _config = MutableStateFlow(UIDebuggerConfig())
    val config: StateFlow<UIDebuggerConfig> = _config.asStateFlow()

    fun setEnabled(enabled: Boolean) {
        _config.update { it.copy(enabled = enabled) }
    }

    fun openPanel() {
        _config.update { it.copy(isPanelOpen = true) }
    }

    fun closePanel() {
        _config.update { it.copy(isPanelOpen = false) }
    }

    fun togglePanel() {
        _config.update { it.copy(isPanelOpen = !it.isPanelOpen) }
    }

    fun setActiveTab(tab: Int) {
        _config.update { it.copy(activeTab = tab) }
    }

    fun setColorOverride(key: String, color: Color) {
        val argb = color.toArgb().toULong() and 0xFFFFFFFFUL
        _config.update { cfg ->
            val updated = cfg.colorOverrides.toMutableMap()
            updated[key] = argb
            cfg.copy(colorOverrides = updated)
        }
    }

    fun removeColorOverride(key: String) {
        _config.update { cfg ->
            val updated = cfg.colorOverrides.toMutableMap()
            updated.remove(key)
            cfg.copy(colorOverrides = updated)
        }
    }

    fun setShapeCorners(
        small: Float? = null,
        medium: Float? = null,
        large: Float? = null,
        pill: Float? = null,
        button: Float? = null,
    ) {
        _config.update { cfg ->
            val s = cfg.shapes
            cfg.copy(
                shapes = s.copy(
                    smallCornerDp = small ?: s.smallCornerDp,
                    mediumCornerDp = medium ?: s.mediumCornerDp,
                    largeCornerDp = large ?: s.largeCornerDp,
                    pillCornerDp = pill ?: s.pillCornerDp,
                    buttonCornerDp = button ?: s.buttonCornerDp,
                )
            )
        }
    }

    fun setAppearance(
        borderWidth: Float? = null,
        borderColorArgb: ULong? = null,
        contentPaddingScale: Float? = null,
        fontScale: Float? = null,
        showComponentOutlines: Boolean? = null,
        glassAlpha: Float? = null,
    ) {
        _config.update { cfg ->
            val a = cfg.appearance
            cfg.copy(
                appearance = a.copy(
                    borderWidthDp = borderWidth ?: a.borderWidthDp,
                    borderColorArgb = borderColorArgb ?: a.borderColorArgb,
                    contentPaddingScale = contentPaddingScale ?: a.contentPaddingScale,
                    fontScale = fontScale ?: a.fontScale,
                    showComponentOutlines = showComponentOutlines ?: a.showComponentOutlines,
                    glassAlpha = glassAlpha ?: a.glassAlpha,
                )
            )
        }
    }

    fun applyColorPreset(preset: ColorPreset) {
        _config.update { it.copy(colorOverrides = preset.overrides) }
    }

    fun applyShapePreset(preset: ShapePreset) {
        _config.update { it.copy(shapes = preset.shapes) }
    }

    fun resetAll() {
        _config.update {
            UIDebuggerConfig(
                enabled = it.enabled,
                isPanelOpen = it.isPanelOpen,
                activeTab = it.activeTab,
            )
        }
    }

    fun exportConfigJson(): String {
        val cfg = _config.value
        val sb = StringBuilder()
        sb.appendLine("{")
        sb.appendLine("  \"shapes\": {")
        sb.appendLine("    \"smallCornerDp\": ${cfg.shapes.smallCornerDp},")
        sb.appendLine("    \"mediumCornerDp\": ${cfg.shapes.mediumCornerDp},")
        sb.appendLine("    \"largeCornerDp\": ${cfg.shapes.largeCornerDp},")
        sb.appendLine("    \"pillCornerDp\": ${cfg.shapes.pillCornerDp},")
        sb.appendLine("    \"buttonCornerDp\": ${cfg.shapes.buttonCornerDp}")
        sb.appendLine("  },")
        sb.appendLine("  \"appearance\": {")
        sb.appendLine("    \"borderWidthDp\": ${cfg.appearance.borderWidthDp},")
        sb.appendLine("    \"fontScale\": ${cfg.appearance.fontScale},")
        sb.appendLine("    \"glassAlpha\": ${cfg.appearance.glassAlpha}")
        sb.appendLine("  },")
        sb.appendLine("  \"colorOverrides\": {")
        val entries = cfg.colorOverrides.entries.toList()
        entries.forEachIndexed { idx, (k, v) ->
            val hex = "#" + v.toString(16).padStart(8, '0').uppercase()
            val trailing = if (idx == entries.size - 1) "" else ","
            sb.appendLine("    \"$k\": \"$hex\"$trailing")
        }
        sb.appendLine("  }")
        sb.append("}")
        return sb.toString()
    }
}

data class ColorPreset(val name: String, val overrides: Map<String, ULong>)
data class ShapePreset(val name: String, val shapes: UIDebuggerShapes)

object UIDebuggerPresets {
    val colorPresets = listOf(
        ColorPreset(
            name = "Default",
            overrides = emptyMap(),
        ),
        ColorPreset(
            name = "Neon Cyberpunk",
            overrides = mapOf(
                "primary" to 0xFF00E5FFUL,
                "onPrimary" to 0xFF00363DUL,
                "primaryContainer" to 0xFF004F58UL,
                "onPrimaryContainer" to 0xFF8BF0FFUL,
                "secondary" to 0xFFFF007FUL,
                "tertiary" to 0xFF76FF03UL,
                "background" to 0xFF0A0E17UL,
                "surface" to 0xFF111827UL,
                "surfaceContainerLow" to 0xFF182234UL,
                "surfaceContainer" to 0xFF1F2D44UL,
                "surfaceContainerHigh" to 0xFF2A3B56UL,
                "surfaceContainerHighest" to 0xFF354869UL,
                "outline" to 0xFF00E5FFUL,
                "onSurface" to 0xFFE0F7FAUL,
                "onSurfaceVariant" to 0xFF8BF0FFUL,
            ),
        ),
        ColorPreset(
            name = "AMOLED Emerald",
            overrides = mapOf(
                "primary" to 0xFF10B981UL,
                "onPrimary" to 0xFF003824UL,
                "primaryContainer" to 0xFF005237UL,
                "onPrimaryContainer" to 0xFF6EE7B7UL,
                "secondary" to 0xFF34D399UL,
                "tertiary" to 0xFFA7F3D0UL,
                "background" to 0xFF000000UL,
                "surface" to 0xFF080808UL,
                "surfaceContainerLow" to 0xFF0D1410UL,
                "surfaceContainer" to 0xFF14201AUL,
                "surfaceContainerHigh" to 0xFF1D2E26UL,
                "surfaceContainerHighest" to 0xFF273D32UL,
                "outline" to 0xFF059669UL,
                "onSurface" to 0xFFE6F4EAUL,
                "onSurfaceVariant" to 0xFFA7D7C5UL,
            ),
        ),
        ColorPreset(
            name = "Sunset Warmth",
            overrides = mapOf(
                "primary" to 0xFFFF6B6BUL,
                "onPrimary" to 0xFF5C0011UL,
                "primaryContainer" to 0xFF81001CUL,
                "onPrimaryContainer" to 0xFFFFDADBUL,
                "secondary" to 0xFFFFA07AUL,
                "tertiary" to 0xFFFFD166UL,
                "background" to 0xFF120E0EUL,
                "surface" to 0xFF191212UL,
                "surfaceContainerLow" to 0xFF231919UL,
                "surfaceContainer" to 0xFF2F2121UL,
                "surfaceContainerHigh" to 0xFF3D2B2BUL,
                "surfaceContainerHighest" to 0xFF4D3636UL,
                "outline" to 0xFFFF8E72UL,
                "onSurface" to 0xFFFFEDE8UL,
                "onSurfaceVariant" to 0xFFFFB5A7UL,
            ),
        ),
        ColorPreset(
            name = "Catppuccin Mocha",
            overrides = mapOf(
                "primary" to 0xFFCBA6F7UL,
                "onPrimary" to 0xFF311456UL,
                "primaryContainer" to 0xFF492B74UL,
                "onPrimaryContainer" to 0xFFEEDCFFUL,
                "secondary" to 0xFFF5C2E7UL,
                "tertiary" to 0xFF89B4FAUL,
                "background" to 0xFF1E1E2EUL,
                "surface" to 0xFF181825UL,
                "surfaceContainerLow" to 0xFF242438UL,
                "surfaceContainer" to 0xFF313244UL,
                "surfaceContainerHigh" to 0xFF45475AUL,
                "surfaceContainerHighest" to 0xFF585B70UL,
                "outline" to 0xFF9399B2UL,
                "onSurface" to 0xFFCDD6F4UL,
                "onSurfaceVariant" to 0xFFA6ADC8UL,
            ),
        ),
        ColorPreset(
            name = "Electric Purple",
            overrides = mapOf(
                "primary" to 0xFFB388FFUL,
                "onPrimary" to 0xFF2E006AUL,
                "primaryContainer" to 0xFF4A148CUL,
                "onPrimaryContainer" to 0xFFE7D8FFUL,
                "secondary" to 0xFFEA80FCUL,
                "tertiary" to 0xFF8C9EFFUL,
                "background" to 0xFF0D0B14UL,
                "surface" to 0xFF141120UL,
                "surfaceContainerLow" to 0xFF1C182EUL,
                "surfaceContainer" to 0xFF26213DUL,
                "surfaceContainerHigh" to 0xFF332D50UL,
                "surfaceContainerHighest" to 0xFF413A63UL,
                "outline" to 0xFF9C27B0UL,
                "onSurface" to 0xFFF3E5F5UL,
                "onSurfaceVariant" to 0xFFCE93D8UL,
            ),
        ),
    )

    val shapePresets = listOf(
        ShapePreset(
            name = "M3 Expressive (Default)",
            shapes = UIDebuggerShapes(
                smallCornerDp = 8f,
                mediumCornerDp = 16f,
                largeCornerDp = 24f,
                pillCornerDp = 24f,
                buttonCornerDp = 20f,
            ),
        ),
        ShapePreset(
            name = "Sharp / Minimal",
            shapes = UIDebuggerShapes(
                smallCornerDp = 2f,
                mediumCornerDp = 4f,
                largeCornerDp = 6f,
                pillCornerDp = 8f,
                buttonCornerDp = 4f,
            ),
        ),
        ShapePreset(
            name = "Playful / Bubbly",
            shapes = UIDebuggerShapes(
                smallCornerDp = 14f,
                mediumCornerDp = 22f,
                largeCornerDp = 32f,
                pillCornerDp = 36f,
                buttonCornerDp = 28f,
            ),
        ),
        ShapePreset(
            name = "Ultra Capsule",
            shapes = UIDebuggerShapes(
                smallCornerDp = 16f,
                mediumCornerDp = 28f,
                largeCornerDp = 44f,
                pillCornerDp = 48f,
                buttonCornerDp = 32f,
            ),
        ),
    )
}
