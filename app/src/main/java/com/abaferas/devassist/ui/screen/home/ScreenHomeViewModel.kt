package com.abaferas.devassist.ui.screen.home

import androidx.lifecycle.SavedStateHandle
import com.abaferas.devassist.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenHomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<HomeUiState, HomeScreenUiEffect>(HomeUiState()), HomeScreenInteraction {

    private val args: HomeScreenArgs = HomeScreenArgs(savedStateHandle = savedStateHandle)

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {
        sendUiEffect(HomeScreenUiEffect.NavigateUp)
    }

    override fun onClickAddNewItem() {
        sendUiEffect(HomeScreenUiEffect.AddNewItem)
    }

    override fun onClickEditItem(itemId: String) {
        sendUiEffect(HomeScreenUiEffect.EditCurrentItem(itemId = itemId))
    }

    override fun onError(errorMsg: String) {

    }

    override fun onSearchValueChange(value: String) {

    }

    override fun onClickFilter() {

    }

    override fun onClickSearch() {

    }
}

