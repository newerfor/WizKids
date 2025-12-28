package com.example.wizkids.presentation.dateScreeen.calendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.dateScreeen.DateHelper
import com.example.wizkids.presentation.dateScreeen.calendarScreen.constant.ViewConstant.GRID_COLUMNS_COUNT_DAY
import com.example.wizkids.presentation.utilPresentation.TextFont

class DayInMounthInformation {
    @Composable
    fun DayInMounth(
        domainDateInfoListVariant: List<DomainDateInfo>,
        monthNumber: MutableState<Int>,
        yearNumber: MutableState<Int>,
        textFont: TextFont
    ) {
        val dayList = DateHelper().getDaysInMonth(monthNumber.value, yearNumber.value)
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_COLUMNS_COUNT_DAY),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dayList) { day->
                DayCard(textFont, day,monthNumber,yearNumber,domainDateInfoListVariant)
            }
        }
    }
    @Composable
    fun DayCard(
        textFont: TextFont,
        dayNumber: Int,
        monthNumber: MutableState<Int>,
        yearNumber: MutableState<Int>,
        domainDateInfoListVariant: List<DomainDateInfo>
    ){
        var openWindowDate = remember { mutableStateOf(false) }
        Column(Modifier.background(DayColorState(DateHelper().GetFullDate(dayNumber,monthNumber.value,yearNumber.value ),domainDateInfoListVariant)).clickable{
            openWindowDate.value = true
        }){
            Column(Modifier.fillMaxWidth().padding(12.dp),Arrangement.Center,Alignment.CenterHorizontally){
                textFont.WhiteText(dayNumber.toString())
            }
        }
        if(openWindowDate.value){
            DateInfo().DateWindow(openWindowDate,"$dayNumber.${monthNumber.value}.${yearNumber.value}",domainDateInfoListVariant,textFont)
        }
    }
    @Composable
    fun DayColorState(currentDate: String, domainDateInfoListVariant: List<DomainDateInfo>): Color{
       return if(DateHelper().isWeekend(currentDate)){
            Color.Gray
       }else{
           if (domainDateInfoListVariant.any {dateInfo -> dateInfo.date == currentDate }) {
               Color.Green
           } else {
               Color.Blue
           }
       }
    }
}