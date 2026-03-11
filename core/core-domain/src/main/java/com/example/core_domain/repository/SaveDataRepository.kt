package com.example.core_domain.repository

import com.example.core_data.local.model.ChildModel
import com.example.core_data.local.model.UserModel
import com.example.core_data.local.model.VisitModel

interface SaveDataRepository {
    suspend fun saveChildData(child: ChildModel, visits: List<VisitModel>)
    suspend fun saveVisitData(visit: VisitModel, childId: Int)
    suspend fun saveUserData(user: UserModel)
}