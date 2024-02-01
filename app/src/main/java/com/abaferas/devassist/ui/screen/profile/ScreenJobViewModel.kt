package com.abaferas.devassist.ui.screen.profile

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.abaferas.devassist.ui.base.BaseViewModel


@HiltViewModel
class ScreenJobViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    /*TODO Add you use cases*/
) : BaseViewModel<JobUiState, JobScreenUiEffect>(JobUiState()), JobScreenInteraction {

    init {
        getData()
    }

    override fun getData() {

    }

    override fun onClickBack() {

    }

    override fun onError(errorMsg: String) {

    }
}