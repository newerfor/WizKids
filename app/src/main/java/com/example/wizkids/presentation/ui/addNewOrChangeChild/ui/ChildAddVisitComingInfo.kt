package com.example.wizkids.presentation.addNewOrChangeChild.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ComingVisitsInformationCard.ChildComingVisitsInformation
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ComingVisitsInformationCard.ChildComingVisitsInformationWindow
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel

class ChildAddVisitComingInfo {
    @Composable
    fun AddVisitComingInfo(
        textFont: TextFont,
        childVisitComing: MutableList<DomainVisitModel>,
        viewModel: VisitViewModel,
        childId: Int?
    ) {
        val visitsToRemove = remember { mutableListOf<DomainVisitModel>() }
        var openWindowAddVisit = remember { mutableStateOf(false) }
        var VisitInfo = remember { mutableStateOf<DomainVisitModel?>(null) }
        var editingIndex = remember { mutableStateOf<Int?>(null) }
        Column {
            childVisitComing.forEachIndexed { index, visit ->
                ChildComingVisitsInformation().ComingVisitsInformation(
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
                            viewModel.deleteVisit(visit.id)
                            childVisitComing.remove(visit)
                        } else {
                            visitsToRemove.add(visit)
                        }
                    },
                )
            }
            childVisitComing.removeAll(visitsToRemove)
            ButtonView().ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.add_button) to {
                        openWindowAddVisit.value = true
                    },
                    stringResource(R.string.reset_all_button) to {
                        for (visit in childVisitComing) {
                            if (visit.id != null) {
                                viewModel.deleteVisit(visit.id)
                            }
                        }
                        childVisitComing.clear()
                    }
                ),
                textFont
            )
            if (openWindowAddVisit.value) {
                ChildComingVisitsInformationWindow().ComingVisitsInformationWindow(
                    textFont = textFont,
                    openVisitWindow = openWindowAddVisit,
                    visit = VisitInfo,
                    isChangeAct = true,
                    inAddIndex = childVisitComing.size,
                    onSave = { newVisit ->
                        if (VisitInfo.value == null) {
                            childVisitComing.add(newVisit)
                        } else {
                            childVisitComing[editingIndex.value!!] = newVisit
                            VisitInfo.value = null
                        }
                    },
                    becomeCalendar = false,
                    childId = childId,
                    selectedDate = "",
                )
            }
        }
    }
}