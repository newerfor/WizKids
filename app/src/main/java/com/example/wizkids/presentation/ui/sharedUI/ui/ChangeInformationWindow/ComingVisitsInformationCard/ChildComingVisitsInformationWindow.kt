package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ComingVisitsInformationCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.sharedUI.GetDate
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.SelectTimeCard
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_INFO_VISIT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_NAME_VISIT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_PAY_STATUS
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_STATUS_VISIT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_VISIT_DATE_COMING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_VISITS_INFORMATION_WINDOW_STATUS_LIST
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_NAME_TEXT_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_NAME_TEXT_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_STATUS_PICKER_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_STATUS_PICKER_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_WINDOW_DIALOG_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.VISIT_INFORMATION_WINDOW_DIALOG_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.VISIT_LEAVE_ICON_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.VISIT_LEAVE_ICON_SIZE
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.redColor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ChildComingVisitsInformationWindow {
    @Composable
    fun ComingVisitsInformationWindow(
        textFont: TextFont,
        openVisitWindow: MutableState<Boolean>,
        visit: MutableState<DomainVisitModel?>?,
        isChangeAct: Boolean,
        onSave: (DomainVisitModel) -> Unit,
        inAddIndex: Int,
        becomeCalendar: Boolean,
        childId: Int?,
        childName: String,
        selectedDate: String
    ) {
        val formatter = DateTimeFormatter.ofPattern("HH.mm")

        var selectedTime = remember {
            mutableStateOf(
                if (visit?.value?.time?.isNotEmpty() == true) {  // ⬅️ ИСПРАВЛЕНО!
                    try {
                        LocalTime.parse(visit.value!!.time, formatter)
                    } catch (e: Exception) {
                        LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                    }
                } else {
                    LocalTime.now().truncatedTo(ChronoUnit.MINUTES)
                }
            )
        }
        val statusList = COMING_VISITS_INFORMATION_WINDOW_STATUS_LIST
        var openWindowGetVisit = remember { mutableStateOf(false) }
        var expandedVisitStatus = remember { mutableStateOf(false) }
        var expandedPayStatus = remember { mutableStateOf(false) }
        var selectedPayStatus = remember {
            mutableStateOf(
                visit?.value?.payStatus ?: COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_PAY_STATUS
            )
        }
        var payStatusList = listOf(
            stringResource(R.string.pay_status_paid),
            stringResource(R.string.pay_status_not_paid)
        )
        var selectedStatusVisit = remember {
            mutableStateOf(
                visit?.value?.visitStatus
                    ?: COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_STATUS_VISIT
            )
        }
        var nameVisit by remember {
            mutableStateOf(
                visit?.value?.visitName ?: COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_NAME_VISIT
            )
        }

        var infoVisit by remember {
            mutableStateOf(
                visit?.value?.notes ?: COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_INFO_VISIT
            )
        }
        var visitDateComing by remember {
            mutableStateOf(
                if (!becomeCalendar) {
                    visit?.value?.date
                        ?: COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_VISIT_DATE_COMING
                } else {
                    selectedDate
                }
            )
        }
        var hasComingVisitError by remember { mutableStateOf(false) }
        var hasVisitNameError by remember { mutableStateOf(false) }
        val timeString = remember { mutableStateOf("$selectedTime") }
        LaunchedEffect(selectedTime) {
            timeString.value =
                selectedTime.value?.format(DateTimeFormatter.ofPattern("HH:mm")).toString()
        }
        Dialog(onDismissRequest = { openVisitWindow.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(COMING_VISITS_INFORMATION_WINDOW_DIALOG_WIDTH)
                    .fillMaxHeight(COMING_VISITS_INFORMATION_WINDOW_DIALOG_HEIGHT)
                    .clip(RoundedCornerShape(COMING_VISITS_INFORMATION_WINDOW_DIALOG_CLIP.dp))
                    .background(color = cardBackground)
                    .padding(VISIT_INFORMATION_WINDOW_DIALOG_PADDING.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = VISIT_LEAVE_ICON_PADDING_VERTICAL.dp)
                ) {
                    Column(Modifier.background(grayColor)) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "",
                            Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    visit?.value = null
                                    openVisitWindow.value = false
                                }
                                .size(VISIT_LEAVE_ICON_SIZE.dp)
                        )
                    }
                }
                if (!becomeCalendar) {
                    InputInformationCard().AddInformationCard(
                        stringResource(R.string.coming_visit_window_date_label),
                        textFont,
                        horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_HORIZONTAL.dp,
                        verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_DATE_PICKER_PADDING_VERTICAL.dp
                    ) {
                        if (!isChangeAct) {
                            textFont.WhiteText(visitDateComing)
                        } else {
                            Row() {
                                textFont.WhiteText(visitDateComing.ifEmpty { stringResource(R.string.date_not_selected) })
                                if (hasComingVisitError) {
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
                                        openWindowGetVisit.value = true
                                    },
                                    stringResource(R.string.reset_button) to {
                                        visitDateComing = ""
                                    },

                                    ),
                                textFont
                            )
                            if (openWindowGetVisit.value) {
                                GetDate().DatePickerExample(
                                    openWindowGetVisit,
                                    textFont
                                ) { newDate ->
                                    visitDateComing = newDate
                                    hasComingVisitError = false
                                }
                            }
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.coming_visit_window_time_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_TIME_PICKER_PADDING_VERTICAL.dp
                ) {
                    if (!isChangeAct) {
                        textFont.WhiteText(stringResource(R.string.dollar_icon))
                    } else {
                        SelectTimeCard().TimeHelper(selectedTime, textFont)
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.name_visit_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_NAME_TEXT_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_NAME_TEXT_PADDING_VERTICAL.dp
                ) {
                    if (!isChangeAct) {
                        textFont.WhiteText(nameVisit)
                    } else {
                        TextFieldVisible().TextOutlineField(
                            nameVisit,
                            textFont,
                            stringResource(R.string.name_visit_hint),
                            trailingIcon = hasVisitNameError
                        ) { newText ->
                            nameVisit = newText
                            hasVisitNameError = false
                        }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.coming_status_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_STATUS_PICKER_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_STATUS_PICKER_PADDING_VERTICAL.dp
                ) {
                    if (!isChangeAct) {
                        textFont.WhiteText(selectedStatusVisit.value)
                    } else {
                        TextFieldVisible().DropMenuField(
                            expanded = expandedVisitStatus,
                            selectedValue = selectedStatusVisit,
                            textFont = textFont,
                            optionList = statusList
                        )
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.additional_info_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_VERTICAL.dp
                ) {
                    if (!isChangeAct) {
                        textFont.WhiteText(infoVisit)
                    } else {
                        TextFieldVisible().TextOutlineField(
                            infoVisit,
                            textFont,
                            stringResource(R.string.additional_info_hint),
                        ) { newText -> infoVisit = newText }
                    }
                }
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.pay_status_label),
                    textFont,
                    horizontalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_HORIZONTAL.dp,
                    verticalPadding = COMING_VISITS_INFORMATION_WINDOW_DIALOG_ADDITIONAL_INFO_PADDING_VERTICAL.dp
                ) {
                    TextFieldVisible().DropMenuField(
                        expanded = expandedPayStatus,
                        selectedValue = selectedPayStatus,
                        textFont = textFont,
                        optionList = payStatusList
                    )
                }
                Column {
                    if (isChangeAct) {
                        Column {
                            ButtonView().ButtonVisibleRow(
                                mapOf(
                                    stringResource(R.string.apply_button) to {
                                        if (visitDateComing.isEmpty()) {
                                            hasComingVisitError = true
                                        }
                                        if (nameVisit.isEmpty()) {
                                            hasVisitNameError = true
                                        }
                                        if (!hasComingVisitError && !hasVisitNameError) {
                                            val newDocument = DomainVisitModel(
                                                date = visitDateComing,
                                                time = selectedTime.value.toString(),
                                                visitName = nameVisit,
                                                visitStatus = selectedStatusVisit.value.ifEmpty { COMING_VISITS_INFORMATION_WINDOW_DEFAULT_VALUE_STATUS_VISIT },
                                                notes = infoVisit,
                                                id = visit?.value?.id,
                                                childId = childId,
                                                payStatus = selectedPayStatus.value,
                                                childName = childName
                                            )
                                            onSave(newDocument)
                                            openVisitWindow.value = false
                                        }
                                    },
                                    stringResource(R.string.cancel_button) to {
                                        visit?.value = null
                                        openVisitWindow.value = false
                                    }
                                ),
                                textFont,
                            )
                        }
                    }
                }
            }
        }
    }
}