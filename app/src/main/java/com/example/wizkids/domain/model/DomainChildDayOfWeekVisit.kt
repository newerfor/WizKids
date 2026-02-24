package com.example.wizkids.domain.model

data class DomainChildDayOfWeekVisit (
    val dayOfWeek: Map<String, Boolean?>,
    val firstDate: String,
    val secondDate: String,
    val time: Map<String, String>
)