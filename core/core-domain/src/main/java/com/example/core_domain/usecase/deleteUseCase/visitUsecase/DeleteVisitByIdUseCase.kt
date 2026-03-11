package com.example.core_domain.usecase.deleteUseCase.visitUsecase

import com.example.core_domain.repository.DeleteDataRepository

class DeleteVisitByIdUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteVisitInfoById(id)

    }
}