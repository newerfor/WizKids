package com.example.core_domain.usecase.saveUseCase.userUsecase

import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.repository.SaveDataRepository

class SaveUserUseCase(
    private val repository: SaveDataRepository,
) {
    suspend operator fun invoke(user: DomainUserModel) {
        repository.saveUserData(user)
    }
}