package com.abaferas.devassist.ui.screen.item

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenViewItem(
    screenNewItemViewModel: ScreenViewItemViewModel = hiltViewModel()
) {
    val state = screenNewItemViewModel.state.collectAsState().value
    ScreenViewItemContent(state = state, interaction = screenNewItemViewModel)
    NavigationHandler(effects = screenNewItemViewModel.effect) { effect, controller ->
        when (effect) {
            is ViewItemScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenViewItemContent(
    state: ViewItemUiState,
    interaction: ViewItemScreenInteraction
) {

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun NewItemTester() {

}