package com.example.wizkids.presentation.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.textLightGray
import com.example.wizkids.ui.theme.textWhite
import com.example.wizkids.presentation.utilPresentation.GetDate
import com.example.wizkids.presentation.main.constant.MainLogicConstant.FiltersMapFirstDateKey
import com.example.wizkids.presentation.main.constant.MainLogicConstant.FiltersMapFirstYearsKey
import com.example.wizkids.presentation.main.constant.MainLogicConstant.FiltersMapPayStatusKey
import com.example.wizkids.presentation.main.constant.MainLogicConstant.FiltersMapSecondDateKey
import com.example.wizkids.presentation.main.constant.MainLogicConstant.FiltersMapSecondYearsKey
import com.example.wizkids.presentation.main.constant.MainLogicConstant.OptionListValue
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusListValue
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.presentation.main.constant.MainViewConstant.FiltersColumnClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.FiltersColumnPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.FiltersColumnPaddingTop
import com.example.wizkids.presentation.main.constant.MainViewConstant.FiltersColumnTextPaddingVertical
import com.example.wizkids.presentation.main.constant.MainViewConstant.FiltersColumnWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardBoxBorderShare
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardBoxBorderWidth
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardBoxFillClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardBoxFillSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardBoxSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardColumnClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardColumnPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.OptionCardWhiteTextPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersButtonApplyPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersButtonApplyWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersButtonCancelPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersButtonCancelWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnGlobalPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowClip
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowIconSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowPaddingHorizontal
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowPaddingTop
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowPaddingVertical
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowSpacerWidth
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersColumnRowWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersExposedDropdownMenuBoxPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersGlobalItalyTextSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersItalyTextSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersRow
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowFiltersSpacerHeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedButtonCancelPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedButtonCancelWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedButtonResetPadding
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedButtonResetWeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedItalyTextSize
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedOptionColumn
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedOptionSpacerHeight
import com.example.wizkids.presentation.main.constant.MainViewConstant.WindowSortedSpacerHeight
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible

