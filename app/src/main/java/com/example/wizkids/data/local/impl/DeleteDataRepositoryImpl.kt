package com.example.wizkids.data.local.impl

import com.example.wizkids.data.local.dao.WizKidsDao
import com.example.wizkids.domain.repository.DeleteDataRepository

class DeleteDataRepositoryImpl(
    private val dao: WizKidsDao,
) : DeleteDataRepository {
    override suspend fun deleteChildInfoById(childId: Int) {
        dao.deleteChildById(childId)
    }

    override suspend fun deleteVisitInfoById(visitId: Int) {
        dao.deleteVisitById(visitId)
    }

    override suspend fun deleteUserInfo() {
        dao.deleteUser()
    }
}