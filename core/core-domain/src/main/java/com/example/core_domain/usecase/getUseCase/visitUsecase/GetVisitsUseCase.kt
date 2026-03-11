package com.example.core_domain.usecase.getUseCase.visitUsecase

import com.example.core_domain.mapper.GetDomainMapper
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.GetDataRepository

class GetVisitsUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(visitList: List<String>?): Result<List<DomainVisitModel?>> =
        runCatching {
            repository.getVisitData(visitList)
                .map { mapper.mapDataVisitModelToDomainVisitModel(it) }
        }
}