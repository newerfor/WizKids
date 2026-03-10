package com.example.wizkids.domain.usecase.getUseCase.childUsecase

import com.example.wizkids.domain.mapper.GetDomainMapper
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.repository.GetDataRepository

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