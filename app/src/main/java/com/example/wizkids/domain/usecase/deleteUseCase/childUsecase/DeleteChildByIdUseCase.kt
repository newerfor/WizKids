package com.example.wizkids.domain.usecase.deleteUseCase.childUsecase

import com.example.wizkids.domain.repository.DeleteDataRepository

class DeleteChildByIdUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke(id: Int){
        repository.deleteChildInfoById(id)
    }
}