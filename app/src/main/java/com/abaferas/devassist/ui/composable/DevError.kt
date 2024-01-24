package com.abaferas.devassist.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.abaferas.devassist.R
import com.abaferas.devassist.ui.theme.color_primaryColor
import com.abaferas.devassist.ui.theme.color_textColor

@Composable
fun DevError(
    isRetrying: Boolean = false,
    errorMsg: String,
    onRetry: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_internet_connection),
            contentDescription = ""
        )
        DevLabel(text = errorMsg, color = color_textColor)
        Button(
            onClick = onRetry,
            enabled = !isRetrying,
            colors = ButtonDefaults.buttonColors(
                contentColor = color_textColor,
                containerColor = color_primaryColor
            )
        ) {
            DevLabel(text = "Try Again", color = color_textColor)
        }
    }
}