package com.example.wizkids.domain.usecase.getUseCase.visitUsecase

import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.repository.GetDataRepository

class GetVisitsUseCase (
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(visitList: List<String>?):Result<List<DomainVisitModel?>> = runCatching {
        repository.getVisitData(visitList).map { mapper.mapDataVisitModelToDomainVisitModel(it) }
    }
}