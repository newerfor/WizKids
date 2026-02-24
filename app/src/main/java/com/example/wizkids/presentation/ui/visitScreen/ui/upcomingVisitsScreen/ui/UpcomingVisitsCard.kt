package com.example.wizkids.presentation.dateScreeen.ui.upcomingVisitsScreen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.dateScreeen.ui.PersonalVisitInfo
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_CALENDAR
import com.example.wizkids.util.IntentHelper

class UpcomingVisitsCard {
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
        childViewModel: ChildViewModel
    ) {
        Column(Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            ChildInformationCardBackGround().InformationCardbackGround {
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.day_today),
                    textFont
                ) {
                    for (todayDate in domainVisitInfoList) {
                        if (todayDate.date == today.value) {
                            PersonalVisitInfo().VisitInformation(
                                todayDate, textFont,
                                visitViewModel = visitViewModel,
                                childViewModel = childViewModel,
                                childByIdUiState = childByIdUiState,
                            )
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.day_tomorrow),
                    textFont
                ) {
                    for (tomorrowDate in domainVisitInfoList) {
                        if (tomorrowDate.date == tomorrow.value) {
                            PersonalVisitInfo().VisitInformation(
                                tomorrowDate, textFont,
                                visitViewModel = visitViewModel,
                                childViewModel = childViewModel,
                                childByIdUiState = childByIdUiState,
                            )
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.day_after_tomorrow),
                    textFont
                ) {
                    for (afterTomorrowDate in domainVisitInfoList) {
                        if (afterTomorrowDate.date == afterTomorrow.value) {
                            PersonalVisitInfo().VisitInformation(
                                afterTomorrowDate, textFont,
                                visitViewModel = visitViewModel,
                                childViewModel = childViewModel,
                                childByIdUiState = childByIdUiState,
                            )
                        }
                    }
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.button_go_to_calendar_label) to {
                            IntentHelper().intentStart(KEY_ACTIVITY_CALENDAR, context)
                        },
                    ),
                    textFont
                )
            }
        }

    }
}
