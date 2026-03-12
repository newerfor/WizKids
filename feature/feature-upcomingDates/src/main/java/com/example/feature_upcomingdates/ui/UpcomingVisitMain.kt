package com.example.feature_upcomingdates.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.core_ui.ui.Footer
import com.example.core_ui.ui.GetFullDate
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_ui.ui.VisitControlState
import com.example.core_ui.ui.getDayNumber
import com.example.core_ui.ui.getMonthNumber
import com.example.core_ui.ui.getYearNumber
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_upcomingdates.R
import com.example.feature_upcomingdates.constant.UpcomingDatesViewConstant.UPCOMING_DATES_ACTIVITY_MAIN_CONTAINER_WEIGHT
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpcomingVisitMain(onCLickGoToCalendar: () -> Unit, navController: NavController) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        Header(stringResource(R.string.upcomin_dates_title), context = context)
        Column(Modifier.weight(UPCOMING_DATES_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
            UpcomingVisitsScreen(context = context, onCLickGoToCalendar = onCLickGoToCalendar)
        }
        Column(
            Modifier.fillMaxWidth(),
            Arrangement.Bottom,
            Alignment.CenterHorizontally
        ) {
            Footer(context = context, navController = navController)
        }
    }
}

@Composable
fun UpcomingVisitsScreen(
    visitViewModel: VisitViewModel = koinViewModel(),
    childViewModel: ChildViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    context: Context,
    onCLickGoToCalendar: () -> Unit
) {
    var monthNumber = remember { mutableStateOf(getMonthNumber()) }
    var yearNumber = remember { mutableStateOf(getYearNumber()) }
    var dayNumber = remember { mutableStateOf(getDayNumber()) }
    var today = remember {
        mutableStateOf(
            GetFullDate(
                dayNumber.value,
                monthNumber.value,
                yearNumber.value
            ),
        )
    }
    var tomorrow = remember {
        mutableStateOf(
            GetFullDate(
                dayNumber.value + 1,
                monthNumber.value,
                yearNumber.value
            ),
        )
    }
    var afterTomorrow = remember {
        mutableStateOf(
            GetFullDate(
                dayNumber.value + 2,
                monthNumber.value,
                yearNumber.value
            ),
        )
    }
    LaunchedEffect(Unit) {
        visitViewModel.getVisit(listOf(today.value, tomorrow.value, afterTomorrow.value))
    }
    val visitUiState by visitViewModel.visitUiState.collectAsState()
    val childByIdUiState by childViewModel.childByIdState.collectAsState()
    VisitControlState(visitUiState, textFont, visitViewModel) { visit ->
        UpcomingVisits(
            visit,
            textFont,
            today,
            tomorrow,
            afterTomorrow,
            visitViewModel,
            childByIdUiState,
            context,
            childViewModel = childViewModel,
            onCLickGoToCalendar = onCLickGoToCalendar
        )
    }
}