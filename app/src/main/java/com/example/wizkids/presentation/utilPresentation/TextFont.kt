package com.example.wizkids.presentation.utilPresentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.wizkids.presentation.main.constant.MainViewConstant
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.Inter
import com.example.wizkids.ui.theme.textLightGray
import com.example.wizkids.ui.theme.textWhite

class TextFont {
    @Composable
    fun WhiteText(text:String, modifier: Modifier = Modifier.Companion,
                  textAlign: TextAlign = TextAlign.Companion.Start){
        Text(
            text, color = textWhite, fontFamily = Inter,
            fontSize = MainViewConstant.WhiteTextFontSize,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign
        )
    }
    @Composable
    fun GrayText(text:String, modifier: Modifier = Modifier.Companion, textAlign: TextAlign = TextAlign.Companion.Start ){
        Text(
            text = text,
            fontFamily = Inter,
            fontSize = MainViewConstant.GrayTextFontSize,
            fontWeight = FontWeight.Companion.Normal,
            color = textLightGray,
            modifier = modifier,
            textAlign = textAlign
        )
    }
    @Composable
    fun ItalyText(text: String, modifier: Modifier = Modifier.Companion, fontSize: TextUnit = MainViewConstant.ItalyTextFontSize){
        Text(
            text = text,
            fontFamily = Inter,
            fontSize = fontSize,
            fontWeight = FontWeight.Companion.Bold,
            fontStyle = FontStyle.Companion.Italic,
            lineHeight = MainViewConstant.ItalyTextLineHeight,
            color = textWhite,
            modifier = modifier
        )
    }
    @Composable
    fun BlueText(text:String, modifier: Modifier = Modifier.Companion,
                 textAlign: TextAlign = TextAlign.Companion.Start ){
        Text(
            text, color = ButtonAndInfoBlue, fontFamily = Inter,
            fontSize = MainViewConstant.BlueTextFontSize,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign
        )
    }
    @Composable
    fun WhiteSortedText(text:String, modifier: Modifier = Modifier.Companion, textAlign: TextAlign = TextAlign.Companion.Start){
        Text(
            text, color = textWhite, fontFamily = Inter,
            fontSize = MainViewConstant.WhiteSortedTextFontSize,
            fontWeight = FontWeight.Companion.Normal,
            modifier = modifier,
            textAlign = textAlign,
            maxLines = 1
        )
    }
}