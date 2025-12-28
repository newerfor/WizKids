package com.example.wizkids.data.local.entity

import com.example.wizkids.data.local.entity.DateInfo
import com.example.wizkids.data.local.model.DocumentsData

data class DataChild(
    val id: Int = 0,
    val foto:String,
    val name:String,
    val Info:String,
    val PayStatus:String,
    val happybithday:String,
    val docs:List<DocumentsData>,
    val workStage:List<String>,
    val DateInfo: List<DateInfo>
)
