package com.example.core_domain.repository

import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel

interface GetDataRepository {
    suspend fun getChildByIdData(id: Int?): DomainChildModel?
    suspend fun getChildrenData(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?
    ): List<DomainChildModel>

    suspend fun getVisitData(visitsList: List<String>?): List<DomainVisitModel>
    suspend fun getUserData(): DomainUserModel?
    suspend fun getVisitByChildIdData(id: Int): List<DomainVisitModel>

}