package com.abaferas.devassist.ui.screen.splash

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue


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

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun SplashTester() {

}