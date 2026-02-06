package com.example.wizkids.presentation.sharedUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_COLUMN_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_COLUMN_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_DEFAULT_HORIZONTAL_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_DEFAULT_VERTICAL_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_TEXT_HORIZONTAL_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ADD_INFO_CARD_TEXT_VERTICAL_PADDING
import com.example.wizkids.ui.theme.darkHeader

class InputInformationCard {
    @Composable
    fun AddInformationCard(italyText: String, textFont: TextFont, verticalPadding: Dp = ADD_INFO_CARD_DEFAULT_VERTICAL_PADDING.dp, horizontalPadding: Dp = ADD_INFO_CARD_DEFAULT_HORIZONTAL_PADDING.dp, function: @Composable () -> Unit) {
        Column(Modifier.fillMaxWidth().padding(vertical = verticalPadding, horizontal = horizontalPadding).clip(RoundedCornerShape(ADD_INFO_CARD_COLUMN_CLIP.dp)).background(darkHeader)) {
            Column(Modifier.padding(ADD_INFO_CARD_COLUMN_PADDING.dp)){
                textFont.ItalyText(italyText, Modifier.padding(vertical = ADD_INFO_CARD_TEXT_VERTICAL_PADDING.dp,horizontal=ADD_INFO_CARD_TEXT_HORIZONTAL_PADDING.dp))
                function.invoke()
            }
        }
    }
}