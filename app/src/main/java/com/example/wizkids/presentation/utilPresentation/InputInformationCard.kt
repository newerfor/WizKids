package com.example.wizkids.presentation.utilPresentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wizkids.ui.theme.darkHeader

class InputInformationCard {
    @Composable
    fun AddInformationCard(ItalyText: String, textFont: TextFont, verticalpadding: Dp = 12.dp, horizontalpadding: Dp = 12.dp, function: @Composable () -> Unit) {
        Column(Modifier.fillMaxWidth().padding(vertical = verticalpadding, horizontal = horizontalpadding).clip(RoundedCornerShape(20.dp)).background(darkHeader)) {
            textFont.ItalyText("$ItalyText")
            function.invoke()
        }
    }
}