package com.abaferas.devassist.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevCardIcon
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLabelWithIcon
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.roundCorner
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.item.edititem.navigateToEditItem
import com.abaferas.devassist.ui.screen.item.selecttype.navigateToSelectType
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_backgroundColor
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_dividerColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_textColor


@Composable
fun ScreenHome(
    screenHomeViewModel: ScreenHomeViewModel = hiltViewModel()
) {
    val state = screenHomeViewModel.state.collectAsState().value
    ScreenHomeContent(state = state, interaction = screenHomeViewModel)
    NavigationHandler(effects = screenHomeViewModel.effect) { effect, controller ->
        when (effect) {
            is HomeScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is HomeScreenUiEffect.AddNewItem -> {
                controller.navigateToSelectType()
            }

            is HomeScreenUiEffect.EditCurrentItem -> {
                controller.navigateToEditItem(effect.itemId)
            }
        }
    }
}

@Composable
fun ScreenHomeContent(
    state: HomeUiState,
    interaction: HomeScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        isInternetConnected = state.isInternetConnected,
        onRetry = interaction::onClickRetry,
        topBar = {
            DevTopAppBarWithLogo("Home")
        },
        floating = {
            AnimatedVisibility(visible = !state.error.isError) {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 72.dp),
                    onClick = interaction::onClickAddNewItem,
                    containerColor = color_AccentColor
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Outlined.AddTask,
                        contentDescription = "",
                        tint = color_textColor
                    )
                }
            }
        }
    ) {
        AnimatedVisibility(visible = state.items.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color_lightPrimaryColor)
                    .padding(top = 72.dp, bottom = 56.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.items) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .border(BorderStroke(1.dp, color_darkPrimaryColor), roundCornerShape(16))
                            .roundCorner(16)
                            .fillMaxWidth()
                            .height(144.dp),
                        onClick = { interaction.onClickEditItem(itemId = it.itemId) }) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            DevLabelWithIcon(
                                text = it.name,
                                fontSize = 24,
                                color = color_darkPrimaryColor,
                                icon = interaction.iconFinder(it.type),
                                iconColor = color_darkPrimaryColor,
                                iconModifier = Modifier.padding(end = 4.dp)
                            )
                            DevLabelWithIcon(
                                text = it.author,
                                fontSize = 18,
                                color = color_darkPrimaryColor,
                                icon = Icons.Outlined.PersonPin,
                                iconColor = color_darkPrimaryColor,
                                iconModifier = Modifier.padding(end = 4.dp)
                            )
                            DevLabelWithIcon(
                                text = it.startDate,
                                fontSize = 14,
                                color = color_dividerColor,
                                icon = Icons.Outlined.Timer,
                                iconColor = color_dividerColor,
                                iconModifier = Modifier.padding(end = 4.dp)
                            )
                            DevLabelWithIcon(
                                text = it.endDate,
                                fontSize = 14,
                                color = color_dividerColor,
                                icon = Icons.Outlined.Timer,
                                iconColor = color_dividerColor,
                                iconModifier = Modifier.padding(end = 4.dp)
                            )
                        }
                    }
                }
            }
        }
        AnimatedVisibility(visible = state.items.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color_lightPrimaryColor)
                    .padding(top = 56.dp, bottom = 56.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DevLottie(
                    modifier = Modifier.fillMaxHeight(0.65f),
                    id = R.raw.empty_list
                )
                DevLabel(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "Nothing yet!", color = color_AccentColor, fontSize = 24,
                )
                DevLabel(
                    text = "hit the circle and get some.", fontSize = 16,
                    fontWeight = FontWeight.SemiBold, color = color_darkPrimaryColor
                )
            }
        }
    }
}