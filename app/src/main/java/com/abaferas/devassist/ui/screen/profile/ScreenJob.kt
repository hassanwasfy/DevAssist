package com.abaferas.devassist.ui.screen.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenJob(
    screenJobViewModel: ScreenJobViewModel = hiltViewModel()
) {
    val state by screenJobViewModel.state.collectAsStateWithLifecycle()
    ScreenJobContent(state = state, interaction = screenJobViewModel)
    NavigationHandler(effects = screenJobViewModel.effect) { effect, controller ->
        when (effect) {
            is JobScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenJobContent(
    state: JobUiState,
    interaction: JobScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        isInternetConnected = true,
        onRetry = {},
        topBar = {
            DevTopAppBarWithLogo("Job Finder")
        },
        floating = {}
    ) {

    }
}