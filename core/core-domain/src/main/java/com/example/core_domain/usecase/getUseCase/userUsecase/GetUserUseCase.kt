package com.example.core_domain.usecase.getUseCase.userUsecase

import com.example.core_domain.mapper.GetDomainMapper
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.repository.GetDataRepository

class GetUserUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(): Result<DomainUserModel?> = runCatching {
        mapper.mapDataUserModelToDomainUserModel(repository.getUserData())
    }
}