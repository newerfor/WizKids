package com.example.wizkids.presentation.sharedUI


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DEFAULT_FONT_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DEFAULT_FONT_SIZE_ITALY_TEXT
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.textLightGray
import com.example.wizkids.ui.theme.textWhite

class TextFont {
    @Composable
    fun WhiteText(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign = TextAlign.Companion.Start,
        fontSize: Int = DEFAULT_FONT_SIZE
    ) {
        Text(
            text, color = textWhite, fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    fun GrayText(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign = TextAlign.Companion.Start,
        fontSize: Int = DEFAULT_FONT_SIZE
    ) {
        Text(
            text = text,
            fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Normal,
            color = textLightGray,
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    fun ItalyText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = DEFAULT_FONT_SIZE_ITALY_TEXT
    ) {
        Text(
            text = text,
            fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Bold,
            fontStyle = FontStyle.Companion.Italic,
            color = textWhite,
            modifier = modifier
        )
    }

    @Composable
    fun BlueText(
        text: String, modifier: Modifier = Modifier,
        textAlign: TextAlign = TextAlign.Companion.Start, fontSize: Int = DEFAULT_FONT_SIZE
    ) {
        Text(
            text, color = ButtonAndInfoBlue, fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign
        )
    }

    @Composable
    fun WhiteSortedText(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign = TextAlign.Companion.Start,
        fontSize: Int = DEFAULT_FONT_SIZE
    ) {
        Text(
            text, color = textWhite, fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign,
            maxLines = 1
        )
    }

    @Composable
    fun UnColorText(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign = TextAlign.Companion.Start,
        fontSize: Int = DEFAULT_FONT_SIZE,
        color: Color,
    ) {
        Text(
            text, color = color, fontFamily = Inter,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign
        )
    }
}