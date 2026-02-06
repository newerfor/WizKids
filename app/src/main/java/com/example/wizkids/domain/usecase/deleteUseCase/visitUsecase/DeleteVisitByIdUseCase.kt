package com.example.wizkids.domain.usecase.deleteUseCase.visitUsecase

import com.example.wizkids.domain.repository.DeleteDataRepository

class DeleteVisitByIdUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteVisitInfoById(id)

    }
}