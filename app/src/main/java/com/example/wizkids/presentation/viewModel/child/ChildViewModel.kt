package com.example.wizkids.presentation.viewModel.child

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizkids.domain.model.DomainChildModel
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.usecase.deleteUseCase.childUsecase.DeleteChildByIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.childUsecase.GetChildByIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.childUsecase.GetChildrenUseCase
import com.example.wizkids.domain.usecase.saveUseCase.childUsecase.SaveChildUseCase
import com.example.wizkids.util.AgeHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChildViewModel(
    private val getChildrenUseCase: GetChildrenUseCase,
    private val saveChildUseCase: SaveChildUseCase,
    private val deleteChildByIdUseCase: DeleteChildByIdUseCase,
    private val getChildByIdUseCase: GetChildByIdUseCase
): ViewModel() {
    private val _childUiState = MutableStateFlow<ChildrenUiState>(ChildrenUiState.Loading)
    val childUiState: StateFlow<ChildrenUiState> = _childUiState.asStateFlow()
    private val _childByIdState = MutableStateFlow<ChildByIdUiState>(ChildByIdUiState.Loading)
    val childByIdState: StateFlow<ChildByIdUiState> = _childByIdState.asStateFlow()
    private val _children = MutableStateFlow<List<DomainChildModel>>(emptyList())
    val children: StateFlow<List<DomainChildModel>> = _children.asStateFlow()

    fun getChildren(
        searchName: String?,
        minAge: Int?,
        maxAge: Int?,
        balanceOperator: String?,
        hasPayStatusDebt: Boolean?,
        selectedOptionSort: SortedOption?
    ) {
        viewModelScope.launch {
            try {
                _childUiState.value = ChildrenUiState.Loading

                val result = getChildrenUseCase(
                    searchName = searchName,
                    minAge = minAge,
                    maxAge = maxAge,
                    balanceOperator = balanceOperator,
                    hasPayStatusDebt = hasPayStatusDebt
                )

                result.onSuccess { domainChildren ->
                    // Полностью заменяем список
                    _children.value = domainChildren.filterNotNull()

                    // Сортируем если нужно
                    if (selectedOptionSort != null) {
                        childrenSorted(selectedOptionSort)
                    }

                    // Обновляем UI состояние
                    _childUiState.value = if (_children.value.isEmpty()) {
                        ChildrenUiState.Empty
                    } else {
                        ChildrenUiState.Success(children.value)
                    }

                }.onFailure { error ->
                    _childUiState.value = ChildrenUiState.Error(error.message ?: "Unknown error")
                    _children.value = emptyList() // Очищаем на случай ошибки
                }

            } catch (e: Exception) {
                _childUiState.value = ChildrenUiState.Error(e.message ?: "Unknown error")
                _children.value = emptyList()
            }
        }
    }
    fun deleteChild(id:Int){
        viewModelScope.launch {
            deleteChildByIdUseCase(id)
        }
    }
    fun getChildById(id: Int?) {
        viewModelScope.launch {
            try {
                _childByIdState.value = ChildByIdUiState.Loading
                val result = getChildByIdUseCase(id)
                result.onSuccess{child->
                    if(child!=null){
                        _childByIdState.value = ChildByIdUiState.Success(child)
                    }else{
                        _childByIdState.value = ChildByIdUiState.Empty
                    }
                }.onFailure{error->
                    _childByIdState.value = ChildByIdUiState.Error(error.toString())
                }
            }catch (e:Exception){
                _childByIdState.value = ChildByIdUiState.Error(e.toString())
            }
        }
    }

    fun saveChild(child: DomainChildModel, visit: List<DomainVisitModel>){
        viewModelScope.launch {
            saveChildUseCase(
                child =child ,
                visits = visit
            )
        }
    }
    fun childrenSorted(selectedOptionSort: SortedOption?){
        when(selectedOptionSort){
            SortedOption.BY_NAME_ASC -> {
                _children.value.sortedBy  { it.name }
            }
            SortedOption.BY_NAME_DESC -> {
                _children.value.sortedByDescending { it.name }
            }
            SortedOption.BY_DATE_ASC -> {
                _children.value.sortedBy { it.dateOfBirth }
            }
            SortedOption.BY_DATE_DESC -> {
                _children.value.sortedByDescending { it.dateOfBirth }
            }
            SortedOption.BY_AGE_ASC -> {
                _children.value.sortedBy { AgeHelper().getAgeFromDate(it.dateOfBirth) }
            }
            SortedOption.BY_AGE_DESC ->{
                _children.value.sortedByDescending { AgeHelper().getAgeFromDate(it.dateOfBirth) }
            }
            null -> {_children.value}
        }
    }
}