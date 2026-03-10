package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_BOX_IMAGE_CLIP_TOP_END
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_BOX_IMAGE_CLIP_TOP_START
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_BOX_IMAGE_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_BOX_TEXT_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_BACK_GROUND_SHAPE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_WIDTH
import com.example.wizkids.ui.theme.lightGray

class ChildDocumentsInformation {
    @Composable
    fun DocumentsCard(
        textFont: TextFont,
        docs: DomainDocumentsModel,
        onClick: (DomainDocumentsModel) -> Unit
    ) {
        Column(
            modifier = Modifier
                .width(DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_WIDTH.dp)
                .padding(DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_PADDING.dp)
                .background(
                    color = lightGray,
                    shape = RoundedCornerShape(
                        DOCUMENT_INFORMATION_CARD_MAIN_CONTAINER_BACK_GROUND_SHAPE.dp
                    )
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    onClick.invoke(docs)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DOCUMENT_INFORMATION_CARD_BOX_IMAGE_HEIGHT.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = DOCUMENT_INFORMATION_CARD_BOX_IMAGE_CLIP_TOP_START.dp,
                            topEnd = DOCUMENT_INFORMATION_CARD_BOX_IMAGE_CLIP_TOP_END.dp
                        )
                    )
            ) {
                AsyncImage(
                    model = docs.imagePaths[0],
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(DOCUMENT_INFORMATION_CARD_BOX_TEXT_PADDING.dp),
                contentAlignment = Alignment.Center
            ) {
                textFont.WhiteText(
                    text = docs.name,
                    modifier = Modifier.wrapContentWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}