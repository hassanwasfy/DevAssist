package com.abaferas.devassist.ui.composable.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.Constants


fun Modifier.mainContainerPadding(): Modifier {
    return this.padding(
        top = Constants.TOP_BAR_HEIGHT.dp,
        bottom = Constants.BOTTOM_BAR_HEIGHT.dp
    )
}