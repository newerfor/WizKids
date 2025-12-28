package com.example.wizkids.presentation.dateScreeen.upcomingDatesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.dateScreeen.PersonalDateInfo
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.lightGray

class UpcomingDateCard {
    @Composable
    fun UpcomingDateInfo(date: DomainDateInfo, textFont: TextFont) {
        Column(Modifier.fillMaxWidth()){
            var openWindowPesonalDate = remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth().padding(10.dp).background(lightGray).clickable{
                openWindowPesonalDate.value = true
            }){
                Column(Modifier.fillMaxWidth().padding(10.dp)){
                    textFont.WhiteText(date.childName)
                    textFont.WhiteText(date.time)
                    textFont.WhiteText(date.status)
                }
            }
            if(openWindowPesonalDate.value){
                PersonalDateInfo().PersonalDateWindow(openWindowPesonalDate,date,textFont)
            }
        }
    }
}
