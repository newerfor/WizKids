package com.example.wizkids.data.local.model

data class ChildModel(
    val id: Int? = null,
    val imagePath: String,
    val name: String,
    val additionalInfo: String,
    val dateOfBirth: String,
    val documents: List<DocumentsModel>,
    val learningStages: List<String>,
    val visitPrice: Int,
    val currentBalance: Int
)