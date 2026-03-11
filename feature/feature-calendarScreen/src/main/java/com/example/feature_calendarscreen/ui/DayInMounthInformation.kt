package com.example.feature_calendarscreen.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.CreateANewVisit
import com.example.core_ui.ui.DateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.feature_calendarscreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_DAY_IN_MONTH_LAZY_VERTICAL_COLUMN
import com.example.wizkids.ui.theme.blackColor
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.greenColor
import com.example.wizkids.ui.theme.lightBlue

class DayInMounthInformation {
    @Composable
    fun DayInMonth(
        visitInfo: List<DomainVisitModel>,
        monthNumber: MutableState<Int>,
        yearNumber: MutableState<Int>,
        textFont: TextFont,
        visitViewModel: VisitViewModel,
        launchedTriger: MutableState<Boolean>,
        allChildUiState: ChildrenUiState,
        context: Context,
        childByIdUiState: ChildByIdUiState,
        childViewModel: ChildViewModel,
    ) {
        val dateHelper = DateHelper()
        val daysInMonth = dateHelper.getDaysInMonth(monthNumber.value, yearNumber.value)
        val firstDayOfMonth = dateHelper.getFirstDayOfMonth(monthNumber.value, yearNumber.value)

        // Создаем список для календаря (42 ячейки)
        val calendarDays = remember(monthNumber.value, yearNumber.value) {
            val days = MutableList(42) { 0 }
            val startIndex = firstDayOfMonth - 1
            for (i in daysInMonth.indices) {
                days[startIndex + i] = daysInMonth[i]
            }
            days
        }

        val weekDays = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

        Column {
            // Заголовки дней недели
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                weekDays.forEach { dayName ->
                    Column(
                        Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {
                        textFont.WhiteText(dayName, textAlign = TextAlign.Center)
                    }
                }
            }

            // Сетка дней
            LazyVerticalGrid(
                columns = GridCells.Fixed(CALENDAR_ACTIVITY_DAY_IN_MONTH_LAZY_VERTICAL_COLUMN),
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(calendarDays) { day ->
                    if (day == 0) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
                    } else {
                        DayCard(
                            textFont, day, monthNumber, yearNumber, visitInfo,
                            visitViewModel = visitViewModel,
                            allChildUiState = allChildUiState,
                            context = context,
                            childByIdUiState = childByIdUiState,
                            childViewModel = childViewModel,
                            launchedTriger
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun DayCard(
        textFont: TextFont,
        dayNumber: Int,
        monthNumber: MutableState<Int>,
        yearNumber: MutableState<Int>,
        visitInfo: List<DomainVisitModel>,
        visitViewModel: VisitViewModel,
        allChildUiState: ChildrenUiState,
        context: Context,
        childByIdUiState: ChildByIdUiState,
        childViewModel: ChildViewModel,
        launchedTriger: MutableState<Boolean>,
    ) {
        val dateHelper = DateHelper()
        val dateToday = dateHelper.getDateToday()
        val fullDate = dateHelper.GetFullDate(dayNumber, monthNumber.value, yearNumber.value)

        var openWindowVisit = remember { mutableStateOf(false) }
        var openWindowAddVisit = remember { mutableStateOf(false) }

        Column(
            Modifier
                .background(DayColorState(fullDate, visitInfo))
                .border(
                    if (fullDate == dateToday) 2.dp else 0.dp,
                    blackColor
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    openWindowVisit.value = true
                }
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                textFont.WhiteText(dayNumber.toString(), fontSize = 12)
            }
        }

        if (openWindowVisit.value) {
            VisitInfo().VisitWindow(
                openWindowVisit,
                fullDate,
                visitInfo,
                childByIdUiState = childByIdUiState,
                textFont = textFont,
                context = context,
                visitViewModel = visitViewModel,
                launchedTriger=launchedTriger,
                openWindowAddVisit = openWindowAddVisit,
                childViewModel = childViewModel
            )
        }

        if (openWindowAddVisit.value) {
            CreateANewVisit().CreateANewVisitWindow(
                openWindowAddVisit,
                visitViewModel = visitViewModel,
                allChildUiState = allChildUiState,
                textFont = textFont,
                context = context,
                fullDate,
                visitInfo.size,
                childViewModel = childViewModel,
                launchedTriger
            )
        }
    }

    @Composable
    fun DayColorState(currentDate: String, dateInfo: List<DomainVisitModel>): Color {
        return if (DateHelper().isWeekend(currentDate)) {
            grayColor
        } else {
            if (dateInfo.any { it.date == currentDate }) {
                greenColor
            } else {
                lightBlue
            }
        }
    }
}