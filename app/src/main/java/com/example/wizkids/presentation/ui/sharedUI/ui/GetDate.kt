package com.example.wizkids.presentation.sharedUI

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.GET_DATE_TEXT_INPUT_DATE_FORMAT
import com.example.wizkids.ui.theme.blackColor
import java.util.Calendar

class GetDate {
    @SuppressLint("DefaultLocale")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerExample(
        openWindowDate: MutableState<Boolean>,
        textFont: TextFont,
        onDateSelected: (String) -> Unit,
    ) {
        val datePickerState = rememberDatePickerState()
        if (openWindowDate.value) {
            DatePickerDialog(
                onDismissRequest = { openWindowDate.value = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val calendar = Calendar.getInstance().apply {
                                    timeInMillis = millis
                                }
                                val day = calendar.get(Calendar.DAY_OF_MONTH)
                                val month = calendar.get(Calendar.MONTH) + 1
                                val year = calendar.get(Calendar.YEAR)
                                val selectedDate = String.format(
                                    GET_DATE_TEXT_INPUT_DATE_FORMAT,
                                    day,
                                    month,
                                    year
                                )
                                onDateSelected(selectedDate)
                            }
                            openWindowDate.value = false
                        }
                    ) { textFont.UnColorText(stringResource(R.string.apply_button), color = blackColor) }
                },
                dismissButton = {
                    TextButton(onClick = { openWindowDate.value = false }) { textFont.UnColorText(
                        stringResource(R.string.cancel_button), color = blackColor
                    ) }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}