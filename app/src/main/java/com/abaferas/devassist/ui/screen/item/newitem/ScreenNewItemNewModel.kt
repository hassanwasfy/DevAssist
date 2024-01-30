package com.abaferas.devassist.ui.screen.item.newitem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddReaction
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.data.repository.AuthRepository
import com.abaferas.devassist.domain.models.LearningItem
import com.abaferas.devassist.domain.models.LearningType
import com.abaferas.devassist.domain.usecase.items.SaveNewItemUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.screen.item.newitem.NewItemScreenArgs.Companion.TYPE_NAME
import com.abaferas.devassist.ui.utils.DateFormatter
import com.abaferas.devassist.ui.utils.NetworkStateManager
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ScreenNewItemNewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val networkStateManager: NetworkStateManager,
    private val repository: AuthRepository,
    private val saveNewItemUseCase: SaveNewItemUseCase
) : BaseViewModel<NewItemUiState, NewItemScreenUiEffect>(NewItemUiState()),
    NewItemScreenInteraction {

    private val args: NewItemScreenArgs = NewItemScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        val id: String = savedStateHandle[TYPE_NAME] ?: ""
        id.let { type ->
            when (type) {
                "Book" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.BOOK,
                            typeIcon = Icons.Outlined.MenuBook
                        )
                    }
                }

                "Azkar" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.Azkar,
                            typeIcon = Icons.Outlined.AddReaction
                        )
                    }
                }

                "Course" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.PLAY_LIST,
                            typeIcon = Icons.Outlined.VideoLibrary
                        )
                    }
                }

                "Video" -> {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            type = LearningType.Video,
                            typeIcon = Icons.Outlined.OndemandVideo
                        )
                    }
                }
            }
        }
    }

    override fun onClickBack() {
        sendUiEffect(NewItemScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {
        iState.update {
            it.copy(
                error = ErrorUiState(true, errorMsg)
            )
        }
    }

    override fun onNameChange(value: String) {
        if (value.length < 4) {
            iState.update {
                it.copy(
                    name = EntryTextValue(
                        value = value,
                        error = ErrorUiState(true, "can't be less 4")
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    name = EntryTextValue(value = value)
                )
            }
        }
    }

    override fun onAuthorChange(value: String) {
        if (value.length < 4) {
            iState.update {
                it.copy(
                    author = EntryTextValue(
                        value = value,
                        error = ErrorUiState(true, "can't be less 4")
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    author = EntryTextValue(value = value)
                )
            }
        }
    }

    override fun onStartDateChange(value: String) {
        if (value.isBlank() || value.isEmpty() || value == "null") {
            onDismiss()
        } else {
            val newValue = DateFormatter.convert(value.toLong())
            iState.update {
                it.copy(
                    startDate = EntryTextValue(value = newValue)
                )
            }
            onDismiss()
        }
    }

    override fun onEndDateChange(value: String) {
        if (value.isBlank() || value.isEmpty() || value == "null") {
            iState.update {
                it.copy(
                    endDate = EntryTextValue(
                        value = "",
                        error = ErrorUiState(true, "select start date first!")
                    )
                )
            }
            onDismiss()
        } else {
            val endDate = value.toLong()
            val currentStart = iState.value.startDate.value
            val startDate = if (currentStart.isBlank() || currentStart.isEmpty()) {
                0L
            } else {
                DateFormatter.convert(currentStart)
            }
            val newValue = DateFormatter.convert(endDate)
            if (currentStart.isBlank() || currentStart.isEmpty()) {
                iState.update {
                    it.copy(
                        startDate = EntryTextValue(
                            value = currentStart,
                            error = ErrorUiState(true, "can't be empty!")
                        )
                    )
                }
            } else if (endDate < startDate) {
                iState.update {
                    it.copy(
                        endDate = EntryTextValue(
                            value = newValue,
                            error = ErrorUiState(true, "can't be before start day!")
                        )
                    )
                }
            } else {
                iState.update {
                    it.copy(
                        endDate = EntryTextValue(value = newValue)
                    )
                }
            }
            onDismiss()
        }
    }

    override fun onAmountChange(value: String) {
        val amount = if (value.isNotEmpty() && value.isNotBlank()) {
            value.toInt()
        } else {
            0
        }
        if (amount < 1) {
            iState.update {
                it.copy(
                    totalAmount = EntryTextValue(
                        value = if (amount != 0) "$amount" else "",
                        error = ErrorUiState(true, "can't be less than 1")
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    totalAmount = EntryTextValue(value = "$amount")
                )
            }
        }
    }

    override fun onFinishedChange(value: String) {
        val amount = if (value.isNotEmpty() && value.isNotBlank()) {
            value.toInt()
        } else {
            0
        }
        val currTotal = iState.value.totalAmount.value
        val total = if (currTotal.isNotEmpty() && currTotal.isNotBlank()) {
            currTotal.toInt()
        } else {
            0
        }
        if (amount > total) {
            iState.update {
                it.copy(
                    finishedAmount = EntryTextValue(
                        value = if (amount != 0) "$amount" else "",
                        error = ErrorUiState(
                            true, if (total == 0) {
                                "total amount is missing! or incorrect!"
                            } else {
                                "can't be more than $total"
                            }
                        )
                    )
                )
            }
        } else {
            iState.update {
                it.copy(
                    finishedAmount = EntryTextValue(value = if (amount != 0) "$amount" else "")
                )
            }
        }
    }

    override fun onClickSave() {
        if (networkStateManager.isInternetAvailable()) {
            tryToExecute(
                onError = ::onError,
                onSuccess = ::onSuccess,
                execute = {
                    iState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                    val value = iState.value
                    val progress =
                        (value.finishedAmount.value.toInt() * 1F) / value.totalAmount.value.toFloat()
                    saveNewItemUseCase(
                        LearningItem(
                            itemId = Random.nextLong().toString(),
                            userId = repository.getUserId(),
                            name = value.name.value,
                            type = value.type,
                            author = value.author.value,
                            startDate = value.startDate.value,
                            endDate = value.endDate.value,
                            totalAmount = value.totalAmount.value.toInt(),
                            finishedAmount = value.finishedAmount.value.toInt(),
                            progress = progress,
                        )
                    )
                }
            )
        } else {
            onError("No Internet connection!")
        }
    }

    private fun onSuccess(result: Task<DocumentReference?>) {
        viewModelScope.launch {
            result
                .addOnSuccessListener {
                    iState.update {
                        it.copy(
                            isLoading = false,
                            isSaved = true
                        )
                    }
                    sendUiEffect(NewItemScreenUiEffect.NavigateHome)
                }
                .addOnFailureListener {
                    onError(it.message.toString())
                }
        }
    }

    override fun onDismiss() {
        iState.update {
            it.copy(
                selectingStartDate = false,
                selectingEndDate = false
            )
        }
    }

    override fun onOpenStartDialog() {
        iState.update {
            it.copy(
                selectingStartDate = true
            )
        }
    }

    override fun onOpenEndDialog() {
        iState.update {
            it.copy(
                selectingEndDate = true
            )
        }
    }

    override fun isValidated(): Boolean {
        val data = iState.value
        return !data.error.isError &&
                !data.name.error.isError && data.name.value.isNotEmpty() &&
                !data.author.error.isError && data.author.value.isNotEmpty() &&
                !data.totalAmount.error.isError && data.totalAmount.value.isNotEmpty() &&
                !data.finishedAmount.error.isError && data.finishedAmount.value.isNotEmpty() &&
                !data.startDate.error.isError && data.startDate.value.isNotEmpty() &&
                !data.endDate.error.isError && data.endDate.value.isNotEmpty()
    }
}