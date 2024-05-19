package com.example.medicare.presentation.signup


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.data.services.AccountService
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
interface ResourceProvider {
    fun getString(resId: Int)
}
 class MyApplication : Application(), ResourceProvider {
    override fun getString(resId: Int): String {
        return resources.getString(resId)
    }
}*/

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()


    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, emailErrorMessage = null)
    }

    fun updateFirstName(newFirstName: String) {
        _uiState.value = _uiState.value.copy(firstName = newFirstName, firstNameErrorMessage = null)
    }

    fun updateSecondName(newSecondName: String) {
        _uiState.value =
            _uiState.value.copy(secondName = newSecondName, secondNameErrorMessage = null)
    }

    fun updatePassword(newPassword: String) {
        _uiState.value =
            _uiState.value.copy(password = newPassword, passwordErrorMessage = null)
    }

    fun updatePasswordVisibilityState() {
        _uiState.value =
            _uiState.value.copy(isPasswordVisible = !_uiState.value.isPasswordVisible)
    }

    fun updateGender(newGender: ChooseTabState) {
        _uiState.value =
            _uiState.value.copy(gender = newGender, genderError = null)
    }

    fun updateCheckState(newState: Boolean) {
        _uiState.value =
            _uiState.value.copy(acceptPrivacyIsChecked = newState)
    }

    fun signUp(onSignUpButtonClick:()->Unit) {
        _uiState.value =
            _uiState.value.copy(
                emailErrorMessage = Validator.checkEmail(uiState.value.email),
                firstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.firstName),
                secondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.secondName),
                passwordErrorMessage = Validator.checkPassword(uiState.value.password),
                genderError = Validator.checkGender(uiState.value.gender),
            )
        if (uiState.value.emailErrorMessage == null &&
            uiState.value.firstNameErrorMessage == null &&
            uiState.value.secondNameErrorMessage == null &&
            uiState.value.passwordErrorMessage == null &&
            uiState.value.genderError == null &&
            uiState.value.acceptPrivacyIsChecked
        ) {
            viewModelScope.launch {
                try {
                    accountService.signUp(uiState.value.email,uiState.value.password)
                }catch (e:Exception){
                    Log.e("Sign Up",e.message?:"Error")
                }
            }
            onSignUpButtonClick()
        }
    }

}