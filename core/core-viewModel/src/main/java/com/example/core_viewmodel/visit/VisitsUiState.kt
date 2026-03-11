package com.example.core_viewmodel.visit

import com.example.core_domain.model.DomainVisitModel

sealed interface VisitsUiState {
    data object Loading : VisitsUiState
    data class Success(val visit: List<DomainVisitModel>) : VisitsUiState
    data class Error(val error: String) : VisitsUiState
}
