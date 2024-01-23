package com.abaferas.devassist.ui.screen.auth.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenLogin(
    screenLoginViewModel: ScreenLoginViewModel = hiltViewModel()
) {
    val state = screenLoginViewModel.state.collectAsState().value
    ScreenLoginContent(state = state, interaction = screenLoginViewModel)
    NavigationHandler(effects = screenLoginViewModel.effect) { effect, controller ->
        when (effect) {
            is LoginScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLoginContent(
    state: LoginUiState,
    interaction: LoginScreenInteraction
) {

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun LoginTester() {

}