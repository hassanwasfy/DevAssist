package com.abaferas.devassist.ui.screen.ai.onechat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.Role
import com.abaferas.devassist.data.repository.AiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.ErrorUiState
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class ScreenAiOneChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val aiRepository: AiRepository
    /*TODO Add you use cases*/
) : BaseViewModel<AiOneChatUiState, AiOneChatScreenUiEffect>(AiOneChatUiState()),
    AiOneChatScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        iState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(AiOneChatScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {

    }

    override fun onTypeMessage(msg: String) {
        iState.update {
            it.copy(
                msgValue = msg
            )
        }
    }

    override fun onClickSend() {
        viewModelScope.launch {
            val msg = iState.value.msgValue
            val updated = state.value.msgList
            updated.add(
                AiOneChatUiState.ChatMessage(
                    Role.USER, msg, 0L
                )
            )
            iState.update {
                it.copy(
                    msgValue = "",
                    isResponsing = true
                )
            }

            aiRepository.sendMessage(msg).catch {
                iState.update { state ->
                    state.copy(
                        error = ErrorUiState(true, it.message.toString())
                    )
                }
            }.collect {
                state.value.msgList.add(
                    AiOneChatUiState.ChatMessage(
                        Role.MODEL, it.text ?: "", 0L
                    )
                )
                iState.update { state ->
                    state.copy(
                        error = ErrorUiState(),
                        msgList = updated,
                        isResponsing = false
                    )
                }
            }
        }
    }
}