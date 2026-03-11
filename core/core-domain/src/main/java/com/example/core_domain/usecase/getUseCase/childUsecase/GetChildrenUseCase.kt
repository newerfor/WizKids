package com.example.core_domain.usecase.getUseCase.childUsecase

import com.example.core_domain.mapper.GetDomainMapper
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.repository.GetDataRepository

class GetChildrenUseCase(
    private val repository: GetDataRepository,
    private val mapper: GetDomainMapper,
) {
    suspend operator fun invoke(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?
    ): Result<List<DomainChildModel?>> = runCatching {
        repository.getChildrenData(
            searchName = searchName,
            minAge = minAge,
            maxAge = maxAge,
            balanceOperator = balanceOperator,
            hasPayStatusDebt = hasPayStatusDebt
        ).map { mapper.mapDataChildModelToDomainChildModel(it) }
    }
}