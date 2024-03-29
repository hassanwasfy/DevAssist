package com.abaferas.devassist.ui.screen.home

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddReaction
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.abaferas.devassist.domain.models.LearningType
import com.abaferas.devassist.domain.usecase.auth.GetUserIdUseCase
import com.abaferas.devassist.domain.usecase.items.GetAllUserLearningItemsUseCase
import com.abaferas.devassist.ui.base.BaseViewModel
import com.abaferas.devassist.ui.base.ErrorUiState
import com.abaferas.devassist.ui.utils.NetworkStateManager
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenHomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getAllUserLearningItemsUseCase: GetAllUserLearningItemsUseCase,
    private val networkStateManager: NetworkStateManager
): BaseViewModel<HomeUiState, HomeScreenUiEffect>(HomeUiState()), HomeScreenInteraction {

    private val args: HomeScreenArgs = HomeScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {
        if (networkStateManager.isInternetAvailable()) {
            try {
                viewModelScope.launch(Dispatchers.IO) {
                    val list = getAllUserLearningItemsUseCase(
                        async {
                            getUserIdUseCase()
                    }.await())

                    list.catch {
                        onError(it.message.toString())
                    }.collect{items ->
                        iState.update {
                            it.copy(
                                isLoading = false,
                                items = items
                            )
                        }
                    }

                }
            } catch (e: Exception) {
                iState.update {
                    it.copy(
                        isLoading = false,
                        error = ErrorUiState(true, "No Internet Connection!"),
                        isInternetConnected = true
                    )
                }
            }
        } else {
            iState.update {
                it.copy(
                    isLoading = false,
                    error = ErrorUiState(true, "No Internet Connection!"),
                    isInternetConnected = false
                )
            }
        }

    }

    private fun onSuccess(result: FirebaseUser?) {
        result?.let {
            iState.update { homeState ->
                homeState.copy(
                    isLoading = false,
                    userName = it.displayName ?: "No Name!"
                )
            }
        }
    }

    override fun onError(errorMsg: String) {
        iState.update {
            it.copy(
                isLoading = false,
                error = ErrorUiState(true, errorMsg)
            )
        }
    }

    override fun onClickBack() {
        sendUiEffect(HomeScreenUiEffect.NavigateUp)
    }

    override fun onClickRetry() {
        iState.update { HomeUiState() }
        getData()
    }

    override fun onClickAddNewItem() {
        sendUiEffect(HomeScreenUiEffect.AddNewItem)
    }

    override fun onClickEditItem(itemId: String) {
        sendUiEffect(HomeScreenUiEffect.EditCurrentItem(itemId = itemId))
    }

    override fun onSearchValueChange(value: String) {

    }

    override fun onClickFilter() {

    }

    override fun onClickSearch() {

    }

    override fun iconFinder(type: LearningType): ImageVector {
        return when(type){
            LearningType.Video -> {
                Icons.Outlined.OndemandVideo
            }
            LearningType.BOOK -> {
                Icons.Outlined.MenuBook
            }
            LearningType.PLAY_LIST -> {
                Icons.Outlined.VideoLibrary
            }
            LearningType.Azkar -> {
                Icons.Outlined.AddReaction
            }
        }
    }
}

