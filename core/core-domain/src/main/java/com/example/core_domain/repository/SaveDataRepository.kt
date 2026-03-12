package com.example.core_domain.repository

import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_domain.model.DomainVisitModel

interface SaveDataRepository {
    suspend fun saveChildData(child: DomainChildModel, visits: List<DomainVisitModel>)
    suspend fun saveVisitData(visit: DomainVisitModel, childId: Int)
    suspend fun saveUserData(user: DomainUserModel)
}