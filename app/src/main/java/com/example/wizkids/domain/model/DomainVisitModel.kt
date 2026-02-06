package com.example.wizkids.domain.model

data class DomainVisitModel(
    val id: Int? = null,
    val date: String,
    val time: String,
    val visitName: String,
    val visitStatus: String,
    val notes: String,
    val payStatus: String,
    val childId: Int?
)