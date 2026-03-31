package com.example.feature_addneworchangeinfochild.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainChildDayOfWeekVisit
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.ButtonVisibleRow
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ComingVisitsInformation
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ComingVisitsInformationWindow
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_addneworchangeinfochild.R
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.PAY_STATUS_PAYED
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.VISIT_STATUS_COMING
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.VISIT_STATUS_NOT_COMING
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.VISIT_STATUS_SOON


@Composable
fun AddVisitComingInfo(
    textFont: TextFont,
    childVisitComing: MutableList<DomainVisitModel>,
    viewModel: VisitViewModel,
    childDayWeek: MutableState<DomainChildDayOfWeekVisit>,
    childId: Int?,
    childName: String,
    price: Int?,
    balance: MutableState<Int>?,
    childGeneralProfit: MutableState<Int>,
    childNumbersVisits: MutableState<Int>,
    newPriceVisit: MutableState<Int>,
) {
    Log.d("ChildAddVisitComingInfo", "AddVisitComingInfo: $childVisitComing")
    var openWindowAddVisit = remember { mutableStateOf(false) }
    var openWindowAddVisitToDayWeek = remember { mutableStateOf(false) }
    var VisitInfo = remember { mutableStateOf<DomainVisitModel?>(null) }
    var editingIndex = remember { mutableStateOf<Int?>(null) }
    Column {
        childVisitComing.forEachIndexed { index, visit ->
            ComingVisitsInformation(
                textFont,
                visit,
                true,
                onClick = { changeVisit ->
                    editingIndex.value = index
                    openWindowAddVisit.value = true
                    VisitInfo.value = changeVisit
                },
                onDelete = {
                    if (visit.id != null) {
                        viewModel.deleteVisit(visit.id!!)
                    }
                    childVisitComing.remove(visit)
                },
            )
        }
        Column {
            ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.add_button) to {
                        openWindowAddVisit.value = true
                    },
                    stringResource(R.string.reset_button) to {
                        for (visit in childVisitComing) {
                            if (visit.id != null) {
                                viewModel.deleteVisit(visit.id!!)
                            }
                        }
                        childVisitComing.clear()
                    }
                ),
                textFont
            )
            ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.add_week_day_visit_button) to {
                        openWindowAddVisitToDayWeek.value = true
                    },
                ), textFont
            )
        }
        if (openWindowAddVisitToDayWeek.value) {
            VisitOfDayWeek(
                textFont,
                openWindowAddVisitToDayWeek,
                childDayWeek,
                childId,
                childName,
                childVisitComing,
                newPriceVisit.value
            ) { newVisit ->
                childVisitComing.addAll(newVisit)
            }

        }
        if (openWindowAddVisit.value) {
            ComingVisitsInformationWindow(
                textFont = textFont,
                openVisitWindow = openWindowAddVisit,
                visit = VisitInfo,
                isChangeAct = true,
                onSave = { newVisit ->
                    if (price != null && balance != null && newVisit.payStatus == PAY_STATUS_PAYED && VisitInfo.value?.payStatus != PAY_STATUS_PAYED) {
                        balance.value -= price
                    }
                    if (VisitInfo.value == null) {
                        childVisitComing.add(newVisit)
                    } else {
                        childVisitComing[editingIndex.value!!] = newVisit
                        VisitInfo.value = null
                    }
                    if (newVisit.visitStatus == VISIT_STATUS_COMING && newVisit.payStatus == PAY_STATUS_PAYED) {
                        if ((VisitInfo.value?.visitStatus == VISIT_STATUS_NOT_COMING || VisitInfo.value?.visitStatus == VISIT_STATUS_SOON)) {
                            childGeneralProfit.value += newVisit.price_of_visit
                            childNumbersVisits.value += 1
                        }
                    } else {
                        if ((newVisit.visitStatus == VISIT_STATUS_NOT_COMING || newVisit.visitStatus == VISIT_STATUS_SOON)) {
                            if (VisitInfo.value?.visitStatus == VISIT_STATUS_COMING && VisitInfo.value?.payStatus == PAY_STATUS_PAYED) {
                                childGeneralProfit.value -= newVisit.price_of_visit
                                childNumbersVisits.value -= 1
                            }
                        }
                    }
                },
                inAddIndex = childVisitComing.size,
                becomeCalendar = false,
                childId = childId,
                childName = childName,
                selectedDate = "",
                childVisitPrice = newPriceVisit,
            )
        }
    }
}
