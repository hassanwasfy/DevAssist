package com.abaferas.devassist.ui.composable

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DevLottie(
    modifier: Modifier = Modifier,
    @RawRes id: Int){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )
    LottieAnimation(
        modifier = modifier.background(color = color_lightPrimaryColor),
        composition = composition,
        progress = { progress },
        alignment = Alignment.Center
    )
}

@Composable
fun DevLottieSimple(
    modifier: Modifier = Modifier,
    @RawRes id: Int){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(id))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
        alignment = Alignment.Center
    )
}

