package com.abaferas.devassist.ui.screen.item.selecttype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abaferas.devassist.ui.composable.DevLabel
import com.abaferas.devassist.ui.composable.DevScaffold
import com.abaferas.devassist.ui.composable.DevTopAppBarWithLogo
import com.abaferas.devassist.ui.composable.modifier.mainContainerPadding
import com.abaferas.devassist.ui.navigation.NavigationHandler
import com.abaferas.devassist.ui.screen.item.newitem.navigateToNewItem
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor


@Composable
fun ScreenSelectType(
    screenSelectTypeViewModel: ScreenSelectTypeViewModel = hiltViewModel()
) {
    val state by screenSelectTypeViewModel.state.collectAsStateWithLifecycle()
    ScreenSelectTypeContent(state = state, interaction = screenSelectTypeViewModel)
    NavigationHandler(effects = screenSelectTypeViewModel.effect) { effect, controller ->
        when (effect) {
            is SelectTypeScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is SelectTypeScreenUiEffect.AddNewItem -> {
                controller.navigateToNewItem(effect.item)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSelectTypeContent(
    state: SelectTypeUiState,
    interaction: SelectTypeScreenInteraction
) {
    DevScaffold(
        isLoading = state.isLoading,
        isError = state.error.isError,
        errorMsg = state.error.message,
        topBar = {
            DevTopAppBarWithLogo(label = "Select Type"){
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
                .background(color = color_lightPrimaryColor)
                .mainContainerPadding(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            items(state.typesList) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = color_darkPrimaryColor
                    ),
                    onClick = { interaction.onClickType(it.type) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = it.icon,
                            contentDescription = "",
                            tint = color_textColor
                        )
                        DevLabel(modifier = Modifier.padding(start = 8.dp, top = 2.dp),
                            text = it.title, color = color_textColor, fontSize = 18)
                    }
                }
            }
        }
    }
}