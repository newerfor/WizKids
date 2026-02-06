package com.example.wizkids.presentation.ui.sharedUI.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ICON_GO_BACK_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ICON_GO_BACK_SIZE
import com.example.wizkids.ui.theme.whiteColor

class IconGoBackView {
    @Composable
    fun IconGoBack(onBackClick: () -> Unit) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "",
            Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) { onBackClick.invoke() }
                .padding(ICON_GO_BACK_PADDING.dp)
                .size(ICON_GO_BACK_SIZE.dp),
            tint = whiteColor
        )
    }
}