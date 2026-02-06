package com.example.wizkids.data.local.database

import android.content.Context
import androidx.room.Room
import com.example.wizkids.data.local.constant.DataBaseConstants.DATABASE_NAME
import kotlin.jvm.java

object DataBaseProvider {
    private var instance: WizKidsDatabase?=null
    fun getDatabase(context: Context): WizKidsDatabase {
        return instance?:synchronized(this){
            instance?: Room.databaseBuilder(
                context.applicationContext,
                WizKidsDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }
    }
}