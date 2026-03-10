package com.example.wizkids.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.R
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_VALUE_SORTED_LIST
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_BOX_SHAPE
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_BOX_SIZE
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SHAPE
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SIZE
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_WIDTH
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_CLIP
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_OPTION_CARD_TEXT_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_COLUMN_OPTION_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_CONTAINER_SPACER_HEIGHT
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_OPTION_SPACER_HEIGHT
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.darkHeader

class MainActSorted {
    @Composable
    fun WindowSorted(
        openWindowSorted: MutableState<Boolean>,
        textFont: TextFont,
        selectedOptions: MutableState<String>,
    ) {
        var optionList by remember {
            mutableStateOf(MAIN_ACTIVITY_VALUE_SORTED_LIST)
        }
        Dialog(onDismissRequest = { openWindowSorted.value = false }) {
            Column(Modifier.background(color = cardBackground)) {
                Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                    textFont.ItalyText(stringResource(R.string.main_activity_sorted_container_label))
                }
                Spacer(Modifier.height(MAIN_ACTIVITY_MAIN_SORTED_CONTAINER_SPACER_HEIGHT.dp))
                Column(Modifier.padding(MAIN_ACTIVITY_MAIN_SORTED_COLUMN_OPTION_PADDING.dp)) {
                    for (option in optionList) {
                        OptionCard(option = option, onChoiseOption = selectedOptions, textFont) {
                            selectedOptions.value = option
                            openWindowSorted.value = false
                        }
                        Spacer(Modifier.height(MAIN_ACTIVITY_MAIN_SORTED_OPTION_SPACER_HEIGHT.dp))
                    }
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.reset_button) to {
                            selectedOptions.value = ""
                            openWindowSorted.value = false
                        },
                        stringResource(R.string.discard_button) to {
                            openWindowSorted.value = false
                        }
                    ), textFont
                )
            }
        }
    }

    @Composable
    fun OptionCard(
        option: String,
        onChoiseOption: MutableState<String>,
        textFont: TextFont,
        OnClick: () -> Unit
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(MAIN_ACTIVITY_MAIN_OPTION_CARD_CLIP))
                .background(
                    color = darkHeader
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    OnClick.invoke()
                }
                .fillMaxWidth()
                .padding(MAIN_ACTIVITY_MAIN_OPTION_CARD_PADDING.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onChoiseOption.value != option) {
                    Box(
                        Modifier
                            .size(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SIZE.dp)
                            .border(
                                width = MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_WIDTH.dp,
                                color = cardBackground,
                                shape = RoundedCornerShape(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SHAPE)
                            )
                            .background(Color.Transparent)
                    )
                } else {
                    Box(
                        Modifier
                            .size(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SIZE.dp)
                            .border(
                                width = MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_WIDTH.dp,
                                color = ButtonAndInfoBlue,
                                shape = RoundedCornerShape(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_SHAPE)
                            )
                            .background(Color.Transparent), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier
                                .size(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_BOX_SIZE.dp)
                                .clip(
                                    RoundedCornerShape(MAIN_ACTIVITY_MAIN_OPTION_CARD_BOX_BOX_SHAPE)
                                )
                                .background(color = ButtonAndInfoBlue)
                        )
                    }
                }

                textFont.WhiteText(
                    option,
                    modifier = Modifier.padding(start = MAIN_ACTIVITY_MAIN_OPTION_CARD_TEXT_PADDING.dp)
                )
            }

        }
    }
}