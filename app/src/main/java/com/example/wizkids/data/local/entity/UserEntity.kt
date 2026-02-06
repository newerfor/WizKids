package com.example.wizkids.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wizkids.data.local.model.DocumentsModel

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int = 0,
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