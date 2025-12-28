package com.example.wizkids.presentation.dateScreeen.upcomingDatesScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.dateScreeen.DateHelper
import com.example.wizkids.presentation.dateScreeen.PersonalDateInfo
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationCardbackGround
import com.example.wizkids.presentation.utilPresentation.InputInformationCard
import com.example.wizkids.presentation.utilPresentation.TextFont
import java.time.format.DateTimeFormatter

class UpcomingDatesCard {
    @Composable
    fun UpcomingDates(domainDateInfoList: List<DomainDateInfo>, textFont: TextFont) {
        var monthNumber = remember { mutableStateOf(DateHelper().getMonthNumber()) }
        var yearNumber = remember { mutableStateOf(DateHelper().getYearNumber()) }
        var dayNumber = remember { mutableStateOf(DateHelper().getDayNumber()) }
        var today = remember { mutableListOf<DomainDateInfo>() }
        var tomorrow = remember { mutableListOf<DomainDateInfo>() }
        var afterTomorrow = remember { mutableListOf<DomainDateInfo>() }
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())){
            ChildInformationCardbackGround().InformationCardbackGround {
                domainDateInfoList.map {dateInfo ->
                    if(dateInfo.date == DateHelper().GetFullDate(dayNumber.value,monthNumber.value,yearNumber.value)){
                        today.add(dateInfo)
                    }else{
                        if(dateInfo.date == DateHelper().GetFullDate(dayNumber.value,monthNumber.value,yearNumber.value)){
                            tomorrow.add(dateInfo)
                        } else {
                            if(dateInfo.date == DateHelper().GetFullDate(dayNumber.value,monthNumber.value,yearNumber.value)){
                                afterTomorrow.add(dateInfo)
                            }
                        }
                    }
                }
                InputInformationCard().AddInformationCard("Сегодня",textFont) {
                    for(todayDate in today){
                        PersonalDateInfo().DateInformation(todayDate,textFont)
                    }
                }
                InputInformationCard().AddInformationCard("Завтра",textFont) {
                    for(tomorrowDate in tomorrow){
                        PersonalDateInfo().DateInformation(tomorrowDate,textFont)
                    }
                }
                InputInformationCard().AddInformationCard("Послезавтра",textFont) {
                    for(afterTomorrowDate in afterTomorrow){
                        PersonalDateInfo().DateInformation(afterTomorrowDate,textFont)
                    }
                }
            }
        }

    }
}
