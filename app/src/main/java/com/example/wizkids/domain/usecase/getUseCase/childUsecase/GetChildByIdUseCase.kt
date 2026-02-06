package com.example.wizkids.domain.usecase.getUseCase.childUsecase

import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.repository.GetDataRepository

class GetChildByIdUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(id: Int?): Result<DomainChildModel?> = runCatching {
        mapper.mapDataChildModelToDomainChildModel(repository.getChildByIdData(id))
    }
}