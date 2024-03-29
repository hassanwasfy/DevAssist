package com.abaferas.devassist.ui.screen.item.edititem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.AssistantPhoto
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.Constants
import com.abaferas.devassist.ui.composable.DevButton
import com.abaferas.devassist.ui.composable.DevDatePicker
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.mainContainerPadding
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.home.navigateToHome
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_red_alert
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor

@Composable
fun ScreenEditItem(
    screenEditItemViewModel: ScreenEditItemViewModel = hiltViewModel()
) {
    val state by screenEditItemViewModel.state.collectAsStateWithLifecycle()
    ScreenEditItemContent(state = state, interaction = screenEditItemViewModel)
    NavigationHandler(effects = screenEditItemViewModel.effect) { effect, controller ->
        when (effect) {
            is EditItemScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
            is EditItemScreenUiEffect.Home -> {
                controller.navigateToHome {
                    popUpTo(controller.graph.id) {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenEditItemContent(
    state: EditItemUiState,
    interaction: EditItemScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        onRetry = interaction::onRetry,
        topBar = {
            DevTopAppBarWithLogo(label = "Item Details") {
                Icon(
                    modifier = Modifier.clickable { interaction.onClickBack() },
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = "",
                    tint = color_textColor,
                )
            }
        },
    ) {
        LazyColumn(
            modifier =
            Modifier
                .fillMaxSize()
                .mainContainerPadding()
                .background(
                    color_lightPrimaryColor,
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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
                    leadingIcon = Icons.Outlined.PersonPin,
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
                    keyboardType = KeyboardType.Decimal,
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
                    keyboardType = KeyboardType.Decimal,
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
                    readOnly = true,
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
                    readOnly = true,
                )
            }
            item {
                DevButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Update",
                    fontSize = 18,
                    textAlign = TextAlign.Center,
                    onClick = interaction::onClickEdit,
                    enabled = interaction.isValidated(),
                )
            }
            item {
                DevButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    text = "Delete",
                    fontSize = 18,
                    textAlign = TextAlign.Center,
                    onClick = interaction::onClickDelete,
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = Color.Red,
                            contentColor = color_textPrimaryColor,
                        ),
                )
            }
        }
        AnimatedVisibility(
            visible = state.selectingStartDate,
            enter = fadeIn(tween(Constants.ANIMATION_DURATION)),
            exit = fadeOut(tween(Constants.ANIMATION_DURATION)),
        ) {
            DevDatePicker(
                title = "Start Date!",
                subtitle = "When did you start?",
                onDismiss = interaction::onDismiss,
                onCancel = interaction::onDismiss,
                onSelect = interaction::onStartDateChange,
            )
        }
        AnimatedVisibility(
            visible = state.selectingEndDate,
            enter = fadeIn(tween(Constants.ANIMATION_DURATION)),
            exit = fadeOut(tween(Constants.ANIMATION_DURATION)),
        ) {
            DevDatePicker(
                title = "End Date!",
                subtitle = "When to finish?",
                onDismiss = interaction::onDismiss,
                onCancel = interaction::onDismiss,
                onSelect = interaction::onEndDateChange,
            )
        }
        AnimatedVisibility(
            visible = state.isDeletingItem,
            enter = fadeIn(tween(Constants.ANIMATION_DURATION)),
            exit = fadeOut(tween(Constants.ANIMATION_DURATION)),
        ) {
            BasicAlertDialog(
                modifier =
                    Modifier
                        .background(color_primaryColor, roundCornerShape(16))
                        .wrapContentHeight(unbounded = true)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                onDismissRequest = interaction::onDeleteDialogDismiss,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    DevLabel(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        text = "Delete ${state.name.value}",
                        color = color_lightPrimaryColor,
                        textAlign = TextAlign.Center,
                        fontSize = 22,
                    )
                    DevLabel(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                        text = "Are you sure?",
                        color = color_lightPrimaryColor,
                        textAlign = TextAlign.Center,
                        fontSize = 18,
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        DevButton(
                            text = "Cancel",
                            modifier = Modifier.padding(end = 4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = color_primaryColor,
                                contentColor = color_darkPrimaryColor
                            ),
                        ) {
                            interaction.onDeleteDialogDismiss()
                        }
                        DevButton(
                            text = "Delete",
                            colors = ButtonDefaults.buttonColors(
                                containerColor = color_red_alert,
                                contentColor = color_lightPrimaryColor
                            ),
                        ) {
                            interaction.onPerformDelete()
                        }
                    }
                }
            }
        }
    }
}
