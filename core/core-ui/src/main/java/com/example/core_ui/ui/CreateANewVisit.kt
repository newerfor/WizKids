package com.example.core_ui.ui

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
import com.example.core_ui.R
import com.example.core_ui.constant.SharedUiLogicConstant.CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_ID
import com.example.core_ui.constant.SharedUiLogicConstant.CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_NAME
import com.example.core_ui.constant.SharedUiViewConstant.CREATE_A_NEW_VISIT_DIALOG_CLIP
import com.example.core_ui.constant.SharedUiViewConstant.CREATE_A_NEW_VISIT_DIALOG_HEIGHT
import com.example.core_ui.constant.SharedUiViewConstant.CREATE_A_NEW_VISIT_DIALOG_WIDTH
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ComingVisitsInformationWindow
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.wizkids.ui.theme.cardBackground


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
    launchedTriger: MutableState<Boolean>,
) {
    var openSelectChild = remember { mutableStateOf(true) }
    var childId = remember { mutableStateOf(CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_ID) }
    var childName = remember { mutableStateOf(CREATE_A_NEW_VISIT_DEFAULT_VALUE_CHILD_NAME) }
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
                    childName = childName,
                    openSelectChild = openSelectChild,
                    visitViewModel = visitViewModel,
                    allChildUiState = allChildUiState,
                    textFont = textFont,
                    context = context,
                    childViewModel = childViewModel
                )
            } else {
                ComingVisitsInformationWindow(
                    textFont = textFont,
                    openVisitWindow = openWindowAddDate,
                    visit = null,
                    isChangeAct = true,
                    onSave = { newVisit ->
                        visitViewModel.saveVisit(newVisit, childId.value)
                        launchedTriger
                        openWindowAddDate.value = false
                    },
                    inAddIndex = visitListSize,
                    becomeCalendar = true,
                    childId = childId.value,
                    childName = childName.value,
                    selectVisit,
                    launchedTriger
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
    openSelectChild: MutableState<Boolean>,
    childName: MutableState<String>
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
            RoundLoad()
        }

        is ChildrenUiState.Success -> {
            textFont.WhiteText(stringResource(R.string.select_child_label))
            ChildScreen(
                textFont,
                allChildUiState.child,
                false,
                context,
                childViewModel
            ) {
                childId.value = it ?: 0
                openSelectChild.value = false
            }
        }

        is ChildrenUiState.Error -> {
            ErrorMassage(textFont = textFont) {
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
