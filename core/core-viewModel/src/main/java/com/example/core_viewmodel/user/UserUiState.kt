package com.example.core_viewmodel.user

import com.example.core_domain.model.DomainUserModel

sealed interface UserUiState {
    data object Loading : UserUiState
    data object Empty : UserUiState
    data class Success(val user: DomainUserModel) : UserUiState
    data class Error(val error: String) : UserUiState
}
