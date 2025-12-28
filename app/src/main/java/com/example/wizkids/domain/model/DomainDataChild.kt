package com.example.wizkids.domain.model

import com.example.wizkids.data.local.entity.DateInfo
import com.example.wizkids.data.local.model.DocumentsData

data class DomainDataChild (
    val id: Int = 0,
    val foto:String,
    val name:String,
    val Info:String,
    val happybithday:String,
    val docs:List<DomainDocumentsData>,
    val workStage:List<String>,
    //val DateInfo: List<DomainDateInfo>,
    val price:Int,
    val balance :Int
)