package com.example.wizkids.presentation.dateScreeen.ui.calendarScreen

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
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.dateScreeen.ui.PersonalVisitInfo
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.ui.sharedUI.ui.IconGoBackView
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
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