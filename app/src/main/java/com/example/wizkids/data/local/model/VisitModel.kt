package com.example.wizkids.data.local.model

data class VisitModel(
    val id: Int? = null,
    val date: String,
    val time: String,
    val visitName: String,
    val visitStatus: String,
    val notes: String,
    val payStatus: String,
    val childId: Int?,
    val childName: String
)