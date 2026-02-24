package com.example.wizkids.presentation.ui.addNewOrChangeChild.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainChildDayOfWeekVisit
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.FRIDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.MONDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.SATURDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.SUNDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.THURSDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.TUESDAY
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.WEDNESDAY
import com.example.wizkids.presentation.sharedUI.GetDate
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.SelectTimeCard
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.VISIT_INFORMATION_WINDOW_DIALOG_PADDING
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.redColor
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class AddVisitToDayWeek {
    @Composable
    fun VisitOfDayWeek(
        textFont: TextFont,
        openWindowAddVisitToDayWeek: MutableState<Boolean>,
        childDayWeek: MutableState<DomainChildDayOfWeekVisit>,
        childId: Int?,
        childName: String,
        childVisitComing: MutableList<DomainVisitModel>,
        onSave: (List<DomainVisitModel>) -> Unit
    ) {
        val formatter = DateTimeFormatter.ofPattern("HH.mm")

        var openWindowGetFirstDate = remember { mutableStateOf(false) }
        var openWindowGetSecondDate = remember { mutableStateOf(false) }
        var firstDate by remember {
            mutableStateOf(
                childDayWeek.value.firstDate
            )
        }
        var secondDate by remember {
            mutableStateOf(
                childDayWeek.value.secondDate

            )
        }

        var mapOfDays by remember {
            mutableStateOf(
                mutableMapOf(
                    MONDAY to childDayWeek.value.dayOfWeek[MONDAY],
                    TUESDAY to childDayWeek.value.dayOfWeek[TUESDAY],
                    WEDNESDAY to childDayWeek.value.dayOfWeek[WEDNESDAY],
                    THURSDAY to childDayWeek.value.dayOfWeek[THURSDAY],
                    FRIDAY to childDayWeek.value.dayOfWeek[FRIDAY],
                    SATURDAY to childDayWeek.value.dayOfWeek[SATURDAY],
                    SUNDAY to childDayWeek.value.dayOfWeek[SUNDAY]
                )
            )
        }
        var timeOfDayWeek by remember {
            mutableStateOf(
                mutableMapOf(
                    MONDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[MONDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    TUESDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[TUESDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    WEDNESDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[WEDNESDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    THURSDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[THURSDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    FRIDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[FRIDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    SATURDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[SATURDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        ),
                    SUNDAY to mutableStateOf(
                            if (!childDayWeek.value.time.isEmpty()) {
                                try {
                                    LocalTime.parse(childDayWeek.value.time[SATURDAY], formatter)
                                } catch (e: Exception) {
                                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                                }
                            } else {
                                LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                            }
                        )
                    ,
                )
            )
        }
        var hasMapDayError by remember { mutableStateOf(false) }
        var hasFirstDateError by remember { mutableStateOf(false) }
        var hasSecondDateError by remember { mutableStateOf(false) }
        Dialog(onDismissRequest = { openWindowAddVisitToDayWeek.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(COMING_VISITS_INFORMATION_WINDOW_DIALOG_WIDTH)
                    .fillMaxHeight(COMING_VISITS_INFORMATION_WINDOW_DIALOG_HEIGHT)
                    .clip(RoundedCornerShape(COMING_VISITS_INFORMATION_WINDOW_DIALOG_CLIP.dp))
                    .background(color = cardBackground)
                    .padding(VISIT_INFORMATION_WINDOW_DIALOG_PADDING.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.select_day_week_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_VERTICAL.dp
                ) {
                    Row() {
                        if (hasMapDayError) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = stringResource(R.string.error_title),
                                tint = redColor
                            )
                        }
                    }
                    ButtonView().ButtonDayWeek(
                        butonSetting = mapOfDays,
                        textFont = textFont,
                        onClick = { dayKey ->
                            mapOfDays = mapOfDays.toMutableMap().apply {
                                this[dayKey] = if (this[dayKey] == true) false else true
                            }
                            hasMapDayError = false
                        }
                    )
                }
                for (time in timeOfDayWeek) {
                    if (mapOfDays[time.key] == true) {
                        InputInformationCard().AddInformationCard(
                            "${stringResource(R.string.coming_visit_window_time_label)} на ${time.key}",
                            textFont,
                            horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_HORIZONTAL.dp,
                            verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_VERTICAL.dp
                        ) {
                            SelectTimeCard().TimeHelper(time.value, textFont)
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.select_first_date_button),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_VERTICAL.dp
                ) {
                    Row() {
                        textFont.WhiteText(firstDate.ifEmpty { stringResource(R.string.date_not_selected) })
                        if (hasFirstDateError) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = stringResource(R.string.error_title),
                                tint = redColor
                            )
                        }
                    }
                    ButtonView().ButtonVisibleRow(
                        mapOf(
                            stringResource(R.string.select_date_button) to {
                                openWindowGetFirstDate.value = true
                            },
                            stringResource(R.string.reset_button) to {
                                firstDate = ""
                            },

                            ),
                        textFont
                    )
                    if (openWindowGetFirstDate.value) {
                        GetDate().DatePickerExample(
                            openWindowGetFirstDate,
                            textFont
                        ) { newDate ->
                            firstDate = newDate
                            hasFirstDateError = false
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.select_second_date_button),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_VERTICAL.dp
                ) {
                    Row() {
                        textFont.WhiteText(secondDate.ifEmpty { stringResource(R.string.date_not_selected) })
                        if (hasSecondDateError) {
                            Icon(
                                Icons.Default.Warning,
                                contentDescription = stringResource(R.string.error_title),
                                tint = redColor
                            )
                        }
                    }
                    ButtonView().ButtonVisibleRow(
                        mapOf(
                            stringResource(R.string.select_date_button) to {
                                openWindowGetSecondDate.value = true
                            },
                            stringResource(R.string.reset_button) to {
                                secondDate = ""
                            },

                            ),
                        textFont
                    )
                    if (openWindowGetSecondDate.value) {
                        GetDate().DatePickerExample(
                            openWindowGetSecondDate,
                            textFont
                        ) { newDate ->
                            secondDate = newDate
                            hasSecondDateError = false
                        }
                    }
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.add_button) to {
                            hasMapDayError = mapOfDays.none { it.value == true }
                            if (firstDate.isEmpty()) hasFirstDateError =
                                true else hasFirstDateError = false
                            if (secondDate.isEmpty()) hasSecondDateError =
                                true else hasSecondDateError = false
                            if (
                                !hasMapDayError &&
                                !hasFirstDateError &&
                                !hasSecondDateError
                            ) {
                                val timeMap = timeOfDayWeek.mapValues { (_, timeState) ->
                                    timeState.value.format(formatter)
                                }
                                onSave(
                                    getDatesByWeekdaysWithInfo(
                                        startDate = firstDate,
                                        endDate = secondDate,
                                        targetWeekdays = mapOfDays,
                                        time =timeMap ,
                                        childName = childName,
                                        childId = childId,
                                        childVisitComing
                                    )
                                )


                                childDayWeek.value = DomainChildDayOfWeekVisit(
                                    dayOfWeek = mapOfDays,
                                    firstDate = firstDate,
                                    secondDate = secondDate,
                                    time  =timeMap
                                )
                                openWindowAddVisitToDayWeek.value = false

                            }

                        },
                        stringResource(R.string.cancel_button) to {
                            openWindowAddVisitToDayWeek.value = false
                        }
                    ),
                    textFont
                )
            }
        }
    }

    fun getDatesByWeekdaysWithInfo(
        startDate: String,
        endDate: String,
        targetWeekdays: MutableMap<String, Boolean?>,
        time: Map<String, String?>,
        childName: String,
        childId: Int?,
        existingVisits: List<DomainVisitModel> // ⬅️ Добавляем список существующих визитов
    ): List<DomainVisitModel> {
        val weekdayMap = mapOf(
            MONDAY to DayOfWeek.MONDAY,
            TUESDAY to DayOfWeek.TUESDAY,
            WEDNESDAY to DayOfWeek.WEDNESDAY,
            THURSDAY to DayOfWeek.THURSDAY,
            FRIDAY to DayOfWeek.FRIDAY,
            SATURDAY to DayOfWeek.SATURDAY,
            SUNDAY to DayOfWeek.SUNDAY
        )

        val reverseWeekdayMap = weekdayMap.entries.associate { (key, value) -> value to key }

        val targetDays = targetWeekdays
            .filter { it.value == true }
            .mapNotNull { weekdayMap[it.key] }

        if (targetDays.isEmpty()) return emptyList()

        return try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val start = LocalDate.parse(startDate, formatter)
            val end = LocalDate.parse(endDate, formatter)

            val allMatchingDates = mutableListOf<LocalDate>()
            var currentDate = start

            while (!currentDate.isAfter(end)) {
                if (currentDate.dayOfWeek in targetDays) {
                    allMatchingDates.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }
            val existingDateTimeSet = existingVisits.map {
                "${it.date}_${it.time}"
            }.toSet()
            allMatchingDates
                .mapNotNull { date ->
                    val dayOfWeekName = reverseWeekdayMap[date.dayOfWeek] ?: ""
                    val timeForDay = time[dayOfWeekName] ?: "09:00"
                    val dateStr = date.format(formatter)
                    val dateTimeKey = "${dateStr}_${time}"
                    if (dateTimeKey in existingDateTimeSet) {
                        Log.d("AddVisitToDayWeek", "Дубликат пропущен: $dateStr $time")
                        null
                    } else {
                        val dayOfWeekName = reverseWeekdayMap[date.dayOfWeek] ?: ""
                        DomainVisitModel(
                            id = null,
                            date = dateStr,
                            time = timeForDay,
                            visitName = "Посещение в $dayOfWeekName",
                            visitStatus = "Назначено",
                            notes = "",
                            payStatus = "Не оплачено",
                            childId = childId,
                            childName = childName
                        )
                    }
                }
        } catch (e: Exception) {
            emptyList()
        }
    }
}