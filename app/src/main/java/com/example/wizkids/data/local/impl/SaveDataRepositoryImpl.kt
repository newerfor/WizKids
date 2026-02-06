package com.example.wizkids.data.local.impl

import com.example.wizkids.data.local.dao.WizKidsDao
import com.example.wizkids.data.local.mapper.SaveMapper
import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel
import com.example.wizkids.domain.repository.SaveDataRepository

class SaveDataRepositoryImpl(
    private val dao: WizKidsDao,
    private val mapper: SaveMapper
) : SaveDataRepository {
    override suspend fun saveChildData(
        child: ChildModel,
        visits: List<VisitModel>,
    ) {
        val generetedId = dao.saveChild(mapper.mapChildModelToEntity(child))
        if(generetedId != null){
            visits.forEach { visit ->
                saveVisitData(visit, generetedId.toInt())
            }
        }
    }
    override suspend fun saveVisitData(visit: VisitModel, childId: Int) {
        dao.saveVisit(mapper.mapVisitModelToEntity(visit, childId))

    }
    override suspend fun saveUserData(user: UserModel) {
        dao.saveUser(mapper.mapUserModelToEntity(user))
    }
}