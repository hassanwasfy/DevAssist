package com.abaferas.devassist.ui.screen.ai.onechat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.Constants
import com.abaferas.devassist.R
import com.abaferas.devassist.Role
import com.abaferas.devassist.ui.composable.DevAnimatedVisibility
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextFieldClickTrailing
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor

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
                        color_lightPrimaryColor,
                    )
                )
            }
        },
        onRetry = interaction::onRetry
    ) {
        val scrollState = rememberLazyListState()
        if (state.msgList.isNotEmpty()) {
            val lastItem = state.msgList.size - 1
            LaunchedEffect(key1 = lastItem + 1) {
                scrollState.scrollToItem(lastItem)
            }
        }

        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(top = Constants.TOP_BAR_HEIGHT.dp)
                .background(color_lightPrimaryColor),
        ) {
            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = Constants.BOTTOM_NO_PADDING.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.msgList) { msg ->
                    if (msg.role == Role.USER) {
                        DevMessageSender(text = msg.msg)
                    } else {
                        DevMessageReceiver(text = msg.msg)
                    }
                }
            }
            Column(
                modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .imePadding(),
            ) {
                DevAnimatedVisibility(visible = state.isResponsing) {
                    DevLottie(id = R.raw.chat_loading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.End)
                            .size(200.dp)
                    )
                }
                DevTextFieldClickTrailing(
                    modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxWidth(),
                    value = state.msgValue,
                    errorText = "",
                    isError = false,
                    placeholder = "Enter message",
                    onValueChange = interaction::onTypeMessage,
                    trailingIcon = Icons.AutoMirrored.Outlined.Send,
                    onClickIcon = interaction::onClickSend
                )
            }
        }
    }
}

@Composable
fun DevMessageSender(
    text: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = color_darkPrimaryColor,
            contentColor = Color.White
        )
    ) {
        DevLabel(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = text,
            maxLines = Int.MAX_VALUE,
            softWrap = true,
            color = Color.White
        )
    }
}

@Composable
fun DevMessageReceiver(
    text: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start),
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 16.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = color_primaryColor,
            contentColor = color_lightPrimaryColor
        )
    ) {
        DevLabel(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = text,
            maxLines = Int.MAX_VALUE,
            softWrap = true,
            color = Color.White
        )
    }
}
