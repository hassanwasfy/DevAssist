package com.abaferas.devassist.ui.screen.ai.onechat

import android.graphics.drawable.Icon
import android.os.Build
import android.view.ViewTreeObserver
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.Constants
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevAnimatedVisibility
import com.abaferas.devassist.ui.composable.DevCardIcon
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickTrailing
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.mainContainerPadding
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_dividerColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor


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
        errorMsg = state.error.message,
        topBar = {
            DevTopAppBarWithLogo(label = "Chat Details") {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { interaction.onClickBack() },
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "", colorFilter = ColorFilter.tint(
                        color_lightPrimaryColor
                    )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .mainContainerPadding()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .imePadding()
            ) {
                items(state.msgList) { msg ->
                    val isUser = msg.role == AiOneChatUiState.ChatMessage.Role.USER
                    Card(
                        modifier = Modifier
                            .align(
                                if (!isUser)
                                    Alignment.Start
                                else
                                    Alignment.End
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isUser)
                                color_darkPrimaryColor
                            else
                                color_lightPrimaryColor
                        ),
                        onClick = { /*TODO*/ },
                        shape = if (isUser) {
                            RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
                        } else {
                            RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)
                        }
                    ) {
                        DevLabel(
                            text = msg.msg,
                            color = if (isUser) {
                                color_lightPrimaryColor
                            } else color_darkPrimaryColor,
                            softWrap = true,
                            maxLines = Int.MAX_VALUE,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)

                        )
                    }
                }
            }
            DevAnimatedVisibility(visible = state.isResponsing) {
                DevLottie(id = R.raw.chat_loading)
            }
            DevTextFieldClickTrailing(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Constants.BOTTOM_BAR_HEIGHT.dp)
                    .padding(top = 8.dp),
                value = state.msgValue,
                placeholder = "Type your message",
                keyboardType = KeyboardType.Email,
                onValueChange = interaction::onTypeMessage,
                errorText = "",
                isError = false,
                trailingIcon = Icons.AutoMirrored.Outlined.Send,
                onClickIcon = interaction::onClickSend
            )
        }
    }
}
