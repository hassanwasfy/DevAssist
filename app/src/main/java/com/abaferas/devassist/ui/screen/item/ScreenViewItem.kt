package com.abaferas.devassist.ui.screen.item

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AssistantPhoto
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.ui.composable.DevButton
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor


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
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        onRetry = { /*TODO*/ },
        topBar = {
            DevTopAppBarWithLogo(label = "Details")
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 64.dp).background(
                color_lightPrimaryColor),
            ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DevButton(
                    text = "Book",
                    modifier = Modifier.width(250.dp),
                    onClick = { interaction.onTypeChange("Book") },
                    shape = roundCornerShape(8),
                    color = if (state.isBook) color_lightPrimaryColor else color_darkPrimaryColor,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (state.isBook)
                            color_darkPrimaryColor else color_lightPrimaryColor
                    )
                )
                DevButton(
                    text = "Course",
                    modifier = Modifier.width(250.dp),
                    onClick = { interaction.onTypeChange("Course") },
                    shape = roundCornerShape(8),
                    color = if (!state.isBook) color_lightPrimaryColor else color_darkPrimaryColor,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (!state.isBook)
                            color_darkPrimaryColor else color_lightPrimaryColor
                    )
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(top = 8.dp),
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
}