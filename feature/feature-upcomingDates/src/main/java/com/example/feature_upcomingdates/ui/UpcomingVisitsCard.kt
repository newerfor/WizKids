package com.example.feature_upcomingdates.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.AddInformationCard
import com.example.core_ui.ui.ButtonVisibleRow
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardBackGround
import com.example.core_ui.ui.TextFont
import com.example.core_ui.ui.VisitInformation
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_upcomingdates.R


@Composable
fun UpcomingVisits(
    domainVisitInfoList: List<DomainVisitModel>,
    textFont: TextFont,
    today: MutableState<String>,
    tomorrow: MutableState<String>,
    afterTomorrow: MutableState<String>,
    visitViewModel: VisitViewModel,
    childByIdUiState: ChildByIdUiState,
    context: Context,
    childViewModel: ChildViewModel,
    onCLickGoToCalendar: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        InformationCardBackGround {
            AddInformationCard(
                stringResource(R.string.day_today),
                textFont
            ) {
                for (todayDate in domainVisitInfoList) {
                    if (todayDate.date == today.value) {
                        VisitInformation(
                            todayDate, textFont,
                            visitViewModel = visitViewModel,
                            childViewModel = childViewModel,
                            childByIdUiState = childByIdUiState,
                        )
                    }
                }
            }
            AddInformationCard(
                stringResource(R.string.day_tomorrow),
                textFont
            ) {
                for (tomorrowDate in domainVisitInfoList) {
                    if (tomorrowDate.date == tomorrow.value) {
                        VisitInformation(
                            tomorrowDate, textFont,
                            visitViewModel = visitViewModel,
                            childViewModel = childViewModel,
                            childByIdUiState = childByIdUiState,
                        )
                    }
                }
            }
            AddInformationCard(
                stringResource(R.string.day_after_tomorrow),
                textFont
            ) {
                for (afterTomorrowDate in domainVisitInfoList) {
                    if (afterTomorrowDate.date == afterTomorrow.value) {
                        VisitInformation(
                            afterTomorrowDate, textFont,
                            visitViewModel = visitViewModel,
                            childViewModel = childViewModel,
                            childByIdUiState = childByIdUiState,
                        )
                    }
                }
            }
            ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.button_go_to_calendar_label) to {
                        onCLickGoToCalendar.invoke()
                    },
                ),
                textFont
            )
        }
    }

}

