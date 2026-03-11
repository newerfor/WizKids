package com.example.core_domain.usecase.saveUseCase.childUsecase

import com.example.core_domain.mapper.SaveDomainMapper
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.SaveDataRepository

class SaveChildUseCase(
    private val repository: SaveDataRepository,
    private val mapper: SaveDomainMapper
) {
    suspend operator fun invoke(child: DomainChildModel, visits: List<DomainVisitModel>) {

        repository.saveChildData(
            mapper.mapDomainChildModelToDataChildModel(child),
            visits.map { mapper.mapDomainVisitModelToDataVisitModel(it) })
    }
}