package com.example.core_viewmodel.child

import com.example.core_domain.model.DomainChildModel

sealed interface ChildByIdUiState {
    data object Loading : ChildByIdUiState
    data object Empty : ChildByIdUiState
    data class Success(val child: DomainChildModel) : ChildByIdUiState
    data class Error(val error: String) : ChildByIdUiState
}
