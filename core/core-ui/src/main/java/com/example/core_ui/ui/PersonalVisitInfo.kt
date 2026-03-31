package com.example.core_ui.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.R
import com.example.core_ui.constant.SharedUiLogicConstant.COMING_STATUS_COMING
import com.example.core_ui.constant.SharedUiLogicConstant.COMING_STATUS_NOT_COMING
import com.example.core_ui.constant.SharedUiLogicConstant.COMING_STATUS_SOON
import com.example.core_ui.constant.SharedUiLogicConstant.PAY_STATUS_PAYED
import com.example.core_ui.constant.SharedUiLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_COMING
import com.example.core_ui.constant.SharedUiLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_NOT_COMING
import com.example.core_ui.constant.SharedUiLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID
import com.example.core_ui.constant.SharedUiLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID
import com.example.core_ui.constant.SharedUiLogicConstant.PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_SOON
import com.example.core_ui.constant.SharedUiViewConstant.PERSONAL_VISIT_WINDOW_DIALOG_CLIP
import com.example.core_ui.constant.SharedUiViewConstant.PERSONAL_VISIT_WINDOW_DIALOG_PADDING
import com.example.core_ui.constant.SharedUiViewConstant.VISIT_INFORMATION_COLUMN_COLUMN_PADDING
import com.example.core_ui.constant.SharedUiViewConstant.VISIT_INFORMATION_MAIN_COLUMN_PADDING
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardValueGrayAndWhiteText
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.lightGray

