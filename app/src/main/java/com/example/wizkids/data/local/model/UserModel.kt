package com.example.wizkids.data.local.model

data class UserModel(
    val imagePath: String,
    val name: String,
    val dateOfBirth: String,
    val about: String,
    val workExperience: String,
    val phone: String,
    val email: String,
    val educationLevel: String,
    val specialization: String,
    val documents: List<DocumentsModel>
)