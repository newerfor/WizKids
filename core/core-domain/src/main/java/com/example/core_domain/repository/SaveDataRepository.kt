package com.example.wizkids.domain.repository

import com.example.wizkids.data.local.model.ChildModel
import com.example.wizkids.data.local.model.UserModel
import com.example.wizkids.data.local.model.VisitModel

interface SaveDataRepository {
    suspend fun saveChildData(child: ChildModel, visits: List<VisitModel>)
    suspend fun saveVisitData(visit: VisitModel, childId: Int)
    suspend fun saveUserData(user: UserModel)
}