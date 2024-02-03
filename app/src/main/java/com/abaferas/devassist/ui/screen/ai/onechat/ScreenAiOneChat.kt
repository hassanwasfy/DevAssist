package com.abaferas.devassist.ui.screen.ai.onechat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.Constants
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.modifier.mainContainerPadding
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenAiOneChat(
    screenAiOneChatViewModel: ScreenAiOneChatViewModel = hiltViewModel()
) {
    val state by screenAiOneChatViewModel.state.collectAsStateWithLifecycle()
    ScreenAiOneChatContent(state = state, interaction = screenAiOneChatViewModel)
    NavigationHandler(effects = screenAiOneChatViewModel.effect) { effect, controller ->
        when (effect) {
            is AiOneChatScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenAiOneChatContent(
    state: AiOneChatUiState,
    interaction: AiOneChatScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .mainContainerPadding()
        ) {

        }
    }
}