package com.example.wizkids.presentation.sharedUI

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.CHILD_CARD_DEFAULT_VALUE_PAY_STATUS
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_FULL_NAME_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_LAST_NAME
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_TAKE_INDEX
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_BORDER_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_COLUMN_COLUMN_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_COLUMN_COLUMN_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_COLUMN_COLUMN_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_COLUMN_PAY_STATUS_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_COLUMN_PAY_STATUS_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_BOX_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_BOX_INITIAL_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_BOX_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_COLUMN_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_MAIN_CONTAINER_VERTICAL_CLIP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_MAIN_CONTAINER_VERTICAL_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_NAME_AND_AGE_COLUMN_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_NAME_AND_AGE_COLUMN_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_NAME_SIZE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_PAY_STATUS_TEXT_PADDING_HORIZONTAL
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.CHILD_CARD_PAY_STATUS_TEXT_PADDING_VERTICAL
import com.example.wizkids.presentation.ui.sharedUI.ui.ChangeInformationWindow.FinanceInfoWindow.ChildFinanceWindow
import com.example.wizkids.presentation.ui.sharedUI.ui.ChildPayStatusHelper
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.ui.theme.blackColor
import com.example.wizkids.ui.theme.blueColor
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.util.AgeHelper

class ChildView {
    @Composable
    fun ChildScreen(
        textFont: TextFont,
        allChild: List<DomainChildModel> = listOf(),
        isMainActivity: Boolean,
        context: Context,
        childViewModel: ChildViewModel,
        onClick: (DomainChildModel) -> Unit,
    ) {
        for (child in allChild) {
            ChildCard(child, textFont, context, isMainActivity, childViewModel, onClick)
        }
    }

