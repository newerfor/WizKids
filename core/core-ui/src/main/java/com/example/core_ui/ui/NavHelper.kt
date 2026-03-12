package com.example.core_ui.ui

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core_navigation.NavRoutes
import com.example.core_ui.R
import com.example.core_ui.constant.SharedUiLogicConstant.FOOTER_ITEM_DEFAULT_VALUE_PADDING_END
import com.example.core_ui.constant.SharedUiLogicConstant.FOOTER_ITEM_DEFAULT_VALUE_PADDING_START
import com.example.core_ui.constant.SharedUiLogicConstant.HEADER_DEFAULT_VALUE_INFO_SELECTION
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_END_ELSE
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_END_IF
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_START_ELSE
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_START_IF
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_MAIN_CONTAINER_BORDER_WIDTH
import com.example.core_ui.constant.SharedUiViewConstant.FOOTER_MAIN_CONTAINER_HEIGHT
import com.example.core_ui.constant.SharedUiViewConstant.HEADER_COLUMN_PADDING_START
import com.example.core_ui.constant.SharedUiViewConstant.HEADER_INFO_SELECTION_PADDING_BOTTOM
import com.example.core_ui.constant.SharedUiViewConstant.HEADER_INFO_SELECTION_PADDING_TOP
import com.example.core_ui.constant.SharedUiViewConstant.HEADER_R0W_WEIGHT
import com.example.core_ui.constant.SharedUiViewConstant.ICON_EXIT_SIZE
import com.example.core_ui.constant.SharedUiViewConstant.ICON_GO_BACK_PADDING
import com.example.wizkids.ui.theme.ButtonAndInfoBlue
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.whiteColor

@Composable
fun Header(
    nameSelection: String,
    infoSelection: String = HEADER_DEFAULT_VALUE_INFO_SELECTION,
    textFont: TextFont = TextFont(),
    isBackActivity: Boolean = false,
    context: Context,
    onBackClick: () -> Unit = {},
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(color = darkHeader)
            .statusBarsPadding()
    ) {
        Row(
            Modifier
                .weight(HEADER_R0W_WEIGHT)
        ) {
            Column(
                Modifier
                    .padding(start = HEADER_COLUMN_PADDING_START.dp)
                    .statusBarsPadding()
            ) {
                Row {
                    if (isBackActivity) {
                        IconGoBack(onBackClick)
                    }
                    Column() {
                        textFont.ItalyText(text = nameSelection)
                        textFont.GrayText(
                            text = infoSelection, modifier = Modifier
                                .padding(
                                    top = HEADER_INFO_SELECTION_PADDING_TOP.dp,
                                    bottom = HEADER_INFO_SELECTION_PADDING_BOTTOM.dp
                                )
                        )
                    }
                    Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.End) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp, contentDescription = "",
                            Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    (context as? Activity)?.finishAffinity()
                                }
                                .padding(ICON_GO_BACK_PADDING.dp)
                                .size(ICON_EXIT_SIZE.dp),
                            tint = whiteColor
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun Footer(
    textFont: TextFont = TextFont(),
    context: Context,
    navController: NavController,
    currentRoute: String? = navController.currentBackStackEntry?.destination?.route
) {
    val selectedIndex = remember(currentRoute) {
        mutableStateOf(
            when (currentRoute) {
                NavRoutes.Main.routes -> 1
                NavRoutes.Calendar.routes -> 2
                NavRoutes.UserProfile.routes -> 3
                else -> 0
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(whiteColor)
            .border(FOOTER_MAIN_CONTAINER_BORDER_WIDTH.dp, grayColor)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        FooterItem(
            selectedIndex = selectedIndex,
            itemIndex = 1,
            nameButton = stringResource(R.string.footer_label_main),
            icon = Icons.Filled.Home,
            textFont,
            onClick = {
                if (selectedIndex.value != 1) {
                    navController.navigate(NavRoutes.Main.routes)
                    selectedIndex.value = 1
                }
            }
        )
        FooterItem(
            selectedIndex = selectedIndex,
            itemIndex = 2,
            nameButton = stringResource(R.string.footer_label_calendar),
            icon = Icons.Filled.DateRange,
            textFont,
            onClick = {
                if (selectedIndex.value != 2) {
                    navController.navigate(NavRoutes.Calendar.routes)
                    selectedIndex.value = 2
                }
            }
        )
        FooterItem(
            selectedIndex = selectedIndex,
            itemIndex = 3,
            nameButton = stringResource(R.string.footer_label_user_profile),
            icon = Icons.Filled.Person,
            textFont,
            onClick = {
                if (selectedIndex.value != 3) {
                    navController.navigate(NavRoutes.UserProfile.routes)
                    selectedIndex.value = 3
                }
            }
        )

    }
}

@Composable
fun FooterItem(
    selectedIndex: MutableState<Int>,
    itemIndex: Int,
    nameButton: String,
    icon: ImageVector,
    textFont: TextFont,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ) { onClick.invoke() }
            .padding(
                start = if (itemIndex == FOOTER_ITEM_DEFAULT_VALUE_PADDING_START) {
                    FOOTER_ITEM_COLUMN_PADDING_START_IF.dp
                } else {
                    FOOTER_ITEM_COLUMN_PADDING_START_ELSE.dp
                },
                end = if (itemIndex == FOOTER_ITEM_DEFAULT_VALUE_PADDING_END) {
                    FOOTER_ITEM_COLUMN_PADDING_END_IF.dp
                } else {
                    FOOTER_ITEM_COLUMN_PADDING_END_ELSE.dp
                },
            )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = nameButton,
            tint = if (selectedIndex.value == itemIndex) ButtonAndInfoBlue
            else grayColor
        )
        textFont.UnColorText(
            text = nameButton,
            fontSize = 12,
            color = if (selectedIndex.value == itemIndex) ButtonAndInfoBlue
            else grayColor
        )
    }
}
