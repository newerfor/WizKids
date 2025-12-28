package com.example.wizkids.presentation.dateScreeen.calendarScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.dateScreeen.DateHelper
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationCardbackGround
import com.example.wizkids.presentation.utilPresentation.TextFont

class CalendarCard {
    @Composable
    fun Calendar(domainDateInfoListVariant: List<DomainDateInfo>, textFont: TextFont) {
        var monthNumber = remember { mutableStateOf(DateHelper().getMonthNumber()) }
        var yearNumber = remember { mutableStateOf(DateHelper().getYearNumber()) }
        ChildInformationCardbackGround().InformationCardbackGround {
            MonthAndYearInformation().MonthAndYearCard(textFont,monthNumber,yearNumber)
            DayInMounthInformation().DayInMounth(domainDateInfoListVariant,monthNumber,yearNumber,textFont)
        }
    }
}
