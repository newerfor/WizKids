package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_CARD_BACK_GROUND_COLUMN
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_CARD_BACK_GROUND_MAIN_CONTAINER_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_CARD_BACK_GROUND_MAIN_CONTAINER_PADDING
import com.example.wizkids.ui.theme.cardBackground

class ChildInformationCardBackGround {
    @Composable
    fun InformationCardbackGround(function: @Composable () -> Unit) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(INFORMATION_CARD_BACK_GROUND_MAIN_CONTAINER_PADDING.dp)
                .clip(RoundedCornerShape(INFORMATION_CARD_BACK_GROUND_MAIN_CONTAINER_CLIP.dp))
                .background(cardBackground)
        ) {
            Column(Modifier.padding(INFORMATION_CARD_BACK_GROUND_COLUMN.dp)) {
                function.invoke()
            }
        }
    }
}