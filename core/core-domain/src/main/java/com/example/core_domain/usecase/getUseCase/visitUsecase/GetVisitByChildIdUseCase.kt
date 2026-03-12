package com.example.core_domain.usecase.getUseCase.visitUsecase

import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.GetDataRepository

class GetVisitByChildIdUseCase(
    private val repository: GetDataRepository,
) {
    suspend operator fun invoke(id: Int): Result<List<DomainVisitModel?>> = runCatching {
        repository.getVisitByChildIdData(id)
    }
}