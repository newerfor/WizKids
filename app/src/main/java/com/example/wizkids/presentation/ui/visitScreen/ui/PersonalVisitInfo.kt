package com.example.wizkids.presentation.dateScreeen.ui

import android.R.attr.name
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_COMING
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_NOT_COMING
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_SOON
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.PERSONAL_VISIT_WINDOW_DIALOG_CLIP
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.PERSONAL_VISIT_WINDOW_DIALOG_PADDING
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.VISIT_BUTTON_WEIGHT
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.VISIT_INFORMATION_COLUMN_COLUMN_PADDING
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitViewConstant.VISIT_INFORMATION_MAIN_COLUMN_PADDING
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.R
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardValueGrayAndWhiteText
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID
import com.example.wizkids.presentation.ui.visitScreen.sharedVisitConstant.SharedVisitLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID

class PersonalVisitInfo {
    @Composable
    fun VisitInformation(
        visitInfo: DomainVisitModel,
        textFont: TextFont,
        visitViewModel: VisitViewModel,
        childViewModel: ChildViewModel,
        childByIdUiState: ChildByIdUiState
    ) {
        LaunchedEffect(Unit) {
            childViewModel.getChildById(visitInfo.childId)
        }
        var openWindowPersonalDate = remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth().padding(VISIT_INFORMATION_MAIN_COLUMN_PADDING.dp).background(lightGray).clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
        ){
            openWindowPersonalDate.value = true
        }){
            Column(Modifier.fillMaxWidth().padding(VISIT_INFORMATION_COLUMN_COLUMN_PADDING.dp)){
                textFont.WhiteText(visitInfo.time)
                textFont.WhiteText(visitInfo.visitStatus)
            }
        }
        if(openWindowPersonalDate.value){
            when (childByIdUiState) {
                is ChildByIdUiState.Loading -> {
                    StateHelper.RoundLoad()
                }
                is ChildByIdUiState.Error -> {
                    StateHelper.ErrorMassage(textFont = textFont){
                        childViewModel.getChildById(visitInfo.childId)
                    }
                }
                is ChildByIdUiState.Success -> {
                    PersonalVisitInfo().PersonalVisitWindow(
                        openWindowPersonalDate, visitInfo, textFont,childViewModel,childByIdUiState.child,visitViewModel
                    )
                }
                is ChildByIdUiState.Empty -> {}
            }
        }
    }
    @Composable
    fun PersonalVisitWindow(
        openWindowPersonalVisit: MutableState<Boolean>,
        visitInfo: DomainVisitModel,
        textFont: TextFont,
        childViewModel: ChildViewModel,
        child: DomainChildModel,
        visitViewModel: VisitViewModel
    ) {
        var price by remember { mutableStateOf(child.visitPrice) }
        var balance by remember { mutableStateOf(child.currentBalance) }
        var visitStatus = remember { mutableStateOf(visitInfo.visitStatus) }
        var payStatus = remember { mutableStateOf(visitInfo.payStatus) }
        var dateStatusUiState by remember { mutableStateOf(VisitComingUiStatus.COMING) }
        var statusVisitExpanded = remember { mutableStateOf(false) }
        var payStatusExpanded = remember { mutableStateOf(false) }
        val payStatusList = listOf(PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID,PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID)
        val statusVisitList = listOf(PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_COMING, PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_SOON, PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_NOT_COMING)
        Dialog(onDismissRequest = {openWindowPersonalVisit.value=false}) {
            Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()), Arrangement.Center, Alignment.CenterHorizontally) {
                Column(
                    Modifier
                        .clip(RoundedCornerShape(PERSONAL_VISIT_WINDOW_DIALOG_CLIP.dp))
                        .background(color = cardBackground).padding(PERSONAL_VISIT_WINDOW_DIALOG_PADDING.dp)
                ) {
                    Column(){
                        Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "", Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            openWindowPersonalVisit.value = false
                        })
                    }
                    InputInformationCard().AddInformationCard(stringResource(R.string.information_on_visit_label), textFont) {
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            stringResource(R.string.date_visit_visit_label),
                            visitInfo.date
                        )
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            stringResource(R.string.date_visit_name_label),
                            visitInfo.visitName
                        )
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            stringResource(R.string.date_visit_time_label),
                            visitInfo.time
                        )
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            stringResource(R.string.date_visit_notes_label),
                            visitInfo.notes
                        )
                    }
                    InputInformationCard().AddInformationCard(stringResource(R.string.information_on_child_in_visit_label), textFont) {
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            "${stringResource(R.string.first_name_label)}: ",
                            child.name
                        )
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            "${stringResource(R.string.balance)}: ",
                            balance.toString()
                        )
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            "${stringResource(R.string.price_label)}: ",
                            price.toString()
                        )
                    }
                    InputInformationCard().AddInformationCard(stringResource(R.string.date_visit_change_status), textFont) {
                        TextFieldVisible().DropMenuField(
                            expanded = statusVisitExpanded,
                            selectedValue = visitStatus,
                            textFont = textFont,
                            optionList =statusVisitList
                        )
                    }
                    InputInformationCard().AddInformationCard(stringResource(R.string.date_visit_change_pay_status), textFont) {
                        TextFieldVisible().DropMenuField(
                            expanded = payStatusExpanded,
                            selectedValue = payStatus,
                            textFont = textFont,
                            optionList =payStatusList
                        )
                    }
                    ButtonView().ButtonVisibleColumn(
                        mapOf(
                            stringResource(R.string.apply_button) to {
                                val dataChildUpdateBalance = DomainChildModel(
                                    id = child.id,
                                    imagePath = child.imagePath,
                                    name = child.name,
                                    additionalInfo = child.additionalInfo,
                                    dateOfBirth = child.dateOfBirth,
                                    documents = child.documents,
                                    learningStages = child.learningStages,
                                    visitPrice = price,
                                    currentBalance = if((payStatus.value == PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID) && (visitInfo.payStatus!=PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID)){
                                        balance - price
                                    }else{
                                        if((payStatus.value == PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID) && (visitInfo.payStatus!=PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID)){
                                            balance+price
                                        }else{balance}
                                    }

                                )
                                val updateDateInfo = DomainVisitModel(
                                    id = visitInfo.id,
                                    date =  visitInfo.date,
                                    time =  visitInfo.time,
                                    visitName =  visitInfo.visitName,
                                    visitStatus = visitStatus.value,
                                    notes =  visitInfo.notes,
                                    payStatus = payStatus.value,
                                    childId =  visitInfo.childId
                                )
                                childViewModel.saveChild(dataChildUpdateBalance,listOf(updateDateInfo))
                                openWindowPersonalVisit.value=false
                            },
                            stringResource(R.string.delete_button) to {
                                if(visitInfo.id!=null){
                                    visitViewModel.deleteVisit(visitInfo.id)
                                    visitViewModel.getVisit()
                                }
                                openWindowPersonalVisit.value=false
                            },

                            ),
                        textFont
                    )
                }
            }
        }
    }
}
