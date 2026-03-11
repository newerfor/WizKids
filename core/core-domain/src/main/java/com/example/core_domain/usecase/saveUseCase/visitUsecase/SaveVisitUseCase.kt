package com.example.core_domain.usecase.saveUseCase.visitUsecase

import com.example.core_domain.mapper.SaveDomainMapper
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.SaveDataRepository

class SaveVisitUseCase(
    private val repository: SaveDataRepository,
    private val mapper: SaveDomainMapper
) {
    suspend operator fun invoke(visit: DomainVisitModel, childId: Int) {
        repository.saveVisitData(
            mapper.mapDomainVisitModelToDataVisitModel(visit),
            childId
        )
    }
}