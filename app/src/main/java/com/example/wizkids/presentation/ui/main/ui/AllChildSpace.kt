package com.example.wizkids.presentation.main.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wizkids.R
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_ALL_CHILD_COLUMN_HORIZONTAL_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_BUTTON_ADD_NEW_CHILD_BOX_CLIP
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_BUTTON_ADD_NEW_CHILD_BOX_SIZE
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.child.ChildrenUiState
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.whiteColor
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_CHILD_ADD_INFO
import com.example.wizkids.util.IntentHelper

class AllChildSpace {
    @Composable
    fun AllChild(
        textFont: TextFont,
        childUiState: ChildrenUiState,
        context: Context,
        childViewModel: ChildViewModel
    ) {
        Column(Modifier.padding(horizontal = MAIN_ACTIVITY_MAIN_ALL_CHILD_COLUMN_HORIZONTAL_PADDING.dp)) {
            MainControlState().StateController(childUiState, textFont, context, childViewModel)
            ButtonAddNewChild(textFont, context)
        }
    }

    @Composable
    fun ButtonAddNewChild(textFont: TextFont, context: Context) {
        Column(
            Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    IntentHelper().intentStart(KEY_ACTIVITY_CHILD_ADD_INFO, context)
                }, Arrangement.Center, Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .size(MAIN_ACTIVITY_MAIN_BUTTON_ADD_NEW_CHILD_BOX_SIZE.dp)
                    .clip(RoundedCornerShape(MAIN_ACTIVITY_MAIN_BUTTON_ADD_NEW_CHILD_BOX_CLIP))
                    .background(color = ButtonAndInfoBlue), contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_button),
                    tint = whiteColor
                )
            }
            textFont.BlueText(stringResource(R.string.add_button), textAlign = TextAlign.Center)
        }
    }

}