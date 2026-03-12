package com.example.feature_main.ui

import android.content.Context
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
import androidx.navigation.NavController
import com.example.core_ui.ui.Footer
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildViewModel
import com.example.feature_main.R
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CONTAINER_HORIZONTAL_PADDING
import com.example.feature_main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_CONTAINER_WEIGHT
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onClickAddChild: (Int?) -> Unit,
    onClickGoToChildInfo: (Int?) -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        Header(
            stringResource(R.string.main_activity_title),
            stringResource(R.string.main_activity_subtitle),
            context = context
        )
        Column(Modifier.weight(MAIN_ACTIVITY_MAIN_CONTAINER_WEIGHT)) {
            ChildScreen(
                context = context,
                onClickAddChild = onClickAddChild,
                onClickGoToChildInfo = onClickGoToChildInfo
            )
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
fun ChildScreen(
    childViewModel: ChildViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    context: Context,
    onClickAddChild: (Int?) -> Unit,
    onClickGoToChildInfo: (Int?) -> Unit
) {
    val childUiState by childViewModel.childUiState.collectAsState()
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = MAIN_ACTIVITY_MAIN_CONTAINER_HORIZONTAL_PADDING.dp)
            .verticalScroll(
                ScrollState(1)
            )
    ) {
        Filters(textFont, childViewModel)
        AllChild(
            textFont,
            childUiState,
            context,
            childViewModel,
            onClickAddChild,
            onClickGoToChildInfo
        )
    }
}