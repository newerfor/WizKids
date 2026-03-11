package com.example.core_ui.ui.ChangeInformationWindow.FinanceInfoWindow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.core_ui.R
import com.example.core_ui.constant.SharedUiLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_FINANCE
import com.example.core_ui.constant.SharedUiViewConstant.ADD_NEW_OR_CHANGE_FINANCE_WINDOW_COLUMN_PADDING
import com.example.core_ui.constant.SharedUiViewConstant.FINANCE_INFORMATION_TEXT_PADDING
import com.example.core_ui.ui.ButtonView
import com.example.core_ui.ui.TextFieldVisible
import com.example.core_ui.ui.TextFont
import com.example.wizkids.ui.theme.cardBackground


class ChildFinanceWindow {
    @Composable
    fun WindowAddFinanceInformation(
        openWindowPrice: MutableState<Boolean>,
        textFont: TextFont,
        italyText: String,
        currentFinanceInfo: Int,
        onSave: (Int) -> Unit,
    ) {
        var textValue by remember {
            mutableStateOf(
                if (currentFinanceInfo == ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_FINANCE) "" else currentFinanceInfo.toString()
            )
        }
        Dialog(onDismissRequest = { openWindowPrice.value = false }) {
            Column(
                Modifier.Companion.background(color = cardBackground),
                Arrangement.Center,
                Alignment.Companion.CenterHorizontally
            ) {
                Column(Modifier.Companion.padding(ADD_NEW_OR_CHANGE_FINANCE_WINDOW_COLUMN_PADDING.dp)) {
                    textFont.ItalyText(
                        italyText,
                        Modifier.Companion.padding(vertical = FINANCE_INFORMATION_TEXT_PADDING.dp)
                    )
                    TextFieldVisible().NumberOutlineField(
                        textValue,
                        textFont,
                        "${stringResource(R.string.enter_prefix)} ${italyText}",
                    ) { newText -> textValue = newText }
                    ButtonView().ButtonVisibleRow(
                        mapOf(
                            stringResource(R.string.apply_button) to {
                                onSave.invoke(textValue.toInt())
                            },
                            stringResource(R.string.cancel_button) to {
                                openWindowPrice.value = false
                            },
                        ),
                        textFont
                    )
                }

            }
        }
    }
}