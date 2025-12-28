package com.example.wizkids.presentation.addNewOrChangeChild

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainDataChild
import com.example.wizkids.domain.model.DomainDateInfo
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.GetDate
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationCardbackGround
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationImageAndPayStatus
import com.example.wizkids.presentation.utilPresentation.InformationCard.ComingDatesInformationCard.ChildComingDatesInformation
import com.example.wizkids.presentation.utilPresentation.InformationCard.DocumentsInforamtionCard.ChildDocumetsInformation
import com.example.wizkids.presentation.utilPresentation.InputInformationCard
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.lightBlue

class ChildAddInformation {
    @Composable
    fun AddInformation(textFont: TextFont, sampleChild: DomainDataChild? = null) {
        var childName = remember { mutableStateOf(sampleChild?.name ?: "") }
        var childNameError = remember { mutableStateOf(false) }
        var childAdditionalInfo = remember { mutableStateOf(sampleChild?.Info ?: "") }
        var openWindowGetDate = remember { mutableStateOf(false) }
        var childHB = remember { mutableStateOf(sampleChild?.happybithday ?: "") }
        var childHBError = remember { mutableStateOf(false) }
        var childImage = remember { mutableStateOf(sampleChild?.foto ?: "") }
        var childImageError = remember { mutableStateOf(false) }
        var childPrice = remember { mutableStateOf(sampleChild?.price ?: 0) }
        var childBalance = remember { mutableStateOf(sampleChild?.balance ?: 0) }
        var childFinanceInfoError = remember { mutableStateOf(false) }
        var childDocuments = remember { mutableStateListOf<DomainDocumentsData>() }
        childDocuments.addAll(sampleChild?.docs ?: emptyList())
        var childDocumentsInfoError = remember { mutableStateOf(false) }
        var openWindowAddDocumet = remember { mutableStateOf(false) }
        var childWorkStage = remember { mutableStateOf(sampleChild?.workStage ?: emptyList()) }
        var childWorkStageError = remember{ mutableStateOf(false) }
        var childDateComing =remember { mutableStateListOf<DomainDateInfo>() }
        //childDateComing.addAll(sampleChild?.DateInfo ?: emptyList())
        var childDateComingError = remember{ mutableStateOf(false) }
        var openWindowAddDate = remember { mutableStateOf(false) }
        ChildInformationCardbackGround().InformationCardbackGround {
            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                childImage.value,
                price = childPrice.value,
                balance = childBalance.value,
                childImageError.value,
                true
            )
            InputInformationCard().AddInformationCard("ФИО", textFont = textFont) {
                TextFieldVisible().TextOutlineField(
                    childName.value,
                    textFont,
                    "Введите ФИО",
                    trailingIcon = childNameError.value
                ) { newText ->
                    childName.value = newText
                    childNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                "Дополнительная информация",
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    childAdditionalInfo.value,
                    textFont,
                    "Доплнительная информация...."
                ) { newText -> childAdditionalInfo.value = newText }
            }
            InputInformationCard().AddInformationCard("Дата рождения", textFont = textFont) {
                Text(childHB.value)
                Button(onClick = { openWindowGetDate.value = true }) {
                    Text("Выбрать дату")
                }
                if (openWindowGetDate.value) {
                    GetDate().DatePickerExample(openWindowGetDate) { newDate ->
                        childHB.value = newDate
                    }
                }
            }
            InputInformationCard().AddInformationCard("Финансовая информация", textFont = textFont) {
                ChildAddFinanceInformation().FinanceAddFinanceInformation(
                    textFont,
                    price = childPrice,
                    balance =childBalance,
                    ChildFinanceInfoError = childFinanceInfoError
                )
            }
            InputInformationCard().AddInformationCard("Документы", textFont) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    for (docs in childDocuments) {
                        ChildDocumetsInformation().DocumentsCard(
                            textFont=textFont,
                            docs=docs,
                            isChangeAct=true,
                            openWindowAddDocuments = openWindowAddDocumet,
                            onDocsAdded={document->
                                childDocuments.add(document)
                                Log.d("aejkgbedjghbeghjddd", "AddInformation: ${childDocuments}")
                            },
                            onChange = {document->
                                childDocuments.remove(docs)
                                childDocuments.add(document)
                                Log.d("aejkgbedjghbeghjddd", "AddInformation: ${childDocuments}")
                            }
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .padding(vertical = 10.dp), Arrangement.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(12.dp)
                                .border(2.dp, lightBlue, RoundedCornerShape(50.dp))
                        ) {
                            IconButton(
                                onClick = {
                                    openWindowAddDocumet.value = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Добавить",
                                    tint = lightBlue
                                )
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(12.dp)
                                .border(2.dp, lightBlue, RoundedCornerShape(50.dp))
                        ) {
                            IconButton(
                                onClick = {childDocuments.clear()}
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    "Удалить",
                                    tint = lightBlue
                                )
                            }
                        }
                    }
                }
            }
            InputInformationCard().AddInformationCard("Этапы развития",textFont) {
                ChildAddWorkStageInfo().AddWorkStage(textFont,childWorkStage)
            }
            InputInformationCard().AddInformationCard("Последние посещения", textFont) {
                for(date in childDateComing){
                    ChildComingDatesInformation().ComingDatesInformation(textFont,date,true,openWindowAddDate,
                            onDateAdded={newdate->
                                childDateComing.add(newdate)
                                Log.d("aejkgbedjghbeghjddd", "AddInformation: ${childDocuments}")
                            },
                            onChange = {newdate->
                                childDateComing.remove(date)
                                childDateComing.add(newdate)
                                Log.d("aejkgbedjghbeghjddd", "AddInformation: ${childDocuments}")
                            })
                }
                Row(Modifier.fillMaxWidth()){
                    Button(onClick = {openWindowAddDate.value = true},Modifier.weight(1f)){
                        textFont.WhiteText("Добавить")
                    }
                    Button(onClick = {childDateComing.clear()},Modifier.weight(1f)){
                        textFont.WhiteText("Сбросить всё")
                    }
                }
            }
        }
    }
}