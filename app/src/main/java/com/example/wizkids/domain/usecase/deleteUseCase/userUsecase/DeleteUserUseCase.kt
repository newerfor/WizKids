package com.example.wizkids.domain.usecase.deleteUseCase.userUsecase

import com.example.wizkids.domain.repository.DeleteDataRepository

class DeleteUserUseCase(
    private val repository: DeleteDataRepository
) {
    suspend operator fun invoke(){
        repository.deleteUserInfo()
    }
}