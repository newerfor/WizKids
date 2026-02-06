package com.example.wizkids.presentation.viewModel.child

import com.example.wizkids.domain.model.DomainChildModel

sealed interface ChildrenUiState {
    data object Loading : ChildrenUiState
    data object Empty : ChildrenUiState
    data class Success(val child: List<DomainChildModel>) : ChildrenUiState
    data class Error(val error: String) : ChildrenUiState
}
