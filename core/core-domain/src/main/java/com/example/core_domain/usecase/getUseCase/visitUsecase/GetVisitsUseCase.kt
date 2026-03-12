package com.example.core_domain.usecase.getUseCase.visitUsecase

import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.GetDataRepository

class GetVisitsUseCase(
    private val repository: GetDataRepository,
) {
    suspend operator fun invoke(visitList: List<String>?): Result<List<DomainVisitModel?>> =
        runCatching {
            repository.getVisitData(visitList)
        }
}