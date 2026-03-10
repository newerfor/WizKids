package com.example.wizkids.domain.usecase.saveUseCase.userUsecase

import com.example.wizkids.domain.mapper.SaveDomainMapper
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.domain.repository.SaveDataRepository

class SaveUserUseCase(
    private val repository: SaveDataRepository,
    private val mapper: SaveDomainMapper
) {
    suspend operator fun invoke(user: DomainUserModel) {
        repository.saveUserData(mapper.mapDomainUserModelToDataUserModel(user))
    }
}