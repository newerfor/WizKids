package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_BORDER_SHAPE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_BORDER_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_BORDER_SHAPE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_BORDER_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_MAIN_CONTAINER_VERTICAL_PADDING
import com.example.wizkids.ui.theme.lightBlue

class DocumentInformation {
    @Composable
    fun AddOrWatchDocumentInformation(
        textFont: TextFont,
        documents: MutableList<DomainDocumentsModel>,
        isChangeAct: Boolean
    ) {
        var openWindowAddDocument = remember { mutableStateOf(false) }
        var docsInfo = remember { mutableStateOf<DomainDocumentsModel?>(null) }
        var editingIndex = remember { mutableStateOf<Int?>(null) }
        Row(
            modifier = Modifier.Companion
                .horizontalScroll(rememberScrollState())
        ) {
            documents.forEachIndexed { index, docs -> // Получаем индекс
                ChildDocumentsInformation().DocumentsCard(
                    textFont = textFont,
                    docs = docs,
                    onClick = {
                        editingIndex.value = index // Сохраняем индекс
                        openWindowAddDocument.value = true
                        docsInfo.value = docs
                    }
                )
            }
            if (isChangeAct) {
                Column(
                    Modifier.Companion
                        .fillMaxHeight()
                        .padding(vertical = ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_MAIN_CONTAINER_VERTICAL_PADDING.dp),
                    Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Companion.Center,
                        modifier = Modifier.Companion
                            .padding(ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_PADDING.dp)
                            .border(
                                ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_BORDER_WIDTH.dp,
                                lightBlue,
                                RoundedCornerShape(
                                    ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_ADD_BOX_BORDER_SHAPE.dp
                                )
                            )
                    ) {
                        IconButton(
                            onClick = {
                                docsInfo.value = null
                                openWindowAddDocument.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                tint = lightBlue
                            )
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Companion.Center,
                        modifier = Modifier.Companion
                            .padding(ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_PADDING.dp)
                            .border(
                                ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_BORDER_WIDTH.dp,
                                lightBlue,
                                androidx.compose.foundation.shape.RoundedCornerShape(
                                    ALL_DOCUMENTS_INFORMATION_CARD_BUTTON_DELETE_BOX_BORDER_SHAPE.dp
                                )
                            )
                    ) {
                        IconButton(
                            onClick = { documents.clear() }
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                "",
                                tint = lightBlue
                            )
                        }
                    }
                }
            }

        }
        if (openWindowAddDocument.value) {
            ChildDocumentInformationWindow().DocumentInformationWindow(
                textFont = textFont,
                openDocumentWindow = openWindowAddDocument,
                docs = docsInfo,
                documentList = documents,
                isChangeAct = isChangeAct,
                inAddIndex = documents.size,
                onSave = { docs ->
                    if (docsInfo.value == null) {
                        documents.add(docs)
                    } else {
                        documents[editingIndex.value!!] = docs
                        docsInfo.value = null
                    }
                }
            )
        }
    }
}