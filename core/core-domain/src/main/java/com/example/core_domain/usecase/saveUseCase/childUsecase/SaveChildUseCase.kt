package com.example.core_domain.usecase.saveUseCase.childUsecase

import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.SaveDataRepository

class SaveChildUseCase(
    private val repository: SaveDataRepository,
) {
    suspend operator fun invoke(child: DomainChildModel, visits: List<DomainVisitModel>) {

        repository.saveChildData(
            child,
            visits
        )
    }
}