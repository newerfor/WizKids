package com.example.wizkids.presentation.viewModel.child

import com.example.wizkids.domain.model.DomainChildModel

sealed interface ChildByIdUiState {
    data object Loading : ChildByIdUiState
    data object Empty : ChildByIdUiState
    data class Success(val child: DomainChildModel) : ChildByIdUiState
    data class Error(val error: String) : ChildByIdUiState
}
