package com.abaferas.devassist.ui.screen.ai.onechat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.Role
import com.abaferas.devassist.data.repository.AiRepository
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


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

    override fun onRetry() {
        iState.update {
            it.copy(
                error = ErrorUiState()
            )
        }
    }

    @OptIn(FlowPreview::class)
    override fun onClickSend() {
        viewModelScope.launch(Dispatchers.IO) {
            val msg = state.value.msgValue
            val listUpdate = state.value.msgList
            listUpdate.add(
                AiOneChatUiState.ChatMessage(
                    Role.USER,msg,System.currentTimeMillis()
                )
            )
            iState.update { item ->
                item.copy(
                    msgList = listUpdate,
                    msgValue = "",
                    isResponsing = true
                )
            }
            aiRepository.sendMessage(msg)
                .catch {
                    iState.update { item ->
                        item.copy(
                            error = ErrorUiState(
                                isError = true,
                                message = it.message.toString()
                            )
                        )
                    }
                }
                .collect() {
                    iState.update { item ->
                        item.copy(
                            isResponsing = true
                        )
                    }
                    val response = it.text.toString()
                    val list = state.value.msgList
                    val added = list.add(
                        AiOneChatUiState.ChatMessage(
                            Role.MODEL,response,System.currentTimeMillis()
                        )
                    )
                    if (added){
                        iState.update { item ->
                            item.copy(
                                msgList = list,
                                isResponsing = false
                            )
                        }
                    }
                }
        }
    }
}