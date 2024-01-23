package com.abaferas.devassist.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abaferas.devassist.data.homeList
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.Tajawal
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_dividerColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_textColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor
import com.abaferas.devassist.ui.theme.color_textSecondaryColor


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
        isLoading = false, isError = false,
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = color_primaryColor,
                    titleContentColor = color_textColor,
                    actionIconContentColor = color_textColor,
                    navigationIconContentColor = color_textColor
                )
            )
        }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 72.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(homeList, key = { it.id }
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { //TODO
                    }
                ) {
                    DevLabel(
                        text = it.name,
                        fontSize = 20,
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold,
                        color = color_textPrimaryColor,
                        modifier = Modifier.padding(start = 16.dp,top = 8.dp)
                    )
                    DevLabel(
                        text = it.author,
                        fontSize = 16,
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold,
                        color = color_textSecondaryColor,
                        modifier = Modifier.padding(start = 16.dp,top = 8.dp)
                    )
                    DevLabel(
                        text = "finished: ${it.progress}%",
                        fontSize = 14,
                        fontFamily = Tajawal,
                        fontWeight = FontWeight.Bold,
                        color = color_textSecondaryColor,
                        modifier = Modifier.padding(start = 16.dp,top = 8.dp)
                    )
                }
            }
        }
    }
}


