package com.example.wizkids.presentation.dateScreeen.ui.upcomingVisitsScreen

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.wizkids.R
import com.example.wizkids.presentation.dateScreeen.ui.DateHelper
import com.example.wizkids.presentation.dateScreeen.ui.VisitControlState
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.visitScreen.ui.upcomingVisitsScreen.constant.UpcomingDatesViewConstant.UPCOMING_DATES_ACTIVITY_MAIN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.ui.theme.WizKidsTheme
import org.koin.androidx.compose.koinViewModel

class UpcomingVisitsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WizKidsTheme {
                val context = LocalContext.current
                Column(Modifier.fillMaxSize()) {
                    NavHelper().Header(stringResource(R.string.upcomin_dates_title))
                    Column(Modifier.weight(UPCOMING_DATES_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
                        UpcomingVisitsScreen(context = context)
                    }
                    Column(
                        Modifier.fillMaxWidth(),
                        Arrangement.Bottom,
                        Alignment.CenterHorizontally
                    ) {
                        NavHelper().Footer(context = context)
                    }
                }
            }
        }
    }

    @Composable
    fun UpcomingVisitsScreen(
        visitViewModel: VisitViewModel = koinViewModel(),
        childViewModel: ChildViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        context: Context
    ) {
        var monthNumber = remember { mutableStateOf(DateHelper().getMonthNumber()) }
        var yearNumber = remember { mutableStateOf(DateHelper().getYearNumber()) }
        var dayNumber = remember { mutableStateOf(DateHelper().getDayNumber()) }
        var today = remember {
            mutableStateOf(
                DateHelper().GetFullDate(
                    dayNumber.value,
                    monthNumber.value,
                    yearNumber.value
                ),
            )
        }
        var tomorrow = remember {
            mutableStateOf(
                DateHelper().GetFullDate(
                    dayNumber.value + 1,
                    monthNumber.value,
                    yearNumber.value
                ),
            )
        }
        var afterTomorrow = remember {
            mutableStateOf(
                DateHelper().GetFullDate(
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
        VisitControlState().ControlState(visitUiState, textFont, visitViewModel) { visit ->
            UpcomingVisitsCard().UpcomingVisits(
                visit,
                textFont,
                today,
                tomorrow,
                afterTomorrow,
                visitViewModel,
                childByIdUiState,
                context,
                childViewModel = childViewModel,
            )
        }
    }
}