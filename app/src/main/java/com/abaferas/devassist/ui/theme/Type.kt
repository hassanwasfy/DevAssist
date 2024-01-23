package com.abaferas.devassist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.abaferas.devassist.R


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )*/
)

val Tajawal = FontFamily(
    Font(R.font.tajawal_light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(R.font.tajawal, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(R.font.tajawal_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.tajawal_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(R.font.tajawal_extrabold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
)