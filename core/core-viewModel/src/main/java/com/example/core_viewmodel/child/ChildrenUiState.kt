package com.example.core_viewmodel.child

import com.example.core_domain.model.DomainChildModel

sealed interface ChildrenUiState {
    data object Loading : ChildrenUiState
    data object Empty : ChildrenUiState
    data class Success(val child: List<DomainChildModel>) : ChildrenUiState
    data class Error(val error: String) : ChildrenUiState
}
