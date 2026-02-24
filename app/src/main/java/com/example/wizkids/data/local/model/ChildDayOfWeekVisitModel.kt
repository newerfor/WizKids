package com.example.wizkids.data.local.model

data class ChildDayOfWeekVisitModel(
    val dayOfWeek: Map<String, Boolean?>,
    val firstDate: String,
    val secondDate: String,
    val time: Map<String, String>
)
