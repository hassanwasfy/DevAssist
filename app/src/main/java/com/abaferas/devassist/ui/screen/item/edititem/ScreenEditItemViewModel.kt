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
                        error = ErrorUiState(true, "can't be less 4"),
                    ),
                    edits = it.edits + 1
                )
            }
        } else {
            iState.update {
                it.copy(
                    name = EntryTextValue(value = value),
                    edits = it.edits + 1
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
                    ),
                    edits = it.edits + 1
                )
            }
        } else {
            iState.update {
                it.copy(
                    author = EntryTextValue(value = value),
                    edits = it.edits + 1
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
                    startDate = EntryTextValue(value = newValue),
                    edits = it.edits + 1
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
                    ),
                    edits = it.edits + 1
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
                        ),
                        edits = it.edits + 1
                    )
                }
            } else if (endDate < startDate) {
                iState.update {
                    it.copy(
                        endDate = EntryTextValue(
                            value = newValue,
                            error = ErrorUiState(true, "can't be before start day!")
                        ),
                        edits = it.edits + 1
                    )
                }
            } else {
                iState.update {
                    it.copy(
                        endDate = EntryTextValue(value = newValue),
                        edits = it.edits + 1
                    )
                }
            }
            onDismiss()
        }
    }

    override fun onAmountChange(value: String) {
        try{
            val amount = if (value.isNotEmpty() && value.isNotBlank()) {
                value.toFloat()
            } else {
                0F
            }
            if (amount < 1F) {
                iState.update {
                    it.copy(
                        totalAmount = EntryTextValue(
                            value = if (amount != 0F) "$amount" else "",
                            error = ErrorUiState(true, "can't be less than 1")
                        ),
                        edits = it.edits + 1
                    )
                }
            } else {
                iState.update {
                    it.copy(
                        totalAmount = EntryTextValue(value = "$amount"),
                        edits = it.edits + 1
                    )
                }
            }
        }catch (e: java.lang.NumberFormatException){
            iState.update {
                it.copy(
                    totalAmount = EntryTextValue(
                        it.totalAmount.value,
                        ErrorUiState(true,"can't have two floating point")
                    )
                )
            }
        }
    }

    override fun onFinishedChange(value: String) {
        try{
            val amount = if (value.isNotEmpty() && value.isNotBlank()) {
                value.toFloat()
            } else {
                0F
            }
            val currTotal = iState.value.totalAmount.value
            val total = if (currTotal.isNotEmpty() && currTotal.isNotBlank()) {
                currTotal.toFloat()
            } else {
                0F
            }
            if (amount > total) {
                iState.update {
                    it.copy(
                        finishedAmount = EntryTextValue(
                            value = if (amount != 0F) "$amount" else "",
                            error = ErrorUiState(
                                true, if (total == 0F) {
                                    "total amount is missing! or incorrect!"
                                } else {
                                    "can't be more than $total"
                                }
                            )
                        ),
                        edits = it.edits + 1
                    )
                }
            } else {
                iState.update {
                    it.copy(
                        finishedAmount = EntryTextValue(value = if (amount != 0F) "$amount" else ""),
                        edits = it.edits + 1
                    )
                }
            }
        }catch (e: java.lang.NumberFormatException){
            iState.update {
                it.copy(
                    finishedAmount = EntryTextValue(
                        it.finishedAmount.value,
                        ErrorUiState(true,e.message.toString())
                    )
                )
            }
        }
    }

    override fun onClickEdit() {
        val value = iState.value
        val progress =
            (value.finishedAmount.value.toFloat() * 1F) / value.totalAmount.value.toFloat()
        iState.update {
            it.copy(
                progress = EntryTextValue("$progress")
            )
        }
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
        return !data.error.isError &&
                !data.name.error.isError && data.name.value != "" &&
                !data.author.error.isError && data.author.value != "" &&
                !data.totalAmount.error.isError && data.totalAmount.value != "" &&
                !data.finishedAmount.error.isError && data.finishedAmount.value != "" &&
                !data.startDate.error.isError && data.startDate.value != "" &&
                !data.endDate.error.isError && data.endDate.value != "" &&
                data.edits > 0

    }

    override fun onRetry() {
        iState.update {
            EditItemUiState()
        }
        getData()
    }
}