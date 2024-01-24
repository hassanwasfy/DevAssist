package com.abaferas.devassist.ui.composable.modifier

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

fun Modifier.roundCorner(cornerSize: Int): Modifier {
    return this.clip(roundCornerShape(cornerSize))
}

fun roundCornerShape(corner: Int): RoundedCornerShape {
    return RoundedCornerShape(CornerSize(corner.dp))
}