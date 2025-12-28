package com.example.wizkids.presentation.utilPresentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.wizkids.presentation.main.constant.MainViewConstant
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.presentation.utilPresentation.TextFont

class Header {
    @Composable
    fun HeadInfo(NameSelection:String,InfoSelection:String="",textstyle: TextFont = TextFont()){
        Row(Modifier.Companion.fillMaxWidth().background(color = darkHeader)) {
            Row(
                Modifier.Companion.weight(MainViewConstant.HeaderWeight)
            ) {
                Column(
                    Modifier.Companion.padding(
                        start = MainViewConstant.HeaderColumnPaddingStart,
                        top = MainViewConstant.HeaderColumnPaddingTop
                    )
                ) {
                    textstyle.ItalyText(text = NameSelection,)
                    textstyle.GrayText(
                        text = InfoSelection, modifier = Modifier.Companion
                            .padding(
                                top = MainViewConstant.HeaderTextPaddingTop,
                                bottom = MainViewConstant.HeaderTextPaddingBottom
                            )
                    )
                }

            }
            if (NameSelection == "Календарь") {
                Row(Modifier.Companion.weight(MainViewConstant.HeaderWeight)) {

                }
            }
        }
    }

}