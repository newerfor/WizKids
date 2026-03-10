package com.example.wizkids.domain.model

data class DomainDocumentsModel(
    val id: Int,
    val name: String,
    val description: String,
    val imagePaths: List<String>
)