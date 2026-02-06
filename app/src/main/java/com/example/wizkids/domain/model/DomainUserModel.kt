package com.example.wizkids.domain.model

import com.example.wizkids.data.local.model.DocumentsModel

data class DomainUserModel(
    val imagePath: String,
    val name: String,
    val dateOfBirth: String,
    val about: String,
    val workExperience: String,
    val phone: String,
    val email: String,
    val educationLevel: String,
    val specialization: String,
    val documents: List<DomainDocumentsModel>
)