package com.abaferas.devassist.ui.composable

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.theme.Tajawal
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor
import com.abaferas.devassist.ui.theme.color_textSecondaryColor

@Composable
fun DevButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 14,
    fontFamily: FontFamily = Tajawal,
    fontWeight: FontWeight = FontWeight.Bold,
    color: Color = color_textColor,
    enabled: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    shape: Shape = roundCornerShape(corner = 12),
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = color_darkPrimaryColor,
        contentColor = color_textPrimaryColor
    ),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
        shape = shape,
        enabled = enabled
    ) {
        DevLabel(
            modifier = Modifier,
            text = text,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight,
            color = color,
            textAlign = textAlign,
        )
    }
}