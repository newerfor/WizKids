package com.example.core_domain.usecase.deleteUseCase.childUsecase

import com.example.core_domain.repository.DeleteDataRepository

class DeleteChildByIdUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteChildInfoById(id)
    }
}