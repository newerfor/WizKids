package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFO_CARD_GRAY_AND_WHITE_TEXT_PADDING_END

class ChildInformationCardValueGrayAndWhiteText {
    @Composable
    fun InformationCardValueGrayAndWhiteText(
        textFont: TextFont,
        nameInformation: String,
        valueInformation: String
    ) {
        Row {
            textFont.GrayText(
                nameInformation,
                Modifier.padding(end = INFO_CARD_GRAY_AND_WHITE_TEXT_PADDING_END.dp)
            )
            textFont.WhiteText(valueInformation)
        }
    }
}