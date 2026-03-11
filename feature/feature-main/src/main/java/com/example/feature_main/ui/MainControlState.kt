package com.example.feature_main.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.ChildView
import com.example.core_ui.ui.StateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_util.IntentHelper
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.feature_main.R
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_BOX_CLIP
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_BOX_SIZE
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_ROW_CLIP
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_ROW_PADDING
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_TEXT_PADDING
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_CHILD_INFORMATION
import com.example.wizkids.util.IntentHelper

class MainControlState {
    @Composable
    fun StateController(
        childUiState: ChildrenUiState,
        textFont: TextFont,
        context: Context,
        childViewModel: ChildViewModel
    ) {
        when (childUiState) {
            is ChildrenUiState.Success -> {
                Row(
                    Modifier
                        .clip(
                            RoundedCornerShape(
                                MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_ROW_CLIP
                            )
                        )
                        .background(color = cardBackground)
                ) {
                    Row(
                        Modifier.padding(
                            MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_ROW_PADDING.dp
                        ), Arrangement.Center, Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .size(MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_BOX_SIZE.dp)
                                .clip(
                                    RoundedCornerShape(
                                        MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_BOX_CLIP
                                    )
                                )
                                .background(color = ButtonAndInfoBlue)
                        )
                        textFont.WhiteText(
                            "${stringResource(R.string.children_founded)} ${childUiState.child.size}",
                            modifier = Modifier.padding(start = MAIN_ACTIVITY_MAIN_CHILD_STATE_CONTROLLER_SUCCESS_TEXT_PADDING.dp)
                        )
                    }

                }
                ChildView().ChildScreen(
                    textFont,
                    childUiState.child,
                    true,
                    context,
                    childViewModel
                ) { child ->
                    IntentHelper().intentStartById(
                        KEY_ACTIVITY_CHILD_INFORMATION,
                        context,
                        child.id
                    )
                }
            }

            is ChildrenUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is ChildrenUiState.Error -> {
                StateHelper.ErrorMassage(textFont = textFont) {
                    childViewModel.getChildren(
                        searchName = null,
                        minAge = null,
                        maxAge = null,
                        balanceOperator = null,
                        hasPayStatusDebt = null,
                        selectedOptionSort = null
                    )
                }
            }

            ChildrenUiState.Empty -> {}
        }
    }
}