package com.example.wizkids.presentation.dateScreeen.calendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.dateScreeen.DateHelper
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.WhiteColor
import com.example.wizkids.ui.theme.lightGray

class MonthAndYearInformation {
    @Composable
    fun MonthAndYearCard(textFont: TextFont, monthNumber: MutableState<Int>, yearNumber: MutableState<Int>) {
        Row(Modifier.fillMaxWidth()){
            textFont.ItalyText("${DateHelper().getMonthName(monthNumber.value)} ${yearNumber.value}")
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).padding(end = 12.dp),horizontalArrangement = Arrangement.End){
                Row(Modifier.background(lightGray)){
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Домой",
                        Modifier.clickable {
                            monthNumber.value = DateHelper().getPreviousMonth(monthNumber.value)
                            if(monthNumber.value == 12) yearNumber.value--
                        },
                        tint = WhiteColor
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Домой",
                        Modifier.clickable {
                            monthNumber.value = DateHelper().getNextMonth(monthNumber.value)
                            if(monthNumber.value == 1) yearNumber.value++
                        },
                        tint = WhiteColor
                    )
                }
            }
        }
    }
}