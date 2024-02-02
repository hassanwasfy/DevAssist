package com.abaferas.devassist.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.utils.Constants

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevScaffold(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isError: Boolean,
    errorMsg: String,
    isInternetConnected: Boolean = true,
    onRetry: () -> Unit = {},
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floating: @Composable () -> Unit = {},
    loading: @Composable () -> Unit = { DevLoading() },
    error: @Composable () -> Unit = {
        DevError(
            errorMsg = errorMsg,
            onRetry = onRetry,
        )
    },
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floating,
    ) { _ ->
        DevAnimatedVisibility(
            visible = isLoading,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                loading()
            }
        }
        DevAnimatedVisibility(visible = isError && !isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                error()
            }
        }
        DevAnimatedVisibility(
            visible = !isError && !isLoading,
            content = content
        )
    }
}