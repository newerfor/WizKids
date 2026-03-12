package com.example.feature_childinformation.ui

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core_domain.model.DomainChildModel
import com.example.core_domain.model.DomainDocumentsModel
import com.example.core_domain.model.DomainVisitModel
import com.example.core_ui.ui.AddInformationCard
import com.example.core_ui.ui.ButtonVisibleColumn
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ComingVisitsInformation
import com.example.core_ui.ui.ChangeInformationWindow.ComingVisitsInformationCard.ComingVisitsInformationWindow
import com.example.core_ui.ui.ChangeInformationWindow.DocumentsInforamtionCard.AddOrWatchDocumentInformation
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardBackGround
import com.example.core_ui.ui.ChangeInformationWindow.InformationCardValueGrayAndWhiteText
import com.example.core_ui.ui.ChangeInformationWindow.InformationImageAndPayStatus
import com.example.core_ui.ui.TextFont
import com.example.core_util.deleteImageByPath
import com.example.core_util.getAgeFromDate
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState
import com.example.feature_childinformation.R
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_FIRST_NAME
import com.example.feature_childinformation.constant.ChildInformationLogicConstant.USER_CARD_INITIAL_PARTS_INDEX_LAST_NAME


@Composable
fun ChildFullInfo(
    child: DomainChildModel,
    textFont: TextFont,
    context: Context,
    visitUiState: VisitsUiState,
    viewModel: ChildViewModel,
    visitViewModel: VisitViewModel,
    onClickAddInfoChild: () -> Unit,
    onClickBack: () -> Unit
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
    InformationCardBackGround {
        InformationImageAndPayStatus(
            textFont,
            mutableImage,
            price = child.visitPrice,
            balance = child.currentBalance,
            firstName = childFirstName,
            lastName = childLastName,
        )
        AddInformationCard(
            stringResource(R.string.basic_information),
            textFont
        ) {
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.full_name),
                child.name
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.birth_date),
                child.dateOfBirth
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.age_label),
                getAgeFromDate(child.dateOfBirth)
            )
        }
        AddInformationCard(
            stringResource(R.string.additional_info_label),
            textFont
        ) {
            textFont.WhiteText(child.additionalInfo)
        }
        AddInformationCard(
            stringResource(R.string.finance_info_label),
            textFont
        ) {
            InformationCardValueGrayAndWhiteText(
                textFont, "${
                    stringResource(R.string.balance)
                } :", child.currentBalance.toString()
            )
            InformationCardValueGrayAndWhiteText(
                textFont,
                stringResource(R.string.visit_price_label), child.visitPrice.toString()
            )
        }
        AddInformationCard(
            stringResource(R.string.documents_label),
            textFont
        ) {
            AddOrWatchDocumentInformation(
                textFont = textFont,
                mutableDocs,
                false,
            )
        }
        AddInformationCard(
            stringResource(R.string.development_stages_label),
            textFont
        ) {
            child.learningStages.forEachIndexed { index, string ->
                Row {
                    InformationCardValueGrayAndWhiteText(
                        textFont,
                        "$index.",
                        string
                    )
                }
            }
        }
        AddInformationCard(
            stringResource(R.string.recent_visits_label),
            textFont
        ) {
            for (visit in visitsInfo) {
                ComingVisitsInformation(
                    textFont,
                    visit,
                    onClick = { visit ->
                        moreInfoVisit.value = visit
                        openWindowMoreVisitInfo.value = true
                    })
            }
        }
        Column(Modifier.navigationBarsPadding()) {
            ButtonVisibleColumn(
                buttonNames = mapOf(
                    stringResource(R.string.edit_child_activity_title) to {
                        onClickAddInfoChild.invoke()
                    },
                    stringResource(R.string.delete_icon_description) to {
                        deleteImageByPath(child.imagePath)
                        for (document in child.documents) {
                            for (docImage in document.imagePaths) {
                                deleteImageByPath(docImage)
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
                        onClickBack.invoke()
                    }
                ),
                textFont,
            )
        }
        if (openWindowMoreVisitInfo.value) {
            ComingVisitsInformationWindow(
                textFont = textFont,
                openVisitWindow = openWindowMoreVisitInfo,
                visit = moreInfoVisit,
                isChangeAct = false,
                inAddIndex = 0,
                becomeCalendar = false,
                childId = moreInfoVisit.value?.childId,
                childName = moreInfoVisit.value?.childName ?: "",
                selectedDate = "",
            )
        }
    }
}
