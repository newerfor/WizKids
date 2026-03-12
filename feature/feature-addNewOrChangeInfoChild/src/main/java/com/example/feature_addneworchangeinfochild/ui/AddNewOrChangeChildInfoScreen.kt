package com.example.feature_addneworchangeinfochild.ui

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
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_addneworchangeinfochild.R
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildViewConstant.ADD_NEW_OR_CHANGE_CHILD_MAIN_CONTENT_WEIGHT
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNewOrChangeChildInfoScreen(
    id: Int? = null,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val defaultTitle = context.getString(R.string.add_child_activity_title)
    val defaultSubtitle = context.getString(R.string.add_child_activity_subtitle)
    var screenTitle = remember { mutableStateOf(defaultTitle) }
    var childNameSubtitle = remember { mutableStateOf(defaultSubtitle) }
    Column(Modifier) {
        Header(
            screenTitle.value,
            childNameSubtitle.value,
            isBackActivity = true,
            context = context
        ) {
            onBack.invoke()
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
                id = id,
                context = context,
                onBack = onBack
            )
        }
    }
}

@Composable
fun NewChildScreen(
    childViewModel: ChildViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    visitViewModel: VisitViewModel = koinViewModel(),
    nameSelection: MutableState<String>,
    childNameSubtitle: MutableState<String>,
    id: Int?,
    context: Context,
    onBack: () -> Unit
) {
    val childByIdUiState by childViewModel.childByIdState.collectAsState()
    val visitUiState by visitViewModel.visitUiState.collectAsState()
    if (id != null) {
        LaunchedEffect(id) {
            childViewModel.getChildById(id)
            visitViewModel.getVisitByChildId(id)
        }
        ControlState(
            childByIdUiState, textFont, nameSelection, childNameSubtitle, context, id,
            visitUiState = visitUiState, childViewModel, visitViewModel, onBack = onBack
        )
    } else {
        AddInformation(
            textFont,
            context = context,
            id = id,
            childViewModel = childViewModel,
            visitViewModel = visitViewModel,
            onBack = onBack
        )
    }
}