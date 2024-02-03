package com.abaferas.devassist.ui.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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