package com.example.wizkids.presentation.viewModel.visit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizkids.domain.model.DomainVisitModel
import com.example.wizkids.domain.usecase.deleteUseCase.visitUsecase.DeleteVisitByIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.visitUsecase.GetVisitByChildIdUseCase
import com.example.wizkids.domain.usecase.getUseCase.visitUsecase.GetVisitsUseCase
import com.example.wizkids.domain.usecase.saveUseCase.visitUsecase.SaveVisitUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VisitViewModel(
    private val saveVisitUseCase: SaveVisitUseCase,
    private val getVisitsUseCase: GetVisitsUseCase,
    private val deleteVisitById: DeleteVisitByIdUseCase,
    private val getVisitByChildIdUseCase: GetVisitByChildIdUseCase
) : ViewModel() {
    private val _visitUiState = MutableStateFlow<VisitsUiState>(VisitsUiState.Loading)
    val visitUiState: StateFlow<VisitsUiState> = _visitUiState.asStateFlow()

    // Список посещений (иммутабельный)
    private val _visits = MutableStateFlow<List<DomainVisitModel>>(emptyList())
    val visits: StateFlow<List<DomainVisitModel>> = _visits.asStateFlow()


    fun saveVisit(visit: DomainVisitModel, childId: Int) {
        viewModelScope.launch {
            saveVisitUseCase(visit, childId)
            getVisit()

        }
    }

    fun getVisit(visitDateList: List<String>? = null) {
        viewModelScope.launch {
            try {
                _visitUiState.value = VisitsUiState.Loading

                val result = getVisitsUseCase(visitDateList)
                result.onSuccess { domainVisits ->
                    // Полностью заменяем список
                    _visits.value = domainVisits.filterNotNull()
                    _visitUiState.value = VisitsUiState.Success(visits.value)

                }.onFailure { error ->
                    _visitUiState.value = VisitsUiState.Error(error.message ?: "Unknown error")
                    _visits.value = emptyList()
                }
            } catch (e: Exception) {
                _visitUiState.value = VisitsUiState.Error(e.message ?: "Unknown error")
                _visits.value = emptyList()
            }
        }
    }

    fun deleteVisit(id: Int) {
        viewModelScope.launch {
            deleteVisitById(id)
        }
    }

    fun getVisitByChildId(id: Int) {
        viewModelScope.launch {
            try {
                _visitUiState.value = VisitsUiState.Loading

                val result = getVisitByChildIdUseCase(id)
                result.onSuccess { domainVisits ->
                    // Полностью заменяем список
                    _visits.value = domainVisits.filterNotNull()

                    _visitUiState.value = VisitsUiState.Success(visits.value)

                }.onFailure { error ->
                    _visitUiState.value = VisitsUiState.Error(error.message ?: "Unknown error")
                    _visits.value = emptyList()
                }
            } catch (e: Exception) {
                _visitUiState.value = VisitsUiState.Error(e.message ?: "Unknown error")
                _visits.value = emptyList()
            }
        }
    }
}