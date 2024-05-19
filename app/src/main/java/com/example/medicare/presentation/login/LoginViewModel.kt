package com.example.medicare.presentation.login





import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicare.data.services.AccountService
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
interface ResourceProvider{
    fun getString(resId:Int)
}

 class MyApplication : Application(), ResourceProvider {
    override fun getString(resId: Int): String {
        return resources.getString(resId)
    }
}*/

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

    fun login(onClickLoginButton:()->Unit) {
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
                    accountService.login(uiState.value.email,uiState.value.password)
                }catch (e:Exception){
                    Log.e("Log in",e.message?:"Error")
                }
            }
            onClickLoginButton()
        }
    }
}