package com.example.feature_userprofile.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.core_ui.ui.Footer
import com.example.core_ui.ui.Header
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.user.UserViewModel
import com.example.feature_userprofile.R
import com.example.feature_userprofile.constant.UserProfileViewConstant.USER_PROFILE_MAIN_CONTENT_WEIGHT
import com.example.wizkids.presentation.UserProfile.ui.ControlState
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserMainScreen(onClickGoToAddUserInfo: () -> Unit, navController: NavController) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize()) {
        Header(
            nameSelection = stringResource(R.string.user_profile_title),
            infoSelection = stringResource(R.string.user_profile_subtitle),
            context = context
        )
        Column(Modifier.weight(USER_PROFILE_MAIN_CONTENT_WEIGHT)) {
            UserProfileScreen(context = context, onClickGoToAddUserInfo = onClickGoToAddUserInfo)
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
fun UserProfileScreen(
    userViewModel: UserViewModel = koinViewModel(),
    textFont: TextFont = TextFont(),
    context: Context,
    onClickGoToAddUserInfo: () -> Unit
) {
    val userUiState by userViewModel.userUiState.collectAsState()
    ControlState(userUiState, textFont, context, userViewModel, onClickGoToAddUserInfo)
}