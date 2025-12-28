package com.example.wizkids.presentation.utilPresentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.main.constant.MainViewConstant
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.textWhite
import com.example.wizkids.presentation.utilPresentation.TextFont

class TextFieldVisible {
    @Composable
    fun TextOutlineField(
        value: String,
        textFont: TextFont,
        placeholderText: String,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: Boolean = false,
        onValueChange: (String) -> Unit,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier.fillMaxWidth(0.9f).padding(10.dp),
            textStyle = LocalTextStyle.current.merge(
                TextStyle(
                    color = textWhite,
                    fontFamily = Inter,
                    fontSize = MainViewConstant.WindowFiltersRowOutlinedTextFieldTextStyleSize,
                    fontWeight = FontWeight.Companion.Normal
                )
            ),
            singleLine = true,
            leadingIcon = leadingIcon,
            placeholder = {
                textFont.GrayText(placeholderText)
            },
            trailingIcon = {
                if (trailingIcon) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Ошибка",
                        tint = Color.Companion.Red
                    )
                }
            },

            )
    }
}