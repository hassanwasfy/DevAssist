package com.abaferas.devassist.ui.screen.ai.chatslist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.composable.DevAnimatedVisibility
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.mainContainerPadding
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.ai.onechat.navigateToAiOneChat
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_textColor


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
            is AiChatScreenUiEffect.NewChat -> {
                controller.navigateToAiOneChat()
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
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        isInternetConnected = true,
        onRetry = {},
        topBar = {
            DevTopAppBarWithLogo("Ai Chat")
        },
        floating = {
            DevAnimatedVisibility(visible = !state.error.isError) {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 72.dp),
                    onClick = interaction::addNewChat,
                    containerColor = color_AccentColor
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "",
                        tint = color_textColor
                    )
                }
            }
        }
    ) {

    }
}