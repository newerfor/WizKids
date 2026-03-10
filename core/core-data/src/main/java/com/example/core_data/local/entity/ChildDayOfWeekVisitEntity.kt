package com.example.core_data.local.entity

data class ChildDayOfWeekVisitEntity(
    val dayOfWeek: Map<String, Boolean>,
    val firstDate: String,
    val secondDate: String,
    val time: Map<String, String>
)
