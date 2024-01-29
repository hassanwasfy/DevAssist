package com.abaferas.devassist.ui.screen.item.selecttype

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddReaction
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector
import com.abaferas.devassist.data.model.LearningType
import com.abaferas.devassist.ui.base.BaseUiState
import com.abaferas.devassist.ui.base.ErrorUiState


data class SelectTypeUiState(
    val isLoading: Boolean = false,
    val error: ErrorUiState = ErrorUiState(),
    val typesList: List<TypeListItem> = listOf(
        TypeListItem("Azkar",LearningType.Azkar, Icons.Outlined.AddReaction),
        TypeListItem("Book",LearningType.BOOK, Icons.Outlined.MenuBook),
        TypeListItem("Course",LearningType.PLAY_LIST, Icons.Outlined.VideoLibrary),
        TypeListItem("Video",LearningType.Video, Icons.Outlined.OndemandVideo),
    )
) : BaseUiState



data class TypeListItem(val title: String,val type: LearningType, val icon: ImageVector)