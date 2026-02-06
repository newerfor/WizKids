package com.example.wizkids.data.local.converters

import androidx.room.TypeConverter
import com.example.wizkids.data.local.model.DocumentsModel
import com.example.wizkids.domain.model.DomainDocumentsModel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromDomainDocumentsDataList(list: List<DomainDocumentsModel>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun toDomainDocumentsDataList(json: String?): List<DomainDocumentsModel>? {
        return if (json == null) {
            null
        } else {
            val type: Type = object : TypeToken<List<DomainDocumentsModel>>() {}.type
            gson.fromJson(json, type)
        }
    }
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun toStringList(json: String?): List<String>? {
        return if (json == null) {
            null
        } else {
            val type: Type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(json, type)
        }
    }
    @TypeConverter
    fun fromDocumentsDataList(list: List<DocumentsModel>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun toDocumentsDataList(json: String?): List<DocumentsModel>? {
        return if (json == null) {
            null
        } else {
            val type: Type = object :
                TypeToken<List<DocumentsModel>>() {}.type
            gson.fromJson(json, type)
        }
    }
    @TypeConverter
    fun fromIntList(list: List<Int>?): String? {
        return if (list == null) null else gson.toJson(list)
    }
    @TypeConverter
    fun toIntList(json: String?): List<Int>? {
        return if (json == null) {
            null
        } else {
            val type: Type = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(json, type)
        }
    }
    @TypeConverter
    fun fromStringMap(map: Map<String, String>?): String? {
        return if (map == null) null else gson.toJson(map)
    }
    @TypeConverter
    fun toStringMap(json: String?): Map<String, String>? {
        return if (json == null) {
            null
        } else {
            val type: Type = object : TypeToken<Map<String, String>>() {}.type
            gson.fromJson(json, type)
        }
    }
}