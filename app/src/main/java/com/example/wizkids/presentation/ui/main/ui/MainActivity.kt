package com.example.wizkids.presentation.main.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.ui.theme.WizKidsTheme
import org.koin.androidx.compose.koinViewModel
import com.example.wizkids.R
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CONTAINER_HORIZONTAL_PADDING
import com.example.wizkids.presentation.viewModel.child.ChildViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        setContent {
            WizKidsTheme {
                val context = LocalContext.current
                Column(Modifier.fillMaxSize()) {
                    NavHelper().Header(stringResource(R.string.main_activity_title),stringResource(R.string.main_activity_subtitle))
                    Column(Modifier.weight(MAIN_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
                        ChildScreen(context = context)
                    }
                    Column(Modifier.fillMaxWidth(),Arrangement.Bottom,Alignment.CenterHorizontally){
                       NavHelper().Footer(context = context)
                    }
                }
            }
        }
    }

    @Composable
    fun ChildScreen(
        childViewModel: ChildViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        context: Context
    ) {
        val childUiState by childViewModel.childUiState.collectAsState()
        Column(
            Modifier.fillMaxWidth().padding(horizontal = MAIN_ACTIVITY_MAIN_CONTAINER_HORIZONTAL_PADDING.dp).verticalScroll(
                ScrollState(1)
            )) {
            MainActFiltersAndSorted().Filters(textFont,childViewModel)
            AllChildSpace().AllChild(textFont, childUiState,context,childViewModel)
        }
    }
}

