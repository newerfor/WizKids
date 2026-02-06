package com.example.wizkids.presentation.dateScreeen.ui

import androidx.compose.runtime.Composable
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.StateHelper
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitsUiState

class VisitControlState {
    @Composable
    fun ControlState(
        visitUiState: VisitsUiState,
        textFont: TextFont,
        visitViewModel: VisitViewModel,
        onSuccess: @Composable (List<DomainVisitModel>) -> Unit,

        ) {
        when (visitUiState) {
            is VisitsUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is VisitsUiState.Success -> {
                onSuccess.invoke(visitUiState.visit)
            }

            is VisitsUiState.Error -> {
                StateHelper.ErrorMassage(textFont = textFont) {
                    visitViewModel.getVisit()
                }
            }
        }
    }
}