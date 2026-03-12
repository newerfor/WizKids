package com.example.feature_addneworchangeinfouser.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.user.UserViewModel
import com.example.feature_addneworchangeinfouser.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNewOrChangeInfoUserScreen(onBack: () -> Unit, onClickUserProfile: () -> Unit) {
    val context = LocalContext.current
    Column(Modifier) {
        Header(
            stringResource(R.string.add_user_activity_title),
            stringResource(R.string.add_user_activity_subtitle),
            isBackActivity = true,
            context = context
        ) {
            onBack.invoke()
        }
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            AddOrChangeUserInformationScreen(onClickUserProfile = onClickUserProfile)
        }
    }
}

@Composable
fun AddOrChangeUserInformationScreen(
    userViewModel: UserViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    onClickUserProfile: () -> Unit
) {
    val userUiState by userViewModel.userUiState.collectAsState()
    ControlState(userUiState, textFont, userViewModel, onClickUserProfile)
}