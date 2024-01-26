package com.abaferas.devassist.ui.screen.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenProfile(
    screenProfileViewModel: ScreenProfileViewModel = hiltViewModel()
) {
    val state by screenProfileViewModel.state.collectAsStateWithLifecycle()
    ScreenProfileContent(state = state, interaction = screenProfileViewModel)
    NavigationHandler(effects = screenProfileViewModel.effect) { effect, controller ->
        when (effect) {
            is ProfileScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenProfileContent(
    state: ProfileUiState,
    interaction: ProfileScreenInteraction
) {

}