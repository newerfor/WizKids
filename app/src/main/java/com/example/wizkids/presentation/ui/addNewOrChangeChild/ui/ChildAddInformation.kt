package com.example.wizkids.presentation.addNewOrChangeChild.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_FIRST_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_MIDDLE_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_ADDITIONAL_INFO
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_BALANCE
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_DAY_OF_BIRTH
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_IMAGE_PATCH
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_PRICE
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationImageAndPayStatus
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard.DocumentInformation
import com.example.wizkids.presentation.sharedUI.GetDate
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ChangeInformationWindow.FinanceInfoWindow.ChildFinanceWindow
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.child.ChildViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitViewModel
import com.example.wizkids.presentation.viewModel.visit.VisitsUiState
import com.example.wizkids.ui.theme.redColor
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_MAIN
import com.example.wizkids.util.IntentHelper

class ChildAddInformation {
    @Composable
    fun AddInformation(
        textFont: TextFont,
        sampleChild: DomainChildModel? = null,
        context: Context,
        id: Int?,
        childViewModel: ChildViewModel,
        visitUiState: VisitsUiState = VisitsUiState.Loading,
        visitViewModel: VisitViewModel,
    ) {
        val fullName = sampleChild?.name ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
        val parts = fullName.split(" ")
        val openWindowBalance = remember { mutableStateOf(false) }
        val openWindowPrice = remember { mutableStateOf(false) }
        var childFirstName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_FIRST_NAME)
                    ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
            )
        }
        var childLastName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_MIDDLE_NAME)
                    ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
            )
        }
        var childMiddleName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_MIDDLE_NAME)
                    ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
            )
        }
        var hasChildFirstNameError = remember { mutableStateOf(false) }
        var hasChildLastNameError = remember { mutableStateOf(false) }
        var hasChildMiddleNameError = remember { mutableStateOf(false) }
        var childAdditionalInfo = remember {
            mutableStateOf(
                sampleChild?.additionalInfo ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_ADDITIONAL_INFO
            )
        }
        var isDatePickerVisible = remember { mutableStateOf(false) }
        var childDateOfBirth = remember {
            mutableStateOf(
                sampleChild?.dateOfBirth ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_DAY_OF_BIRTH
            )
        }
        var childDayOfBirthError = remember { mutableStateOf(false) }
        var hasChildImageError = remember { mutableStateOf(false) }
        var childVisitPrice = remember {
            mutableStateOf(
                sampleChild?.visitPrice ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_PRICE
            )
        }
        var childCurrentBalance = remember {
            mutableStateOf(
                sampleChild?.currentBalance ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_BALANCE
            )
        }
        var childDocuments = remember {
            mutableStateListOf<DomainDocumentsModel>().apply {
                addAll(sampleChild?.documents ?: emptyList())
            }
        }
        var childImage = remember { mutableStateOf(sampleChild?.imagePath) }
        var childLearningStages =
            remember { mutableStateOf(sampleChild?.learningStages ?: emptyList()) }
        val visitComingFromDataBase = remember { mutableStateListOf<DomainVisitModel>() }
        val childVisitComing = remember { mutableStateListOf<DomainVisitModel>() }
        var childWorkStageError = remember { mutableStateOf(false) }
        when (visitUiState) {
            is VisitsUiState.Loading -> {}

            is VisitsUiState.Success -> {
                visitComingFromDataBase.addAll(visitUiState.visit)
                childVisitComing.addAll(visitUiState.visit)
            }

            is VisitsUiState.Error -> {}
        }
        ChildInformationCardBackGround().InformationCardbackGround {
            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                childImage,
                price = childVisitPrice.value,
                balance = childCurrentBalance.value,
                hasChildImageError.value,
                true,
                firstName = childFirstName,
                lastName = childLastName
            )
            InputInformationCard().AddInformationCard(
                stringResource(R.string.first_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    childFirstName.value,
                    textFont,
                    stringResource(R.string.first_name_hint),
                    trailingIcon = hasChildFirstNameError.value
                ) { newText ->
                    childFirstName.value = newText
                    hasChildFirstNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.last_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    childLastName.value,
                    textFont,
                    stringResource(R.string.last_name_hint),
                    trailingIcon = hasChildLastNameError.value
                ) { newText ->
                    childLastName.value = newText
                    hasChildLastNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.middle_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    childMiddleName.value,
                    textFont,
                    stringResource(R.string.middle_name_hint),
                    trailingIcon = hasChildMiddleNameError.value
                ) { newText ->
                    childMiddleName.value = newText
                    hasChildMiddleNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.additional_info_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    childAdditionalInfo.value,
                    textFont,
                    stringResource(R.string.additional_info_hint)
                ) { newText -> childAdditionalInfo.value = newText }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.birth_date_label),
                textFont = textFont
            ) {
                Row(){
                    textFont.WhiteText(childDateOfBirth.value.ifEmpty {stringResource(R.string.date_not_selected)})
                    if (childDayOfBirthError.value) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = stringResource(R.string.error_title),
                            tint = redColor
                        )
                    }
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.select_date_button) to {
                            isDatePickerVisible.value = true
                        },
                        stringResource(R.string.reset_button) to {
                            childDateOfBirth.value = ""
                        },

                    ),
                    textFont
                )
                if (isDatePickerVisible.value) {
                    GetDate().DatePickerExample(isDatePickerVisible, textFont) { newVisit ->
                        childDateOfBirth.value = newVisit
                        childDayOfBirthError.value = false
                    }
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.finance_info_label),
                textFont = textFont
            ) {
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        "${stringResource(R.string.balance)}: ${childCurrentBalance.value} ${
                            stringResource(
                                R.string.currency_rub
                            )
                        }" to {
                            openWindowBalance.value = true
                        },
                        "${stringResource(R.string.price_label)}: ${childVisitPrice.value} ${stringResource(R.string.currency_rub)}" to {
                            openWindowPrice.value = true
                        },),
                    textFont
                )
                if(openWindowPrice.value){
                    ChildFinanceWindow().WindowAddFinanceInformation(openWindowPrice,textFont,
                        stringResource(R.string.price_label),
                        childVisitPrice.value
                    ){newFinanceInfo->
                        childVisitPrice.value = newFinanceInfo
                        openWindowPrice.value = false
                    }
                }
                if(openWindowBalance.value){
                    ChildFinanceWindow().WindowAddFinanceInformation(openWindowBalance,textFont,
                        stringResource(R.string.balance),
                        childCurrentBalance.value
                    ){newFinanceInfo->
                        childCurrentBalance.value = newFinanceInfo
                        openWindowBalance.value = false
                    }
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.documents_label),
                textFont
            ) {
                DocumentInformation().AddOrWatchDocumentInformation(textFont, childDocuments, true)
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.development_stages_label),
                textFont
            ) {
                ChildAddLearningStagesInfo().AddChildLearningStages(
                    textFont,
                    childLearningStages,
                    childWorkStageError
                )
            }

            InputInformationCard().AddInformationCard(
                stringResource(R.string.recent_visits_label),
                textFont
            ) {
                ChildAddVisitComingInfo().AddVisitComingInfo(
                    textFont,
                    childVisitComing,
                    visitViewModel,
                    sampleChild?.id
                )
            }
            Column(Modifier.fillMaxWidth()) {
                Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {
                    textFont.BlueText(
                        stringResource(R.string.reset_button),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple()
                        ) {
                            childFirstName.value = ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
                            childLastName.value = ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
                            childMiddleName.value = ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
                            childAdditionalInfo.value =
                                ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_ADDITIONAL_INFO
                            childDateOfBirth.value =
                                ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_DAY_OF_BIRTH
                            childImage.value = ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_IMAGE_PATCH
                            childVisitPrice.value = ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_PRICE
                            childCurrentBalance.value =
                                ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_BALANCE
                            childDocuments.clear()
                            childLearningStages.value = listOf()
                            childVisitComing.clear()
                        })
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.save_button) to {
                            if (childLearningStages.value.isNotEmpty()) {
                                childWorkStageError.value =
                                    childLearningStages.value.any { it.isEmpty() }
                            }
                            if (childFirstName.value.isEmpty()) {
                                hasChildFirstNameError.value = true
                            }
                            if (childLastName.value.isEmpty()) {
                                hasChildLastNameError.value = true
                            }
                            if (childMiddleName.value.isEmpty()) {
                                hasChildMiddleNameError.value = true
                            }
                            if (childDateOfBirth.value.isEmpty()) {
                                childDayOfBirthError.value = true
                            }
                            if (!hasChildFirstNameError.value && !hasChildLastNameError.value && !hasChildMiddleNameError.value && !childDayOfBirthError.value && !childWorkStageError.value) {
                                val child = DomainChildModel(
                                    id = id,
                                    imagePath = childImage.value
                                        ?: ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_IMAGE_PATCH,
                                    name = "${childLastName.value} ${childFirstName.value} ${childMiddleName.value}",
                                    additionalInfo = childAdditionalInfo.value,
                                    dateOfBirth = childDateOfBirth.value,
                                    documents = childDocuments,
                                    learningStages = childLearningStages.value,
                                    visitPrice = childVisitPrice.value,
                                    currentBalance = childCurrentBalance.value
                                )
                                childViewModel.saveChild(child, childVisitComing)
                                IntentHelper().intentStart(KEY_ACTIVITY_MAIN, context)
                            }
                        },
                    ),
                    textFont
                )
            }
        }
    }
}