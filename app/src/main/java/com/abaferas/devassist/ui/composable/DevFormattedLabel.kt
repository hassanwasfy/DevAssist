package com.abaferas.devassist.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor

@Composable
fun DevFormattedLabel(value: Float) {
    val formattedPercentage = remember(value) {
        AnnotatedString.Builder().apply {
            append("${(value * 100).toInt()}%")
        }.toAnnotatedString()
    }

    DevLabel(text = formattedPercentage.text, modifier = Modifier.padding(top = 16.dp), fontSize = 20,
        fontWeight = FontWeight.ExtraBold,
        color = color_darkPrimaryColor
    )
}