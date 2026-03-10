package com.example.wizkids.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.wizkids.R
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_FIRST_YEAR
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_NAME_SEARCH
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_SECOND_YEAR
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_OPTION_SORT
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_STATUS
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_PAY_STATUS_NOT_PAID
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_PAY_STATUS_PAID
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_PAY_STATUS_PAYMENT_SOON
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_AGE_ASC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_AGE_DESC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_DATE_VISIT_ASC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_DATE_VISIT_DESC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_NAME_ASC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_KEY_SORTED_BY_NAME_DESC
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_VALUE_PAY_STATUS_NOT_PAID
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_VALUE_PAY_STATUS_PAID
import com.example.wizkids.presentation.main.constant.MainLogicConstant.MAIN_ACTIVITY_VALUE_PAY_STATUS_PAYMENT_SOON
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_CLIP
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_TEXT_VERTICAL_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_FILTERS_MAIN_CONTAINER_TOP_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_CLIP
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_PADDING
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_WEIGHT
import com.example.wizkids.presentation.main.constant.MainViewConstant.MAIN_ACTIVITY_MAIN_SORTED_COLUMN_TEXT_VERTICAL_PADDING
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.child.SortedOption
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.blackColor
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.grayColor

class MainActFiltersAndSorted {
    @Composable
    fun Filters(
        textFont: TextFont,
        viewModel: ChildViewModel,
    ) {
        var selectedOptionSort = remember {
            mutableStateOf(
                MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_OPTION_SORT
            )
        }
        var openWindowFilters = remember { mutableStateOf(false) }
        var openWindowSorted = remember { mutableStateOf(false) }
        var hasFiltersEnable by remember { mutableStateOf(false) }
        var nameSearch = remember { mutableStateOf(MAIN_ACTIVITY_DEFAULT_VALUE_NAME_SEARCH) }
        var firstYear = remember { mutableStateOf(MAIN_ACTIVITY_DEFAULT_VALUE_FIRST_YEAR) }
        var secondYears = remember { mutableStateOf(MAIN_ACTIVITY_DEFAULT_VALUE_SECOND_YEAR) }
        var selectedStatus =
            remember { mutableStateOf(MAIN_ACTIVITY_DEFAULT_VALUE_SELECTED_STATUS) }
        var hasPayStatusDebt = remember { mutableStateOf(false) }
        var sortedOption: SortedOption? = null
        FiltersScreen(
            nameSerch = nameSearch,
            textStyle = textFont,
            openWindowFilters = openWindowFilters,
            openWindowSorted = openWindowSorted,
            isFiltersEnable = hasFiltersEnable,
            selectedOptionSort = selectedOptionSort.value
        )
        if (openWindowFilters.value) {
            MainActFilters().WindowFilters(
                openWindowFilters,
                textFont,
                selectedStatus = selectedStatus,
                secondYears = secondYears,
                firstYear = firstYear,
            )
        }
        if (openWindowSorted.value) {
            MainActSorted().WindowSorted(
                openWindowSorted,
                textFont,
                selectedOptionSort,
            )
        }
        LaunchedEffect(selectedOptionSort) {
            if (selectedOptionSort.value.isNotEmpty()) {
                when (selectedOptionSort.value) {
                    MAIN_ACTIVITY_KEY_SORTED_BY_NAME_ASC -> {
                        sortedOption = SortedOption.BY_NAME_ASC
                    }

                    MAIN_ACTIVITY_KEY_SORTED_BY_NAME_DESC -> {
                        sortedOption = SortedOption.BY_NAME_DESC

                    }

                    MAIN_ACTIVITY_KEY_SORTED_BY_DATE_VISIT_ASC -> {
                        sortedOption = SortedOption.BY_DATE_ASC

                    }

                    MAIN_ACTIVITY_KEY_SORTED_BY_DATE_VISIT_DESC -> {
                        sortedOption = SortedOption.BY_DATE_DESC

                    }

                    MAIN_ACTIVITY_KEY_SORTED_BY_AGE_ASC -> {
                        sortedOption = SortedOption.BY_AGE_ASC

                    }

                    MAIN_ACTIVITY_KEY_SORTED_BY_AGE_DESC -> {
                        sortedOption = SortedOption.BY_AGE_DESC
                    }

                    "" -> {
                        sortedOption = null
                    }
                }
                viewModel.childrenSorted(sortedOption)
            }
        }
        LaunchedEffect(nameSearch.value, firstYear.value, secondYears.value, selectedStatus.value) {
            viewModel.getChildren(
                searchName = nameSearch.value.ifEmpty { null },
                minAge = if (firstYear.value == 0) {
                    null
                } else {
                    firstYear.value
                },
                maxAge = if (secondYears.value == 0) {
                    null
                } else {
                    secondYears.value
                },
                balanceOperator = if (selectedStatus.value.isNotEmpty()) {
                    val balanceOperator = when (selectedStatus.value) {
                        MAIN_ACTIVITY_KEY_PAY_STATUS_PAID -> MAIN_ACTIVITY_VALUE_PAY_STATUS_PAID
                        MAIN_ACTIVITY_KEY_PAY_STATUS_NOT_PAID -> MAIN_ACTIVITY_VALUE_PAY_STATUS_NOT_PAID
                        MAIN_ACTIVITY_KEY_PAY_STATUS_PAYMENT_SOON -> MAIN_ACTIVITY_VALUE_PAY_STATUS_PAYMENT_SOON
                        else -> {
                            hasPayStatusDebt.value = true
                            null
                        }
                    }
                    balanceOperator
                } else {
                    null
                },
                hasPayStatusDebt = if (hasPayStatusDebt.value) {
                    hasPayStatusDebt.value
                } else false,
                selectedOptionSort = sortedOption
            )
        }
    }

