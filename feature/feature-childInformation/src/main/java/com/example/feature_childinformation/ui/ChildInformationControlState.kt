package com.example.feature_childinformation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.core_ui.ui.ErrorMassage
import com.example.core_ui.ui.RoundLoad
import com.example.core_ui.ui.TextFont
import com.example.core_viewmodel.child.ChildByIdUiState
import com.example.core_viewmodel.child.ChildViewModel
import com.example.core_viewmodel.visit.VisitViewModel
import com.example.core_viewmodel.visit.VisitsUiState


@Composable
fun ControlState(
    textFont: TextFont,
    name: MutableState<String>,
    years: MutableState<String>,
    context: Context,
    childByIdUiState: ChildByIdUiState,
    childViewModel: ChildViewModel,
    visitUiState: VisitsUiState,
    visitViewModel: VisitViewModel,
    id: Int,
    onClickBack:()->Unit,
    onClickAddInfoChild:()->Unit
) {
    when (childByIdUiState) {
        is ChildByIdUiState.Loading -> {
            RoundLoad()
        }

        is ChildByIdUiState.Success -> {
            name.value = childByIdUiState.child.name
            years.value = childByIdUiState.child.dateOfBirth
            ChildFullInfo(
                childByIdUiState.child,
                textFont,
                context,
                visitUiState,
                viewModel = childViewModel,
                visitViewModel,
                onClickAddInfoChild = onClickAddInfoChild,
                onClickBack=onClickBack
            )
        }

        is ChildByIdUiState.Error -> {
            ErrorMassage(textFont = textFont) {
                childViewModel.getChildById(id)
            }
        }

        ChildByIdUiState.Empty -> {}
    }
}
