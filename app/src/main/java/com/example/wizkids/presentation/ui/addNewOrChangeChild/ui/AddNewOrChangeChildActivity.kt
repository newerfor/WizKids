package com.example.wizkids.presentation.addNewOrChangeChild.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.wizkids.R
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildViewConstant.ADD_NEW_OR_CHANGE_CHILD_MAIN_CONTENT_WEIGHT
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.ui.theme.WizKidsTheme
import com.example.wizkids.util.UtilLogicConstant.ID_KEY
import org.koin.androidx.compose.koinViewModel

class AddNewOrChangeChildActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val defaultTitle = getString(R.string.add_child_activity_title)
        val defaultSubtitle = getString(R.string.add_child_activity_subtitle)
        setContent {
            WizKidsTheme {
                val idString = intent?.getStringExtra(ID_KEY)
                val id = idString?.toIntOrNull()
                var screenTitle = remember { mutableStateOf(defaultTitle) }
                var childNameSubtitle = remember { mutableStateOf(defaultSubtitle) }
                Column(Modifier) {
                    NavHelper().Header(screenTitle.value, childNameSubtitle.value, isBackActivity = true){
                        finish()
                    }
                    Column(
                        Modifier
                            .weight(ADD_NEW_OR_CHANGE_CHILD_MAIN_CONTENT_WEIGHT)
                            .verticalScroll(
                                rememberScrollState()
                            )
                    ) {
                        NewChildScreen(
                            nameSelection = screenTitle,
                            childNameSubtitle = childNameSubtitle,
                            id = id
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun NewChildScreen(
        childViewModel: ChildViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        visitViewModel: VisitViewModel= koinViewModel(),
        nameSelection: MutableState<String>,
        childNameSubtitle: MutableState<String>,
        id: Int?
    ) {
        val context = LocalContext.current
        val childByIdUiState by childViewModel.childByIdState.collectAsState()
        val visitUiState by visitViewModel.visitUiState.collectAsState()
        if (id != null) {
            LaunchedEffect(id) {
                childViewModel.getChildById(id)
                visitViewModel.getVisitByChildId(id)
            }
            ChildStateScreen().ControlState(
                childByIdUiState, textFont, nameSelection, childNameSubtitle, context, id,
                visitUiState = visitUiState, childViewModel, visitViewModel
            )
        } else {
            ChildAddInformation().AddInformation(
                textFont,
                context = context,
                id = id,
                childViewModel = childViewModel,
                visitViewModel = visitViewModel,
            )
        }
    }
}