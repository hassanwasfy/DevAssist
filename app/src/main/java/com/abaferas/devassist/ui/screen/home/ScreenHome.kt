package com.abaferas.devassist.ui.screen.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.PersonPin
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevLabelWithIcon
import com.abaferas.devassist.ui.composable.DevLottie
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.roundCorner
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.item.edititem.navigateToEditItem
import com.abaferas.devassist.ui.screen.item.selecttype.navigateToSelectType
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_dividerColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textSecondaryColor
import kotlinx.coroutines.delay


@Composable
fun ScreenHome(
    screenHomeViewModel: ScreenHomeViewModel = hiltViewModel()
) {
    val state = screenHomeViewModel.state.collectAsStateWithLifecycle().value
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
                Log.w("XCV","in home handler: ${effect.itemId}")
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
                    Log.w("XCV","in home list: ${it.itemId}")
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, color_darkPrimaryColor),
                                roundCornerShape(16)
                            )
                            .roundCorner(16)
                            .fillMaxWidth()
                            .height(144.dp),
                        onClick = { interaction.onClickEditItem(itemId = it.itemId) }) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                        ) {
                            DevLabelWithIcon(
                                text = it.name,
                                fontSize = 24,
                                color = color_darkPrimaryColor,
                                icon = interaction.iconFinder(it.type),
                                iconColor = color_darkPrimaryColor,
                                iconModifier = Modifier.padding(end = 4.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
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
                                        color = color_textSecondaryColor,
                                        icon = Icons.Outlined.Timer,
                                        iconColor = color_textSecondaryColor,
                                        iconModifier = Modifier.padding(end = 4.dp)
                                    )
                                    DevLabelWithIcon(
                                        text = it.endDate,
                                        fontSize = 14,
                                        color = color_textSecondaryColor,
                                        icon = Icons.Outlined.Timer,
                                        iconColor = color_textSecondaryColor,
                                        iconModifier = Modifier.padding(end = 4.dp)
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.65f)
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center

                                ) {
                                    val progress = remember {
                                       Animatable(0f)
                                    }
                                    LaunchedEffect(key1 = it.progress){
                                        progress.animateTo(
                                            targetValue = it.progress,
                                            animationSpec = spring(
                                                Spring.DampingRatioHighBouncy,
                                                Spring.StiffnessMediumLow
                                            ),
                                            initialVelocity = 0.6f
                                        )
                                    }
                                    CircularProgressIndicator(
                                        modifier = Modifier.fillMaxSize(),
                                        progress = { progress.value },
                                        color = color_darkPrimaryColor,
                                        trackColor = color_dividerColor,
                                        strokeCap = StrokeCap.Round,
                                        strokeWidth = 6.dp
                                    )
                                    DevFormattedLabel(value = progress.value)
                                }
                            }
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

@Composable
fun DevFormattedLabel(value: Float) {
    val formattedPercentage = remember(value) {
        AnnotatedString.Builder().apply {
            append("${(value * 100).toInt()}%")
        }.toAnnotatedString()
    }

    DevLabel(text = formattedPercentage.text, modifier = Modifier.padding(top = 16.dp), fontSize = 20,
        fontWeight = FontWeight.ExtraBold,
        color = color_darkPrimaryColor)
}