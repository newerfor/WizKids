package com.example.wizkids.presentation.dateScreeen.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.R
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ComingVisitsInformationCard.ChildComingVisitsInformationWindow
import com.example.wizkids.presentation.sharedUI.ChildView
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_ID
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.CREATE_A_NEW_VISIT_DIALOG_CLIP
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.CREATE_A_NEW_VISIT_DIALOG_HEIGHT
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.CREATE_A_NEW_VISIT_DIALOG_WIDTH
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.child.ChildrenUiState
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.ui.theme.cardBackground

class CreateANewVisit {
    @Composable
    fun CreateANewVisitWindow(
        openWindowAddDate: MutableState<Boolean>,
        visitViewModel: VisitViewModel,
        allChildUiState: ChildrenUiState,
        textFont: TextFont,
        context: Context,
        selectVisit: String,
        visitListSize: Int,
        childViewModel: ChildViewModel,
    ) {
        var openSelectChild = remember { mutableStateOf(true) }
        var childId = remember { mutableStateOf(CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_ID) }
        Dialog(onDismissRequest = { openWindowAddDate.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(CREATE_A_NEW_VISIT_DIALOG_WIDTH)
                    .fillMaxHeight(CREATE_A_NEW_VISIT_DIALOG_HEIGHT)
                    .clip(RoundedCornerShape(CREATE_A_NEW_VISIT_DIALOG_CLIP.dp))
                    .background(color = cardBackground)
            ) {
                Column() {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft, contentDescription = "", Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            openWindowAddDate.value = false
                        })
                }
                if (openSelectChild.value) {
                    WindowChildPicker(
                        childId = childId,
                        openSelectChild = openSelectChild,
                        visitViewModel = visitViewModel,
                        allChildUiState = allChildUiState,
                        textFont = textFont,
                        context = context,
                        childViewModel = childViewModel
                    )
                } else {
                    ChildComingVisitsInformationWindow().ComingVisitsInformationWindow(
                        textFont = textFont,
                        openVisitWindow = openWindowAddDate,
                        visit = null,
                        isChangeAct = true,
                        onSave = { newVisit ->
                            visitViewModel.saveVisit(newVisit, childId.value)
                            visitViewModel.getVisit()
                        },
                        inAddIndex = visitListSize,
                        becomeCalendar = true,
                        childId = childId.value,
                        selectVisit
                    )
                }
            }
        }
    }

    @Composable
    fun WindowChildPicker(
        visitViewModel: VisitViewModel,
        childViewModel: ChildViewModel,
        allChildUiState: ChildrenUiState,
        textFont: TextFont,
        context: Context,
        childId: MutableState<Int>,
        openSelectChild: MutableState<Boolean>
    ) {
        LaunchedEffect(Unit) {
            childViewModel.getChildren(
                searchName = null,
                minAge = null,
                maxAge = null,
                balanceOperator = null,
                hasPayStatusDebt = null,
                selectedOptionSort = null,
            )
        }
        when (allChildUiState) {
            is ChildrenUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is ChildrenUiState.Success -> {
                textFont.WhiteText(stringResource(R.string.select_child_label))
                ChildView().ChildScreen(
                    textFont,
                    allChildUiState.child,
                    false,
                    context,
                    childViewModel
                ) {
                    childId.value = it
                    openSelectChild.value = false
                }
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