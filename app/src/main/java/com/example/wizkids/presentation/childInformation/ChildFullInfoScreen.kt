package com.example.wizkids.presentation.childInformation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wizkids.domain.model.DomainDataChild
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationCardValueGrayAndWhiteText
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationCardbackGround
import com.example.wizkids.presentation.utilPresentation.InformationCard.ChildInformationImageAndPayStatus
import com.example.wizkids.presentation.utilPresentation.InformationCard.ComingDatesInformationCard.ChildComingDatesInformation
import com.example.wizkids.presentation.utilPresentation.InformationCard.DocumentsInforamtionCard.ChildDocumetsInformation
import com.example.wizkids.presentation.utilPresentation.InputInformationCard
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.util.AgeHelper

class ChildFullInfoScreen {
    @Composable
    fun ChildFullInfo(sampleChild: DomainDataChild, textFont: TextFont = TextFont()) {
        ChildInformationCardbackGround().InformationCardbackGround {
            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                sampleChild.foto,
                price = sampleChild.price,
                balance = sampleChild.balance,
            )
            InputInformationCard().AddInformationCard("Основная информация", textFont) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "ФИО:",sampleChild.name)
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "Дата рождения:",sampleChild.happybithday)
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "Возраст:",AgeHelper().getAgeFromDate(sampleChild.happybithday))
            }
            InputInformationCard().AddInformationCard("Дополнительная информация", textFont) {
                textFont.WhiteText(sampleChild.Info)
            }
            InputInformationCard().AddInformationCard("Финансовая информация", textFont) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "Баланс:",sampleChild.balance.toString())
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "Цена посещения:",sampleChild.price.toString())
            }
            InputInformationCard().AddInformationCard("Документы", textFont) {
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    for (docs in sampleChild.docs) {
                        ChildDocumetsInformation().DocumentsCard(textFont, docs)
                    }
                }
            }
            InputInformationCard().AddInformationCard("Этапы разватия", textFont) {
                sampleChild.workStage.forEachIndexed { index, string ->
                    Row {
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(textFont, "$index.",string)
                    }
                }
            }
            InputInformationCard().AddInformationCard("Последние посещения", textFont) {
                /*for(date in sampleChild.DateInfo){
                    ChildComingDatesInformation().ComingDatesInformation(textFont,date)
                }
                 */
            }
        }
    }
}