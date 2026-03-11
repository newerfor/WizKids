package com.example.feature_childinformation.ui

import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainDocumentsModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.ButtonView
import com.example.core_ui.ui.ChangeInformationWindow.ChildInformationCardBackGround
import com.example.core_ui.ui.ChangeInformationWindow.ChildInformationCardValueGrayAndWhiteText
import com.example.core_ui.ui.ChangeInformationWindow.ChildInformationImageAndPayStatus
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ChildComingVisitsInformation
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ChildComingVisitsInformationWindow
import com.example.core_ui.ui.ChangeInformationWindow.DocumentsInforamtionCard.DocumentInformation
import com.example.core_ui.ui.InputInformationCard
import com.example.core_ui.ui.TextFont
import com.example.core_util.AgeHelper
import com.example.core_util.ImageHelper
import com.example.core_util.IntentHelper
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState
import com.example.feature_childinformation.R
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME


class ChildFullInfoScreen {
    @Composable
    fun ChildFullInfo(
        child: DomainChildModel,
        textFont: TextFont,
        context: Context,
        visitUiState: VisitsUiState,
        viewModel: ChildViewModel,
        visitViewModel: VisitViewModel,
    ) {
        val mutableImage = remember { mutableStateOf(child?.imagePath) }
        val visitsInfo = remember { mutableStateListOf<DomainVisitModel>() }
        var mutableDocs = remember { mutableStateListOf<DomainDocumentsModel>() }
        mutableDocs.addAll(child.documents)
        when (visitUiState) {
            is VisitsUiState.Loading -> {}
            is VisitsUiState.Error -> {}
            is VisitsUiState.Success -> {
                visitsInfo.clear()
                visitsInfo.addAll(visitUiState.visit)
            }
        }
        var moreInfoVisit = remember { mutableStateOf<DomainVisitModel?>(null) }

        var openWindowMoreVisitInfo = remember { mutableStateOf(false) }
        val parts = child.name.trim().split(" ")
        val childFirstName =
            remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME]) }
        val childLastName =
            remember { mutableStateOf(parts[USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME]) }
        ChildInformationCardBackGround().InformationCardBackGround {
            ChildInformationImageAndPayStatus().InformationImageAndPayStatus(
                textFont,
                mutableImage,
                price = child.visitPrice,
                balance = child.currentBalance,
                firstName = childFirstName,
                lastName = childLastName,
            )
            InputInformationCard().AddInformationCard(
                stringResource(R.string.basic_information),
                textFont
            ) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.full_name),
                    child.name
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.birth_date),
                    child.dateOfBirth
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.age_label),
                    AgeHelper().getAgeFromDate(child.dateOfBirth)
                )
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.additional_info_label),
                textFont
            ) {
                textFont.WhiteText(child.additionalInfo)
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.finance_info_label),
                textFont
            ) {
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont, "${
                        stringResource(R.string.balance)
                    } :", child.currentBalance.toString()
                )
                ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                    textFont,
                    stringResource(R.string.visit_price_label), child.visitPrice.toString()
                )
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.documents_label),
                textFont
            ) {
                DocumentInformation().AddOrWatchDocumentInformation(
                    textFont = textFont,
                    mutableDocs,
                    false,
                )
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.development_stages_label),
                textFont
            ) {
                child.learningStages.forEachIndexed { index, string ->
                    Row {
                        ChildInformationCardValueGrayAndWhiteText().InformationCardValueGrayAndWhiteText(
                            textFont,
                            "$index.",
                            string
                        )
                    }
                }
            }
            InputInformationCard().AddInformationCard(
                stringResource(R.string.recent_visits_label),
                textFont
            ) {
                for (visit in visitsInfo) {
                    ChildComingVisitsInformation().ComingVisitsInformation(textFont, visit, onClick = { visit ->
                        moreInfoVisit.value = visit
                        openWindowMoreVisitInfo.value = true
                    })
                }
            }
            ButtonView().ButtonVisibleColumn(
                buttonNames = mapOf(
                    stringResource(R.string.edit_child_activity_title) to {
                        IntentHelper().intentStartById(
                            KEY_ACTIVITY_CHILD_ADD_INFO,
                            context,
                            child.id
                        )
                    },
                    stringResource(R.string.delete_icon_description) to {
                        ImageHelper().deleteImageByPath(child.imagePath)
                        for (document in child.documents) {
                            for (docImage in document.imagePaths) {
                                ImageHelper().deleteImageByPath(docImage)
                            }
                        }
                        if (child.id != null) {
                            viewModel.deleteChild(child.id!!)
                            visitsInfo.map {
                                if (it.id != null) {
                                    visitViewModel.deleteVisit(it.id!!)
                                }
                            }
                        }
                        IntentHelper().intentStartById(
                            KEY_ACTIVITY_MAIN,
                            context,
                            child.id
                        )
                    }
                ),
                textFont,
            )
            if(openWindowMoreVisitInfo.value) {
                ChildComingVisitsInformationWindow().ComingVisitsInformationWindow(
                    textFont = textFont,
                    openVisitWindow = openWindowMoreVisitInfo,
                    visit = moreInfoVisit,
                    isChangeAct = false,
                    inAddIndex = 0,
                    becomeCalendar = false,
                    childId = moreInfoVisit.value?.childId,
                    childName = moreInfoVisit.value?.childName?:"",
                    selectedDate = "",
                )
            }
        }
    }
}