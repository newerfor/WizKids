package com.example.wizkids.data.local.impl

import com.example.wizkids.data.local.dao.WizKidsDao
import com.example.wizkids.data.local.entity.VisitEntity
import com.example.wizkids.data.local.mapper.GetMapper
import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel

import com.example.wizkids.domain.repository.GetDataRepository

class GetDataRepositoryImpl(
    private val dao: WizKidsDao,
    private val mapper: GetMapper
) : GetDataRepository {
    override suspend fun getChildByIdData(id: Int?): ChildModel? {
        return mapper.mapChildToModel(dao.getChildById(id))
    }

    override suspend fun getChildrenData(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?,
        ): List<ChildModel> {
        return dao.getChildren(
            searchName = searchName,
            minAge = minAge,
            maxAge = maxAge,
            balanceOperator = balanceOperator,
            showOnlyDebt = hasPayStatusDebt
        ).mapNotNull  { child -> mapper.mapChildToModel(child) }
    }

    override suspend fun getVisitData(visitsList: List<String>?): List<VisitModel> {
        return if (visitsList == null) {
            dao.getVisits().mapNotNull  { visit -> mapper.mapVisitToModel(visit) }
        } else {
            val result = mutableListOf<VisitEntity>()
            for (visit in visitsList) {
                dao.getVisitByDate(visit).let { result.addAll(it) }
            }
            return result.mapNotNull  { visit -> mapper.mapVisitToModel(visit) }
        }
    }

    override suspend fun getUserData(): UserModel? {
        return mapper.mapUserToModel(dao.getUser())
    }

    override suspend fun getVisitByChildIdData(id: Int): List<VisitModel> {
        return dao.getVisitByChildId(id).mapNotNull  { visit -> mapper.mapVisitToModel(visit) }
    }

}