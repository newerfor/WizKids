package com.example.wizkids.presentation.ui.sharedUI.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.BUTTON_VISIBLE_DEFAULT_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.BUTTON_VISIBLE_WEIGHT
import com.example.wizkids.ui.theme.buttonColor
import com.example.wizkids.ui.theme.lightBlue

class ButtonView {
    @Composable
    fun ButtonVisibleRow(
        buttonNames: Map<String, () -> Unit>,
        textFont: TextFont,
        padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
    ) {
        Row(Modifier.fillMaxWidth()) {
            for (button in buttonNames) {
                Button(
                    onClick = button.value,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier
                        .weight(BUTTON_VISIBLE_WEIGHT)
                        .padding(end = padding.dp)
                ) {
                    textFont.WhiteText(button.key)
                }
            }
        }
    }

    @Composable
    fun ButtonVisibleColumn(
        buttonNames: Map<String, () -> Unit>,
        textFont: TextFont,
        padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
    ) {
        Column {
            for (button in buttonNames) {
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = button.value,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        modifier = Modifier
                            .weight(BUTTON_VISIBLE_WEIGHT)
                            .padding(top = padding.dp)
                    ) {
                        textFont.WhiteText(button.key)
                    }
                }
            }
        }
    }

    @Composable
    fun ButtonDayWeek(
        butonSetting: Map<String, Boolean?>,
        textFont: TextFont,
        onClick: (String) -> Unit,
        padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
    ) {
        Column {
            butonSetting.entries.forEach { entry ->
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { onClick(entry.key) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (entry.value == true) lightBlue else buttonColor
                        ),
                        modifier = Modifier
                            .weight(BUTTON_VISIBLE_WEIGHT)
                            .padding(top = padding.dp)
                    ) {
                        textFont.WhiteText(entry.key)
                    }
                }
            }
        }
    }

}