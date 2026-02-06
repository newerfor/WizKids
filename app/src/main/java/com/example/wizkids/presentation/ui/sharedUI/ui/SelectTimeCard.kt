package com.example.wizkids.presentation.sharedUI

import android.R.attr.onClick
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.TIME_HELPER_DEFAULT_VALUE_HOUR
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.TIME_HELPER_DEFAULT_VALUE_MINUTE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TIME_HELPER_DIALOG_COLUMN_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TIME_HELPER_DIALOG_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TIME_HELPER_DIALOG_SPACER_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.TIME_HELPER_SPACER_HEIGHT
import java.time.LocalTime
import com.example.wizkids.R
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView

@OptIn(ExperimentalMaterial3Api::class)
class SelectTimeCard {
    @Composable
    fun TimeHelper(
        selectedTime: MutableState<LocalTime?>,
        textFont: TextFont,
        timeString: MutableState<String>
    ) {
        var showTimePicker by remember { mutableStateOf(false) }
        val timePickerState = rememberTimePickerState(
            initialHour = selectedTime.value?.hour ?: TIME_HELPER_DEFAULT_VALUE_HOUR,
            initialMinute = selectedTime.value?.minute ?:TIME_HELPER_DEFAULT_VALUE_MINUTE,
            is24Hour = true
        )

        Column() {
            textFont.WhiteText(
                text = "${stringResource(R.string.selected_time)} ${timeString.value}"
            )

            Spacer(modifier = Modifier.height(TIME_HELPER_SPACER_HEIGHT.dp))
            ButtonView().ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.choice_time_button) to {
                        showTimePicker = true
                    },
                ),
                textFont
            )
        }

        if (showTimePicker) {
            Dialog(
                onDismissRequest = { showTimePicker = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(TIME_HELPER_DIALOG_PADDING.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(TIME_HELPER_DIALOG_COLUMN_PADDING.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        textFont.WhiteText(
                            text = stringResource(R.string.choice_time_text),
                        )
                        Spacer(modifier = Modifier.height(TIME_HELPER_DIALOG_SPACER_HEIGHT.dp))
                        TimePicker(
                            state = timePickerState,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        Spacer(modifier = Modifier.height(TIME_HELPER_DIALOG_SPACER_HEIGHT.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = { showTimePicker = false }
                            ) {
                                textFont.WhiteText(stringResource(R.string.cancel_button))
                            }
                            Spacer(modifier = Modifier.width(TIME_HELPER_DIALOG_SPACER_HEIGHT.dp))
                            Button(
                                onClick = {
                                    selectedTime.value = LocalTime.of(
                                        timePickerState.hour,
                                        timePickerState.minute
                                    )
                                    showTimePicker = false
                                }
                            ) {
                                textFont.WhiteText(stringResource(R.string.apply_button))
                            }
                        }
                    }
                }
            }
        }
    }
}