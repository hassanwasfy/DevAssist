package com.abaferas.devassist.ui.screen.item.edititem

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.domain.models.LearningItem
import com.abaferas.devassist.domain.models.LearningType
import com.abaferas.devassist.domain.usecase.items.DeleteItemUseCase
import com.abaferas.devassist.domain.usecase.items.EditItemUseCase
import com.abaferas.devassist.domain.usecase.items.GetItemByIdUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.EntryTextValue
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.mappers.toDomain
import com.abaferas.devassist.ui.utils.Constants
import com.abaferas.devassist.ui.utils.DateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ScreenEditItemViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val editItemUseCase: EditItemUseCase
) : BaseViewModel<EditItemUiState, EditItemScreenUiEffect>(EditItemUiState()),
    EditItemScreenInteraction {

    init {
        getData()
    }

    override fun getData() {
        tryToExecute(
            onError = ::onError,
            onSuccess = ::onSuccess,
            execute = {
                getItemByIdUseCase(savedStateHandle[EditItemScreenArgs.ITEM_ID] ?: "")
            }
        )
    }

    private fun onSuccess(result: LearningItem) {
        iState.update {
            it.copy(
                isLoading = false,
                itemId = result.itemId,
                userId = result.userId,
                name = EntryTextValue(result.name),
                type = result.type,
                author = EntryTextValue(result.author),
                startDate = EntryTextValue(result.startDate),
                endDate = EntryTextValue(result.endDate),
                totalAmount = EntryTextValue("${result.totalAmount}"),
                finishedAmount = EntryTextValue("${result.finishedAmount}"),
                progress = EntryTextValue("${result.progress}"),
            )
        }
    }


    override fun onClickBack() {
        sendUiEffect(EditItemScreenUiEffect.NavigateUp)
    }

    override fun onError(errorMsg: String) {
        iState.update {
            it.copy(
                error = ErrorUiState(true, errorMsg)
            )
        }
    }

    override fun onNameChange(value: String) {
        if (value.length < Constants.NAME_LENGTH) {
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
        if (value.length < Constants.NAME_LENGTH) {
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

    override fun onClickEdit() {
        tryToExecute(
            onError = { msg ->
                iState.update {
                    it.copy(
                        isLoading = false,
                        error = ErrorUiState(true, msg)
                    )
                }
            },
            onSuccess = {
                it.addOnSuccessListener {
                    sendUiEffect(EditItemScreenUiEffect.Home)
                }.addOnFailureListener { e ->
                    onError(e.message.toString())
                }
            },
            execute = {
                val item = iState.value
                editItemUseCase(item.toDomain())
            }
        )
    }

    override fun onClickDelete() {
        tryToExecute(
            onError = { msg ->
                iState.update {
                    it.copy(
                        isLoading = false,
                        error = ErrorUiState(true, msg)
                    )
                }
            },
            onSuccess = {
                it.addOnSuccessListener {
                    sendUiEffect(EditItemScreenUiEffect.Home)
                }.addOnFailureListener { e ->
                    onError(e.message.toString())
                }
            },
            execute = {
                val item = iState.value
                deleteItemUseCase(item.toDomain())
            }
        )
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
        return validateErrors(data) && validateInputs(data)
    }

    private fun validateErrors(data: EditItemUiState): Boolean {
        return !data.error.isError &&
                !data.name.error.isError &&
                !data.author.error.isError &&
                !data.totalAmount.error.isError &&
                !data.finishedAmount.error.isError &&
                !data.startDate.error.isError &&
                !data.endDate.error.isError
    }

    private fun validateInputs(data: EditItemUiState): Boolean {
        val start = DateFormatter.convert(data.startDate.value)
        val end = DateFormatter.convert(data.endDate.value)
        return data.name.value.isNotEmpty() &&
                data.author.value.isNotEmpty() &&
                data.totalAmount.value.isNotEmpty() &&
                data.finishedAmount.value.isNotEmpty() &&
                data.startDate.value.isNotEmpty() &&
                data.endDate.value.isNotEmpty() &&
                data.finishedAmount.value <= data.totalAmount.value &&
                start <= end
    }

    override fun onRetry() {
        iState.update {
            EditItemUiState()
        }
        getData()
    }
}