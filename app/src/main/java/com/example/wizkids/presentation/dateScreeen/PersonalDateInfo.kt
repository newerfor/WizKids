package com.example.wizkids.presentation.dateScreeen

import android.R.attr.onClick
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.domain.model.DomainDataChild
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.lightGray

class PersonalDateInfo {
    @Composable
    fun DateInformation(DateInfo: DomainDateInfo, textFont: TextFont) {
        var openWindowPesonalDate = remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth().padding(10.dp).background(lightGray).clickable{
            openWindowPesonalDate.value = true
        }){
            Column(Modifier.fillMaxWidth().padding(10.dp)){
                textFont.WhiteText(DateInfo.childName)
                textFont.WhiteText(DateInfo.time)
                textFont.WhiteText(DateInfo.status)
            }
        }
        if(openWindowPesonalDate.value){
            PersonalDateInfo().PersonalDateWindow(openWindowPesonalDate,DateInfo,textFont)
        }
    }
    @Composable
    fun PersonalDateWindow(
        openWindowPesonalDate: MutableState<Boolean>,
        DateInfo: DomainDateInfo,
        textFont: TextFont
    ) {
        Dialog(onDismissRequest = {openWindowPesonalDate.value=false}) {
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Column(
                    Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = cardBackground).padding(20.dp)
                ) {
                    textFont.WhiteText(DateInfo.date)
                    textFont.WhiteText(DateInfo.info)
                    textFont.WhiteText(DateInfo.status)
                    textFont.WhiteText(DateInfo.time)
                    textFont.WhiteText(DateInfo.childName)
                    textFont.WhiteText(DateInfo.name)
                    //ЗДЕСЬ СТАТУС ОПЛАТЫ
                    Row(Modifier.fillMaxWidth()) {
                        Button(onClick = {openWindowPesonalDate.value=false},Modifier.weight(1f)) {
                            textFont.WhiteText("Пришёл")
                        }
                        Button(onClick = {openWindowPesonalDate.value=false},Modifier.weight(1f)) {
                            textFont.WhiteText("Не пришёл")
                        }
                    }
                }
            }
        }
    }
}