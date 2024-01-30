package com.abaferas.devassist.ui.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.abaferas.devassist.ui.composable.modifier.roundCornerShape
import com.abaferas.devassist.ui.theme.color_darkPrimaryColor
import com.abaferas.devassist.ui.theme.color_lightPrimaryColor
import com.abaferas.devassist.ui.theme.color_textColor

@Composable
fun DevTextField(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    errorText: String,
    placeholder: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTogglePassword: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = color_darkPrimaryColor,
        unfocusedContainerColor = color_lightPrimaryColor
    ),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        supportingText = {
            AnimatedVisibility(
                visible = isError
            ) {
                DevLabel(
                    text = errorText,
                    fontSize = 12,
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(imageVector = it, contentDescription = "")
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "",
                    modifier = Modifier.clickable { onTogglePassword() })
            }
        },
        placeholder = {
            DevLabel(
                modifier = Modifier,
                text = placeholder,
                fontSize = 16,
            )
        },
        isError = isError,
        shape = roundCornerShape(corner = 16),
        colors = colors,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
    )
}

@Composable
fun DevTextFieldClickLeading(
    modifier: Modifier = Modifier,
    value: String,
    isError: Boolean,
    errorText: String,
    maxLines: Int = 1,
    singleLine: Boolean = true,
    placeholder: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: ImageVector? = null,
    leadingTint: Color = color_textColor,
    trailingIcon: ImageVector? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = color_darkPrimaryColor,
        unfocusedContainerColor = color_lightPrimaryColor
    ),
    onClickIcon: () -> Unit = {},
    onTogglePassword: () -> Unit = {},
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        singleLine = singleLine,
        maxLines = maxLines,
        modifier = modifier,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        supportingText = {
            AnimatedVisibility(
                visible = isError
            ) {
                DevLabel(
                    text = errorText,
                    fontSize = 12,
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    tint = leadingTint,
                    imageVector = it,
                    contentDescription = "",
                    modifier = Modifier.clickable { onClickIcon() })
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = "",
                    modifier = Modifier.clickable { onTogglePassword() })
            }
        },
        placeholder = {
            DevLabel(
                modifier = Modifier,
                text = placeholder,
                fontSize = 16,
            )
        },
        isError = isError,
        shape = roundCornerShape(corner = 16),
        colors = colors,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}