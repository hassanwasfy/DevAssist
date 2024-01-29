package com.abaferas.devassist.ui.screen.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenSettings(
    screenSettingsViewModel: ScreenSettingsViewModel = hiltViewModel()
) {
    val state by screenSettingsViewModel.state.collectAsStateWithLifecycle()
    ScreenSettingsContent(state = state, interaction = screenSettingsViewModel)
    NavigationHandler(effects = screenSettingsViewModel.effect) { effect, controller ->
        when (effect) {
            is SettingsScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSettingsContent(
    state: SettingsUiState,
    interaction: SettingsScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        onRetry = {}
    ) {
        DevLabel(text = "Settings")
    }
}