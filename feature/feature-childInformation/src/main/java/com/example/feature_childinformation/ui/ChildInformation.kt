package com.example.feature_childinformation.ui

import android.content.Context
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
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_childinformation.R
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.CHILD_INFORMATION_DEFAULT_SUBTITLE
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.CHILD_INFORMATION_DEFAULT_TITLE
import com.example.feature_childinformation.constant.ChildInformationViewConstant.CHILD_INFORMATION_MAIN_CONTAINER_WEIGHT
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChildInformation(id: Int?, onClickBack: () -> Unit, onClickAddInfoChild: () -> Unit) {
    var name = remember { mutableStateOf(CHILD_INFORMATION_DEFAULT_TITLE) }
    var years = remember { mutableStateOf(CHILD_INFORMATION_DEFAULT_SUBTITLE) }
    Column(Modifier) {
        val context = LocalContext.current
        Header(
            nameSelection = name.value,
            infoSelection = "${years.value} ${stringResource(R.string.subtitle_number)}${id}",
            isBackActivity = true,
            context = context
        ) {
            onClickBack.invoke()
        }
        Column(
            Modifier
                .weight(CHILD_INFORMATION_MAIN_CONTAINER_WEIGHT)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            ChildInfoScreen(
                name = name, years = years, id = id, context = context,
                onClickBack = onClickBack,
                onClickAddInfoChild = onClickAddInfoChild
            )
        }
    }
}

@Composable
fun ChildInfoScreen(
    childViewModel: ChildViewModel = koinViewModel(),
    visitViewModel: VisitViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    name: MutableState<String>,
    years: MutableState<String>,
    id: Int?,
    context: Context,
    onClickBack: () -> Unit,
    onClickAddInfoChild: () -> Unit
) {
    val childByIdUiState by childViewModel.childByIdState.collectAsState()
    val visitUiState by visitViewModel.visitUiState.collectAsState()
    if (id != null) {
        LaunchedEffect(Unit) {
            childViewModel.getChildById(id)
            visitViewModel.getVisitByChildId(id)
        }
        ControlState(
            textFont,
            name,
            years,
            context,
            childByIdUiState,
            childViewModel,
            visitUiState,
            visitViewModel,
            id,
            onClickAddInfoChild = onClickAddInfoChild,
            onClickBack = onClickBack
        )
    }
}