package com.example.core_domain.usecase.getUseCase.userUsecase

import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.repository.GetDataRepository

class GetUserUseCase(
    private val repository: GetDataRepository,
) {
    suspend operator fun invoke(): Result<DomainUserModel?> = runCatching {
        repository.getUserData()
    }
}