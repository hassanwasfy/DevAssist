package com.abaferas.devassist.ui.screen.books

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenBook(
    screenBookViewModel: ScreenBookViewModel = hiltViewModel()
) {
    val state by screenBookViewModel.state.collectAsStateWithLifecycle()
    ScreenBookContent(state = state, interaction = screenBookViewModel)
    NavigationHandler(effects = screenBookViewModel.effect) { effect, controller ->
        when (effect) {
            is BookScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBookContent(
    state: BookUiState,
    interaction: BookScreenInteraction
) {

}