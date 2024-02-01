package com.abaferas.devassist.ui.screen.books.newbooks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor


@Composable
fun ScreenBook(
    screenBookViewModel: ScreenBookViewModel = hiltViewModel()
) {
    val state by screenBookViewModel.state.collectAsStateWithLifecycle()
    ScreenBookContent(state = state, interaction = screenBookViewModel)
    NavigationHandler(effects = screenBookViewModel.effect) { effect, controller ->
        when (effect) {
            is BookScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBookContent(
    state: BookUiState,
    interaction: BookScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        isInternetConnected = state.isInternetConnected,
        onRetry = interaction::onClickRetry,
        topBar = {
            DevTopAppBarWithLogo("IT Books")
        },
        floating = {}
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            state = rememberLazyStaggeredGridState(),
            verticalItemSpacing = 8.dp,
            modifier = Modifier
                .fillMaxSize()
                .background(color_lightPrimaryColor)
                .padding(top = 56.dp, bottom = 72.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(state.items, key = {item -> item.isbn13}){
                Card(
                    modifier = Modifier.height(180.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red
                    )
                ) {
                    DevLabel(text = "${it.title} + ${it.price}")
                }
            }
        }
    }
}