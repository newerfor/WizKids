package com.example.wizkids.util

import android.content.Context
import android.content.Intent
import com.example.wizkids.util.UtilLogicConstant.ID_KEY

class IntentHelper {
    fun intentStart(NameActivity: String, context: Context) {
        val actClass = Class.forName(NameActivity)
        val intent = Intent(context, actClass)
        context.startActivity(intent)
    }
    fun intentStartById(NameActivity: String, context: Context, id: Int?) {
        val actClass = Class.forName(NameActivity)
        val intent = Intent(context, actClass)
        intent.putExtra(ID_KEY,id.toString())
        context.startActivity(intent)
    }
}