class MainActFilters {
    @Composable
    fun Filters(textStyle: TextFont = TextFont()){
        var ChoisedOptionSort by remember { mutableStateOf("") }
        var openWindowFilters = remember { mutableStateOf(false) }
        var isFiltersEnable by remember { mutableStateOf(false) }
        var openWindowSorted = remember { mutableStateOf(false) }
        var textSerch by remember { mutableStateOf("") }
        val filtersMap = remember { mutableStateOf(mapOf(
            FiltersMapFirstYearsKey to null as String?,
            FiltersMapSecondYearsKey to null as String?,
            FiltersMapFirstDateKey to null as String?,
            FiltersMapSecondDateKey to null as String?,
            FiltersMapPayStatusKey to null as String?
        )) }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = FiltersColumnPaddingTop)) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextFieldVisible().TextOutlineField(textSerch,textStyle,"Поиск по имени....",{                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Поиск",
                    tint = Color.Gray
                )}){newText -> textSerch = newText}
            }
            Row(Modifier.fillMaxWidth()) {
                Column(
                    Modifier
                        .weight(FiltersColumnWeight)
                        .padding(FiltersColumnPadding)
                        .clip(RoundedCornerShape(FiltersColumnClip))
                        .background(
                            color = if (!isFiltersEnable) {
                                cardBackground
                            } else {
                                ButtonAndInfoBlue
                            }
                        )
                        .clickable { openWindowFilters.value = true }) {
                    textStyle.WhiteText(text = "Фильтр", textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = FiltersColumnTextPaddingVertical))
                }
                Column(
                    Modifier
                        .weight(FiltersColumnWeight)
                        .padding(FiltersColumnPadding)
                        .clip(RoundedCornerShape(FiltersColumnClip))
                        .background(color = if(ChoisedOptionSort.isEmpty()){ cardBackground}else{
                            ButtonAndInfoBlue})
                        .clickable { openWindowSorted.value = true }) {
                    textStyle.WhiteSortedText(text = if(ChoisedOptionSort.isEmpty()){"Сортировка"}else{ChoisedOptionSort}, textAlign = TextAlign.Center, modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = FiltersColumnTextPaddingVertical))
                }
            }
        }
        if(openWindowFilters.value){
            WindowFilters(openWindowFilters, filtersList = filtersMap, onApplyFilters = { newFilters ->
                filtersMap.value = newFilters
                if(filtersMap.value[FiltersMapFirstYearsKey]!=null || filtersMap.value[FiltersMapSecondYearsKey]!=null || filtersMap.value[FiltersMapFirstDateKey]!=null||filtersMap.value[FiltersMapSecondDateKey]!=null||filtersMap.value[FiltersMapPayStatusKey]!=null){
                    isFiltersEnable  = true
                }else{
                    isFiltersEnable  = false

                }

            })
        }
        if(openWindowSorted.value){
            ChoisedOptionSort = WindowSorted(
                openWindowSorted,
                ChoisedOptions =ChoisedOptionSort
            )
        }
        if(isFiltersEnable){
            Log.d("wfawfwfwfwfwfwf",filtersMap.value.toString())
        }
        if(ChoisedOptionSort.isNotEmpty()){
           Log.d("wfawfwfwfwfwfwf",ChoisedOptionSort)
        }
    }
    @Composable
    fun WindowSorted(openWindowSorted:MutableState<Boolean>,textFont: TextFont = TextFont(),ChoisedOptions:String):String{
        var Clicked by remember { mutableStateOf(false) }
        var onChoiseOption = remember { mutableStateOf(ChoisedOptions) }
        var OptionList by remember {
            mutableStateOf(OptionListValue)
        }
        Dialog(onDismissRequest = {openWindowSorted.value = false}) {
            Column(Modifier.background(color = cardBackground)){
                Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                    textFont.ItalyText("Сортировка", fontSize = WindowSortedItalyTextSize)
                }
                Spacer(Modifier.height(WindowSortedSpacerHeight))
                Column(Modifier.padding(WindowSortedOptionColumn)) {
                    for (option in OptionList) {
                        OptionCard(option = option, onChoiseOption = onChoiseOption){
                            onChoiseOption.value = option
                            Clicked = true
                        }
                        Spacer(Modifier.height(WindowSortedOptionSpacerHeight))
                    }
                }

                Row {
                    Button(onClick = {
                        onChoiseOption.value = ""
                        Clicked = true
                    }, Modifier
                        .weight(WindowSortedButtonResetWeight)
                        .padding(WindowSortedButtonResetPadding),colors = ButtonDefaults.buttonColors(
                        containerColor = darkHeader,
                    )) { textFont.WhiteText("Сбросить") }
                    Button(onClick = {
                        openWindowSorted.value = false
                        },
                        Modifier
                            .weight(WindowSortedButtonCancelWeight)
                            .padding(WindowSortedButtonCancelPadding),colors = ButtonDefaults.buttonColors(
                            containerColor = darkHeader,
                        )) { textFont.WhiteText("Отменить") }
                }
            }
        }
        if(Clicked){
            openWindowSorted.value = false
        }
        return onChoiseOption.value
    }
    @Composable
    fun OptionCard(
        option: String,
        onChoiseOption: MutableState<String>,
        textFont: TextFont = TextFont(),
        OnClick:()->Unit
    ){
        Column(
            Modifier.clip(RoundedCornerShape(OptionCardColumnClip))
                .background(
                    color = darkHeader
                )
                .clickable {
                    OnClick.invoke() }
                .fillMaxWidth().padding(OptionCardColumnPadding)
        ) {
            Row(  verticalAlignment = Alignment.CenterVertically ) {
            if (onChoiseOption.value != option) {
                    Box(Modifier.size(OptionCardBoxSize).border(
                        width = OptionCardBoxBorderWidth,
                        color = cardBackground,
                        shape = RoundedCornerShape(OptionCardBoxBorderShare)
                    ).background(Color.Transparent))
                }else{
                    Box(Modifier.size(OptionCardBoxSize).border(
                        width = OptionCardBoxBorderWidth,
                        color = ButtonAndInfoBlue,
                        shape = RoundedCornerShape(OptionCardBoxBorderShare)
                    ).background(Color.Transparent), contentAlignment = Alignment.Center){
                            Box(Modifier.size(OptionCardBoxFillSize).clip(
                                RoundedCornerShape(OptionCardBoxFillClip)
                            ).background(color = ButtonAndInfoBlue))
                    }
                }

                textFont.WhiteText(option, modifier = Modifier.padding(start = OptionCardWhiteTextPadding))
            }

        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun WindowFilters(
        openWindowFilters: MutableState<Boolean>,
        textFont: TextFont = TextFont(),
        filtersList: MutableState<Map<String, String?>>,
        onApplyFilters: (Map<String, String?>) -> Unit
    ) {
        val openWindowGetFirstDate = remember { mutableStateOf(false) }
        val openWindowGetSecondDate = remember { mutableStateOf(false) }
        var firstyears by remember { mutableStateOf(filtersList.value[FiltersMapFirstYearsKey]?.toString() ?: "") }
        var secondyears by remember { mutableStateOf(filtersList.value[FiltersMapSecondYearsKey]?.toString() ?: "") }
        var firstDate by remember { mutableStateOf(filtersList.value[FiltersMapFirstDateKey]?.toString() ?: "dd.mm.yyyy") }
        var secondDate by remember { mutableStateOf(filtersList.value[FiltersMapSecondDateKey]?.toString() ?: "dd.mm.yyyy") }
        var expanded by remember { mutableStateOf(false) }
        var selectedStatus by remember { mutableStateOf(filtersList.value[FiltersMapPayStatusKey]?.toString() ?: "Выберите вариант") }

        var StatusList by remember { mutableStateOf(StatusListValue) }


        val textColor = if (selectedStatus != "Выберите вариант") textWhite else textLightGray
        Dialog(onDismissRequest = { openWindowFilters.value = false }) {
            Column(Modifier.background(color = cardBackground)){
                Column(Modifier.padding(WindowFiltersColumnGlobalPadding)){
                    Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                        textFont.ItalyText("Фильтрация", fontSize = WindowFiltersGlobalItalyTextSize)
                    }
                    Spacer(Modifier.height(WindowFiltersSpacerHeight))
                    textFont.ItalyText("Фильтрация по возрасту", fontSize = WindowFiltersItalyTextSize)
                    Row(Modifier.padding(top = WindowFiltersRow)) {
                        TextFieldVisible().TextOutlineField(firstyears,textFont,"От"){newText -> firstyears = newText}
                        TextFieldVisible().TextOutlineField(secondyears,textFont,"До"){newText -> secondyears = newText}
                    }
               }
                Column(Modifier.padding(WindowFiltersColumnPadding)){
                    textFont.ItalyText("Фильтрация по дате последнего визита", fontSize = WindowFiltersItalyTextSize)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = WindowFiltersColumnRowPaddingTop)){
                        Row(
                            Modifier
                                .padding(WindowFiltersColumnRowPadding)
                                .weight(WindowFiltersColumnRowWeight)
                        ){
                            Row(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(WindowFiltersColumnRowClip))
                                    .background(darkHeader)
                                    .padding(horizontal = WindowFiltersColumnRowPaddingHorizontal, vertical = WindowFiltersColumnRowPaddingVertical)
                                    .clickable {
                                        openWindowGetFirstDate.value = true
                                    },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Дата",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(WindowFiltersColumnRowIconSize)
                                )
                                Spacer(modifier = Modifier.width(WindowFiltersColumnRowSpacerWidth))
                                if(firstDate=="dd.mm.yyyy"){
                                    textFont.GrayText(firstDate)
                                }
                                else{
                                    textFont.WhiteText(firstDate)
                                }
                            }
                        }
                        Row(
                            Modifier
                                .padding(WindowFiltersColumnRowPadding)
                                .weight(WindowFiltersColumnRowWeight)
                        ) {
                            Row(
                                modifier = Modifier
                                    .weight(WindowFiltersColumnRowWeight)
                                    .clip(RoundedCornerShape(WindowFiltersColumnRowClip))
                                    .background(darkHeader)
                                    .padding(horizontal = WindowFiltersColumnRowPaddingHorizontal, vertical = WindowFiltersColumnRowPaddingVertical)
                                    .clickable {
                                        openWindowGetSecondDate.value = true
                                    },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Дата",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(WindowFiltersColumnRowIconSize)
                                )
                                Spacer(modifier = Modifier.width(WindowFiltersColumnRowSpacerWidth))
                                if(secondDate=="dd.mm.yyyy"){
                                    textFont.GrayText(secondDate)
                                }
                                else{
                                    textFont.WhiteText(secondDate)

                                }
                            }
                        }
                    }
                }
                Column(Modifier.padding(WindowFiltersColumnPadding)){
                    textFont.ItalyText("Фильтрация по статусу оплаты", fontSize = WindowFiltersItalyTextSize)
                    ExposedDropdownMenuBox(
                        modifier = Modifier.padding(WindowFiltersExposedDropdownMenuBoxPadding),
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            value = selectedStatus,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .background(color = darkHeader),
                            textStyle = LocalTextStyle.current.merge(
                                TextStyle(
                                    color = textColor,
                                    fontFamily = Inter,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = darkHeader,
                                unfocusedContainerColor = darkHeader,
                                disabledContainerColor = darkHeader,
                            ),
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(color = darkHeader)
                        ) {
                            StatusList.forEach { item ->
                                DropdownMenuItem(
                                    text = { textFont.WhiteText(item)},
                                    onClick = {
                                        selectedStatus = item
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                    textFont.BlueText("Сбросить", modifier = Modifier.clickable {
                        firstyears = ""
                        secondyears = ""
                        firstDate = "dd.mm.yyyy"
                        secondDate = "dd.mm.yyyy"
                        selectedStatus = "Выберите вариант"
                    })
                }

                Row {
                    Button(onClick = {
                        val newFilters = mapOf(
                            FiltersMapFirstYearsKey to firstyears.ifEmpty { null },
                            FiltersMapSecondYearsKey to secondyears.ifEmpty { null },
                            FiltersMapFirstDateKey to if (firstDate != "dd.mm.yyyy") firstDate else null, // Исправлено: firstDate.value
                            FiltersMapSecondDateKey to if (secondDate != "dd.mm.yyyy") secondDate else null, // Исправлено: secondDate.value
                            FiltersMapPayStatusKey to if (selectedStatus != "Выберите вариант") selectedStatus else null
                        )
                        onApplyFilters(newFilters)
                        openWindowFilters.value = false
                    }, Modifier
                            .weight(WindowFiltersButtonApplyWeight)
                            .padding(WindowFiltersButtonApplyPadding),colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonAndInfoBlue,
                    )) { textFont.WhiteText("Применить") }
                    Button(onClick = {openWindowFilters.value = false},
                        Modifier
                            .weight(WindowFiltersButtonCancelWeight)
                            .padding(WindowFiltersButtonCancelPadding),colors = ButtonDefaults.buttonColors(
                            containerColor = darkHeader,
                        )
                        ) { textFont.WhiteText("Отменить") }
                }
            }
        }
        if(openWindowGetFirstDate.value){
            GetDate().DatePickerExample(openWindowGetFirstDate) {newDate->firstDate = newDate }
        }
        else{
            if(openWindowGetSecondDate.value){
                GetDate().DatePickerExample(openWindowGetSecondDate) {newDate->secondDate = newDate }
            }
        }
    }
}