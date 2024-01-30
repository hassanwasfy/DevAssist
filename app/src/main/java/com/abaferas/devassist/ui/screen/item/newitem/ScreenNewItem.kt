package com.abaferas.devassist.ui.screen.item.newitem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AssistantPhoto
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevButton
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor


@Composable
fun ScreenNewItem(
    screenNewItemViewModel: ScreenNewItemNewModel = hiltViewModel()
) {
    val state = screenNewItemViewModel.state.collectAsState().value
    ScreenNewItemContent(state = state, interaction = screenNewItemViewModel)
    NavigationHandler(effects = screenNewItemViewModel.effect) { effect, controller ->
        when (effect) {
            is NewItemScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            NewItemScreenUiEffect.NavigateHome -> {
                controller.navigateToHome()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNewItemContent(
    state: NewItemUiState,
    interaction: NewItemScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        onRetry = { /*TODO*/ },
        topBar = {
            DevTopAppBarWithLogo(label = "Add New") {
                Icon(
                    modifier = Modifier.clickable { interaction.onClickBack() },
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "",
                    tint = color_textColor
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 64.dp)
                .background(
                    color_lightPrimaryColor
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                DevTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.name.value,
                    isError = state.name.error.isError,
                    errorText = state.name.error.message,
                    placeholder = "Name",
                    onValueChange = interaction::onNameChange,
                    leadingIcon = state.typeIcon,
                )
            }
            item {
                DevTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.author.value,
                    isError = state.author.error.isError,
                    errorText = state.author.error.message,
                    placeholder = "Author",
                    onValueChange = interaction::onAuthorChange,
                    leadingIcon = Icons.Outlined.PersonPin
                )
            }
            item {
                DevTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.totalAmount.value,
                    isError = state.totalAmount.error.isError,
                    errorText = state.totalAmount.error.message,
                    placeholder = "Total",
                    onValueChange = interaction::onAmountChange,
                    leadingIcon = Icons.Outlined.AssistantPhoto,
                    keyboardType = KeyboardType.Decimal
                )
            }
            item {
                DevTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.finishedAmount.value,
                    isError = state.finishedAmount.error.isError,
                    errorText = state.finishedAmount.error.message,
                    placeholder = "Finished",
                    onValueChange = interaction::onFinishedChange,
                    leadingIcon = Icons.Outlined.Done,
                    keyboardType = KeyboardType.Decimal
                )
            }
            item {
                DevTextFieldClickLeading(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.startDate.value,
                    isError = state.startDate.error.isError,
                    errorText = state.startDate.error.message,
                    placeholder = "Start Date",
                    onClickIcon = interaction::onOpenStartDialog,
                    onValueChange = interaction::onStartDateChange,
                    leadingIcon = Icons.Outlined.Timer,
                    leadingTint = color_darkPrimaryColor,
                    enabled = false,
                    readOnly = true
                )
            }
            item {
                DevTextFieldClickLeading(
                    modifier = Modifier.fillMaxWidth(),
                    onClickIcon = interaction::onOpenEndDialog,
                    value = state.endDate.value,
                    isError = state.endDate.error.isError,
                    errorText = state.endDate.error.message,
                    placeholder = "End Date",
                    onValueChange = interaction::onEndDateChange,
                    leadingIcon = Icons.Outlined.Timer,
                    leadingTint = color_darkPrimaryColor,
                    enabled = false,
                    readOnly = true
                )
            }
            item {
                DevButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Save",
                    fontSize = 18,
                    textAlign = TextAlign.Center,
                    onClick = interaction::onClickSave,
                    enabled = interaction.isValidated()
                )
            }
        }

        AnimatedVisibility(visible = state.isSaved) {
            DevLottie(
                modifier = Modifier.shadow(24.dp),
                id = R.raw.splash_robot)
        }

        AnimatedVisibility(
            visible = state.selectingStartDate,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(300))
        ) {
            DevDatePicker(
                title = "Start Date!",
                subtitle = "When did you start?",
                onDismiss = interaction::onDismiss,
                onCancel = interaction::onDismiss,
                onSelect = interaction::onStartDateChange
            )
        }
        AnimatedVisibility(
            visible = state.selectingEndDate,
            enter = fadeIn(tween(300)),
            exit = fadeOut(tween(300))
        ) {
            DevDatePicker(
                title = "End Date!",
                subtitle = "When to finish?",
                onDismiss = interaction::onDismiss,
                onCancel = interaction::onDismiss,
                onSelect = interaction::onEndDateChange
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevDatePicker(
    title: String,
    subtitle: String,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onSelect: (String) -> Unit
) {
    val dState = rememberDatePickerState(yearRange = 2000..2050)
    DatePickerDialog(
        tonalElevation = 24.dp,
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                DevLabel(text = "cancel", color = color_textPrimaryColor,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clickable {
                            onCancel()
                        })
                Spacer(modifier = Modifier.padding(end = 4.dp))
                DevLabel(fontSize = 18,
                    text = "Select",
                    color = color_AccentColor,
                    modifier = Modifier
                        .clickable {
                            onSelect("${dState.selectedDateMillis}")
                        })
            }
        }) {
        DatePicker(
            state = dState,
            showModeToggle = false,
            title = {
                DevLabel(
                    fontSize = 22,
                    text = title,
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                )
            },
            headline = {
                DevLabel(
                    fontSize = 18,
                    text = subtitle,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        )
    }
}
