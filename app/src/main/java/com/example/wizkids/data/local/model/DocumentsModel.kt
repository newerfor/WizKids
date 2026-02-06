package com.example.wizkids.data.local.model

data class DocumentsModel(
    val id: Int,
    val name: String,
    val description: String,
    val imagePaths: List<String>
)