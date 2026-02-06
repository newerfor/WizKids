package com.example.wizkids.domain.model

import com.example.wizkids.data.local.model.DocumentsModel

data class DomainChildModel (
    val id: Int? = null,
    val imagePath: String,
    val name: String,
    val additionalInfo: String,
    val dateOfBirth: String,
    val documents: List<DomainDocumentsModel>,
    val learningStages: List<String>,
    val visitPrice: Int,
    val currentBalance: Int
)