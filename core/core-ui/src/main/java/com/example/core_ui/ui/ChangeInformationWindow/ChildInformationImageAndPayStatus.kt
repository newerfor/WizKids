package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_INITIAL_FIRST_NAME_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_INITIAL_SECOND_NAME_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_SHAPE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_WIDTH_ERROR
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_ICON_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.INFORMATION_IMAGE_AND_PAY_STATUS_SPACER_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.ui.ChildPayStatusHelper
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.redColor
import com.example.wizkids.util.ImageHelper
import java.util.UUID

class ChildInformationImageAndPayStatus {
    @Composable
    fun InformationImageAndPayStatus(
        textFont: TextFont,
        childImage: MutableState<String?>?,
        price: Int? = null,
        balance: Int? = null,
        childImageError: Boolean = false,
        isChangeAct: Boolean = false,
        firstName: MutableState<String>,
        lastName: MutableState<String>
    ) {
        var payStatus by remember { mutableStateOf("") }
        var payColor by remember { mutableStateOf(Color.Transparent) }
        var imageUri by remember { mutableStateOf<Uri?>(null) }
        val context = LocalContext.current
        var imagePathState by remember { mutableStateOf("") }
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                imageUri = uri
                imagePathState = uri.toString()
                // Сохраняем изображение ТОЛЬКО когда оно выбрано
                if (uri != null) {
                    val fileName = "${UUID.randomUUID()}.jpg"
                    childImage?.value =
                        ImageHelper().saveImageToLocalStorage(context, uri, fileName, childImage)
                            .toString()
                }
            }
        )
        LaunchedEffect(balance) {
            payStatus = ChildPayStatusHelper().calculatePayStatus(balance ?: 0, price ?: 0)
            payColor = ChildPayStatusHelper().colorPayStatus(payStatus)
        }
        Column(Modifier.fillMaxWidth()) {
            Column(
                Modifier.fillMaxWidth(),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .size(INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_SIZE.dp)
                        .clip(RoundedCornerShape(INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_CLIP.dp))
                        .border(
                            width = if (childImageError) INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_WIDTH_ERROR.dp else INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_WIDTH.dp,
                            color = if (childImageError) redColor else lightGray,
                            shape = RoundedCornerShape(
                                INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_BORDER_SHAPE.dp
                            )
                        )
                        .background(lightGray)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            if (isChangeAct) {
                                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (childImageError) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "",
                            Modifier.size(INFORMATION_IMAGE_AND_PAY_STATUS_BOX_IMAGE_ICON_SIZE.dp),
                            tint = redColor
                        )
                    }
                    if (childImage?.value == null || childImage.value == "") {
                        textFont.ItalyText(
                            "${
                                if (firstName.value.isNotEmpty()) {
                                    firstName.value[INFORMATION_INITIAL_FIRST_NAME_INDEX]
                                } else {
                                    ""
                                }
                            } ${
                                if (lastName.value.isNotEmpty()) {
                                    lastName.value[INFORMATION_INITIAL_SECOND_NAME_INDEX]
                                } else {
                                    ""
                                }
                            }"
                        )
                    } else {
                        childImage.value?.let { image ->
                            if (image.isNotEmpty()) {
                                AsyncImage(
                                    model = image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
            Spacer(Modifier.height(INFORMATION_IMAGE_AND_PAY_STATUS_SPACER_HEIGHT.dp))
            Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                Column(
                    Modifier
                        .clip(
                            RoundedCornerShape(
                                INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_CLIP.dp
                            )
                        )
                        .background(payColor)
                ) {
                    textFont.WhiteText(
                        payStatus,
                        modifier = Modifier.padding(
                            horizontal = INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_PADDING_HORIZONTAL.dp,
                            vertical = INFORMATION_IMAGE_AND_PAY_STATUS_BOX_PAY_STATUS_PADDING_VERTICAL.dp
                        )
                    )
                }
            }
        }
    }
}