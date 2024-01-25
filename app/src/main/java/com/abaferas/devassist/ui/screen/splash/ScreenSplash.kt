package com.abaferas.devassist.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor


@Composable
fun ScreenSplash(
    screenSplashViewModel: ScreenSplashViewModel = hiltViewModel()
) {
    val state by screenSplashViewModel.state.collectAsStateWithLifecycle()
    ScreenSplashContent(state = state, interaction = screenSplashViewModel)
    NavigationHandler(effects = screenSplashViewModel.effect) { effect, controller ->
        when (effect) {
            is SplashScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSplashContent(
    state: SplashUiState,
    interaction: SplashScreenInteraction
) {
    DevScaffold(
        isError = state.error.isError,
        isLoading = state.isLoading
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color_lightPrimaryColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DevLottie(id = R.raw.splash_robot)
            Box(modifier = Modifier.fillMaxWidth()){
                LinearProgressIndicator()
                DevLabel(text = "Dev Assist", color = color_lightPrimaryColor)
            }
        }
    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun SplashTester() {

}