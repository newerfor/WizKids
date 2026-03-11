package com.example.feature_childinformation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.core_ui.ui.StateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState

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