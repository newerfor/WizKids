package com.example.wizkids.presentation.utilPresentation

import android.annotation.SuppressLint
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.wizkids.presentation.main.constant.MainLogicConstant
import java.util.Calendar

class GetDate {
    @SuppressLint("DefaultLocale")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerExample(
        openWindowDate: MutableState<Boolean>,
        onDateSelected: (String) -> Unit
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
                                    MainLogicConstant.DateStringFormat,
                                    day,
                                    month,
                                    year
                                )
                                onDateSelected(selectedDate)
                            }
                            openWindowDate.value = false
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { openWindowDate.value = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}