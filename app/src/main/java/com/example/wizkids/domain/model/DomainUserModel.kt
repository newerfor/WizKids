package com.example.wizkids.domain.model

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