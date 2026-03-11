package com.example.feature_addneworchangeinfouser.ui

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.core_ui.ui.StateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.user.UserUiState
import com.example.core_viewmodel.user.UserViewModel

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