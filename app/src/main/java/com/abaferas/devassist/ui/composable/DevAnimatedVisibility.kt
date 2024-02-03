package com.abaferas.devassist.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.abaferas.devassist.Constants

@Composable
fun DevAnimatedVisibility(
    modifier: Modifier = Modifier,
    label: String = "",
    visible: Boolean,
    enter: EnterTransition = fadeIn(
        tween(Constants.ANIMATION_DURATION, easing = EaseIn)
    ),
    exit: ExitTransition = fadeOut(
        tween(Constants.ANIMATION_DURATION, easing = EaseOut)
    ),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        label = label,
        visible = visible,
        enter = enter,
        exit = exit
    ) {
        content()
    }
}