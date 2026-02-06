package com.example.wizkids.presentation.dateScreeen.ui.calendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.dateScreeen.ui.DateHelper
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.MAX_MONTH_COUNT
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.MIN_MONTH_COUNT
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_MONTH_AND_YEAR_CARD_ROW_CLIP
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_MONTH_AND_YEAR_CARD_ROW_PADDING_END
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.whiteColor

class MonthAndYearInformation {
    @Composable
    fun MonthAndYearCard(textFont: TextFont, monthNumber: MutableState<Int>, yearNumber: MutableState<Int>) {
        Row(Modifier.fillMaxWidth()){
            textFont.ItalyText("${DateHelper().getMonthName(monthNumber.value)} ${yearNumber.value}")
            Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(CALENDAR_ACTIVITY_MONTH_AND_YEAR_CARD_ROW_CLIP.dp)).padding(end = CALENDAR_ACTIVITY_MONTH_AND_YEAR_CARD_ROW_PADDING_END.dp),horizontalArrangement = Arrangement.End){
                Row(Modifier.background(lightGray)){
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            monthNumber.value = DateHelper().getPreviousMonth(monthNumber.value)
                            if(monthNumber.value == MAX_MONTH_COUNT) yearNumber.value--
                        },
                        tint = whiteColor
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            monthNumber.value = DateHelper().getNextMonth(monthNumber.value)
                            if(monthNumber.value == MIN_MONTH_COUNT) yearNumber.value++
                        },
                        tint = whiteColor
                    )
                }
            }
        }
    }
}