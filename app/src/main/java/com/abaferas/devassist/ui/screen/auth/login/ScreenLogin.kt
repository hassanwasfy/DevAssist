package com.abaferas.devassist.ui.screen.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.ui.composable.DevButton
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.navigation.NavigationDestination
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor
import com.abaferas.devassist.ui.theme.color_textSecondaryColor


@Composable
fun ScreenLogin(
    screenLoginViewModel: ScreenLoginViewModel = hiltViewModel()
) {
    val state by screenLoginViewModel.state.collectAsStateWithLifecycle()
    ScreenLoginContent(state = state, interaction = screenLoginViewModel)
    NavigationHandler(effects = screenLoginViewModel.effect) { effect, controller ->
        when (effect) {
            is LoginScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is LoginScreenUiEffect.Login -> {
                controller.navigateToHome(){
                    popUpTo(controller.graph.id){
                        inclusive = true
                    }
                }
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
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        isInternetConnected = state.isInternetConnected,
        onRetry = interaction::onRetry,
        topBar = {
            TopAppBar(
                title = {
                    DevLabel(
                        text = "Login",
                        fontSize = 24,
                        color = color_textColor
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = color_darkPrimaryColor,
                    titleContentColor = color_textColor
                )
            )
        }
    ) {

        AnimatedVisibility(visible = state.isInternetConnected) {
            Column(
                modifier = Modifier
                    .background(color_lightPrimaryColor)
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 80.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                DevLabel(
                    text = "Welcome back Developer!",
                    fontSize = 20,
                    color = color_textPrimaryColor,
                    fontWeight = FontWeight.ExtraBold
                )
                DevLabel(
                    text = "Ready for the next milestone?",
                    fontSize = 18,
                    color = color_textPrimaryColor,
                    fontWeight = FontWeight.Medium
                )
                DevTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = state.userEmailValue.value,
                    isError = state.userEmailValue.error.isError,
                    errorText = state.userEmailValue.error.message,
                    placeholder = "Email",
                    keyboardType = KeyboardType.Email,
                    onValueChange = interaction::onEmailValueChange,
                    leadingIcon = Icons.Outlined.Email
                )
                DevTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = state.passwordValue.value,
                    isError = state.passwordValue.error.isError,
                    errorText = state.passwordValue.error.message,
                    placeholder = "Password",
                    onValueChange = interaction::onPasswordValueChange,
                    leadingIcon = Icons.Outlined.Password,
                    keyboardType = KeyboardType.Password,
                    onTogglePassword = interaction::onTogglePassword,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = if (!state.isPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
                )
                DevButton(
                    text = "Login!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onClick = interaction::onClickLogin
                )
            }
        }
    }
}