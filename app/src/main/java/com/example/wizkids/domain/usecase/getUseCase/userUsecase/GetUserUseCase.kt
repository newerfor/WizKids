package com.example.wizkids.domain.usecase.getUseCase.userUsecase

import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.domain.repository.GetDataRepository

class GetUserUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(): Result<DomainUserModel?> = runCatching {
        mapper.mapDataUserModelToDomainUserModel(repository.getUserData())
    }
}