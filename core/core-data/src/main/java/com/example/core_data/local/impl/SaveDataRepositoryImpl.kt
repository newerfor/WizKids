package com.example.core_data.local.impl

import android.util.Log
import com.example.core_data.local.dao.WizKidsDao
import com.example.core_data.local.mapper.SaveMapper
import com.example.core_data.local.model.ChildModel
import com.example.core_data.local.model.UserModel
import com.example.core_data.local.model.VisitModel
import com.example.core_domain.repository.SaveDataRepository

class SaveDataRepositoryImpl(
    private val dao: WizKidsDao,
    private val mapper: SaveMapper
) : SaveDataRepository {
    override suspend fun saveChildData(
        child: ChildModel,
        visits: List<VisitModel>,
    ) {
        val generetedId = dao.saveChild(mapper.mapChildModelToEntity(child))
        if (generetedId != null) {
            visits.forEach { visit ->
                saveVisitData(visit, generetedId.toInt())
            }
        }
    }

    override suspend fun saveVisitData(visit: VisitModel, childId: Int) {
        Log.d("khfgjyghfhjtyfhtuft", "saveVisitData: $visit $childId")
        dao.saveVisit(mapper.mapVisitModelToEntity(visit, childId))

    }

    override suspend fun saveUserData(user: UserModel) {
        dao.saveUser(mapper.mapUserModelToEntity(user))
    }
}