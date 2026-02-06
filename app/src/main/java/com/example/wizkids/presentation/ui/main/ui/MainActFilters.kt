package com.example.wizkids.presentation.main.ui

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
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
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_FIRST_YEAR
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_SECOND_YEAR
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_STATUS
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_VALUE_STATUS_LIST
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BUTTON_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BUTTON_WEIGHT
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_CONTAINER_SPACER_HEIGHT
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_HINT_CONTAINER_TOP_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_MAIN_CONTAINER_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_BY_PAY_STATUS_MAIN_CONTAINER_PADDING
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.textLightGray
import com.example.wizkids.ui.theme.textWhite
import com.example.wizkids.R
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_ITALY_TEXT_PADDING
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView

class MainActFilters {

    @Composable
    fun WindowFilters(
        openWindowFilters: MutableState<Boolean>,
        textFont: TextFont,
        selectedStatus: MutableState<String>,
        secondYears: MutableState<Int>,
        firstYear: MutableState<Int>,
        ) {
        var firstYearString by remember { mutableStateOf(
            if(firstYear.value == 0) "" else (firstYear.value.toString()
        ))}
        var secondYearString by remember { mutableStateOf(
            if(secondYears.value == 0) "" else (secondYears.value.toString()
                    ))}
        var selectedStatusString = remember { mutableStateOf(selectedStatus.value) }
        var expanded = remember { mutableStateOf(false) }
        var statusList by remember { mutableStateOf(MAIN_ACTIVITY_VALUE_STATUS_LIST) }
        val textColor = if (selectedStatus.value != stringResource(R.string.select_status_default_value_label)) textWhite else textLightGray
        Dialog(onDismissRequest = { openWindowFilters.value = false }) {
            Column(Modifier.background(color = cardBackground)){
                Column(Modifier.padding(MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_MAIN_CONTAINER_PADDING.dp)){
                    Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                        textFont.ItalyText(stringResource(R.string.main_activity_filters_container_label))
                    }
                    Spacer(Modifier.height(MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_CONTAINER_SPACER_HEIGHT.dp))
                    textFont.ItalyText(stringResource(R.string.main_activity_filters_container_sublabel_filter_by_age),Modifier.padding(start = MAIN_ACTIVITY_MAIN_FILTERS_ITALY_TEXT_PADDING.dp))
                    Column(Modifier.padding(top = MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_HINT_CONTAINER_TOP_PADDING.dp)) {
                       TextFieldVisible().NumberOutlineField(firstYearString,textFont,stringResource(R.string.main_activity_filters_first_date_label)){newText -> firstYearString = newText}
                        Spacer(Modifier.height(MAIN_ACTIVITY_MAIN_FILTERS_BY_AGE_CONTAINER_SPACER_HEIGHT.dp))
                        TextFieldVisible().NumberOutlineField(secondYearString,textFont,stringResource(R.string.main_activity_filters_second_date_label)){newText -> secondYearString = newText}
                    }
               }
                Column(Modifier.padding(MAIN_ACTIVITY_MAIN_FILTERS_BY_PAY_STATUS_MAIN_CONTAINER_PADDING.dp)){
                    textFont.ItalyText(stringResource(R.string.main_activity_filters_container_sublabel_filter_by_pay_status),Modifier.padding(start = MAIN_ACTIVITY_MAIN_FILTERS_ITALY_TEXT_PADDING.dp) )
                    TextFieldVisible().DropMenuField(
                        expanded, selectedStatusString,
                        textFont = textFont,
                        optionList = statusList
                    )
                }
                Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                    textFont.BlueText(stringResource(R.string.reset_text), modifier = Modifier.clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple()
                    ){
                        openWindowFilters.value = false
                        selectedStatus.value = MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_STATUS
                        secondYears.value = MAIN_ACTIVITY_DEFAULT_VALUE_SECOND_YEAR
                        firstYear.value = MAIN_ACTIVITY_DEFAULT_VALUE_FIRST_YEAR
                    })
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.apply_button) to {
                            selectedStatus.value = selectedStatusString.value
                            secondYears.value = secondYearString.toInt()
                            firstYear.value = firstYearString.toInt()
                            openWindowFilters.value = false
                        },
                        stringResource(R.string.cancel_button) to { openWindowFilters.value = false }
                    ),
                    textFont
                )
            }
        }
    }
}