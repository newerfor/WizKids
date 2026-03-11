package com.example.core_domain.usecase.getUseCase.childUsecase

import com.example.core_domain.mapper.GetDomainMapper
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.repository.GetDataRepository

class GetChildByIdUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(id: Int?): Result<DomainChildModel?> = runCatching {
        mapper.mapDataChildModelToDomainChildModel(repository.getChildByIdData(id))
    }
}