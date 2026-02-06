package com.example.wizkids.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import com.example.wizkids.R
import com.example.wizkids.util.UtilLogicConstant.DATE_PATTERN

class AgeHelper {

    fun getAgeFromDate(dateString: String): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
        val birthDate = LocalDate.parse(dateString, formatter)
        val currentDate = LocalDate.now()
        val childYears = Period.between(birthDate, currentDate).years
        return childYears.getChildAge()

    }

    fun Int.getChildAge():String{
        return when {
            this % 10 == 1 && this % 100 != 11 -> "$this год"
            this % 10 in 2..4 && this % 100 !in 12..14 -> "$this года"
            else -> "$this лет"
        }
    }
}