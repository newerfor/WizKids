package com.example.wizkids.presentation.sharedUI

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
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
import com.example.wizkids.R
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.FOOTER_ITEM_DEFAULT_VALUE_PADDING_END
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.FOOTER_ITEM_DEFAULT_VALUE_PADDING_START
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.FOOTER_SELECTED_INDEX_DEFAULT_VALUE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.HEADER_DEFAULT_VALUE_INFO_SELECTION
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_END_ELSE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_END_IF
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_START_ELSE
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_ITEM_COLUMN_PADDING_START_IF
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_MAIN_CONTAINER_BORDER_WIDTH
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.FOOTER_MAIN_CONTAINER_HEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.HEADER_COLUMN_PADDING_START
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.HEADER_INFO_SELECTION_PADDING_BOTTOM
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.HEADER_INFO_SELECTION_PADDING_TOP
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.HEADER_R0W_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.ui.IconGoBackView
import com.example.wizkids.ui.theme.darkHeader
import com.example.wizkids.ui.theme.grayColor
import com.example.wizkids.ui.theme.whiteColor
import com.example.wizkids.ui.theme.yellowColor
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_CALENDAR
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_MAIN
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_USER_PROFILE
import com.example.wizkids.util.IntentHelper

class NavHelper {
    @Composable
    fun Header(nameSelection:String,infoSelection:String=HEADER_DEFAULT_VALUE_INFO_SELECTION,textFont: TextFont = TextFont(),
               isBackActivity:Boolean = false,
               onBackClick: () -> Unit = {},
    ) {
        Row(Modifier.fillMaxWidth().background(color = darkHeader)) {
            Row(
                Modifier.weight(HEADER_R0W_WEIGHT)
            ) {
                Column(Modifier.padding(start =HEADER_COLUMN_PADDING_START.dp).statusBarsPadding()) {
                    Row {
                        if (isBackActivity) {
                            IconGoBackView().IconGoBack(onBackClick)
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
                    }
                }
            }
        }
    }
    @Composable
    fun Footer(textFont: TextFont = TextFont(),context: Context) {
        var selectedIndex = remember { mutableStateOf(FOOTER_SELECTED_INDEX_DEFAULT_VALUE) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(FOOTER_MAIN_CONTAINER_HEIGHT.dp)
                .background(whiteColor)
                .border(FOOTER_MAIN_CONTAINER_BORDER_WIDTH.dp, grayColor),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FooterItem(selectedIndex=selectedIndex,itemIndex = 1,nameButton = stringResource(R.string.footer_label_main),icon = Icons.Filled.Home,textFont)
            FooterItem(selectedIndex=selectedIndex,itemIndex = 2,nameButton = stringResource(R.string.footer_label_calendar),icon = Icons.Filled.DateRange,textFont)
            FooterItem(selectedIndex=selectedIndex,itemIndex = 3,nameButton = stringResource(R.string.footer_label_user_profile),icon = Icons.Filled.Person,textFont)
            if(selectedIndex.value == 1){
                IntentHelper().intentStart(KEY_ACTIVITY_MAIN, context)
            }
            if(selectedIndex.value == 2){
                IntentHelper().intentStart(KEY_ACTIVITY_CALENDAR, context)
            }
            if(selectedIndex.value == 3){
                IntentHelper().intentStart(KEY_ACTIVITY_USER_PROFILE, context)
            }
        }
    }
    @Composable
    fun FooterItem(
        selectedIndex: MutableState<Int>,
        itemIndex: Int,
        nameButton: String,
        icon: ImageVector,
        textFont: TextFont
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            )  { selectedIndex.value = itemIndex }.padding( start =if(itemIndex == FOOTER_ITEM_DEFAULT_VALUE_PADDING_START){FOOTER_ITEM_COLUMN_PADDING_START_IF.dp}else{FOOTER_ITEM_COLUMN_PADDING_START_ELSE.dp},end =if(itemIndex == FOOTER_ITEM_DEFAULT_VALUE_PADDING_END){FOOTER_ITEM_COLUMN_PADDING_END_IF.dp}else{FOOTER_ITEM_COLUMN_PADDING_END_ELSE.dp})
        ) {
            Icon(
                imageVector = icon,
                contentDescription = nameButton,
                tint = if (selectedIndex.value == itemIndex) yellowColor
                else grayColor
            )
            textFont.UnColorText(
                text = nameButton,
                fontSize = 12,
                color = if (selectedIndex.value == itemIndex) yellowColor
                else grayColor
            )
        }
    }
}