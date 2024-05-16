package com.example.medicare.presentation.login





import androidx.lifecycle.ViewModel
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val validator:Validator=Validator()


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
                emailErrorMessage = validator.checkEmail(uiState.value.email),
                passwordErrorMessage = validator.checkPassword(uiState.value.password),
            )
        if(uiState.value.emailErrorMessage==null&&
            uiState.value.passwordErrorMessage==null&&
            uiState.value.acceptPrivacyIsChecked
            ){
            onClickLoginButton()
        }
    }
}