    @Composable
    fun ChildCard(
        child: DomainChildModel,
        textFont: TextFont,
        context: Context,
        isMainActivity: Boolean,
        childViewModel: ChildViewModel,
        onClick: (DomainChildModel) -> Unit
    ) {
        var balance = remember { mutableStateOf(child.currentBalance) }
        val parts = child.name.trim().split(" ")
        val initials = if (parts.size >= CHILD_CARD_INITIAL_FULL_NAME_INDEX) {
            "${parts[CHILD_CARD_INITIAL_PARTS_INDEX_FIRST_NAME].first()}${parts[CHILD_CARD_INITIAL_PARTS_INDEX_LAST_NAME].first()}"
        } else {
            child.name.take(CHILD_CARD_INITIAL_PARTS_INDEX_TAKE_INDEX)
        }
        var openWindowChangeBalance = remember { mutableStateOf(false) }
        val childVisitComing = remember { mutableStateListOf<DomainVisitModel>() }
        var payStatus by remember { mutableStateOf(CHILD_CARD_DEFAULT_VALUE_PAY_STATUS) }
        var payStatusColor by remember { mutableStateOf(grayColor) }
        LaunchedEffect(balance.value) {
            payStatus = ChildPayStatusHelper().calculatePayStatus(balance.value, child.visitPrice)
            payStatusColor = ChildPayStatusHelper().colorPayStatus(payStatus)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = CHILD_CARD_MAIN_CONTAINER_VERTICAL_PADDING.dp)
                .clip(RoundedCornerShape(CHILD_CARD_MAIN_CONTAINER_VERTICAL_CLIP.dp))
                .background(color = cardBackground)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    onClick.invoke(child)
                }
                .border(CHILD_CARD_BORDER_WIDTH.dp, blackColor)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.padding(start = CHILD_CARD_IMAGE_COLUMN_PADDING.dp)) {
                    Box(
                        modifier = Modifier
                            .size(CHILD_CARD_IMAGE_BOX_SIZE.dp)
                            .clip(RoundedCornerShape(CHILD_CARD_IMAGE_BOX_CLIP.dp))
                            .background(lightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        textFont.ItalyText(initials, fontSize = CHILD_CARD_IMAGE_BOX_INITIAL_SIZE)
                        AsyncImage(
                            model = child.imagePath,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = Crop
                        )
                    }
                }
                Column(
                    Modifier
                        .weight(CHILD_CARD_NAME_AND_AGE_COLUMN_WEIGHT)
                        .padding(CHILD_CARD_NAME_AND_AGE_COLUMN_PADDING.dp)
                ) {
                    textFont.ItalyText(child.name, fontSize = CHILD_CARD_NAME_SIZE)
                    textFont.GrayText("${AgeHelper().getAgeFromDate(child.dateOfBirth)} • №${child.id}")
                }
                if (isMainActivity) {

                    Column(
                        Modifier
                            .weight(CHILD_CARD_COLUMN_PAY_STATUS_WEIGHT)
                            .padding(CHILD_CARD_COLUMN_PAY_STATUS_PADDING.dp)
                            .fillMaxWidth(),
                        Arrangement.Center,
                        Alignment.End
                    ) {
                        Column(
                            Modifier
                                .fillMaxWidth(CHILD_CARD_COLUMN_COLUMN_WIDTH)
                                .clip(RoundedCornerShape(CHILD_CARD_COLUMN_COLUMN_CLIP.dp))
                                .padding(CHILD_CARD_COLUMN_COLUMN_PADDING.dp)
                                .background(color = payStatusColor),
                            Arrangement.Center,
                            Alignment.CenterHorizontally
                        ) {
                            textFont.WhiteText(
                                payStatus,
                                modifier = Modifier.padding(
                                    horizontal = CHILD_CARD_PAY_STATUS_TEXT_PADDING_HORIZONTAL.dp,
                                    vertical = CHILD_CARD_PAY_STATUS_TEXT_PADDING_VERTICAL.dp
                                )
                            )
                        }
                        Column(
                            Modifier
                                .fillMaxWidth(CHILD_CARD_COLUMN_COLUMN_WIDTH)
                                .clip(RoundedCornerShape(CHILD_CARD_COLUMN_COLUMN_CLIP.dp))
                                .padding(CHILD_CARD_COLUMN_COLUMN_PADDING.dp)
                                .background(
                                    blueColor
                                ), Arrangement.Center, Alignment.CenterHorizontally
                        ) {
                            Column {
                                textFont.WhiteText(
                                    "${stringResource(R.string.balance)}:${balance.value}",
                                    modifier = Modifier.padding(
                                        horizontal = CHILD_CARD_PAY_STATUS_TEXT_PADDING_HORIZONTAL.dp,
                                        vertical = CHILD_CARD_PAY_STATUS_TEXT_PADDING_VERTICAL.dp
                                    )
                                )
                                textFont.WhiteText(
                                    "${stringResource(R.string.price_label)}:${child.visitPrice}",
                                    modifier = Modifier.padding(
                                        horizontal = CHILD_CARD_PAY_STATUS_TEXT_PADDING_HORIZONTAL.dp,
                                        vertical = CHILD_CARD_PAY_STATUS_TEXT_PADDING_VERTICAL.dp
                                    )
                                )
                            }
                        }
                        Column(
                            Modifier
                                .fillMaxWidth(CHILD_CARD_COLUMN_COLUMN_WIDTH)
                                .clip(RoundedCornerShape(CHILD_CARD_COLUMN_COLUMN_CLIP.dp))
                                .padding(CHILD_CARD_COLUMN_COLUMN_PADDING.dp)
                                .background(
                                    lightGray
                                )
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    openWindowChangeBalance.value = true
                                }, Arrangement.Center, Alignment.CenterHorizontally
                        ) {
                            textFont.WhiteText(
                                stringResource(R.string.change_balance),
                                modifier = Modifier.padding(
                                    horizontal = CHILD_CARD_PAY_STATUS_TEXT_PADDING_HORIZONTAL.dp,
                                    vertical = CHILD_CARD_PAY_STATUS_TEXT_PADDING_VERTICAL.dp
                                )
                            )
                        }
                    }
                    if (openWindowChangeBalance.value) {
                        ChildFinanceWindow().WindowAddFinanceInformation(
                            openWindowChangeBalance,
                            textFont,
                            stringResource(R.string.balance),
                            balance.value
                        ) { newBalance ->
                            Log.d("sajergnsdkejgb", newBalance.toString())
                            balance.value = newBalance
                            childViewModel.saveChild(
                                DomainChildModel(
                                    id = child.id,
                                    imagePath = child.imagePath,
                                    name = child.name,
                                    additionalInfo = child.additionalInfo,
                                    dateOfBirth = child.dateOfBirth,
                                    documents = child.documents,
                                    learningStages = child.learningStages,
                                    visitPrice = child.visitPrice,
                                    currentBalance = balance.value,
                                    childDayOfWeekVisit = child.childDayOfWeekVisit,
                                ), childVisitComing
                            )
                            openWindowChangeBalance.value = false
                        }
                    }
                }
            }
        }
    }
}