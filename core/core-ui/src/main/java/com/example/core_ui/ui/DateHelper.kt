package com.example.wizkids.presentation.dateScreeen.ui

import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.DATE_HELPER_DATE_FORMATER
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.DATE_HELPER_DATE_SECOND_FORMATER
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.DATE_HELPER_LANGUAGE
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenLogicConstant.monthNames
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DateHelper {
    fun getMonthNumber(): Int {
        return LocalDate.now().monthValue
    }
    fun getFirstDayOfMonth(month: Int, year: Int): Int {
        val date = LocalDate.of(year, month, 1)
        return when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> 1
            DayOfWeek.TUESDAY -> 2
            DayOfWeek.WEDNESDAY -> 3
            DayOfWeek.THURSDAY -> 4
            DayOfWeek.FRIDAY -> 5
            DayOfWeek.SATURDAY -> 6
            DayOfWeek.SUNDAY -> 7
        }
    }
    fun getYearNumber(): Int {
        return LocalDate.now().year
    }

    fun getDayNumber(): Int {
        return LocalDate.now().dayOfMonth
    }

    fun getDateToday(): String {
        return "${getDayNumber()}.${getMonthNumber()}.${getYearNumber()}"
    }

    fun getMonthName(monthNumber: Int, locale: Locale = Locale(DATE_HELPER_LANGUAGE)): String {
        return monthNames[monthNumber] ?: ""
    }

    fun getNextMonth(currentMonth: Int): Int {
        return if (currentMonth == 12) 1 else currentMonth + 1
    }

    fun getPreviousMonth(currentMonth: Int): Int {
        return if (currentMonth == 1) 12 else currentMonth - 1
    }

    fun GetFullDate(day: Int, mounth: Int, year: Int): String {
        return if (day < 10 && mounth < 10) {
            "0$day.0$mounth.$year"
        } else {
            if (day < 10) {
                "0$day.$mounth.$year"
            } else {
                if (mounth < 10) "$day.0$mounth.$year" else ("$day.$mounth.$year")
            }
        }
    }

    fun getDaysInMonth(month: Int, year: Int): List<Int> {
        val yearMonth = YearMonth.of(year, month)
        val daysInMonth = yearMonth.lengthOfMonth()
        return (1..daysInMonth).toList()
    }

    fun getWeekdayNamesInOrder(startDayIndex: Int): List<String> {
        val weekdays = mutableListOf<String>()
        for (i in 0 until 7) {
            val dayIndex = (startDayIndex + i) % 7
            val dayOfWeek = DayOfWeek.of(if (dayIndex == 0) 7 else dayIndex)
            val weekdayName = dayOfWeek.getDisplayName(
                TextStyle.SHORT_STANDALONE,
                Locale(DATE_HELPER_LANGUAGE)
            ).lowercase(Locale.getDefault())
            weekdays.add(weekdayName)
        }
        return weekdays
    }

    fun getWeekdaysNamesForMonth(month: Int, year: Int): List<String> {
        val yearMonth = YearMonth.of(year, month)
        val firstDayOfMonth = yearMonth.atDay(1)
        val startDayIndex = firstDayOfMonth.dayOfWeek.value

        return getWeekdayNamesInOrder(startDayIndex)
    }

    fun isWeekend(date: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern(DATE_HELPER_DATE_FORMATER)
            val date = LocalDate.parse(date, formatter)
            val dayOfWeek = date.dayOfWeek
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
        } catch (e: Exception) {
            val formatter = DateTimeFormatter.ofPattern(DATE_HELPER_DATE_SECOND_FORMATER)
            val date = LocalDate.parse(date, formatter)
            val dayOfWeek = date.dayOfWeek
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
        }
    }
}