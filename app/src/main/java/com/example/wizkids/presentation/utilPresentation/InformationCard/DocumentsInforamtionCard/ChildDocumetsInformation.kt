package com.example.wizkids.presentation.utilPresentation.InformationCard.DocumentsInforamtionCard

import android.R.attr.onClick
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.lightGray

class ChildDocumetsInformation {
    @Composable
    fun DocumentsCard(
        textFont: TextFont,
        docs: DomainDocumentsData,
        isChangeAct: Boolean = false,
        openWindowAddDocuments: MutableState<Boolean>? = null,
        onDocsAdded: (DomainDocumentsData) -> Unit = {},
        onChange : (DomainDocumentsData) -> Unit = {}
    ) {
        var openDocumentWindow = remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .padding(8.dp)
                        .background(
                            color = lightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            openDocumentWindow.value = true
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    ) {
                        AsyncImage(
                            model = docs.image[0],
                            contentDescription = "Документ: ${docs.name}",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        textFont.WhiteText(
                            text = docs.name,
                            modifier = Modifier.wrapContentWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
        if(openDocumentWindow.value){
            ChildDocumentInformationWindow().DocumentInformationWindow(
                textFont,
                openDocumentWindow,
                isChangeAct = isChangeAct,
                onSave = onChange,
                docs = docs
            )
        }
        if(openWindowAddDocuments?.value ?: false){
            ChildDocumentInformationWindow().DocumentInformationWindow(
                textFont,
                openWindowAddDocuments,
                isChangeAct = isChangeAct,
                onSave = onDocsAdded
            )
        }
    }
}