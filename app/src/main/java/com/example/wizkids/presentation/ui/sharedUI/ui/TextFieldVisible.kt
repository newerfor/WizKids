package com.example.wizkids.presentation.sharedUI

import android.R.attr.textColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BY_PAY_STATUS_SELECTED_ITEM_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DROW_MENU_EXPOSED_MENU_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TEXT_OUTLINE_FIELD_FONT_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TEXT_OUTLINE_FIELD_WIDTH
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.blackColor
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.redColor
import com.example.wizkids.ui.theme.whiteColor

class TextFieldVisible {
    @Composable
    fun TextOutlineField(
        value: String,
        textFont: TextFont,
        placeholderText: String,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: Boolean = false,
        textColor: Color = whiteColor,
        onValueChange: (String) -> Unit,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            Modifier.fillMaxWidth(TEXT_OUTLINE_FIELD_WIDTH),
            textStyle = LocalTextStyle.current.merge(
                TextStyle(
                    color = textColor,
                    fontFamily = Inter,
                    fontSize = TEXT_OUTLINE_FIELD_FONT_SIZE.sp,
                    fontWeight = FontWeight.Companion.Normal
                )
            ),
            singleLine = true,
            leadingIcon = leadingIcon,
            placeholder = {
                textFont.GrayText(placeholderText)
            },
            trailingIcon = {
                if (trailingIcon && value.isEmpty()) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "",
                        tint = redColor
                    )
                }
            },
         )
    }
    @Composable
    fun NumberOutlineField(
        value: String,
        textFont: TextFont,
        placeholderText: String,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: Boolean = false,
        textColor: Color = whiteColor,
        onValueChange: (String) -> Unit,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                // Фильтруем только цифры
                if (newValue.all { it.isDigit() }) {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.fillMaxWidth(TEXT_OUTLINE_FIELD_WIDTH),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,  // Открывает цифровую клавиатуру
                imeAction = ImeAction.Done
            ),
            textStyle = LocalTextStyle.current.merge(
                TextStyle(
                    color = textColor,
                    fontFamily = Inter,
                    fontSize = TEXT_OUTLINE_FIELD_FONT_SIZE.sp,
                    fontWeight = FontWeight.Normal
                )
            ),
            singleLine = true,
            leadingIcon = leadingIcon,
            placeholder = {
                textFont.GrayText(placeholderText)
            },
            trailingIcon = {
                if (trailingIcon && value.isEmpty()) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "",
                        tint = redColor
                    )
                }
            }
        )
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropMenuField(expanded: MutableState<Boolean>, selectedValue: MutableState<String>,textFont: TextFont, optionList: List<String>) {
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(DROW_MENU_EXPOSED_MENU_PADDING.dp),
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = !expanded.value
            }
        ) {
            TextField(
                value = selectedValue.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                modifier = Modifier
                    .menuAnchor()
                    .background(color = lightGray),
                textStyle = LocalTextStyle.current.merge(
                    TextStyle(
                        color = blackColor,
                        fontFamily = Inter,
                        fontSize = MAIN_ACTIVITY_MAIN_FILTERS_BY_PAY_STATUS_SELECTED_ITEM_SIZE.sp,
                        fontWeight = FontWeight.Normal
                    )
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = lightGray,
                    unfocusedContainerColor = lightGray,
                    disabledContainerColor = lightGray,
                ),
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(color = darkHeader)
            ) {
                optionList.forEach { item ->
                    DropdownMenuItem(
                        text = { textFont.WhiteText(item)},
                        onClick = {
                            selectedValue.value = item
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}