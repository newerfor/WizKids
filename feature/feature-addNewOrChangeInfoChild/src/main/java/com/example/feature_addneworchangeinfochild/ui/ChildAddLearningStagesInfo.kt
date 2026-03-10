package com.example.wizkids.presentation.addNewOrChangeChild.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wizkids.R
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_INDEX_IN_LIST
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildLogicConstant.ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_WORK_STAGE
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildViewConstant.ADD_NEW_OR_CHANGE_LEARNING_STAGES_COLUMN_PADDING
import com.example.wizkids.presentation.addNewOrChangeChild.constant.AddNewOrChangeChildViewConstant.ADD_NEW_OR_CHANGE_LEARNING_STAGES_TEXT_PADDING
import com.example.wizkids.presentation.sharedUI.TextFieldVisible
import com.example.wizkids.presentation.sharedUI.TextFont
import com.example.wizkids.presentation.ui.sharedUI.ui.ButtonView
import com.example.wizkids.ui.theme.textWhite

class ChildAddLearningStagesInfo {
    @Composable
    fun AddChildLearningStages(
        textFont: TextFont,
        childLearningStages: MutableState<List<String>>,
        childWorkStageError: MutableState<Boolean>
    ) {
        childLearningStages.value.forEachIndexed { index, string ->
            Column(Modifier.padding(vertical = ADD_NEW_OR_CHANGE_LEARNING_STAGES_COLUMN_PADDING.dp)) {
                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    textFont.GrayText(
                        "${index + ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_INDEX_IN_LIST}.",
                        Modifier.padding(end = ADD_NEW_OR_CHANGE_LEARNING_STAGES_TEXT_PADDING.dp)
                    )
                    TextFieldVisible().TextOutlineField(
                        string,
                        textFont,
                        stringResource(R.string.development_stage_hint),
                        trailingIcon = childWorkStageError.value
                    ) { newText ->
                        val newList = childLearningStages.value.toMutableList()
                        newList[index] = newText
                        childLearningStages.value = newList
                    }
                    IconButton(
                        onClick = {
                            childLearningStages.value =
                                childLearningStages.value.toMutableList().apply {
                                    removeAt(index)
                                }
                        },
                    ) {
                        Icon(
                            Icons.Default.Delete,
                            stringResource(R.string.delete_icon_description),
                            tint = textWhite
                        )
                    }
                }
            }
        }
        ButtonView().ButtonVisibleRow(
            mapOf(
                stringResource(R.string.add_button) to {
                    childLearningStages.value =
                        childLearningStages.value + ADD_NEW_OR_CHANGE_CHILD_DEFAULT_VALUE_WORK_STAGE
                },
                stringResource(R.string.reset_text) to {
                    childLearningStages.value = emptyList()
                }
            ),
            textFont
        )
    }
}