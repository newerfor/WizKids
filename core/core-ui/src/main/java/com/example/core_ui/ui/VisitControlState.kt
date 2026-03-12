package com.example.core_ui.ui

import androidx.compose.runtime.Composable
import com.example.core_domain.model.DomainVisitModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState

@Composable
fun VisitControlState(
    visitUiState: VisitsUiState,
    textFont: TextFont,
    visitViewModel: VisitViewModel,
    onSuccess: @Composable (List<DomainVisitModel>) -> Unit,

    ) {
    when (visitUiState) {
        is VisitsUiState.Loading -> {
            RoundLoad()
        }

        is VisitsUiState.Success -> {
            onSuccess.invoke(visitUiState.visit)
        }

        is VisitsUiState.Error -> {
            ErrorMassage(textFont = textFont) {
                visitViewModel.getVisit()
            }
        }
    }
}
