package com.example.wizkids.presentation.viewModel.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wizkids.domain.model.DomainUserModel
import com.example.wizkids.domain.usecase.getUseCase.userUsecase.GetUserUseCase
import com.example.wizkids.domain.usecase.saveUseCase.userUsecase.SaveUserUseCase
import com.example.wizkids.domain.usecase.deleteUseCase.userUsecase.DeleteUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
): ViewModel() {
    private val _userUiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val userUiState: StateFlow<UserUiState> = _userUiState.asStateFlow()
    init {
        getUser()
    }
    fun getUser() {
        viewModelScope.launch {
            try {
                _userUiState.value = UserUiState.Loading
                val result = getUserUseCase()
                result.onSuccess{user->
                    if(user == null) {
                        _userUiState.value = UserUiState.Empty
                    }else{
                        _userUiState.value = UserUiState.Success(user)
                    }
                }.onFailure{error->
                    if(error.toString() == "java.lang.NullPointerException: Parameter specified as non-null is null: method com.example.wizkids.data.local.mapper.GetMapper.mapUserToDomain, parameter user"){
                        _userUiState.value = UserUiState.Empty
                    }else{
                        _userUiState.value = UserUiState.Error(error.toString())
                    }
                }
            }catch (e:Exception){
                _userUiState.value = UserUiState.Error(e.toString())
            }
        }
    }
    fun deleteUser() {
        viewModelScope.launch {
            deleteUserUseCase()
            _userUiState.value = UserUiState.Empty
        }
    }
    fun saveUser(user: DomainUserModel) {
        viewModelScope.launch {
            saveUserUseCase(user)
        }
    }
}