package com.abaferas.devassist.ui.screen.ai

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenAiChat(
    screenAiChatViewModel: ScreenAiChatViewModel = hiltViewModel()
) {
    val state by screenAiChatViewModel.state.collectAsStateWithLifecycle()
    ScreenAiChatContent(state = state, interaction = screenAiChatViewModel)
    NavigationHandler(effects = screenAiChatViewModel.effect) { effect, controller ->
        when (effect) {
            is AiChatScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAiChatContent(
    state: AiChatUiState,
    interaction: AiChatScreenInteraction
) {

}