package com.abaferas.devassist.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.abaferas.devassist.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun DevLoading(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_lottie))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}