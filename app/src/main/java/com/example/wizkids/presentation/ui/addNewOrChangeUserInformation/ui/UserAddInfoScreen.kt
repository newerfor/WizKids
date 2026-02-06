package com.example.wizkids.presentation.addNewOrChangeUserInformation.ui

import android.R.attr.name
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
import androidx.compose.material3.Button
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
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_ABOUT
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_DATE_OF_BIRTH
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EDUCATION_LEVEL
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EMAIL
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_FIRST_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_FULLNAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_IMAGE
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_LAST_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_MIDDLE_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_PHONE
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_SPECIALIZATION
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_WORK_EXPERIENCE
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_INDEX_OF_FIRST_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_INDEX_OF_LAST_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserLogicConstant.ADD_NEW_OR_CHANGE_USER_INDEX_OF_MIDDLE_NAME
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeUserViewConstant.ADD_NEW_OR_CHANGE_USER_BUTTON_WEIGHT
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationImageAndPayStatus
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard.DocumentInformation
import com.example.wizkids.presentation.sharedUI.GetDate
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.ui.theme.redColor
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_USER_PROFILE
import com.example.wizkids.util.IntentHelper

class UserAddInfoScreen {
    @Composable
    fun UserAddInfo(
        userInfo: DomainUserModel?,
        textFont: TextFont,
        viewModel: UserViewModel,
        context: Context
    ) {
        val fullName = userInfo?.name ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_FULLNAME
        val parts = fullName.split(" ")

        var userFirstName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_USER_INDEX_OF_FIRST_NAME)
                    ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_FIRST_NAME
            )
        }
        var userLastName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_USER_INDEX_OF_LAST_NAME)
                    ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_LAST_NAME
            )
        }
        var userMiddleName = remember {
            mutableStateOf(
                parts.getOrNull(ADD_NEW_OR_CHANGE_USER_INDEX_OF_MIDDLE_NAME)
                    ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_MIDDLE_NAME
            )
        }
        var userImage = remember { mutableStateOf(userInfo?.imagePath) }
        var userDateOfBirth = remember {
            mutableStateOf(
                userInfo?.dateOfBirth ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_DATE_OF_BIRTH
            )
        }
        var userAbout = remember {
            mutableStateOf(
                userInfo?.about ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_ABOUT
            )
        }
        var userWorkExperience = remember {
            mutableStateOf(
                userInfo?.workExperience ?: ""
            )
        }
        var userPhone = remember {
            mutableStateOf(
                userInfo?.phone ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_PHONE
            )
        }
        var userEmail = remember {
            mutableStateOf(
                userInfo?.email ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EMAIL
            )
        }
        var userEducationLevel = remember {
            mutableStateOf(
                userInfo?.educationLevel ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EDUCATION_LEVEL
            )
        }
        var userSpecialization = remember {
            mutableStateOf(
                userInfo?.specialization ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_SPECIALIZATION
            )
        }
        var userDocuments = remember {
            mutableStateListOf<DomainDocumentsModel>().apply {
                addAll(userInfo?.documents ?: emptyList())
            }
        }
        var openWindowGetDateWorkExpirience = remember { mutableStateOf(false) }
        var openWindowGetDateDayOfBirth = remember { mutableStateOf(false) }
        var hasUserFirstNameError = remember { mutableStateOf(false) }
        var hasUserMiddleNameError = remember { mutableStateOf(false) }
        var hasUserDateOfBirthError = remember { mutableStateOf(false) }
        var hasUserLastNameError = remember { mutableStateOf(false) }
        var hasUserPhoneError = remember { mutableStateOf(false) }
        var hasUserEmailError = remember { mutableStateOf(false) }
        var hasWorkExperienceError = remember { mutableStateOf(false) }
        ChildInformationCardBackGround().InformationCardbackGround {

            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                userImage,
                isChangeAct = true,
                firstName = userFirstName,
                lastName = userLastName
            )
            InputInformationCard().AddInformationCard(
                stringResource(R.string.first_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userFirstName.value,
                    textFont,
                    stringResource(R.string.first_name_hint),
                    trailingIcon = hasUserFirstNameError.value
                ) { newText ->
                    userFirstName.value = newText
                    hasUserFirstNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.last_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userLastName.value,
                    textFont,
                    stringResource(R.string.last_name_hint),
                    trailingIcon = hasUserLastNameError.value
                ) { newText ->
                    userLastName.value = newText
                    hasUserLastNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.middle_name_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userMiddleName.value,
                    textFont,
                    stringResource(R.string.middle_name_hint),
                    trailingIcon = hasUserMiddleNameError.value
                ) { newText ->
                    userMiddleName.value = newText
                    hasUserMiddleNameError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.birth_date_label),
                textFont = textFont
            ) {
                Row() {
                    textFont.WhiteText(userDateOfBirth.value.ifEmpty { stringResource(R.string.date_not_selected) })
                    if (hasUserDateOfBirthError.value) {
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
                            openWindowGetDateDayOfBirth.value = true
                            hasUserDateOfBirthError.value = false
                        },
                        stringResource(R.string.reset_button) to {
                            userDateOfBirth.value = ""
                        },

                        ),
                    textFont
                )
                if (openWindowGetDateDayOfBirth.value) {
                    GetDate().DatePickerExample(openWindowGetDateDayOfBirth, textFont) { newDate ->
                        userDateOfBirth.value = newDate
                    }
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.about_me_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userAbout.value,
                    textFont,
                    stringResource(R.string.about_me_hint),
                ) { newText ->
                    userAbout.value = newText
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.expirience_label),
                textFont = textFont
            ) {
                Row() {
                    textFont.WhiteText(userWorkExperience.value.ifEmpty { stringResource(R.string.date_not_selected) })
                    if (hasWorkExperienceError.value) {
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
                            openWindowGetDateWorkExpirience.value = true
                            hasWorkExperienceError.value = false
                        },
                        stringResource(R.string.reset_button) to {
                            userWorkExperience.value = ""
                        },

                        ),
                    textFont
                )
                if (openWindowGetDateWorkExpirience.value) {
                    GetDate().DatePickerExample(openWindowGetDateWorkExpirience, textFont) { newDate ->
                        userWorkExperience.value = newDate
                    }
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.phone_label),
                textFont = textFont
            ) {
                TextFieldVisible().NumberOutlineField(
                    userPhone.value,
                    textFont,
                    stringResource(R.string.phone_hint),
                    trailingIcon = hasUserPhoneError.value
                ) { newText ->
                    userPhone.value = newText
                    hasUserPhoneError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.email_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userEmail.value,
                    textFont,
                    stringResource(R.string.email_hint),
                    trailingIcon = hasUserEmailError.value
                ) { newText ->
                    userEmail.value = newText
                    hasUserEmailError.value = false
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.learning_level_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userEducationLevel.value,
                    textFont,
                    stringResource(R.string.learning_level_hint),
                ) { newText ->
                    userEducationLevel.value = newText
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.specialization_label),
                textFont = textFont
            ) {
                TextFieldVisible().TextOutlineField(
                    userSpecialization.value,
                    textFont,
                    stringResource(R.string.specialization_hint),
                ) { newText ->
                    userSpecialization.value = newText
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.documents_label),
                textFont
            ) {
                DocumentInformation().AddOrWatchDocumentInformation(textFont, userDocuments, true)
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
                            userFirstName.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_FIRST_NAME
                            userLastName.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_LAST_NAME
                            userMiddleName.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_MIDDLE_NAME
                            userImage.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_IMAGE
                            userDateOfBirth.value =
                                ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_DATE_OF_BIRTH
                            userAbout.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_ABOUT
                            userWorkExperience.value =
                                ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_WORK_EXPERIENCE
                            userPhone.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_PHONE
                            userEmail.value = ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EMAIL
                            userEducationLevel.value =
                                ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_EDUCATION_LEVEL
                            userSpecialization.value =
                                ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_SPECIALIZATION
                            userDocuments.clear()
                        })
                }
                ButtonView().ButtonVisibleRow(
                    mapOf(
                        stringResource(R.string.save_button) to {
                            if (userFirstName.value.isEmpty()) {
                                hasUserFirstNameError.value = true
                            }
                            if (userLastName.value.isEmpty()) {
                                hasUserLastNameError.value = true
                            }
                            if (userMiddleName.value.isEmpty()) {
                                hasUserMiddleNameError.value = true
                            }
                            if (userDateOfBirth.value.isEmpty()) {
                                hasUserDateOfBirthError.value = true
                            }
                            if (userPhone.value.isEmpty()) {
                                hasUserPhoneError.value = true
                            }
                            if (userEmail.value.isEmpty()) {
                                hasUserEmailError.value = true
                            }
                            if (userWorkExperience.value.isEmpty()) {
                                hasWorkExperienceError.value = true
                            }
                            if (userDateOfBirth.value.isEmpty()) {
                                hasUserDateOfBirthError.value = true
                            }
                            if (!hasUserDateOfBirthError.value&&!hasWorkExperienceError.value&&!hasUserFirstNameError.value && !hasUserLastNameError.value && !hasUserMiddleNameError.value && !hasUserDateOfBirthError.value && !hasUserPhoneError.value && !hasUserEmailError.value) {
                                val user = DomainUserModel(
                                    imagePath = userImage.value
                                        ?: ADD_NEW_OR_CHANGE_USER_DEFAULT_VALUE_IMAGE,
                                    name = "${userLastName.value} ${userFirstName.value} ${userMiddleName.value}",
                                    dateOfBirth = userDateOfBirth.value,
                                    about = userAbout.value,
                                    workExperience = userWorkExperience.value,
                                    phone = userPhone.value,
                                    email = userEmail.value,
                                    educationLevel = userEducationLevel.value,
                                    specialization = userSpecialization.value,
                                    documents = userDocuments
                                )
                                viewModel.saveUser(user)
                                IntentHelper().intentStart(KEY_ACTIVITY_USER_PROFILE, context)
                            }

                        },
                        ),
                    textFont
                )
            }
        }
    }
}