package com.example.wizkids.domain.usecase.saveUseCase.childUsecase

import com.example.wizkids.domain.mapper.SaveDomainMapper
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.repository.SaveDataRepository

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