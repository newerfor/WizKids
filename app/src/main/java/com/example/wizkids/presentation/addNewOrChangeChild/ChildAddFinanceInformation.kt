package com.example.wizkids.presentation.addNewOrChangeChild

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.cardBackground

class ChildAddFinanceInformation{
    @Composable
    fun FinanceAddFinanceInformation(
        textFont: TextFont,
        price: MutableState<Int>,
        balance: MutableState<Int>,
        ChildFinanceInfoError: MutableState<Boolean>
    ) {
        val openWindowBalance = remember { mutableStateOf(false) }
        val openWindowPrice = remember { mutableStateOf(false) }
        Row(Modifier.Companion.fillMaxWidth()) {
            Button(
                onClick = { openWindowBalance.value = true },
                Modifier.Companion.weight(1f)
            ) { textFont.WhiteText("Баланс: ${balance.value} ₽") }
            Button(
                onClick = { openWindowPrice.value = true },
                Modifier.Companion.weight(1f)
            ) { textFont.WhiteText("Цена: ${price.value} ₽") }
        }
        if(openWindowPrice.value){
            WindowAddFinaceInformation(openWindowPrice,textFont,price,"цену",ChildFinanceInfoError)
        }
        if(openWindowBalance.value){
            WindowAddFinaceInformation(openWindowBalance,textFont,balance,"баланс",ChildFinanceInfoError)
        }
    }
    @Composable
    fun WindowAddFinaceInformation(
        openWindowPrice: MutableState<Boolean>,
        textFont: TextFont,
        finace_info: MutableState<Int>,
        ItalyText: String,
        ChildFinanceInfoError: MutableState<Boolean>
    ) {
        var textValue by remember {
            mutableStateOf(
                if (finace_info.value.toString() != "0") {
                    finace_info.value.toString()
                } else {
                    ""
                }
            )
        }
        Dialog(onDismissRequest = { openWindowPrice.value = false }) {
            textFont.ItalyText(ItalyText)
            Column(
                Modifier.Companion.background(color = cardBackground),
                Arrangement.Center,
                Alignment.Companion.CenterHorizontally
            ) {
                TextFieldVisible().TextOutlineField(
                    textValue,
                    textFont,
                    "Введите $ItalyText",
                    trailingIcon = ChildFinanceInfoError.value
                ) { newText -> textValue = newText }
                Row(Modifier.Companion.fillMaxWidth()) {
                    Button({
                        finace_info.value = textValue.toIntOrNull() ?: 0
                        openWindowPrice.value = false
                    }, Modifier.Companion.weight(1f)) {
                        textFont.WhiteText("Применить")
                    }
                    Button({
                        openWindowPrice.value = false
                    }, Modifier.Companion.weight(1f)) {
                        textFont.WhiteText("Отменить")
                    }
                }
            }
        }
    }
}