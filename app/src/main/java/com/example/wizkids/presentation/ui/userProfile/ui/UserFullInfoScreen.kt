package com.example.wizkids.presentation.UserProfile.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.wizkids.R
import com.example.wizkids.domain.model.DomainDocumentsModel
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.presentation.UserProfile.constant.UserProfileLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.wizkids.presentation.UserProfile.constant.UserProfileLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationCardValueGrayAndWhiteText
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.ChildInformationImageAndPayStatus
import com.example.wizkids.presentation.sharedUI.ChangeInformationWindow.DocumentsInforamtionCard.DocumentInformation
import com.example.wizkids.presentation.sharedUI.InputInformationCard
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.presentation.viewModel.user.UserViewModel
import com.example.wizkids.util.ActivityKeys.KEY_ACTIVITY_ADD_USER_INFO
import com.example.wizkids.util.AgeHelper
import com.example.wizkids.util.ImageHelper
import com.example.wizkids.util.IntentHelper
import java.time.LocalDate

class UserFullInfoScreen {
    @Composable
    fun UserFullInfo(
        userInfo: DomainUserModel,
        textFont: TextFont,
        context: Context,
        userViewModel: UserViewModel
    ) {
        val mutableImage = remember { mutableStateOf<String?>(userInfo.imagePath) }
        var mutableDocs = remember { mutableStateListOf<DomainDocumentsModel>() }
        val parts = userInfo.name.trim().split(" ")
        val userFirstName =
            remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME]) }
        val userLastName =
            remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME]) }
        mutableDocs.addAll(userInfo.documents)
        ChildInformationCardBackGround().InformationCardbackGround {
            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                mutableImage,
                firstName = userFirstName,
                lastName = userLastName
            )
            InputInformationCard().AddInformationCard(
                stringResource(R.string.basic_information),
                textFont
            ) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.full_name),
                    userInfo.name
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.birth_date),
                    userInfo.dateOfBirth
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.about_me),
                    userInfo.about
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.work_experience),
                    AgeHelper().getAgeFromDate(userInfo.workExperience)

                )
            }

            InputInformationCard().AddInformationCard(
                stringResource(R.string.contact_information),
                textFont
            ) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.phone),
                    userInfo.phone
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.email),
                    userInfo.email
                )
            }

            InputInformationCard().AddInformationCard(
                stringResource(R.string.education_and_qualification),
                textFont
            ) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.education_level),
                    userInfo.educationLevel
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.specialization),
                    userInfo.specialization
                )
            }

            InputInformationCard().AddInformationCard(
                stringResource(R.string.documents),
                textFont
            ) {
                DocumentInformation().AddOrWatchDocumentInformation(textFont, mutableDocs, false)
            }
            ButtonView().ButtonVisibleColumn(
                mapOf(
                    stringResource(R.string.edit_profile_button) to {
                        IntentHelper().intentStart(
                            KEY_ACTIVITY_ADD_USER_INFO,
                            context
                        )
                    },
                    stringResource(R.string.delete_button) to {
                        ImageHelper().deleteImageByPath(userInfo.imagePath)
                        for (document in userInfo.documents) {
                            for (docImage in document.imagePaths) {
                                ImageHelper().deleteImageByPath(docImage)
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
}