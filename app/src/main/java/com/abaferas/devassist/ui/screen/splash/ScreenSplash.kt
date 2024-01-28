package com.abaferas.devassist.ui.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.auth.signup.navigateToSignUp
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
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

            is SplashScreenUiEffect.Home -> {
                controller.navigateToHome()
            }
            is SplashScreenUiEffect.SignUp -> {
                controller.navigateToSignUp()
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
        isLoading = state.isLoading,
        onRetry = {}
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color_lightPrimaryColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DevLottie(id = R.raw.splash_robot)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .border(2.dp, color_darkPrimaryColor)
                    .padding(8.dp),
            ) {
                val progress by animateFloatAsState(
                    targetValue = state.progress, label = "",
                    animationSpec = tween(200)
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    color = color_darkPrimaryColor,
                    trackColor = color_lightPrimaryColor,
                    strokeCap = StrokeCap.Square,
                    progress = progress
                )
                DevLabel(
                    text = "Dev Assist",
                    color = color_lightPrimaryColor,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontSize = 24,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}