package com.example.core_domain.usecase.getUseCase.visitUsecase

import com.example.core_domain.mapper.GetDomainMapper
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.GetDataRepository

class GetVisitByChildIdUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(id: Int): Result<List<DomainVisitModel?>> = runCatching {
        repository.getVisitByChildIdData(id).map { mapper.mapDataVisitModelToDomainVisitModel(it) }
    }
}