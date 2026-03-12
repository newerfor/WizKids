package com.example.core_data.local.entity

data class DocumentModel (
    val id: Int,
    val name: String,
    val description: String,
    val imagePaths: List<String>
)