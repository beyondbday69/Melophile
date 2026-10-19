package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

object ShapeTables {
    // 240 samples keeps the outline smooth on large covers (the old 72-point
    // table produced visible chords at 196dp) while still being cheap to
    // rebuild on every animation frame.
    const val NUM_POINTS = 240

    /**
     * Precomputes normalized radius table r(theta) for [NUM_POINTS] points:
     * r = (1 + a * cos(k * theta)) / (1 + a)
     */
    fun createRadiusTable(lobes: Int, depth: Float): FloatArray {
        return FloatArray(NUM_POINTS) { i ->
            val theta = (i.toFloat() / NUM_POINTS) * 2f * PI.toFloat()
            (1f + depth * cos(lobes * theta)) / (1f + depth)
        }
    }

    // 1. Cookie: 9 lobes, depth 0.09 (Favorites)
    val CookieRadiusTable = createRadiusTable(9, 0.09f)

    // 2. Flower: 6 lobes, depth 0.14 (Followed)
    val FlowerRadiusTable = createRadiusTable(6, 0.14f)

    // 3. Star: 8 lobes, depth 0.12 (Downloaded)
    val StarRadiusTable = createRadiusTable(8, 0.12f)
    val DownloadRadiusTable = StarRadiusTable

    // 4. Clover: 4 lobes, depth 0.18
    val CloverRadiusTable = createRadiusTable(4, 0.18f)

    // 5. Sunburst: 12 lobes, depth 0.08
    val SunburstRadiusTable = createRadiusTable(12, 0.08f)

    // 6. Circle: uniform radius 1.0f
    val CircleRadiusTable = FloatArray(NUM_POINTS) { 1.0f }

    // 7. Squircle: superellipse n = 4.0
    val SquircleRadiusTable = FloatArray(NUM_POINTS) { i ->
        val theta = (i.toFloat() / NUM_POINTS) * 2f * PI.toFloat()
        val c = abs(cos(theta))
        val s = abs(sin(theta))
        val denom = sqrt(sqrt(c * c * c * c + s * s * s * s))
        if (denom > 0.001f) 1.0f / denom else 1.0f
    }

    // 8. Arch: semicircular dome top, rounded rectangular bottom
    val ArchRadiusTable = FloatArray(NUM_POINTS) { i ->
        val theta = (i.toFloat() / NUM_POINTS) * 2f * PI.toFloat()
        val ct = cos(theta)
        val st = sin(theta)
        if (st <= 0f) {
            1.0f
        } else {
            val c = abs(ct)
            val s = st
            val c6 = c * c * c * c * c * c
            val s6 = s * s * s * s * s * s
            val denom = (c6 + s6).toDouble().pow(1.0 / 6.0).toFloat()
            if (denom > 0.001f) 1.0f / denom else 1.0f
        }
    }

    // 9. Diamond / Cut corner: chamfered corners matching CutCornerShape(20.dp)
    val DiamondRadiusTable = FloatArray(NUM_POINTS) { i ->
        val theta = (i.toFloat() / NUM_POINTS) * 2f * PI.toFloat()
        val ct = abs(cos(theta))
        val st = abs(sin(theta))
        val maxAxis = max(ct, st)
        val rBox = if (maxAxis > 0f) 1.0f / maxAxis else 1.0f
        val sumAxis = ct + st
        val rCut = if (sumAxis > 0f) 1.65f / sumAxis else 1.0f
        min(rBox, rCut)
    }

    /**
     * Linearly interpolates two radius tables for continuous shape morphing.
     */
    fun lerpTables(from: FloatArray, to: FloatArray, progress: Float): FloatArray {
        val clamped = progress.coerceIn(0f, 1f)
        val count = min(from.size, to.size)
        return FloatArray(count) { i ->
            from[i] * (1f - clamped) + to[i] * clamped
        }
    }
}

/**
 * Parametric lobed shape evaluated at [ShapeTables.NUM_POINTS] points from precomputed or dynamically morphing radius tables.
 */
class LobedShape(
    val radiusTable: FloatArray,
) : Shape {
    constructor(lobes: Int, depth: Float) : this(ShapeTables.createRadiusTable(lobes, depth))

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path()
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radiusX = size.width / 2f
        val radiusY = size.height / 2f
        val numPoints = radiusTable.size

        for (i in 0 until numPoints) {
            val theta = (i.toFloat() / numPoints) * 2f * PI.toFloat()
            val r = radiusTable[i]
            val x = centerX + radiusX * r * cos(theta)
            val y = centerY + radiusY * r * sin(theta)
            if (i == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }
        path.close()
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LobedShape) return false
        return radiusTable.contentEquals(other.radiusTable)
    }

    override fun hashCode(): Int {
        return radiusTable.contentHashCode()
    }
}
