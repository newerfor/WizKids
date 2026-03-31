package com.example.wizkids.presentation.UserProfile.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainDocumentsModel
import com.example.core_domain.model.DomainUserModel
import com.example.core_ui.ui.AddInformationCard
import com.example.core_ui.ui.ButtonVisibleColumn
import com.example.core_ui.ui.ChangeInformationWindow.DocumentsInforamtionCard.AddOrWatchDocumentInformation
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardBackGround
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardValueGrayAndWhiteText
import com.example.core_ui.ui.ChangeInformationWindow.InformationImageAndPayStatus
import com.example.core_ui.ui.RoundLoad
import com.example.core_ui.ui.TextFont
import com.example.core_util.deleteImageByPath
import com.example.core_util.getAgeFromDate
import com.example.core_viewmodel.child.ChildrenUiState
import com.example.core_viewmodel.user.UserUiState
import com.example.core_viewmodel.user.UserViewModel
import com.example.feature_userprofile.R
import com.example.feature_userprofile.constant.UserProfileLogicConstant.DEFAULT_VALUE_TOTAL_PROFIT
import com.example.feature_userprofile.constant.UserProfileLogicConstant.DEFAULT_VALUE_TOTAL_VISITS
import com.example.feature_userprofile.constant.UserProfileLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.feature_userprofile.constant.UserProfileLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME


@Composable
fun UserFullInfo(
    userInfo: DomainUserModel,
    textFont: TextFont,
    context: Context,
    userViewModel: UserViewModel,
    onClickGoToAddUserInfo: () -> Unit,
    childUiState: ChildrenUiState
) {
    val mutableImage = remember { mutableStateOf<String?>(userInfo.imagePath) }
    var mutableDocs = remember { mutableStateListOf<DomainDocumentsModel>() }
    val parts = userInfo.name.trim().split(" ")
    val userFirstName =
        remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME]) }
    val userLastName =
        remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME]) }
    val totalVisitsSuccess= remember { mutableStateOf(DEFAULT_VALUE_TOTAL_VISITS) }
    val totalProfitSuccess= remember { mutableStateOf(DEFAULT_VALUE_TOTAL_PROFIT) }
    Log.d("aejgdjkghbdghb",childUiState.toString())
    LaunchedEffect(childUiState) {
        if (childUiState is ChildrenUiState.Success) {
            totalVisitsSuccess.value = childUiState.child.sumOf { it.numbers_visits }
            totalProfitSuccess.value = childUiState.child.sumOf { it.general_profit }
        }
    }
    mutableDocs.addAll(userInfo.documents)
    InformationCardBackGround {
        InformationImageAndPayStatus(
            textFont,
            mutableImage,
            firstName = userFirstName,
            lastName = userLastName
        )
        AddInformationCard(
            stringResource(R.string.basic_information),
            textFont
        ) {
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.full_name),
                userInfo.name
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.birth_date),
                userInfo.dateOfBirth
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.about_me),
                userInfo.about
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.work_experience),
                getAgeFromDate(userInfo.workExperience)

            )
        }

        AddInformationCard(
            stringResource(R.string.contact_information),
            textFont
        ) {
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.phone),
                userInfo.phone
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.email),
                userInfo.email
            )
        }

        AddInformationCard(
            stringResource(R.string.education_and_qualification),
            textFont
        ) {
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.education_level),
                userInfo.educationLevel
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.specialization),
                userInfo.specialization
            )
        }
        AddInformationCard(
            stringResource(R.string.documents),
            textFont
        ) {
            AddOrWatchDocumentInformation(textFont, mutableDocs, false)
        }
        AddInformationCard(stringResource(R.string.total_visit_and_profit),textFont) {
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.total_success_visit),
                totalVisitsSuccess.value.toString()
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.total_profit),
                totalProfitSuccess.value.toString()
            )
        }
        ButtonVisibleColumn(
            mapOf(
                stringResource(R.string.edit_profile_button) to {
                    onClickGoToAddUserInfo.invoke()
                },
                stringResource(R.string.delete_button) to {
                    deleteImageByPath(userInfo.imagePath)
                    for (document in userInfo.documents) {
                        for (docImage in document.imagePaths) {
                            deleteImageByPath(docImage)
                        }
                    }
                    userViewModel.deleteUser()
                },
                stringResource(R.string.convert_to_pdf_button) to {
                    PDFDownloader(context).createAndDownloadPDF(userInfo)
                },
            ),
            textFont
        )
    }
}
