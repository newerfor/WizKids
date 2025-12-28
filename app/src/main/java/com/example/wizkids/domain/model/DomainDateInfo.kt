package com.example.wizkids.domain.model

data class DomainDateInfo (
    val dateId:Int,
    val date :String,
    val time:String,
    val name:String,
    val status:String,
    val info: String,
    val childId:String,
    val childName:String
)