package com.example.wizkids.domain.usecase.getUseCase.visitUsecase

import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.repository.GetDataRepository

class GetVisitByChildIdUseCase (
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper
) {
    suspend operator fun invoke(id: Int):Result<List<DomainVisitModel?>> = runCatching {
        repository.getVisitByChildIdData(id).map { mapper.mapDataVisitModelToDomainVisitModel(it) }
    }
}