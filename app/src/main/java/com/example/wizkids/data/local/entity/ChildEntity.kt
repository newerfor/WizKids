package com.example.wizkids.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wizkids.data.local.model.ChildDayOfWeekVisitModel
import com.example.wizkids.data.local.model.DocumentsModel

@Entity(tableName = "child")
data class ChildEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val imagePath: String,
    val name: String,
    val additionalInfo: String,
    val dateOfBirth: String,
    val documents: List<DocumentsModel>,
    val learningStages: List<String>,
    val visitPrice: Int,
    val currentBalance: Int,
    val childDayOfWeekVisit: ChildDayOfWeekVisitEntity
)
