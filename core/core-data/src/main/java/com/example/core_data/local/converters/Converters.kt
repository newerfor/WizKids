package com.example.core_data.local.converters

import androidx.room.TypeConverter
import com.example.core_data.local.entity.ChildDayOfWeekVisitEntity
import com.example.core_data.local.entity.DocumentModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // ✅ Только data модели
    @TypeConverter
    fun fromDocumentsDataList(list: List<DocumentModel>?): String? =
        if (list == null) null else gson.toJson(list)

    @TypeConverter
    fun toDocumentsDataList(json: String?): List<DocumentModel>? =
        if (json == null) null
        else gson.fromJson(json, object : TypeToken<List<DocumentModel>>() {}.type)

    @TypeConverter
    fun fromChildDayOfWeekVisitEntity(entity: ChildDayOfWeekVisitEntity?): String? =
        if (entity == null) null else gson.toJson(entity)

    @TypeConverter
    fun toChildDayOfWeekVisitEntity(json: String?): ChildDayOfWeekVisitEntity? =
        if (json == null) null
        else gson.fromJson(json, object : TypeToken<ChildDayOfWeekVisitEntity>() {}.type)

    @TypeConverter
    fun fromStringList(list: List<String>?): String? =
        if (list == null) null else gson.toJson(list)

    @TypeConverter
    fun toStringList(json: String?): List<String>? =
        if (json == null) null
        else gson.fromJson(json, object : TypeToken<List<String>>() {}.type)

    @TypeConverter
    fun fromIntList(list: List<Int>?): String? =
        if (list == null) null else gson.toJson(list)

    @TypeConverter
    fun toIntList(json: String?): List<Int>? =
        if (json == null) null
        else gson.fromJson(json, object : TypeToken<List<Int>>() {}.type)

    @TypeConverter
    fun fromStringMap(map: Map<String, String>?): String? =
        if (map == null) null else gson.toJson(map)

    @TypeConverter
    fun toStringMap(json: String?): Map<String, String>? =
        if (json == null) null
        else gson.fromJson(json, object : TypeToken<Map<String, String>>() {}.type)
}