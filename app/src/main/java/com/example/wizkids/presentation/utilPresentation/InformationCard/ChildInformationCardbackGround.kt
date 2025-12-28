package com.example.wizkids.presentation.utilPresentation.InformationCard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.wizkids.ui.theme.cardBackground

class ChildInformationCardbackGround {
    @Composable
    fun InformationCardbackGround(function: @Composable () -> Unit){
       Column(
           Modifier.fillMaxSize().padding(12.dp)
               .clip(RoundedCornerShape(20.dp)).background(cardBackground)
       ) {
           Column(Modifier.padding(12.dp)){
               function.invoke()
           }
       }
    }
}