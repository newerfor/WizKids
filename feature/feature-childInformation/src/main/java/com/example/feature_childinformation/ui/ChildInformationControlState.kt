package com.example.wizkids.presentation.childInformation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitsUiState

class ChildInformationControlState {
    @Composable
    fun ControlState(
        textFont: TextFont,
        name: MutableState<String>,
        years: MutableState<String>,
        context: Context,
        childByIdUiState: ChildByIdUiState,
        childViewModel: ChildViewModel,
        visitUiState: VisitsUiState,
        visitViewModel: VisitViewModel,
        id: Int,
    ) {
        when (childByIdUiState) {
            is ChildByIdUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is ChildByIdUiState.Success -> {
                name.value = childByIdUiState.child.name
                years.value = childByIdUiState.child.dateOfBirth
                ChildFullInfoScreen().ChildFullInfo(
                    childByIdUiState.child,
                    textFont,
                    context,
                    visitUiState,
                    viewModel = childViewModel,
                    visitViewModel
                )
            }

            is ChildByIdUiState.Error -> {
                StateHelper.ErrorMassage(textFont = textFont) {
                    childViewModel.getChildById(id)
                }
            }

            ChildByIdUiState.Empty -> {}
        }
    }
}