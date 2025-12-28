package com.example.wizkids.presentation.utilPresentation.InformationCard.ComingDatesInformationCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.payFalse
import com.example.wizkids.ui.theme.payLater
import com.example.wizkids.ui.theme.payTrue

class ChildComingDatesInformation {
    @Composable
    fun ComingDatesInformation(
        textFont: TextFont,
        date: DomainDateInfo,
        onChangeAct: Boolean = false,
        openWindowAddDate: MutableState<Boolean>? = null,
        onDateAdded: (DomainDateInfo) -> Unit = {},
        onChange : (DomainDateInfo) -> Unit = {}
    ){
        var openWindowDate = remember { mutableStateOf(false) }
            Row(Modifier.fillMaxWidth().padding(12.dp).background(
                /*when(date.Status){
                    ComingStatusTrue-> payTrue
                    ComingStatusFalse-> payFalse
                    else-> payLater
                }
                */Color.White
            )) {
                Row(Modifier.padding(10.dp)){
                    textFont.WhiteText("${date.date}/${date.time} - ${date.name}", Modifier.weight(3f))
                    if(onChangeAct){
                        Row(Modifier.weight(1f)) {
                            Box(Modifier.weight(1f).padding(5.dp).background(Color.White).clickable{

                            }, contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Delete, "delete", tint = Color.Black)
                            }
                            Box(Modifier.weight(1f).padding(5.dp).background(Color.White).clickable{
                                openWindowDate.value = true
                            }, contentAlignment = Alignment.Center) {
                                Icon(Icons.Default.Create, "edit", tint = Color.Black)
                            }
                        }
                    }
                }
            }

        if(openWindowDate.value){
            ChildComingDatesInformationWindow().ComingDatesInformationWindow(textFont,openWindowDate,date,onChangeAct,onChange)
        }
        if(openWindowAddDate?.value ?: false){
            ChildComingDatesInformationWindow().ComingDatesInformationWindow(textFont,openWindowAddDate,null,onChangeAct,onDateAdded)
        }
    }
}