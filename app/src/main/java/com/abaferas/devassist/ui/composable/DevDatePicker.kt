package com.abaferas.devassist.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.theme.color_AccentColor
import com.abaferas.devassist.ui.theme.color_textPrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevDatePicker(
    title: String,
    subtitle: String,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onSelect: (String) -> Unit
) {
    val dState = rememberDatePickerState(yearRange = 2000..2050)
    DatePickerDialog(
        tonalElevation = 24.dp,
        onDismissRequest = onDismiss,
        confirmButton = {
            Row(
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                DevLabel(text = "cancel", color = color_textPrimaryColor,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .clickable {
                            onCancel()
                        })
                Spacer(modifier = Modifier.padding(end = 4.dp))
                DevLabel(fontSize = 18,
                    text = "Select",
                    color = color_AccentColor,
                    modifier = Modifier
                        .clickable {
                            onSelect("${dState.selectedDateMillis}")
                        })
            }
        }) {
        DatePicker(
            state = dState,
            showModeToggle = false,
            title = {
                DevLabel(
                    fontSize = 22,
                    text = title,
                    modifier = Modifier.padding(start = 16.dp, top = 32.dp)
                )
            },
            headline = {
                DevLabel(
                    fontSize = 18,
                    text = subtitle,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }
        )
    }
}