package com.example.medicare.presentation.login





import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.model.result.AuthState
import com.example.medicare.data.services.AccountService
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()


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

    fun updateLoadingDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = isVisible)
    }

    fun login() {
        _uiState.value =
            _uiState.value.copy(
                emailErrorMessage = Validator.checkEmail(uiState.value.email),
                passwordErrorMessage = Validator.checkPassword(uiState.value.password),
            )
        if (checkAllEnteredData()) {
            viewModelScope.launch {
                try {
                    updateLoadingDialogVisibilityState(true)
                    accountService.login(uiState.value.email, uiState.value.password)
                    _uiState.value = _uiState.value.copy(authState = AuthState.Success)
                    updateLoadingDialogVisibilityState(false)
                    Log.e("Log in viewmodel", uiState.value.authState.toString())
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(authState = AuthState.Error(e))
                    updateLoadingDialogVisibilityState(false)
                    updateErrorDialogVisibilityState(true)
                    Log.e("Log in", e.message ?: "Error")
                }
            }
        }
    }
    private fun checkAllEnteredData(): Boolean {
        return uiState.value.emailErrorMessage==null&&
                uiState.value.passwordErrorMessage==null&&
                uiState.value.acceptPrivacyIsChecked
    }
}