package com.example.wizkids.presentation.childInformation.ui

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
import androidx.compose.ui.res.stringResource
import com.example.wizkids.ui.theme.WizKidsTheme
import com.example.wizkids.presentation.sharedUI.NavHelper
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.childInformation.constant.ChildInformationLogicConstant.CHILD_INFORMATION_DEFAULT_SUBTITLE
import com.example.wizkids.presentation.ui.childInformation.constant.ChildInformationLogicConstant.CHILD_INFORMATION_DEFAULT_TITLE
import com.example.wizkids.util.UtilLogicConstant.ID_KEY
import org.koin.androidx.compose.koinViewModel
import com.example.wizkids.R
import com.example.wizkids.presentation.ui.childInformation.constant.ChildInformationViewConstant.CHILD_INFORMATION_MAIN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel

class ChildInformationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WizKidsTheme {
                var name = remember { mutableStateOf(CHILD_INFORMATION_DEFAULT_TITLE) }
                var years = remember { mutableStateOf(CHILD_INFORMATION_DEFAULT_SUBTITLE) }
                val idString = intent?.getStringExtra(ID_KEY)
                val id = idString?.toIntOrNull()
                Column(Modifier) {
                    NavHelper().Header(
                        nameSelection = name.value,
                        infoSelection = "${years.value} ${stringResource(R.string.subtitle_number)}${id}", isBackActivity =true,
                    ){
                        finish()
                    }
                    Column(Modifier
                        .weight(CHILD_INFORMATION_MAIN_CONTAINER_WEIGHT)
                        .verticalScroll(
                            rememberScrollState()
                        )) {
                        ChildInfoScreen(name = name, years =years, id =id)
                    }
                }
            }
        }
    }
    @Composable
    fun ChildInfoScreen(
        childViewModel: ChildViewModel = koinViewModel(),
        visitViewModel: VisitViewModel= koinViewModel(),
        textFont: TextFont = TextFont(),
        name: MutableState<String>,
        years: MutableState<String>,
        id: Int?
    ) {
        val context= LocalContext.current
        val childByIdUiState by childViewModel.childByIdState.collectAsState()
        val visitUiState by visitViewModel.visitUiState.collectAsState()
        if(id!=null){
            LaunchedEffect(Unit) {
                childViewModel.getChildById(id)
                visitViewModel.getVisitByChildId(id)
            }
            ChildInformationControlState().ControlState(
                textFont,
                name,
                years,
                context,
                childByIdUiState,
                childViewModel,
                visitUiState,
                visitViewModel,
                id
            )
        }
    }
}
