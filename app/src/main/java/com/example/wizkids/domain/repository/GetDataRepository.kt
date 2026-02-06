package com.example.wizkids.domain.repository

import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel

interface GetDataRepository {
    suspend fun getChildByIdData(id: Int?): ChildModel?
    suspend fun getChildrenData(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?
    ): List<ChildModel>
    suspend fun getVisitData(visitsList: List<String>?): List<VisitModel>
    suspend fun getUserData(): UserModel?
    suspend fun getVisitByChildIdData(id: Int): List<VisitModel>

}