package com.example.wizkids.presentation.dateScreeen.ui.calendarScreen

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.dateScreeen.ui.DateHelper
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.child.ChildrenUiState
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_UPCOMING_VISITS
import com.example.wizkids.util.IntentHelper

class CalendarCard {
    @Composable
    fun Calendar(
        visitInfo: List<DomainVisitModel>,
        textFont: TextFont,
        allChildUiState: ChildrenUiState,
        childByIdUiState: ChildByIdUiState,
        visitViewModel: VisitViewModel,
        context: Context,
        childViewModel: ChildViewModel
    ) {
        var monthNumber = remember { mutableStateOf(DateHelper().getMonthNumber()) }
        var yearNumber = remember { mutableStateOf(DateHelper().getYearNumber()) }
        ChildInformationCardBackGround().InformationCardbackGround {
            MonthAndYearInformation().MonthAndYearCard(textFont, monthNumber, yearNumber)
            DayInMounthInformation().DayInMonth(
                visitInfo, monthNumber, yearNumber, textFont,
                visitViewModel = visitViewModel,
                allChildUiState = allChildUiState,
                context = context,
                childByIdUiState,
                childViewModel = childViewModel
            )
            ButtonView().ButtonVisibleRow(
                mapOf(
                    stringResource(R.string.go_to_upcoming_dates) to {
                        IntentHelper().intentStart(KEY_ACTIVITY_UPCOMING_VISITS, context)
                    },
                ),
                textFont
            )
        }
    }
}
