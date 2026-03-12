package com.example.wizkids.presentation.UserProfile.ui

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core_ui.ui.ButtonVisibleColumn
import com.example.core_ui.ui.ErrorMassage
import com.example.core_ui.ui.RoundLoad
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.user.UserUiState
import com.example.core_viewmodel.user.UserViewModel
import com.example.feature_userprofile.R


@Composable
fun ControlState(
    userUiState: UserUiState,
    textFont: TextFont,
    context: Context,
    userViewModel: UserViewModel,
    onClickGoToAddUserInfo: () -> Unit
) {
    when (userUiState) {
        is UserUiState.Loading -> {
            RoundLoad()
        }

        is UserUiState.Success -> {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                UserFullInfo(
                    userUiState.user, textFont, context, userViewModel = userViewModel,
                    onClickGoToAddUserInfo = onClickGoToAddUserInfo
                )
            }
        }

        is UserUiState.Error -> {
            ErrorMassage(textFont = textFont) {
                userViewModel.getUser()
            }
        }

        is UserUiState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonVisibleColumn(
                    mapOf(
                        stringResource(R.string.create_profile_button) to {
                            onClickGoToAddUserInfo.invoke()
                        },
                    ), textFont
                )
            }
        }
    }
}
