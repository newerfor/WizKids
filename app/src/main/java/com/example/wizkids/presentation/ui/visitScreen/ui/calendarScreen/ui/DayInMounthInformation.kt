package com.example.wizkids.presentation.dateScreeen.ui.calendarScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.dateScreeen.ui.CreateANewVisit
import com.example.wizkids.presentation.dateScreeen.ui.DateHelper
import com.example.wizkids.presentation.dateScreeen.ui.calendarScreen.constant.VisitScreenViewConstant.CALENDAR_ACTIVITY_DAY_IN_MONTH_LAZY_VERTICAL_COLUMN
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.child.ChildByIdUiState
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.child.ChildrenUiState
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
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
        allChildUiState: ChildrenUiState,
        context: Context,
        childByIdUiState: ChildByIdUiState,
        childViewModel: ChildViewModel,
    ) {
        val dayList = DateHelper().getDaysInMonth(monthNumber.value, yearNumber.value)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            for (dayNaming in DateHelper().getWeekdaysNamesForMonth(
                monthNumber.value,
                yearNumber.value
            )) {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    textFont.WhiteText(dayNaming, textAlign = TextAlign.Center)
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(CALENDAR_ACTIVITY_DAY_IN_MONTH_LAZY_VERTICAL_COLUMN),
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(dayList) { day ->
                DayCard(
                    textFont, day, monthNumber, yearNumber, visitInfo,
                    visitViewModel = visitViewModel,
                    allChildUiState = allChildUiState,
                    context = context,
                    childByIdUiState = childByIdUiState,
                    childViewModel = childViewModel
                )
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
    ) {
        val dateToday = DateHelper().getDateToday()
        var openWindowVisit = remember { mutableStateOf(false) }
        var openWindowAddVisit = remember { mutableStateOf(false) }
        Column(
            Modifier
                .background(
                    DayColorState(
                        DateHelper().GetFullDate(
                            dayNumber,
                            monthNumber.value,
                            yearNumber.value
                        ), visitInfo
                    )
                )
                .border(
                    if ("$dayNumber.${monthNumber.value}.${yearNumber.value}" == dateToday) 5.dp else 0.dp,
                    blackColor
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    openWindowVisit.value = true
                }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp), Arrangement.Center, Alignment.CenterHorizontally
            ) {
                textFont.WhiteText(dayNumber.toString(), fontSize = 12)
            }
        }
        if (openWindowVisit.value) {
            VisitInfo().VisitWindow(
                openWindowVisit,
                DateHelper().GetFullDate(dayNumber, monthNumber.value, yearNumber.value),
                visitInfo,
                childByIdUiState = childByIdUiState,
                textFont = textFont,
                context = context,
                visitViewModel = visitViewModel,
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
                DateHelper().GetFullDate(dayNumber, monthNumber.value, yearNumber.value),
                visitInfo.size,
                childViewModel = childViewModel
            )
        }
    }

    @Composable
    fun DayColorState(currentDate: String, dateInfo: List<DomainVisitModel>): Color {
        return if (DateHelper().isWeekend(currentDate)) {
            grayColor
        } else {
            if (dateInfo.any { dateInfo -> dateInfo.date == currentDate }) {
                greenColor
            } else {
                lightBlue
            }
        }
    }
}