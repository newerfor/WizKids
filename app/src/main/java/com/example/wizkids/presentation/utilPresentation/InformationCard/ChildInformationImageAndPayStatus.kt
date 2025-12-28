package com.example.wizkids.presentation.utilPresentation.InformationCard

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusFalse
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusLater
import com.example.wizkids.presentation.main.constant.MainLogicConstant.StatusTrue
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.payFalse
import com.example.wizkids.ui.theme.payLater
import com.example.wizkids.ui.theme.payTrue
import com.example.wizkids.util.ImageSaveHelper
import java.util.UUID

class ChildInformationImageAndPayStatus {
    @Composable
    fun InformationImageAndPayStatus(
        textFont: TextFont,
        childImage: String,
        price: Int,
        balance: Int,
        childImageError: Boolean = false,
        isChangeAct: Boolean = false,
        ){
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current
        var imagePathState by remember { mutableStateOf("") }
        var imagePath: String? by remember { mutableStateOf(childImage) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                imageUri = uri
                imagePathState = uri.toString()
                // Сохраняем изображение ТОЛЬКО когда оно выбрано
                if (uri != null) {
                    val fileName = "${UUID.randomUUID()}.jpg"
                    imagePath = ImageSaveHelper().saveImageToLocalStorage(context, uri, fileName)
                }
            }
        )
        Column(Modifier.fillMaxWidth()){
            Column(
                Modifier.fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .border(
                            width = if (childImageError) 2.dp else 1.dp,
                            color = if (childImageError) Color.Red else lightGray,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .background(lightGray)
                        .clickable {
                            if(isChangeAct){
                                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (childImageError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Ошибка",
                            Modifier.size(50.dp),
                            tint = Color.Red
                        )
                    }
                    imagePath?.let { image ->
                        if (image.isNotEmpty()) {
                            AsyncImage(
                                model = image,
                                contentDescription = "Изображение ребенка",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            var PayStatus by remember { mutableStateOf("") }
            var PayColor by remember { mutableStateOf(Color.Transparent) }
            if(balance==price){
                PayStatus = StatusLater
                PayColor = payLater
            }else{
                if(balance>price){
                    PayStatus = StatusTrue
                    PayColor= payTrue
                }else{
                    PayStatus = StatusFalse
                    PayColor=payFalse
                }
            }
            Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally){
                Column(Modifier.clip(RoundedCornerShape(20.dp)).background(PayColor)){
                    textFont.WhiteText("$PayStatus", modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp))
                }
            }
        }
    }
}