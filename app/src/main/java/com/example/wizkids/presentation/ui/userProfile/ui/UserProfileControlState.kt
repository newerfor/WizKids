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
import com.example.wizkids.R
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.viewModel.user.UserUiState
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_ADD_USER_INFO
import com.example.wizkids.util.IntentHelper

class UserProfileControlState {
    @Composable
    fun ControlState(
        userUiState: UserUiState,
        textFont: TextFont,
        context: Context,
        userViewModel: UserViewModel,
    ) {
        when (userUiState) {
            is UserUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is UserUiState.Success -> {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    UserFullInfoScreen().UserFullInfo(
                        userUiState.user, textFont, context, userViewModel = userViewModel
                    )
                }
            }

            is UserUiState.Error -> {
                StateHelper.ErrorMassage(textFont = textFont) {
                    userViewModel.getUser()
                }
            }

            is UserUiState.Empty -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonView().ButtonVisibleColumn(
                        mapOf(
                            stringResource(R.string.create_profile_button) to {
                                IntentHelper().intentStart(
                                    KEY_ACTIVITY_ADD_USER_INFO, context
                                )
                            },
                        ), textFont
                    )
                }
            }
        }
    }
}