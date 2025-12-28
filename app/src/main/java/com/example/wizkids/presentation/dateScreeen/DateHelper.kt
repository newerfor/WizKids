package com.example.wizkids.presentation.dateScreeen

import com.example.wizkids.presentation.dateScreeen.calendarScreen.constant.LogicConstant.monthNames
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateHelper {
    fun getMonthNumber(): Int {
        return LocalDate.now().monthValue
    }
    fun getYearNumber(): Int {
        return LocalDate.now().year
    }
    fun getDayNumber(): Int {
        return LocalDate.now().dayOfMonth
    }
    fun getMonthName(monthNumber:Int,locale: Locale = Locale("ru")): String {
        return monthNames[monthNumber]?:"Неизвестно"
    }
    fun getNextMonth(currentMonth: Int): Int {
        return if (currentMonth == 12) 1 else currentMonth + 1
    }
    fun getPreviousMonth(currentMonth: Int): Int {
        return if (currentMonth == 1) 12 else currentMonth - 1
    }
    fun GetFullDate(day:Int,mounth:Int,year:Int): String {
        return if(day < 10 && mounth < 10) {
            "0$day.0$mounth.$year"
        }else{
            if (day < 10) {
                "0$day.$mounth.$year"
            } else {
                "$day.0$mounth.$year"
            }
        }
    }
    fun getDaysInMonth(month: Int, year: Int): List<Int> {
        val yearMonth = YearMonth.of(year, month)

        // Получаем количество дней в месяце
        val daysInMonth = yearMonth.lengthOfMonth()

        // Создаем список от 1 до количества дней
        return (1..daysInMonth).toList()
    }
    fun isWeekend(date: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val date = LocalDate.parse(date, formatter)
            val dayOfWeek = date.dayOfWeek
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
        }catch (e: Exception) {
            val formatter = DateTimeFormatter.ofPattern("d.MM.yyyy")
            val date = LocalDate.parse(date, formatter)
            val dayOfWeek = date.dayOfWeek
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY
        }
    }
}