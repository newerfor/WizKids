package com.example.wizkids.presentation.addNewOrChangeUserInformation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.viewModel.user.UserUiState
import com.example.wizkids.presentation.viewModel.user.UserViewModel

class UserInfoControlState {
    @Composable
    fun ControlState(
        userUiState: UserUiState,
        textFont: TextFont,
        context: Context,
        viewModel: UserViewModel,
    ) {
        when (userUiState) {
            is UserUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is UserUiState.Success -> {
                UserAddInfoScreen().UserAddInfo(userUiState.user, textFont, viewModel, context)

            }

            is UserUiState.Error -> {
                StateHelper.ErrorMassage(textFont = textFont) {
                    viewModel.getUser()
                }
            }

            is UserUiState.Empty -> {
                UserAddInfoScreen().UserAddInfo(null, textFont, viewModel, context)
            }
        }
    }
}