    @Composable
    fun FiltersScreen(
        nameSerch: MutableState<String>,
        textStyle: TextFont,
        selectedOptionSort: String,
        openWindowFilters: MutableState<Boolean>,
        openWindowSorted: MutableState<Boolean>,
        isFiltersEnable: Boolean
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = MAIN_ACTIVITY_MAIN_FILTERS_MAIN_CONTAINER_TOP_PADDING.dp)
        ) {
            NameFilters(
                nameSerch,
                textFont = textStyle
            )
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier
                        .weight(MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_WEIGHT)
                        .padding(MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_PADDING.dp)
                        .clip(RoundedCornerShape(MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_CONTAINER_CLIP.dp))
                        .background(
                            color = if (!isFiltersEnable) {
                                cardBackground
                            } else {
                                ButtonAndInfoBlue
                            }
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) { openWindowFilters.value = true }) {
                    textStyle.WhiteText(
                        text = stringResource(R.string.main_activity_filters_label),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MAIN_ACTIVITY_MAIN_FILTERS_COLUMN_TEXT_VERTICAL_PADDING.dp)
                    )
                }
                Column(
                    Modifier
                        .weight(MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_WEIGHT)
                        .padding(MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_PADDING.dp)
                        .clip(RoundedCornerShape(MAIN_ACTIVITY_MAIN_SORTED_COLUMN_CONTAINER_CLIP.dp))
                        .background(
                            color = if (selectedOptionSort.isEmpty()) {
                                cardBackground
                            } else {
                                ButtonAndInfoBlue
                            }
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) { openWindowSorted.value = true }) {
                    textStyle.WhiteSortedText(
                        text = selectedOptionSort.ifEmpty {
                            stringResource(R.string.main_activity_sorted_label)
                        }, textAlign = TextAlign.Center, modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MAIN_ACTIVITY_MAIN_SORTED_COLUMN_TEXT_VERTICAL_PADDING.dp)
                    )
                }
            }
        }
    }

    @Composable
    fun NameFilters(nameSearch: MutableState<String>, textFont: TextFont) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextFieldVisible().TextOutlineField(
                nameSearch.value,
                textFont,
                stringResource(R.string.main_activity_search_by_name_hint),
                {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.content_description_search),
                        tint = grayColor
                    )
                },
                textColor = blackColor,
            ) { newText -> nameSearch.value = newText }
        }
    }
}