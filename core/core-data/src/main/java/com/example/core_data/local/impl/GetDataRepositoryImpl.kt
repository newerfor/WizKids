package com.example.core_data.local.impl

import com.example.core_data.local.dao.WizKidsDao
import com.example.core_data.local.entity.VisitEntity
import com.example.core_data.local.mapper.GetMapper
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.GetDataRepository

class GetDataRepositoryImpl(
    private val dao: WizKidsDao,
    private val mapper: GetMapper
) : GetDataRepository {
    override suspend fun getChildByIdData(id: Int?): DomainChildModel? {
        return mapper.mapChildToModel(dao.getChildById(id))
    }

    override suspend fun getChildrenData(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?,
    ): List<DomainChildModel> {
        return dao.getChildren(
            searchName = searchName,
            minAge = minAge,
            maxAge = maxAge,
            balanceOperator = balanceOperator,
            showOnlyDebt = hasPayStatusDebt
        ).mapNotNull { child -> mapper.mapChildToModel(child) }
    }

    override suspend fun getVisitData(visitsList: List<String>?): List<DomainVisitModel> {
        return if (visitsList == null) {
            dao.getVisits().mapNotNull { visit -> mapper.mapVisitToModel(visit) }
        } else {
            val result = mutableListOf<VisitEntity>()
            for (visit in visitsList) {
                dao.getVisitByDate(visit).let { result.addAll(it) }
            }
            return result.mapNotNull { visit -> mapper.mapVisitToModel(visit) }
        }
    }


    override suspend fun getUserData(): DomainUserModel? {
        return mapper.mapUserToModel(dao.getUser())
    }

    override suspend fun getVisitByChildIdData(id: Int): List<DomainVisitModel> {
        return dao.getVisitByChildId(id).mapNotNull { visit -> mapper.mapVisitToModel(visit) }
    }


}