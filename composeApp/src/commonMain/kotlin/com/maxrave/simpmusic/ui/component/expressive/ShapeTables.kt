package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

object ShapeTables {
    const val NUM_POINTS = 48

    /**
     * Precomputes normalized radius table r(theta) for 48 points:
     * r = (1 + a * cos(k * theta)) / (1 + a)
     */
    fun createRadiusTable(lobes: Int, depth: Float): FloatArray {
        return FloatArray(NUM_POINTS) { i ->
            val theta = (i.toFloat() / NUM_POINTS) * 2f * PI.toFloat()
            (1f + depth * cos(lobes * theta)) / (1f + depth)
        }
    }

    // Cookie: 9 lobes, depth 0.09 (Favorites)
    val CookieRadiusTable = createRadiusTable(9, 0.09f)

    // Flower: 6 lobes, depth 0.14 (Followed)
    val FlowerRadiusTable = createRadiusTable(6, 0.14f)

    // Downloaded: 8 lobes, depth 0.12 (Downloaded)
    val DownloadRadiusTable = createRadiusTable(8, 0.12f)
}

/**
 * Parametric lobed shape evaluated at 48 points from precomputed or custom radius tables.
 */
class LobedShape(
    private val radiusTable: FloatArray,
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
}
