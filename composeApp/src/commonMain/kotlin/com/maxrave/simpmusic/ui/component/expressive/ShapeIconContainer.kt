package com.maxrave.simpmusic.ui.component.expressive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.maxrave.simpmusic.ui.icon.Downloading
import com.maxrave.simpmusic.ui.icon.Favorite
import com.maxrave.simpmusic.ui.icon.PeopleAlt
import com.maxrave.simpmusic.ui.icon.SimpIcons

/**
 * Expressive lobed shape icon container.
 *
 * Used across the Quick Access Grid:
 * - Favorites: 9-lobe cookie (72dp, icon 34dp)
 * - Followed: 6-lobe flower (44dp, icon 26dp)
 * - Downloaded: 8-lobe (44dp, icon 26dp)
 */
@Composable
fun ShapeIconContainer(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    shape: Shape,
    containerColor: Color,
    contentColor: Color,
    size: Dp = 44.dp,
    iconSize: Dp = 26.dp,
    rotation: Float = 0f,
    contentDescription: String? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer {
                rotationZ = rotation
            }
            .clip(shape)
            .background(containerColor),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = contentColor,
            modifier = Modifier.size(iconSize),
        )
    }
}

@Preview
@Composable
private fun ShapeIconContainerPreviewLight() {
    MaterialTheme {
        Surface {
            Row(modifier = Modifier.padding(16.dp)) {
                ShapeIconContainer(
                    icon = SimpIcons.Favorite,
                    shape = LobedShape(9, 0.09f),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    size = 72.dp,
                    iconSize = 34.dp,
                    rotation = 0f,
                )
                ShapeIconContainer(
                    icon = SimpIcons.PeopleAlt,
                    shape = LobedShape(6, 0.14f),
                    containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondaryContainer,
                    size = 44.dp,
                    iconSize = 26.dp,
                    rotation = 40f,
                    modifier = Modifier.padding(start = 12.dp),
                )
                ShapeIconContainer(
                    icon = SimpIcons.Downloading,
                    shape = LobedShape(8, 0.12f),
                    containerColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.tertiaryContainer,
                    size = 44.dp,
                    iconSize = 26.dp,
                    rotation = 0f,
                    modifier = Modifier.padding(start = 12.dp),
                )
            }
        }
    }
}
