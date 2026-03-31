package com.example.core_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "child")
data class ChildEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val imagePath: String,
    val name: String,
    val additionalInfo: String,
    val dateOfBirth: String,
    val documents: List<DocumentModel>,
    val learningStages: List<String>,
    val visitPrice: Int,
    val currentBalance: Int,
    val childDayOfWeekVisit: ChildDayOfWeekVisitEntity,
    val numbers_visits:Int,
    val general_profit:Int
)
