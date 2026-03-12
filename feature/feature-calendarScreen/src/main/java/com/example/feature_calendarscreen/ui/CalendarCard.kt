package com.example.feature_calendarscreen.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.ButtonVisibleRow
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardBackGround
import com.example.core_ui.ui.TextFont
import com.example.core_ui.ui.getMonthNumber
import com.example.core_ui.ui.getYearNumber
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_calendarscreen.R


@Composable
fun Calendar(
    visitInfo: List<DomainVisitModel>,
    textFont: TextFont,
    allChildUiState: ChildrenUiState,
    childByIdUiState: ChildByIdUiState,
    visitViewModel: VisitViewModel,
    context: Context,
    childViewModel: ChildViewModel,
    launchedTriger: MutableState<Boolean>,
    onClickUpcomingDates: () -> Unit
) {
    var monthNumber = remember { mutableStateOf(getMonthNumber()) }
    var yearNumber = remember { mutableStateOf(getYearNumber()) }
    InformationCardBackGround {
        MonthAndYearCard(textFont, monthNumber, yearNumber)
        DayInMonth(
            visitInfo, monthNumber, yearNumber, textFont,
            visitViewModel = visitViewModel,
            launchedTriger,
            allChildUiState = allChildUiState,
            context = context,
            childByIdUiState,
            childViewModel = childViewModel
        )
        ButtonVisibleRow(
            mapOf(
                stringResource(R.string.go_to_upcoming_dates) to {
                    onClickUpcomingDates.invoke()
                },
            ),
            textFont
        )
    }
}

