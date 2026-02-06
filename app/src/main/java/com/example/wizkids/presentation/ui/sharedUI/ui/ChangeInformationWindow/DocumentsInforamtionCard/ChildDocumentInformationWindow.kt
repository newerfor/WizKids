package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard

import android.util.Log.i
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_ADD_IMAGE_ICON_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DESCRIPTION
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DOCS_LIST_ADD_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DOCS_LIST_REMOVE_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_NAME
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_SELECTED_IMAGE_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_SELECTED_IMAGE_MINUS_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_BOX_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_BOX_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_IMAGE_SPACER_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.DOCUMENT_INFORMATION_WINDOW_SPACER_IMAGE_TO_DOCS_INFO_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_WINDOW_DIALOG_BUTTON_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_WINDOW_DIALOG_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_WINDOW_DIALOG_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_WINDOW_DIALOG_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_WINDOW_DIALOG_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_LEAVE_ICON_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_LEAVE_ICON_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_MAIN_IMAGE_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_MAIN_IMAGE_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.ui.theme.blueColor
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.redColor
import com.example.wizkids.util.ImageHelper
import java.util.UUID

class ChildDocumentInformationWindow {
    @Composable
    fun DocumentInformationWindow(
        textFont: TextFont,
        openDocumentWindow: MutableState<Boolean>,
        docs: MutableState<DomainDocumentsModel?>,
        isChangeAct: Boolean,
        onSave: (DomainDocumentsModel) -> Unit,
        inAddIndex: Int,
        documentList: MutableList<DomainDocumentsModel>? = null
    ) {
        val imageList = remember { mutableStateListOf<String>() }
        LaunchedEffect(Unit) {
            if (docs.value?.imagePaths?.isNotEmpty() == true) {
                imageList.clear()
                imageList.addAll(docs.value!!.imagePaths)
            }
        }
        var selectedImageIndex by remember { mutableStateOf(DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_SELECTED_IMAGE_INDEX) }
        var docsName by remember { mutableStateOf(docs.value?.name ?: DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_NAME) }
        var docsInfo by remember { mutableStateOf(docs.value?.description ?: DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DESCRIPTION) }
        val context = LocalContext.current
        var hasDocsImageError by remember { mutableStateOf(false) }
        var hasDocsNameError by remember { mutableStateOf(false) }
        var hasDocsInfoError by remember { mutableStateOf(false) }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                if (uri != null) {
                    val fileName = "${UUID.randomUUID()}.jpg"
                    val imagePath = ImageHelper().saveImageToLocalStorage(
                        context,
                        uri,
                        fileName,
                    )
                    imagePath?.let { path ->
                        imageList.add(path)
                        selectedImageIndex = imageList.size - DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_SELECTED_IMAGE_MINUS_INDEX
                    }
                }
            }
        )

        Dialog(onDismissRequest = { openDocumentWindow.value = false }) {
            Column(
                Modifier
                    .fillMaxWidth(DOCUMENT_INFORMATION_WINDOW_DIALOG_WIDTH)
                    .fillMaxHeight(DOCUMENT_INFORMATION_WINDOW_DIALOG_HEIGHT)
                    .clip(RoundedCornerShape(DOCUMENT_INFORMATION_WINDOW_DIALOG_CLIP.dp))
                    .background(color = cardBackground)
                    .padding(DOCUMENT_INFORMATION_WINDOW_DIALOG_PADDING.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(Modifier.fillMaxWidth().padding(vertical = DOCUMENT_LEAVE_ICON_PADDING_VERTICAL.dp)) {
                   Column(Modifier.background(grayColor)){
                       Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = "", Modifier.clickable(
                           interactionSource = remember { MutableInteractionSource() },
                           indication = rememberRipple()
                       ) {
                           openDocumentWindow.value = false
                           if(docs.value==null) {
                               imageList.map { imagePath -> ImageHelper().deleteImageByPath(imagePath) }
                           }
                       }.size(DOCUMENT_LEAVE_ICON_SIZE.dp))
                   }
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(DOCUMENT_MAIN_IMAGE_HEIGHT.dp)
                        .background(grayColor)
                        .clip(RoundedCornerShape(DOCUMENT_MAIN_IMAGE_CLIP.dp))
                ) {
                    if (hasDocsImageError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "",
                            tint = redColor
                        )
                    }
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
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    launcher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            Arrangement.Center,
                            Alignment.CenterHorizontally
                        ) {

                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                Modifier.size(DOCUMENT_INFORMATION_WINDOW_ADD_IMAGE_ICON_SIZE.dp),
                                tint = blueColor
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(DOCUMENT_INFORMATION_WINDOW_IMAGE_SPACER_HEIGHT.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_PADDING_HORIZONTAL.dp)
                ) {
                    itemsIndexed(imageList) { index, image ->
                        Box(
                            Modifier
                                .size(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_SIZE.dp)
                                .clip(RoundedCornerShape(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_CLIP.dp))
                                .background(if (selectedImageIndex == index) lightGray else grayColor)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) { selectedImageIndex = index }
                        ) {
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                    item {
                        Box(
                            Modifier
                                .size(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_BOX_SIZE.dp)
                                .clip(RoundedCornerShape(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_BOX_CLIP.dp))
                                .background(grayColor)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    launcher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "",
                                Modifier.size(DOCUMENT_INFORMATION_WINDOW_IMAGE_LIST_ITEM_ICON_ADD_NEW_IMAGE_SIZE.dp),
                                tint = blueColor
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(DOCUMENT_INFORMATION_WINDOW_SPACER_IMAGE_TO_DOCS_INFO_HEIGHT.dp))
                InputInformationCard().AddInformationCard(
                    stringResource(R.string.name_document_add_name_docs_label),
                    textFont,
                    horizontalPadding = 2.dp,
                    verticalPadding = 5.dp
                ) {
                    if (isChangeAct) {
                        TextFieldVisible().TextOutlineField(
                            docsName,
                            textFont,
                            stringResource(R.string.name_name_other_hint),
                            trailingIcon = hasDocsNameError
                        ) {
                            newText -> docsName = newText
                            hasDocsNameError = false
                        }
                    } else {
                        textFont.WhiteText(docsName)
                    }
                }

                InputInformationCard().AddInformationCard(
                    stringResource(R.string.information_on_docs_label),
                    textFont,
                    horizontalPadding = 2.dp,
                    verticalPadding = 5.dp
                ) {
                    if (isChangeAct) {
                        TextFieldVisible().TextOutlineField(
                            docsInfo,
                            textFont,
                            stringResource(R.string.information_on_docs_hint),
                            trailingIcon = hasDocsInfoError
                        ) {
                            newText -> docsInfo = newText
                            hasDocsInfoError = false
                        }
                    } else {
                        textFont.WhiteText(docsInfo)
                    }
                }
                Column {
                    if (isChangeAct) {
                        Column {
                            val buttonMap =if(docs.value!=null && documentList!=null){
                                mapOf<String, () -> Unit>(
                                    stringResource(R.string.apply_button) to {
                                        if(imageList.isEmpty()){
                                            hasDocsImageError = true
                                        }
                                        if(docsName.isEmpty()){hasDocsNameError = true}
                                        if(docsInfo.isEmpty()){hasDocsInfoError = true}
                                        if(!hasDocsImageError && !hasDocsNameError && !hasDocsInfoError){
                                            val newDocument = DomainDocumentsModel(
                                                name = docsName,
                                                description = docsInfo,
                                                imagePaths = imageList.toList(),
                                                id = docs.value?.id ?: (inAddIndex + DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DOCS_LIST_ADD_INDEX)
                                            )
                                            onSave(newDocument)
                                            openDocumentWindow.value = false
                                        }

                                    },
                                    stringResource(R.string.delete_button) to {
                                        {
                                            imageList.map { imagePath -> ImageHelper().deleteImageByPath(imagePath) }
                                            imageList.clear()
                                            documentList.removeAt(docs.value!!.id-DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DOCS_LIST_REMOVE_INDEX)
                                            docs.value = null
                                            openDocumentWindow.value = false
                                            hasDocsImageError = false
                                        }
                                    },
                                )
                            }else{
                                mapOf(
                                    stringResource(R.string.apply_button) to {
                                        if(imageList.isEmpty()){
                                            hasDocsImageError = true
                                        }
                                        if(docsName.isEmpty()){hasDocsNameError = true}
                                        if(docsInfo.isEmpty()){hasDocsInfoError = true}
                                        if(!hasDocsImageError && !hasDocsNameError && !hasDocsInfoError){
                                            val newDocument = DomainDocumentsModel(
                                                name = docsName,
                                                description = docsInfo,
                                                imagePaths = imageList.toList(),
                                                id = docs.value?.id ?: (inAddIndex + DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DOCS_LIST_ADD_INDEX)
                                            )
                                            onSave(newDocument)
                                            openDocumentWindow.value = false
                                        }

                                    },
                                )
                            }
                            Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
                                textFont.BlueText(
                                    stringResource(R.string.reset_text), Modifier.clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple()
                                    ) {
                                        docsName = DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_NAME
                                        for (image in imageList) {
                                            ImageHelper().deleteImageByPath(image)
                                        }
                                        imageList.clear()
                                        docsInfo =
                                            DOCUMENT_INFORMATION_WINDOW_DEFAULT_VALUE_DESCRIPTION
                                    })
                            }
                            ButtonView().ButtonVisibleRow(buttonMap,textFont)
                        }
                    }
                }
            }
        }
    }
}