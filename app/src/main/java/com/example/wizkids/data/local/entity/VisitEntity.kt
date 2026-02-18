package com.example.wizkids.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visits")
data class VisitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val date: String,
    val time: String,
    val visitName: String,
    val visitStatus: String,
    val notes: String,
    val payStatus: String,
    val childId: Int,
    val childName: String
)