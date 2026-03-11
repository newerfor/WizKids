package com.example.feature_calendarscreen.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.ButtonView
import com.example.core_ui.ui.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.core_ui.ui.DateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_util.IntentHelper
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_calendarscreen.R
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_UPCOMING_VISITS

class CalendarCard {
    @Composable
    fun Calendar(
        visitInfo: List<DomainVisitModel>,
        textFont: TextFont,
        allChildUiState: ChildrenUiState,
        childByIdUiState: ChildByIdUiState,
        visitViewModel: VisitViewModel,
        context: Context,
        childViewModel: ChildViewModel,
        launchedTriger: MutableState<Boolean>
    ) {
        var monthNumber = remember { mutableStateOf(DateHelper().getMonthNumber()) }
        var yearNumber = remember { mutableStateOf(DateHelper().getYearNumber()) }
        ChildInformationCardBackGround().InformationCardBackGround {
            MonthAndYearInformation().MonthAndYearCard(textFont, monthNumber, yearNumber)
            DayInMounthInformation().DayInMonth(
                visitInfo, monthNumber, yearNumber, textFont,
                visitViewModel = visitViewModel,
                launchedTriger,
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
