package com.example.wizkids.domain.model

data class DomainDocumentsModel (
    val id: Int,
    val name: String,                 // название документа
    val description: String,          // Info → description
    val imagePaths: List<String>
)