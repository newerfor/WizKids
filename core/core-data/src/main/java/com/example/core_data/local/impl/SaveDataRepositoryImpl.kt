package com.example.core_data.local.impl

import com.example.core_data.local.dao.WizKidsDao
import com.example.core_data.local.entity.ChildEntity
import com.example.core_data.local.mapper.SaveMapper
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_domain.repository.SaveDataRepository

class SaveDataRepositoryImpl(
    private val dao: WizKidsDao, private val mapper: SaveMapper
) : SaveDataRepository {
    override suspend fun saveChildData(
        child: DomainChildModel,
        visits: List<DomainVisitModel>
    ) {
        val generetedId = dao.saveChild(mapper.mapChildModelToEntity(child))
        if (generetedId != null) {
            visits.forEach { visit ->
                saveVisitData(visit, generetedId.toInt())
            }
        }
    }

    override suspend fun saveVisitData(visit: DomainVisitModel, childId: Int) {
        dao.saveVisit(mapper.mapVisitModelToEntity(visit, childId))

    }

    override suspend fun saveUserData(user: DomainUserModel) {
        dao.saveUser(mapper.mapUserModelToEntity(user))
    }
}