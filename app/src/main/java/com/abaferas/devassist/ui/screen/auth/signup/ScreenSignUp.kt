package com.abaferas.devassist.ui.screen.auth.signup

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.ui.navigation.NavigationHandler


@Composable
fun ScreenSignUp(
    screenSignUpViewModel: ScreenSignUpViewModel = hiltViewModel()
) {
    val state = screenSignUpViewModel.state.collectAsState().value
    ScreenSignUpContent(state = state, interaction = screenSignUpViewModel)
    NavigationHandler(effects = screenSignUpViewModel.effect) { effect, controller ->
        when (effect) {
            is SignUpScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSignUpContent(
    state: SignUpUiState,
    interaction: SignUpScreenInteraction
) {

}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun SignUpTester() {

}