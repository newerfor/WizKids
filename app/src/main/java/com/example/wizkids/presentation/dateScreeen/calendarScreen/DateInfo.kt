package com.example.wizkids.presentation.dateScreeen.calendarScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.presentation.dateScreeen.PersonalDateInfo
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.lightGray

class DateInfo {
    @Composable
    fun DateWindow(
        openWindowDate: MutableState<Boolean>,
        currentDate: String,
        domainDateInfoListVariant: List<DomainDateInfo>,
        textFont: TextFont,
    ) {
        Dialog(onDismissRequest = {openWindowDate.value = false}) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = cardBackground)
            ) {
                for(DateInfo in domainDateInfoListVariant){
                    if(DateInfo.date == currentDate){
                        PersonalDateInfo().DateInformation(DateInfo,textFont)
                    }
                }
            }
        }
    }

}