package com.example.core_ui.ui.ChangeInformationWindow

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core_ui.constant.SharedUiViewConstant.INFO_CARD_GRAY_AND_WHITE_TEXT_PADDING_END
import com.example.core_ui.ui.TextFont

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