package com.example.wizkids.presentation.viewModel.user

import com.example.wizkids.domain.model.DomainUserModel

sealed interface UserUiState {
    data object Loading : UserUiState
    data object Empty : UserUiState
    data class Success(val user: DomainUserModel) : UserUiState
    data class Error(val error: String) : UserUiState
}
