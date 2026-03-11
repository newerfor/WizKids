package com.example.core_domain.usecase.saveUseCase.userUsecase

import com.example.core_domain.mapper.SaveDomainMapper
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.repository.SaveDataRepository

class SaveUserUseCase(
    private val repository: SaveDataRepository,
    private val mapper: SaveDomainMapper
) {
    suspend operator fun invoke(user: DomainUserModel) {
        repository.saveUserData(mapper.mapDomainUserModelToDataUserModel(user))
    }
}