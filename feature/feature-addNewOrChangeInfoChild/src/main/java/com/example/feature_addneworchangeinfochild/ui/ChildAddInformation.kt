package com.example.feature_addneworchangeinfochild.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.example.core_domain.model.DomainChildDayOfWeekVisit
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainDocumentsModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.AddInformationCard
import com.example.core_ui.ui.ButtonVisibleRow
import com.example.core_ui.ui.ChangeInformationWindow.DocumentsInforamtionCard.AddOrWatchDocumentInformation
import com.example.core_ui.ui.ChangeInformationWindow.FinanceInfoWindow.WindowAddFinanceInformation
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardBackGround
import com.example.core_ui.ui.ChangeInformationWindow.InformationImageAndPayStatus
import com.example.core_ui.ui.DatePickerExample
import com.example.core_ui.ui.TextFont
import com.example.core_ui.ui.TextOutlineField
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState
import com.example.feature_addneworchangeinfochild.R
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_FIRST_NAME
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_LAST_NAME
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_MIDDLE_NAME
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_ADDITIONAL_INFO
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_BALANCE
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_DAY_OF_BIRTH
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_IMAGE_PATCH
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_NAME
import com.example.feature_addneworchangeinfochild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_PRICE
import com.example.wizkids.ui.theme.redColor


@Composable
fun AddInformation(
    textFont: TextFont,
    sampleChild: DomainChildModel? = null,
    context: Context,
    id: Int?,
    childViewModel: ChildViewModel,
    visitUiState: VisitsUiState = VisitsUiState.Loading,
    visitViewModel: VisitViewModel,
    onBack: () -> Unit
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
    var childDateOfWeek = remember {
        mutableStateOf(
            sampleChild?.childDayOfWeekVisit ?: DomainChildDayOfWeekVisit(
                dayOfWeek = mapOf(),
                firstDate = "",
                secondDate = "",
                time = mapOf()
            )
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
            parts.getOrNull(ADD_NEW_OR_CHANGE_CHILD_DEFAULT_INDEX_LAST_NAME)
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
            visitComingFromDataBase.clear()
            childVisitComing.clear()

            visitComingFromDataBase.addAll(visitUiState.visit)
            childVisitComing.addAll(visitUiState.visit)


        }

        is VisitsUiState.Error -> {}
    }
    InformationCardBackGround {
        InformationImageAndPayStatus(
            textFont,
            childImage,
            price = childVisitPrice.value,
            balance = childCurrentBalance.value,
            hasChildImageError.value,
            true,
            firstName = childFirstName,
            lastName = childLastName
        )
        AddInformationCard(
            stringResource(R.string.first_name_label),
            textFont = textFont
        ) {
            TextOutlineField(
                childFirstName.value,
                textFont,
                stringResource(R.string.first_name_hint),
                trailingIcon = hasChildFirstNameError.value
            ) { newText ->
                childFirstName.value = newText
                hasChildFirstNameError.value = false
            }
        }
        AddInformationCard(
            stringResource(R.string.last_name_label),
            textFont = textFont
        ) {
            TextOutlineField(
                childLastName.value,
                textFont,
                stringResource(R.string.last_name_hint),
                trailingIcon = hasChildLastNameError.value
            ) { newText ->
                childLastName.value = newText
                hasChildLastNameError.value = false
            }
        }
        AddInformationCard(
            stringResource(R.string.middle_name_label),
            textFont = textFont
        ) {
            TextOutlineField(
                childMiddleName.value,
                textFont,
                stringResource(R.string.middle_name_hint),
                trailingIcon = hasChildMiddleNameError.value
            ) { newText ->
                childMiddleName.value = newText
                hasChildMiddleNameError.value = false
            }
        }
        AddInformationCard(
            stringResource(R.string.additional_info_label),
            textFont = textFont
        ) {
            TextOutlineField(
                childAdditionalInfo.value,
                textFont,
                stringResource(R.string.additional_info_hint)
            ) { newText -> childAdditionalInfo.value = newText }
        }
        AddInformationCard(
            stringResource(R.string.birth_date_label),
            textFont = textFont
        ) {
            Row() {
                textFont.WhiteText(childDateOfBirth.value.ifEmpty { stringResource(R.string.date_not_selected) })
                if (childDayOfBirthError.value) {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = stringResource(R.string.error_title),
                        tint = redColor
                    )
                }
            }
            ButtonVisibleRow(
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
                DatePickerExample(isDatePickerVisible, textFont) { newVisit ->
                    childDateOfBirth.value = newVisit
                    childDayOfBirthError.value = false
                }
            }
        }
        AddInformationCard(
            stringResource(R.string.finance_info_label),
            textFont = textFont
        ) {
            ButtonVisibleRow(
                mapOf(
                    "${stringResource(R.string.balance)}: ${childCurrentBalance.value} ${
                        stringResource(
                            R.string.currency_rub
                        )
                    }" to {
                        openWindowBalance.value = true
                    },
                    "${stringResource(R.string.price_label)}: ${childVisitPrice.value} ${
                        stringResource(
                            R.string.currency_rub
                        )
                    }" to {
                        openWindowPrice.value = true
                    },
                ),
                textFont
            )
            if (openWindowPrice.value) {
                WindowAddFinanceInformation(
                    openWindowPrice, textFont,
                    stringResource(R.string.price_label),
                    childVisitPrice.value
                ) { newFinanceInfo ->
                    childVisitPrice.value = newFinanceInfo
                    openWindowPrice.value = false
                }
            }
            if (openWindowBalance.value) {
                WindowAddFinanceInformation(
                    openWindowBalance, textFont,
                    stringResource(R.string.balance),
                    childCurrentBalance.value
                ) { newFinanceInfo ->
                    childCurrentBalance.value = newFinanceInfo
                    openWindowBalance.value = false
                }
            }
        }
        AddInformationCard(
            stringResource(R.string.documents_label),
            textFont
        ) {
            AddOrWatchDocumentInformation(textFont, childDocuments, true)
        }
        AddInformationCard(
            stringResource(R.string.development_stages_label),
            textFont
        ) {
            AddChildLearningStages(
                textFont,
                childLearningStages,
                childWorkStageError
            )
        }
        AddInformationCard(
            stringResource(R.string.recent_visits_label),
            textFont
        ) {
            AddVisitComingInfo(
                textFont,
                childVisitComing,
                visitViewModel,
                childDateOfWeek,
                sampleChild?.id,
                sampleChild?.name
                    ?: "${childLastName.value} ${childFirstName.value} ${childMiddleName.value}",
                price = if (sampleChild == null) {
                    null
                } else {
                    childVisitPrice.value
                },
                balance = if (sampleChild == null) {
                    null
                } else {
                    childCurrentBalance
                },

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
            Column(Modifier.navigationBarsPadding()){
                ButtonVisibleRow(
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
                                    currentBalance = childCurrentBalance.value,
                                    childDayOfWeekVisit = childDateOfWeek.value,
                                )
                                childViewModel.saveChild(child, childVisitComing)
                                onBack.invoke()
                            }
                        },
                    ),
                    textFont
                )
            }

        }
    }
}
