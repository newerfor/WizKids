package com.example.wizkids.presentation.viewModel.visit

import com.example.wizkids.domain.model.DomainVisitModel

sealed interface VisitsUiState {
    data object Loading : VisitsUiState
    data class Success(val visit: List<DomainVisitModel>) : VisitsUiState
    data class Error(val error: String) : VisitsUiState
}
