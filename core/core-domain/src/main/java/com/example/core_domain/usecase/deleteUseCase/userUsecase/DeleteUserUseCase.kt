package com.example.core_domain.usecase.deleteUseCase.userUsecase

import com.example.core_domain.repository.DeleteDataRepository

class DeleteUserUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke() {
        repository.deleteUserInfo()
    }
}