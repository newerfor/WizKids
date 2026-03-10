package com.example.wizkids.domain.usecase.saveUseCase.visitUsecase

import com.example.wizkids.domain.mapper.SaveDomainMapper
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.repository.SaveDataRepository

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