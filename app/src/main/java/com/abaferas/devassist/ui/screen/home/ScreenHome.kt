package com.abaferas.devassist.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevCardIcon
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTextField
import com.abaferas.devassist.ui.composable.DevTextFieldClickLeading
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.item.navigateToEditItem
import com.abaferas.devassist.ui.screen.item.navigateToNewItem
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
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
                controller.navigateToNewItem()
            }

            is HomeScreenUiEffect.EditCurrentItem -> {
                controller.navigateToEditItem(effect.itemId)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color_lightPrimaryColor)
                .padding(top = 56.dp, bottom = 56.dp),
        ) {
            DevLabel(
                modifier = Modifier.padding(16.dp),
                text = "Welcome ${state.userName}!",
                fontSize = 24,
                fontWeight = FontWeight.ExtraBold,
                color = color_darkPrimaryColor
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DevTextFieldClickLeading(
                    value = state.searchValue,
                    isError = state.error.isError,
                    errorText = state.error.message,
                    placeholder = "Search Learning items",
                    onValueChange = interaction::onSearchValueChange,
                    onClickIcon = interaction::onClickSearch,
                    modifier = Modifier.fillMaxWidth(0.85f),
                    leadingIcon = Icons.Outlined.Search
                )
                DevCardIcon(
                    modifier = Modifier,
                    iconModifier = Modifier
                        .size(32.dp)
                        .padding(8.dp),
                    icon = Icons.Outlined.FilterAlt,
                    iconColor = color_lightPrimaryColor,
                    onClick = interaction::onClickFilter,
                    colors = CardDefaults.cardColors(
                        containerColor = color_darkPrimaryColor
                    )
                )
            }
            AnimatedVisibility(visible = state.items.isNotEmpty()) {
                LazyColumn {
                    items(state.items) {
                        Card(onClick = {interaction.onClickEditItem(itemId = it.id)}) {

                        }
                    }
                }
            }
            AnimatedVisibility(visible = state.items.isEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 56.dp)
                        .weight(1f)
                        .fillMaxWidth(),
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
}