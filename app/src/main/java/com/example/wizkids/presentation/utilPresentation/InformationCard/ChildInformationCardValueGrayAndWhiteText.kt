package com.example.wizkids.presentation.utilPresentation.InformationCard

import android.R.attr.name
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.example.wizkids.presentation.utilPresentation.TextFont

class ChildInformationCardValueGrayAndWhiteText {
    @Composable
    fun InformationCardValueGrayAndWhiteText(textFont: TextFont,NameInformation: String,ValueInformation: String){
        Row {
            textFont.GrayText("$NameInformation")
            textFont.WhiteText(ValueInformation)
        }
    }
}