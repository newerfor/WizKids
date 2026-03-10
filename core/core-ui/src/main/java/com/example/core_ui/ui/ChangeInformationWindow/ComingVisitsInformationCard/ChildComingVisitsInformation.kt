package com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ComingVisitsInformationCard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_STATUS_COMING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.COMING_STATUS_NOT_COMING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_BOX_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_BOX_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_MAIN_CONTAINER_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_ROW_PADDING
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_ROW_ROW_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.COMING_VISITS_INFORMATION_TEXT_LABEL_WEIGHT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiViewConstant.VISIT_INFORMATION_ICON_SIZE
import com.example.wizkids.ui.theme.blackColor
import com.example.wizkids.ui.theme.payFalse
import com.example.wizkids.ui.theme.payLater
import com.example.wizkids.ui.theme.payTrue

class ChildComingVisitsInformation {
    @Composable
    fun ComingVisitsInformation(
        textFont: TextFont,
        visit: DomainVisitModel,
        onChangeAct: Boolean = false,
        onClick: (DomainVisitModel) -> Unit = {},
        onDelete: () -> Unit = {}
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(COMING_VISITS_INFORMATION_MAIN_CONTAINER_PADDING.dp)
                .background(
                    when (visit.visitStatus) {
                        COMING_STATUS_COMING -> payTrue
                        COMING_STATUS_NOT_COMING -> payFalse
                        else -> payLater
                    }
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ) {
                    onClick.invoke(visit)
                }) {
            Row(Modifier.padding(COMING_VISITS_INFORMATION_ROW_PADDING.dp)) {
                textFont.WhiteText(
                    "${visit.date}/${visit.time} - ${visit.visitName}",
                    Modifier.weight(COMING_VISITS_INFORMATION_TEXT_LABEL_WEIGHT)
                )
                if (onChangeAct) {
                    Row(Modifier.weight(COMING_VISITS_INFORMATION_ROW_ROW_WEIGHT)) {
                        Box(
                            Modifier
                                .weight(COMING_VISITS_INFORMATION_BOX_WEIGHT)
                                .padding(COMING_VISITS_INFORMATION_BOX_PADDING.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = rememberRipple()
                                ) {
                                    onDelete.invoke()
                                }, contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                "",
                                Modifier.size(VISIT_INFORMATION_ICON_SIZE.dp),
                                tint = blackColor
                            )
                        }
                    }
                }
            }
        }
    }
}