package com.example.doctor.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.data.services.AccountService
import com.example.doctor.Validator
import com.example.doctor.data.model.result.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()


    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, emailErrorMessage = null)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value =
            _uiState.value.copy(password = newPassword, passwordErrorMessage = null)
    }

    fun updatePasswordVisibilityState() {
        _uiState.value =
            _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    fun updateCheckState(newState: Boolean) {
        _uiState.value =
            _uiState.value.copy(acceptPrivacyIsChecked = newState)
    }

    fun updateErrorDialogVisibilityState(isVisible:Boolean) {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = isVisible)
    }
    fun updateLoadingDialogVisibilityState(isVisible:Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = isVisible)
    }

    private fun updateSnackBarState(isVisible: Boolean) {
        _uiState.update { it.copy(showSnackBar = isVisible) }
    }

    fun login() {
        _uiState.value=
            _uiState.value.copy(
                emailErrorMessage = Validator.checkEmail(uiState.value.email),
                passwordErrorMessage = Validator.checkPassword(uiState.value.password),
            )
        if(uiState.value.emailErrorMessage==null&&
            uiState.value.passwordErrorMessage==null&&
            uiState.value.acceptPrivacyIsChecked
        ){
            viewModelScope.launch {
                try {
                    updateLoadingDialogVisibilityState(true)
                    val authState = accountService.login(uiState.value.email,uiState.value.password)
                    updateLoadingDialogVisibilityState(false)
                    _uiState.value = _uiState.value.copy(authState = authState)
                    updateSnackBarState(authState is AuthState.Error)
                }catch (e:Exception){
                    _uiState.value = _uiState.value.copy(authState = AuthState.Error(e))
                    updateLoadingDialogVisibilityState(false)
                    updateErrorDialogVisibilityState(true)
                    Log.e("Log in",e.message?:"Error")
                    updateSnackBarState(true)
                }
            }
        }
    }
}