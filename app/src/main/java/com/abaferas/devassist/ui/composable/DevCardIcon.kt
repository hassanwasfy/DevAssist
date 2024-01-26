package com.abaferas.devassist.ui.composable

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevCardIcon(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color = color_darkPrimaryColor,
    shape: Shape = RoundedCornerShape(CornerSize(8.dp)),
    colors: CardColors = CardDefaults.cardColors(),
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        colors = colors,
    ) {
        Icon(
            modifier = iconModifier,
            imageVector = icon,
            contentDescription = "",
            tint = iconColor
        )
    }
}