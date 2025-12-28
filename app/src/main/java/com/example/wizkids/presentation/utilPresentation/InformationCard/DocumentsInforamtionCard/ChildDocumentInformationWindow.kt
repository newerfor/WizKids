package com.example.wizkids.presentation.utilPresentation.InformationCard.DocumentsInforamtionCard

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.wizkids.domain.model.DomainDocumentsData
import com.example.wizkids.presentation.utilPresentation.InputInformationCard
import com.example.wizkids.presentation.utilPresentation.TextFieldVisible
import com.example.wizkids.presentation.utilPresentation.TextFont
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.util.ImageSaveHelper
import java.util.UUID

class ChildDocumentInformationWindow {
    @Composable
    fun DocumentInformationWindow(
        textFont: TextFont,
        openDocumentWindow: MutableState<Boolean>,
        docs: DomainDocumentsData? = null,
        isChangeAct: Boolean,
        onSave: (DomainDocumentsData) -> Unit
    ) {
        val imageList = remember { mutableStateListOf<String>() }

        // Инициализируем список при первом отображении
        LaunchedEffect(Unit) {
            if (docs?.image?.isNotEmpty() == true) {
                imageList.clear()
                imageList.addAll(docs.image)
            }
        }

        var selectedImageIndex by remember { mutableStateOf(0) }
        val childDocs = docs ?: DomainDocumentsData(
            name = "",
            Info = "",
            image = listOf()
        )
        var docsName by remember { mutableStateOf(childDocs.name) }
        var docsInfo by remember { mutableStateOf(childDocs.Info) }
        val context = LocalContext.current

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                if (uri != null) {
                    val fileName = "${UUID.randomUUID()}.jpg"
                    val imagePath = ImageSaveHelper().saveImageToLocalStorage(context, uri, fileName)
                    imagePath?.let { path ->
                        // Теперь imageList - mutableStateList, изменения будут видны в UI
                        imageList.add(path)
                        selectedImageIndex = imageList.size - 1
                    }
                }
            }
        )

        Dialog(onDismissRequest = { openDocumentWindow.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.8f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = cardBackground)
                    .padding(16.dp)
            ) {
                // Основное изображение
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .background(Color.Gray)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    if (imageList.isNotEmpty() && selectedImageIndex < imageList.size) {
                        AsyncImage(
                            model = imageList[selectedImageIndex],
                            contentDescription = null,
                            Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .clickable {
                                    launcher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            Arrangement.Center,
                            Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Добавить изображение",
                                Modifier.size(50.dp),
                                tint = Color.Blue
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Миниатюры изображений с LazyRow
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    itemsIndexed(imageList) { index, image ->
                        Box(
                            Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selectedImageIndex == index) Color.LightGray else Color.Gray)
                                .clickable { selectedImageIndex = index }
                        ) {
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }

                    // Кнопка добавления
                    item {
                        Box(
                            Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Gray)
                                .clickable {
                                    launcher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Добавить изображение",
                                Modifier.size(30.dp),
                                tint = Color.Blue
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Остальная часть вашего кода...
                InputInformationCard().AddInformationCard(
                    "Название документа",
                    textFont,
                    horizontalpadding = 2.dp,
                    verticalpadding = 5.dp
                ) {
                    if (isChangeAct) {
                        TextFieldVisible().TextOutlineField(
                            docsName,
                            textFont,
                            "Название....."
                        ) { newText -> docsName = newText }
                    } else {
                        textFont.WhiteText(childDocs.name)
                    }
                }

                InputInformationCard().AddInformationCard(
                    "Информация о документе",
                    textFont,
                    horizontalpadding = 2.dp,
                    verticalpadding = 5.dp
                ) {
                    if (isChangeAct) {
                        TextFieldVisible().TextOutlineField(
                            docsInfo,
                            textFont,
                            "Дополнительная информация....."
                        ) { newText -> docsInfo = newText }
                    } else {
                        textFont.WhiteText(childDocs.Info)
                    }
                }
                Column {
                    if (isChangeAct) {
                        Column {
                            Row(Modifier.fillMaxWidth()){
                                textFont.BlueText("сбросить")
                                Button(onClick = {
                                    val newDocument = DomainDocumentsData(
                                        name = docsName,
                                        Info = docsInfo,
                                        image = imageList.toList()
                                    )
                                    // Передаем через callback
                                    onSave(newDocument)
                                    openDocumentWindow.value = false
                                },Modifier.weight(1f)) {
                                    textFont.WhiteText("Применить")
                                }
                                Button(onClick = {
                                    openDocumentWindow.value = false
                                },Modifier.weight(1f)) {
                                    textFont.WhiteText("Отмена")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}