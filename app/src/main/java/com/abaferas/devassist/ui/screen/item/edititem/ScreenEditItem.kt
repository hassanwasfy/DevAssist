package com.abaferas.devassist.ui.screen.item.edititem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssistantPhoto
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor


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
        onRetry = { /*TODO*/ },
        topBar = {
            DevTopAppBarWithLogo(label = "Details")
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
                    leadingIcon = Icons.Outlined.AutoStories
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
                DevTextFieldClickLeading(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.startDate.value,
                    isError = state.startDate.error.isError,
                    errorText = state.startDate.error.message,
                    placeholder = "Start Date",
                    onValueChange = interaction::onStartDateChange,
                    leadingIcon = Icons.Outlined.Timer
                )
            }
            item {
                DevTextFieldClickLeading(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.endDate.value,
                    isError = state.endDate.error.isError,
                    errorText = state.endDate.error.message,
                    placeholder = "End Date",
                    onValueChange = interaction::onEndDateChange,
                    leadingIcon = Icons.Outlined.Timer
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
                    leadingIcon = Icons.Outlined.AssistantPhoto
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
                    leadingIcon = Icons.Outlined.Done
                )
            }
        }
    }
}