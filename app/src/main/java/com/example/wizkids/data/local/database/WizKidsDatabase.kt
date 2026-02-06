package com.example.wizkids.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wizkids.data.local.constant.DataBaseConstants.DATABASE_VERSION
import com.example.wizkids.data.local.converters.Converters
import com.example.wizkids.data.local.dao.WizKidsDao
import com.example.wizkids.data.local.entity.ChildEntity
import com.example.wizkids.data.local.entity.UserEntity
import com.example.wizkids.data.local.entity.VisitEntity

@Database(entities = [ChildEntity::class, VisitEntity::class, UserEntity::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class WizKidsDatabase : RoomDatabase() {
    abstract fun wizKidsDao(): WizKidsDao
}