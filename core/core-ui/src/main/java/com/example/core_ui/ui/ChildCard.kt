package com.example.core_ui.ui

import android.content.Context
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.R
import com.example.core_ui.constant.SharedUiLogicConstant.CHILD_CARD_DEFAULT_VALUE_PAY_STATUS
import com.example.core_ui.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_FULL_NAME_INDEX
import com.example.core_ui.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.core_ui.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_LAST_NAME
import com.example.core_ui.constant.SharedUiLogicConstant.CHILD_CARD_INITIAL_PARTS_INDEX_TAKE_INDEX
import com.example.core_ui.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_BOX_INITIAL_SIZE
import com.example.core_ui.constant.SharedUiViewConstant.CHILD_CARD_IMAGE_BOX_SIZE
import com.example.core_ui.constant.SharedUiViewConstant.CHILD_CARD_MAIN_CONTAINER_VERTICAL_CLIP
import com.example.core_ui.constant.SharedUiViewConstant.CHILD_CARD_MAIN_CONTAINER_VERTICAL_PADDING
import com.example.core_ui.constant.SharedUiViewConstant.CHILD_CARD_NAME_SIZE
import com.example.core_ui.ui.ChangeInformationWindow.FinanceInfoWindow.WindowAddFinanceInformation
import com.example.core_util.getAgeFromDate
import com.example.core_viewmodel.child.ChildViewModel
import com.example.wizkids.ui.theme.additionalTextColor
import com.example.wizkids.ui.theme.balanceBadgeBg
import com.example.wizkids.ui.theme.balanceBadgeText
import com.example.wizkids.ui.theme.cardBackground
import com.example.wizkids.ui.theme.changeBalanceBg
import com.example.wizkids.ui.theme.changeBalanceText
import com.example.wizkids.ui.theme.dividerColor
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.lightGray
import com.example.wizkids.ui.theme.metaTextColor
import com.example.wizkids.ui.theme.nameTextColor
import com.example.wizkids.ui.theme.priceBadgeBg
import com.example.wizkids.ui.theme.priceBadgeText

@Composable
fun ChildScreen(
    textFont: TextFont,
    allChild: List<DomainChildModel> = listOf(),
    isMainActivity: Boolean,
    context: Context,
    childViewModel: ChildViewModel,
    onClick: (Int?) -> Unit,
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
    onClick: (Int?) -> Unit
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
        payStatus = calculatePayStatus(balance.value, child.visitPrice)
        payStatusColor = colorPayStatus(payStatus)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = CHILD_CARD_MAIN_CONTAINER_VERTICAL_PADDING.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(cardBackground)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) { onClick.invoke(child.id) }
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        // Верхняя часть: аватар + инфо
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = child.name,
                        fontSize = CHILD_CARD_NAME_SIZE.sp,
                        fontWeight = FontWeight.Medium,
                        color = nameTextColor,
                        modifier = Modifier.weight(1f, fill = false),
                        overflow = TextOverflow.Visible
                    )
                    Spacer(Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(balanceBadgeBg)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "№${child.id}",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = balanceBadgeText
                        )
                    }
                }
                Text(
                    text = "${getAgeFromDate(child.dateOfBirth)} лет",
                    fontSize = 11.sp,
                    color = metaTextColor
                )
                if (child.additionalInfo.isNotEmpty()) {
                    Text(
                        text = child.additionalInfo,
                        fontSize = 11.sp,
                        color = additionalTextColor
                    )
                }
            }
        }

        if (isMainActivity) {
            // Разделитель
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(dividerColor)
            )
            Spacer(Modifier.height(10.dp))

            // Нижняя часть: бейджи + кнопки
            val buttonHeight = 64.dp
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Бейджи баланс + цена
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(balanceBadgeBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${stringResource(R.string.balance)}: ${balance.value} ₽",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = balanceBadgeText
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(6.dp))
                            .background(priceBadgeBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${stringResource(R.string.price_label)}: ${child.visitPrice} ₽",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = priceBadgeText
                        )
                    }
                }

                // Кнопки действий
                Column(
                    modifier = Modifier
                        .width(110.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(payStatusColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = payStatus,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                            .background(changeBalanceBg)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ) { openWindowChangeBalance.value = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.change_balance),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = changeBalanceText,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    if (openWindowChangeBalance.value) {
        WindowAddFinanceInformation(
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
                    numbers_visits = child.numbers_visits,
                    general_profit = child.general_profit,
                ), childVisitComing
            )
            openWindowChangeBalance.value = false
        }
    }
}