@Composable
fun VisitInformation(
    visitInfo: DomainVisitModel,
    textFont: TextFont,
    visitViewModel: VisitViewModel,
    childViewModel: ChildViewModel,
    childByIdUiState: ChildByIdUiState,
    launchedTriger: MutableState<Boolean> = mutableStateOf(false)
) {
    var openWindowPersonalDate = remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(VISIT_INFORMATION_MAIN_COLUMN_PADDING.dp)
            .background(lightGray)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) {
                openWindowPersonalDate.value = true
                childViewModel.getChildById(visitInfo.childId)
            }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(VISIT_INFORMATION_COLUMN_COLUMN_PADDING.dp)
        ) {
            textFont.WhiteText(visitInfo.time)
            textFont.WhiteText("${visitInfo.childName}/${visitInfo.visitStatus}")
        }
    }
    if (openWindowPersonalDate.value) {
        when (childByIdUiState) {
            is ChildByIdUiState.Loading -> {
                RoundLoad()
            }

            is ChildByIdUiState.Error -> {
                ErrorMassage(textFont = textFont) {
                    childViewModel.getChildById(visitInfo.childId)
                }
            }

            is ChildByIdUiState.Success -> {
                PersonalVisitWindow(
                    openWindowPersonalDate,
                    visitInfo,
                    textFont,
                    childViewModel,
                    childByIdUiState.child,
                    visitViewModel,
                    launchedTriger
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
    visitViewModel: VisitViewModel,
    launchedTriger: MutableState<Boolean>
) {
    var price by remember { mutableStateOf(child.visitPrice) }
    var balance by remember { mutableStateOf(child.currentBalance) }
    var visitStatus = remember { mutableStateOf(visitInfo.visitStatus) }
    var payStatus = remember { mutableStateOf(visitInfo.payStatus) }
    var statusVisitExpanded = remember { mutableStateOf(false) }
    var payStatusExpanded = remember { mutableStateOf(false) }
    val payStatusList = listOf(
        PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID,
        PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID
    )
    val statusVisitList = listOf(
        PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_SOON,
        PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_COMING,
        PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_NOT_COMING,
    )
    Dialog(onDismissRequest = { openWindowPersonalVisit.value = false }) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .clip(RoundedCornerShape(PERSONAL_VISIT_WINDOW_DIALOG_CLIP.dp))
                    .background(color = cardBackground)
                    .padding(PERSONAL_VISIT_WINDOW_DIALOG_PADDING.dp)
            ) {
                Column() {
                    Icon(
                        Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            openWindowPersonalVisit.value = false
                        })
                }
                AddInformationCard(
                    stringResource(R.string.information_on_visit_label),
                    textFont
                ) {
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        stringResource(R.string.date_visit_visit_label),
                        visitInfo.date
                    )
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        stringResource(R.string.date_visit_name_label),
                        visitInfo.visitName
                    )
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        stringResource(R.string.date_visit_time_label),
                        visitInfo.time
                    )
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        stringResource(R.string.date_visit_notes_label),
                        visitInfo.notes
                    )
                }
                AddInformationCard(
                    stringResource(R.string.information_on_child_in_visit_label),
                    textFont
                ) {
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        "${stringResource(R.string.first_name_label)}: ",
                        child.name
                    )
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        "${stringResource(R.string.balance)}: ",
                        balance.toString()
                    )
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        "${stringResource(R.string.price_label)}: ",
                        price.toString()
                    )
                }
                AddInformationCard(
                    stringResource(R.string.date_visit_change_status),
                    textFont
                ) {
                    DropMenuField(
                        expanded = statusVisitExpanded,
                        selectedValue = visitStatus,
                        textFont = textFont,
                        optionList = statusVisitList
                    )
                }
                AddInformationCard(
                    stringResource(R.string.date_visit_change_pay_status),
                    textFont
                ) {
                    DropMenuField(
                        expanded = payStatusExpanded,
                        selectedValue = payStatus,
                        textFont = textFont,
                        optionList = payStatusList
                    )
                }
                ButtonVisibleColumn(
                    mapOf(
                        stringResource(R.string.apply_button) to {
                            val updateDateInfo = DomainVisitModel(
                                id = visitInfo.id,
                                date = visitInfo.date,
                                time = visitInfo.time,
                                visitName = visitInfo.visitName,
                                visitStatus = visitStatus.value,
                                notes = visitInfo.notes,
                                payStatus = payStatus.value,
                                childId = visitInfo.childId,
                                childName = visitInfo.childName,
                                price_of_visit = visitInfo.price_of_visit
                            )
                            val dataChildUpdateBalance = DomainChildModel(
                                id = child.id,
                                imagePath = child.imagePath,
                                name = child.name,
                                additionalInfo = child.additionalInfo,
                                dateOfBirth = child.dateOfBirth,
                                documents = child.documents,
                                learningStages = child.learningStages,
                                visitPrice = price,
                                currentBalance = if ((payStatus.value == PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID) && (visitInfo.payStatus != PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_PAID)) {
                                    balance - price
                                } else {
                                    if ((payStatus.value == PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID) && (visitInfo.payStatus != PERSONAL_VISIT_WINDOW_DEFAULT_VALUE_PAY_STATUS_NOT_PAID)) {
                                        balance + price
                                    } else {
                                        balance
                                    }
                                },
                                childDayOfWeekVisit = child.childDayOfWeekVisit,
                                numbers_visits = ValueCheker(
                                    oldVisit =visitInfo,
                                    newVisit =updateDateInfo,
                                    value = child.numbers_visits,
                                ),
                                general_profit =ValueCheker(
                                    oldVisit =visitInfo,
                                    newVisit =updateDateInfo,
                                    value = child.general_profit,
                                    visitPrice = price,
                                )
                            )

                            Log.d("ghjfhgfghfhgfhgfhgf", payStatus.value)
                            childViewModel.saveChild(
                                dataChildUpdateBalance,
                                listOf(updateDateInfo)
                            )
                            launchedTriger.value = true
                            openWindowPersonalVisit.value = false
                        },
                        stringResource(R.string.delete_button) to {
                            if (visitInfo.id != null) {
                                visitViewModel.deleteVisit(visitInfo.id!!)
                            }
                            launchedTriger.value = true
                            openWindowPersonalVisit.value = false
                        },

                        ),
                    textFont
                )
            }
        }
    }
}
fun ValueCheker(
    oldVisit: DomainVisitModel,
    newVisit: DomainVisitModel,
    value: Int,
    visitPrice: Int? =null):Int{
    var newValue = value
    if (newVisit.visitStatus == COMING_STATUS_COMING && newVisit.payStatus == PAY_STATUS_PAYED) {
        if ((oldVisit.visitStatus == COMING_STATUS_NOT_COMING || oldVisit.visitStatus == COMING_STATUS_SOON)) {
            if(visitPrice==null){
                newValue+=1
            }else{
                newValue+=visitPrice
            }
        }
    } else {
        if ((newVisit.visitStatus == COMING_STATUS_NOT_COMING || newVisit.visitStatus == COMING_STATUS_SOON)) {
            if (oldVisit.visitStatus == COMING_STATUS_COMING && oldVisit.payStatus == PAY_STATUS_PAYED) {
                if(visitPrice==null){
                    newValue-=1
                }else{
                    newValue-=visitPrice
                }
            }
        }
    }
    return newValue
}
