package com.example.wizkids.presentation.ui.sharedUI.ui

import androidx.compose.ui.graphics.Color
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_DEBT
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_NOT_PAYED
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAYED
import com.example.wizkids.presentation.ui.sharedUI.constant.SharedUiLogicConstant.INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAY_SOON
import com.example.wizkids.ui.theme.payDebt
import com.example.wizkids.ui.theme.payFalse
import com.example.wizkids.ui.theme.payLater
import com.example.wizkids.ui.theme.payTrue

class ChildPayStatusHelper {
    fun calculatePayStatus(balance: Int, visitPrice: Int): String {
        return when {
            balance < 0 -> INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_DEBT
            balance == visitPrice -> INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAY_SOON
            balance > visitPrice -> INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAYED
            else -> INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_NOT_PAYED
        }
    }
    fun colorPayStatus(payStatus: String): Color {
        return when (payStatus) {
            INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_DEBT -> payDebt
            INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAY_SOON -> payLater
            INFORMATION_IMAGE_AND_PAY_STATUS_STATUS_PAYED -> payTrue
            else -> payFalse
        }
    }
}