package com.example.core_ui.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.constant.SharedUiViewConstant.BUTTON_VISIBLE_DEFAULT_PADDING
import com.example.core_ui.constant.SharedUiViewConstant.BUTTON_VISIBLE_WEIGHT
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.buttonColor
import com.example.wizkids.ui.theme.lightBlue
import com.example.wizkids.ui.theme.textWhite

@Composable
fun ButtonVisibleRow(
    buttonNames: Map<String, () -> Unit>,
    textFont: TextFont,
    padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
) {
    Row(Modifier.fillMaxWidth()) {
        for (button in buttonNames) {
            Button(
                onClick = button.value,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                modifier = Modifier
                    .weight(BUTTON_VISIBLE_WEIGHT)
                    .padding(end = padding.dp)
            ) {
                AutoSizeText(
                    text = button.key,
                    maxLines = 1,
                    minTextSize = 8.sp,
                    maxTextSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ButtonVisibleColumn(
    buttonNames: Map<String, () -> Unit>,
    textFont: TextFont,
    padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
) {
    Column {
        for (button in buttonNames) {
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = button.value,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier
                        .weight(BUTTON_VISIBLE_WEIGHT)
                        .padding(top = padding.dp)
                ) {
                    AutoSizeText(
                        text = button.key,
                        maxLines = 1,
                        minTextSize = 8.sp,
                        maxTextSize = 14.sp
                    )
                }
            }
        }
    }
}
@Composable
fun AutoSizeText(
    text: String,
    maxLines: Int = 1,
    minTextSize: TextUnit = 8.sp,
    maxTextSize: TextUnit = 14.sp,
) {
    var fontSize by remember { mutableStateOf(maxTextSize) }
    Text(
        text = text,
        color = textWhite,
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Visible,
        onTextLayout = { result ->
            if (result.hasVisualOverflow && fontSize > minTextSize) {
                fontSize = (fontSize.value - 1f).sp
            }
        }
    )
}

@Composable
fun ButtonDayWeek(
    butonSetting: Map<String, Boolean?>,
    textFont: TextFont,
    onClick: (String) -> Unit,
    padding: Int = BUTTON_VISIBLE_DEFAULT_PADDING
) {
    Column {
        butonSetting.entries.forEach { entry ->
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = { onClick(entry.key) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (entry.value == true) lightBlue else buttonColor
                    ),
                    modifier = Modifier
                        .weight(BUTTON_VISIBLE_WEIGHT)
                        .padding(top = padding.dp)
                ) {
                    AutoSizeText(
                        text = entry.key,
                        maxLines = 1,
                        minTextSize = 8.sp,
                        maxTextSize = 14.sp
                    )
                }
            }
        }
    }
}

