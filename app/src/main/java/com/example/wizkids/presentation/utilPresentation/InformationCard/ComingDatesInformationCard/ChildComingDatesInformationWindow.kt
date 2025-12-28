package com.example.wizkids.presentation.utilPresentation.InformationCard.ComingDatesInformationCard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.InputInformationCard
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.lightGray

class ChildComingDatesInformationWindow {
    @Composable
    fun ComingDatesInformationWindow(
        textFont: TextFont,
        openDateWindow: MutableState<Boolean>,
        date: DomainDateInfo?,
        isChangeAct: Boolean,
        onSave: (DomainDateInfo) -> Unit
    ){
        /*var DateDefoult = date?: DomainDateInfo(
            date = "",
            time = "",
            name ="",
            Status = "",
            Info = ""
        )
        val timeParts = DateDefoult.time.split(":")
        var hour by remember {
            mutableStateOf(if (timeParts.size >= 1) timeParts[0] else "00")
        }
        var minute by remember {
            mutableStateOf(if (timeParts.size >= 1) timeParts[0] else "00")
        }
        var openWindowGetDate = remember { mutableStateOf(false) }
        var choisedStatusDate by remember { mutableStateOf(DateDefoult.Status) }
        var nameDate by remember { mutableStateOf(DateDefoult.name) }
        var infoDate by remember { mutableStateOf(DateDefoult.Info) }
        var dateComing by remember { mutableStateOf(DateDefoult.date) }
        var ComingDateError by remember { mutableStateOf(false) }
        Dialog(onDismissRequest = {openDateWindow.value = false}) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = cardBackground)
            ) {
                InputInformationCard().AddInformationCard("Дата", textFont, horizontalpadding = 2.dp, verticalpadding = 5.dp) {
                    if(!isChangeAct){
                        textFont.WhiteText(DateDefoult.date)
                    }else{
                        textFont.WhiteText(dateComing)
                        Button(onClick = {openWindowGetDate.value = true}, Modifier.border(
                            width = if (ComingDateError) 2.dp else 1.dp,
                            color = if (ComingDateError) Color.Red else lightGray,
                            shape = RoundedCornerShape(20.dp)
                        )) {
                            textFont.WhiteText("Выберите дату")
                        }
                    }
                }
                InputInformationCard().AddInformationCard("Время", textFont, horizontalpadding = 2.dp, verticalpadding = 5.dp) {
                    if(!isChangeAct){
                        textFont.WhiteText(DateDefoult.time)
                    }else{
                        Row(Modifier.fillMaxWidth()){
                            Column(Modifier.weight(1f)){
                                TextFieldVisible().TextOutlineField(hour,textFont,"00"){ newText ->hour=newText}
                            }
                            Column(Modifier.weight(1f)){
                                TextFieldVisible().TextOutlineField(minute,textFont,"00"){ newText ->minute=newText}

                            }
                        }
                    }
                }
                InputInformationCard().AddInformationCard("Короткое название посещения", textFont, horizontalpadding = 2.dp, verticalpadding = 5.dp) {
                    if(!isChangeAct){
                        textFont.WhiteText(DateDefoult.name)
                    }else{
                        TextFieldVisible().TextOutlineField(nameDate,textFont,"Название...."){ newText ->nameDate=newText}
                    }
                }
                InputInformationCard().AddInformationCard("Статус посещения", textFont, horizontalpadding = 2.dp, verticalpadding = 5.dp) {
                    if(!isChangeAct){
                        textFont.WhiteText(DateDefoult.Status)
                    }else{
                        Row(){
                            StatusList.forEach {StatusName->
                                Button(onClick = {
                                    choisedStatusDate = StatusName
                                },Modifier.weight(1f).border(
                                    width = if (ComingDateError) 2.dp else 1.dp,
                                    color = if (ComingDateError) Color.Red else lightGray,
                                    shape = RoundedCornerShape(20.dp)
                                ), colors = ButtonDefaults.buttonColors(
                                    containerColor = if(choisedStatusDate == StatusName){ButtonAndInfoBlue}else{darkHeader},
                                )) {
                                    textFont.WhiteText("$StatusName")
                                }
                            }
                        }
                    }
                }
                InputInformationCard().AddInformationCard("Дополнительная информация", textFont, horizontalpadding = 2.dp, verticalpadding = 5.dp) {
                    if(!isChangeAct){
                        textFont.WhiteText(DateDefoult.Info)
                    }else{
                        TextFieldVisible().TextOutlineField(infoDate,textFont,"Дополнительная информация......"){ newText ->infoDate=newText}
                    }
                }
                Column {
                    if (isChangeAct) {
                        Column {
                            Row(Modifier.fillMaxWidth()){
                                textFont.BlueText("сбросить")
                                Button(onClick = {
                                    val newDocument = DomainDateInfo(
                                        date = dateComing,
                                        time = "$hour:$minute",
                                        name = nameDate,
                                        Status = choisedStatusDate,
                                        Info = infoDate
                                    )
                                    // Передаем через callback
                                    onSave(newDocument)
                                    openDateWindow.value = false
                                },Modifier.weight(1f)) {
                                    textFont.WhiteText("Применить")
                                }
                                Button(onClick = {
                                    openDateWindow.value = false
                                },Modifier.weight(1f)) {
                                    textFont.WhiteText("Отмена")
                                }
                            }
                        }
                    }
                }
            }
        }
         */
    }
}