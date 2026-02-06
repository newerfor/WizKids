package com.example.wizkids.presentation.dateScreeen.ui.calendarScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.wizkids.presentation.dateScreeen.ui.VisitControlState
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_MAIN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.ui.theme.WizKidsTheme
import org.koin.androidx.compose.koinViewModel
import com.example.wizkids.R
class CalendarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WizKidsTheme {
                val context = LocalContext.current
                Column(Modifier.fillMaxSize()) {
                    NavHelper().Header(stringResource(R.string.calendar_screen_title))
                    Column(Modifier.weight(CALENDAR_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
                        CalendarScreen(context = context)
                    }
                    Column(Modifier.fillMaxWidth(),Arrangement.Bottom,Alignment.CenterHorizontally){
                        NavHelper().Footer(context = context)
                    }
                }
            }
        }
    }
    @Composable
    fun CalendarScreen(
        visitViewModel: VisitViewModel = koinViewModel(),
        childViewModel: ChildViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        context: Context
    ) {
        val allChildUiState by childViewModel.childUiState.collectAsState()
        val childByIdUiState by childViewModel.childByIdState.collectAsState()
        val visitUiState by visitViewModel.visitUiState.collectAsState()
        LaunchedEffect(Unit) {
            visitViewModel.getVisit()
        }
        VisitControlState().ControlState(visitUiState,textFont,visitViewModel){ visit->
            CalendarCard().Calendar(visit,textFont,allChildUiState,childByIdUiState,visitViewModel,context,childViewModel)
        }
    }
}
