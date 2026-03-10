package com.example.wizkids.domain.repository

interface DeleteDataRepository {
    suspend fun deleteChildInfoById(childId: Int)
    suspend fun deleteVisitInfoById(visitId: Int)
    suspend fun deleteUserInfo()

}