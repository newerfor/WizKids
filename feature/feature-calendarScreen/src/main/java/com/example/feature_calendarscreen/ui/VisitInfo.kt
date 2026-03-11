package com.example.feature_calendarscreen.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.ButtonView
import com.example.core_ui.ui.IconGoBackView
import com.example.core_ui.ui.PersonalVisitInfo
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_calendarscreen.R
import com.example.wizkids.ui.theme.cardBackground

class VisitInfo {
    @Composable
    fun VisitWindow(
        openWindowVisit: MutableState<Boolean>,
        currentVisit: String,
        visitInfo: List<DomainVisitModel>,
        visitViewModel: VisitViewModel,
        childByIdUiState: ChildByIdUiState,
        textFont: TextFont,
        context: Context,
        openWindowAddVisit: MutableState<Boolean>,
        childViewModel: ChildViewModel,
        launchedTriger: MutableState<Boolean>
    ) {
        Dialog(onDismissRequest = { openWindowVisit.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = cardBackground)
            ) {
                Column() {
                    IconGoBackView().IconGoBack() { openWindowVisit.value = false }
                }
                for (visit in visitInfo.sortedBy { it.time }) {
                    if (visit.date == currentVisit) {
                        PersonalVisitInfo().VisitInformation(
                            visit,
                            textFont,
                            childByIdUiState = childByIdUiState,
                            visitViewModel = visitViewModel,
                            childViewModel = childViewModel,
                            launchedTriger = launchedTriger
                        )
                    }
                }
                Column(Modifier.fillMaxSize(), Arrangement.Bottom, Alignment.CenterHorizontally) {
                    ButtonView().ButtonVisibleRow(
                        mapOf(
                            stringResource(R.string.addVisit) to {
                                openWindowAddVisit.value = true
                                openWindowVisit.value = false
                            },
                        ),
                        textFont
                    )
                }
            }
        }
    }

}