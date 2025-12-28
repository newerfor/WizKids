package com.example.wizkids.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.wizkids.domain.model.DomainDataChild
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.payFalse
import com.example.wizkids.ui.theme.payLater
import com.example.wizkids.ui.theme.payTrue
import com.example.wizkids.util.AgeHelper
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusFalse
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusLater
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusTrue
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildBoxClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildBoxSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildColumnHorizontalPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildRowClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildRowPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.AllChuildTextPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.ButtonAddNewChildBoxClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.ButtonAddNewChildBoxSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnInfoItalyRextSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnInfoPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnInfoWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPaddingVertical
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoSizeWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoTextPaddingHorizontal
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoTextPaddingVertical
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardColumnPayInfoWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardImageBoxClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardImageBoxSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardImageColumnPaddingStart
import com.example.wizkids.presentation.main.constant.MainViewConstant.ChildCardImageTextSize
import com.example.wizkids.ui.theme.lightGray

class AllChildScreen {
    @Composable
    fun AllChuild(textfont:TextFont = TextFont(), allchild: List<DomainDataChild>){
        Column(Modifier.padding(horizontal = AllChuildColumnHorizontalPadding)) {
            Row (Modifier.clip(RoundedCornerShape(AllChuildRowClip)).background(color = cardBackground)){
                Row (Modifier.padding(AllChuildRowPadding),Arrangement.Center,Alignment.CenterVertically){
                    Box(Modifier.size(AllChuildBoxSize).clip(
                        RoundedCornerShape(AllChuildBoxClip)
                    ).background(color = ButtonAndInfoBlue))
                    textfont.WhiteText("Найдено: ${allchild.size}", modifier = Modifier.padding(start = AllChuildTextPadding))
                }

            }
            for(child in allchild){
                ChildCard(child)
            }
            ButtonAddNewChild()
        }
    }
    @Composable
    fun ChildCard(child: DomainDataChild,textfont:TextFont= TextFont()) {
        var PayStatus by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxWidth().padding(vertical = ChildCardColumnPaddingVertical).clip(RoundedCornerShape(ChildCardColumnClip)).background(color = cardBackground)) {
            Row(  verticalAlignment = Alignment.CenterVertically ) {
                Column(Modifier.padding(start = ChildCardImageColumnPaddingStart)) {
                    Box(
                        modifier = Modifier
                            .size(ChildCardImageBoxSize)
                            .clip(RoundedCornerShape(ChildCardImageBoxClip))
                            .background(lightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        textfont.ItalyText("АЯ", fontSize = ChildCardImageTextSize)
                    }
                }
                Column(Modifier.weight(ChildCardColumnInfoWeight).padding(ChildCardColumnInfoPadding)) {
                    textfont.ItalyText(child.name, fontSize = ChildCardColumnInfoItalyRextSize)
                    textfont.GrayText("${AgeHelper().getAgeFromDate(child.happybithday)} • №${child.id}")
                   // textfont.GrayText("Записан: ${child.DateInfo.last().date} ")
                }
                Column( Modifier.weight(ChildCardColumnPayInfoWeight).padding(ChildCardColumnPayInfoPadding).fillMaxWidth(),Arrangement.Center,Alignment.End) {
                    Column(Modifier.fillMaxWidth(ChildCardColumnPayInfoSizeWeight).clip(RoundedCornerShape(ChildCardColumnPayInfoClip)).background(color =
                    if(child.balance==child.price){
                        PayStatus = StatusLater
                        payLater
                    }else{
                        if(child.balance>child.price){
                            PayStatus = StatusTrue
                            payTrue
                        }else{
                            PayStatus = StatusFalse
                            payFalse
                        }
                    }),Arrangement.Center,Alignment.CenterHorizontally) {
                        textfont.WhiteText(PayStatus, modifier = Modifier.padding(horizontal = ChildCardColumnPayInfoTextPaddingHorizontal, vertical = ChildCardColumnPayInfoTextPaddingVertical))
                    }
                }
            }
        }
    }
    @Composable
    fun ButtonAddNewChild(textfont: TextFont=TextFont()){
        Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
            Box (Modifier.size(ButtonAddNewChildBoxSize).clip(RoundedCornerShape(ButtonAddNewChildBoxClip)).background(color = ButtonAndInfoBlue), contentAlignment = Alignment.Center){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить",
                    tint = Color.White
                )
            }
            textfont.BlueText("Добавить", textAlign = TextAlign.Center)
        }
    }

}