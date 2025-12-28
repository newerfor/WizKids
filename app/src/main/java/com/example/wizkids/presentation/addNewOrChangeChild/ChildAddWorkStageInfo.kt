package com.example.wizkids.presentation.addNewOrChangeChild

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.textWhite

class ChildAddWorkStageInfo {
    @Composable
    fun AddWorkStage(textFont: TextFont, ChildWorkStage: MutableState<List<String>>) {
        ChildWorkStage.value.forEachIndexed { index, string ->
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically){
                textFont.GrayText("${index+1}.")
                TextFieldVisible().TextOutlineField(string, textFont, "Введите этап......") { newText ->
                    ChildWorkStage.value = ChildWorkStage.value.toMutableList().apply {
                        this[index] = newText
                    }
                }
                IconButton(
                    onClick = {
                        ChildWorkStage.value = ChildWorkStage.value.toMutableList().apply {
                            removeAt(index)
                        }
                    },
                ) {
                    Icon(
                        Icons.Default.Delete,
                        "Удалить",
                        tint = textWhite // Добавьте этот параметр
                    )
                }
            }
        }
        Row(Modifier.Companion.fillMaxWidth()) {
            Button(
                onClick = { ChildWorkStage.value = ChildWorkStage.value + "" },
                Modifier.Companion.weight(1f)
            ) { textFont.WhiteText("Добавить") }
            Button(
                onClick = { ChildWorkStage.value = emptyList() },
                Modifier.Companion.weight(1f)
            ) { textFont.WhiteText("Сбросить всё") }
        }
    }
}