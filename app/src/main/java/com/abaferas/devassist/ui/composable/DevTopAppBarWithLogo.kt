package com.abaferas.devassist.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevTopAppBarWithLogo(
    label: String,
    actions: @Composable RowScope.() -> Unit = {},
    navigationIcon: @Composable () -> Unit = {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.logo_png),
            contentDescription = "", colorFilter = ColorFilter.tint(
                color_lightPrimaryColor
            )
        )
    },

    ) {
    TopAppBar(
        title = { Text(text = label) },
        navigationIcon = navigationIcon,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color_primaryColor,
            titleContentColor = color_textColor,
            actionIconContentColor = color_textColor,
            navigationIconContentColor = color_textColor
        ),
        actions = actions
    )
}