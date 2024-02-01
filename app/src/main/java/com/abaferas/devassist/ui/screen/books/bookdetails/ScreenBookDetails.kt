package com.abaferas.devassist.ui.screen.books.bookdetails

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenBookDetails(
    screenBookDetailsViewModel: ScreenBookDetailsViewModel = hiltViewModel()
) {
    val state by screenBookDetailsViewModel.state.collectAsStateWithLifecycle()
    ScreenBookDetailsContent(state = state, interaction = screenBookDetailsViewModel)
    NavigationHandler(effects = screenBookDetailsViewModel.effect) { effect, controller ->
        when (effect) {
            is BookDetailsScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBookDetailsContent(
    state: BookDetailsUiState,
    interaction: BookDetailsScreenInteraction
) {

}