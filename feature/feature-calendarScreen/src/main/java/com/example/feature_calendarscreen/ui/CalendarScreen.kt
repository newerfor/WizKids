package com.example.feature_calendarscreen.ui

import android.content.Context
import android.util.Log
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
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_ui.ui.VisitControlState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_calendarscreen.R
import com.example.feature_calendarscreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_MAIN_CONTAINER_WEIGHT
import org.koin.androidx.compose.koinViewModel

@Composable
fun CalendarScreen(onClickUpcomingDates: () -> Unit, navController: NavController) {
    val context = LocalContext.current
    Log.d("TEST","Start CalendarScreen")
    Column(Modifier.fillMaxSize()) {
        Header(
            stringResource(R.string.calendar_screen_title),
            context = context
        )
        Column(Modifier.weight(CALENDAR_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
            CalendarSpace(context = context, onClickUpcomingDates = onClickUpcomingDates)
        }
        Column(
            Modifier.fillMaxWidth(),
            Arrangement.Bottom,
            Alignment.CenterHorizontally
        ) {
            Footer(
                context = context,
                navController = navController
            )
        }
    }
}

@Composable
fun CalendarSpace(
    visitViewModel: VisitViewModel = koinViewModel(),
    childViewModel: ChildViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    context: Context,
    onClickUpcomingDates: () -> Unit
) {
    val allChildUiState by childViewModel.childUiState.collectAsState()
    val childByIdUiState by childViewModel.childByIdState.collectAsState()
    val visitUiState by visitViewModel.visitUiState.collectAsState()
    var launchedTriger = remember { mutableStateOf(true) }
    LaunchedEffect(launchedTriger.value) {
        visitViewModel.getVisit()
        launchedTriger.value = false
    }
    VisitControlState(visitUiState, textFont, visitViewModel) { visit ->
        Calendar(
            visit,
            textFont,
            allChildUiState,
            childByIdUiState,
            visitViewModel,
            context,
            childViewModel,
            launchedTriger,
            onClickUpcomingDates = onClickUpcomingDates
        )
    }
}