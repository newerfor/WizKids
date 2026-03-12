package com.example.core_domain.usecase.getUseCase.childUsecase

import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.repository.GetDataRepository

class GetChildByIdUseCase(
    private val repository: GetDataRepository,
) {
    suspend operator fun invoke(id: Int?): Result<DomainChildModel?> = runCatching {
        repository.getChildByIdData(id)
    }
}