package com.abaferas.devassist.ui.screen.auth.signup

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
import com.abaferas.devassist.ui.composable.DevLabelClick
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.auth.login.navigateToLogin
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor
import com.abaferas.devassist.ui.theme.color_textSecondaryColor

@Composable
fun ScreenSignUp(
    screenSignUpViewModel: ScreenSignUpViewModel = hiltViewModel()
) {
    val state by screenSignUpViewModel.state.collectAsStateWithLifecycle()
    ScreenSignUpContent(state = state, interaction = screenSignUpViewModel)
    NavigationHandler(effects = screenSignUpViewModel.effect) { effect, controller ->
        when (effect) {
            is SignUpScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is SignUpScreenUiEffect.NavigateToHome -> {
                controller.navigateToHome()
            }
            is SignUpScreenUiEffect.NavigateToLogin -> {
                controller.navigateToLogin()
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
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        isInternetConnected = state.isInternetConnected,
        isRetrying = state.isRetrying,
        onRetry = interaction::onRetry,
        topBar = {
            TopAppBar(
                title = {
                    DevLabel(
                        text = "SignUp",
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
                    text = "Welcome to DevAssist,",
                    fontSize = 20,
                    color = color_textPrimaryColor,
                    fontWeight = FontWeight.ExtraBold
                )
                DevLabel(
                    text = "Few Steps To a fantastic journey!",
                    fontSize = 18,
                    color = color_textPrimaryColor,
                    fontWeight = FontWeight.Medium
                )
                DevTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    value = state.userNameValue.value,
                    isError = state.userNameValue.error.isError,
                    errorText = state.userNameValue.error.message,
                    placeholder = "Name",
                    keyboardType = KeyboardType.Text,
                    onValueChange = interaction::onUserNameValueChange,
                    leadingIcon = Icons.Outlined.Person
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
                DevTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    value = state.passwordConfirmation.value,
                    isError = state.passwordConfirmation.error.isError,
                    errorText = state.passwordConfirmation.error.message,
                    placeholder = "Confirm Password!",
                    onValueChange = interaction::onPasswordConfirmationValueChange,
                    leadingIcon = Icons.Outlined.Password,
                    keyboardType = KeyboardType.Password,
                    onTogglePassword = interaction::onTogglePassword,
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = if (!state.isPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
                )
                DevButton(
                    text = "Register!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    onClick = interaction::onClickSignUp
                )
                DevButton(
                    text = "SignIn Anonymously!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color_textSecondaryColor,
                        contentColor = color_textPrimaryColor
                    ),
                    onClick = interaction::onClickSignInAnonymously
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                ) {
                    DevLabel(
                        text = "Already a user? ",
                        fontSize = 14,
                        color = color_textPrimaryColor,
                        fontWeight = FontWeight.Normal
                    )
                    DevLabelClick(
                        text = "Login!",
                        fontSize = 16,
                        color = color_darkPrimaryColor,
                        fontWeight = FontWeight.Bold,
                        onCLick = interaction::onClickLogin
                    )
                }
            }
        }
    }
}




