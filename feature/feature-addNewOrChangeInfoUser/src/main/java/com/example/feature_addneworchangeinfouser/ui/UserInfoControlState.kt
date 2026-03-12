package com.example.feature_addneworchangeinfouser.ui

import androidx.compose.runtime.Composable
import com.example.core_ui.ui.ErrorMassage
import com.example.core_ui.ui.RoundLoad
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.user.UserUiState
import com.example.core_viewmodel.user.UserViewModel


@Composable
fun ControlState(
    userUiState: UserUiState,
    textFont: TextFont,
    viewModel: UserViewModel,
    onClickUserProfile: () -> Unit
) {
    when (userUiState) {
        is UserUiState.Loading -> {
            RoundLoad()
        }

        is UserUiState.Success -> {
            UserAddInfo(
                userUiState.user,
                textFont,
                viewModel,
                onClickUserProfile = onClickUserProfile
            )

        }

        is UserUiState.Error -> {
            ErrorMassage(textFont = textFont) {
                viewModel.getUser()
            }
        }

        is UserUiState.Empty -> {
            UserAddInfo(null, textFont, viewModel, onClickUserProfile = onClickUserProfile)
        }
    }
}
