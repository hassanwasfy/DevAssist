package com.abaferas.devassist.ui.composable

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.abaferas.devassist.ui.theme.Tajawal
import com.abaferas.devassist.ui.theme.color_textSecondaryColor

@Composable
fun DevLabel(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 14,
    fontFamily: FontFamily = Tajawal,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = color_textSecondaryColor,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier,
        textAlign = textAlign,
    )
}