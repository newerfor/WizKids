package com.example.feature_addneworchangeinfochild.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.example.core_ui.ui.StateHelper
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState
import com.example.feature_addneworchangeinfochild.R

class ChildStateScreen {
    @Composable
    fun ControlState(
        childUiState: ChildByIdUiState,
        textFont: TextFont,
        nameSelection: MutableState<String>,
        childNameSubtitle: MutableState<String>,
        context: Context,
        id: Int?,
        visitUiState: VisitsUiState,
        childViewModel: ChildViewModel,
        visitViewModel: VisitViewModel,
    ) {
        when (childUiState) {
            is ChildByIdUiState.Loading -> {
                StateHelper.RoundLoad()
            }

            is ChildByIdUiState.Success -> {
                ChildAddInformation().AddInformation(
                    textFont,
                    childUiState.child,
                    context,
                    id,
                    childViewModel,
                    visitUiState = visitUiState,
                    visitViewModel,

                    )
                nameSelection.value = stringResource(R.string.edit_child_activity_title)
                childNameSubtitle.value = childUiState.child.name
            }

            is ChildByIdUiState.Error -> {
                StateHelper.ErrorMassage(
                    textFont = textFont,
                ) {
                    childViewModel.getChildById(id)
                }
            }

            is ChildByIdUiState.Empty -> {
                ChildAddInformation().AddInformation(
                    textFont,
                    context = context,
                    id = id,
                    childViewModel = childViewModel,
                    visitViewModel = visitViewModel,
                )
            }
        }
    }
}