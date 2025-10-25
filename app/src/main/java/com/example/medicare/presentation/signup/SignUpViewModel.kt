package com.example.medicare.presentation.signup


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.core.enums.Gender
import com.example.medicare.data.model.result.AuthState
import com.example.medicare.data.model.user.User
import com.example.medicare.data.repositories.UserRepository
import com.example.medicare.data.services.AccountService
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userRepository: UserRepository,
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

    fun updateGender(newState: ChooseTabState) {
        _uiState.value =
            _uiState.value.copy(
                chooseTabState = newState,
                gender = when (newState) {
                    is ChooseTabState.First -> Gender.MALE
                    is ChooseTabState.Second -> Gender.FEMALE
                },
                genderError = null
            )
    }

    fun updateCheckState(newState: Boolean) {
        _uiState.value =
            _uiState.value.copy(acceptPrivacyIsChecked = newState)
    }

    fun updateErrorDialogVisibilityState() {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = !uiState.value.showErrorDialog)
    }

    fun updateLoadingDialogVisibilityState() {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = !uiState.value.showLoadingDialog)
    }


    fun signUp() = viewModelScope.launch {
        _uiState.value =
            _uiState.value.copy(
                emailErrorMessage = Validator.checkEmail(uiState.value.email),
                firstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.firstName),
                secondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.secondName),
                passwordErrorMessage = Validator.checkPassword(uiState.value.password),
                genderError = Validator.checkGender(uiState.value.chooseTabState),
            )

        if (checkAllEnteredData()) {
            try {
                updateLoadingDialogVisibilityState()
                val authState = accountService.signUp(uiState.value.email, uiState.value.password)
                userRepository.addNewUser(
                    User(
                        email = uiState.value.email,
                        firstName = uiState.value.firstName,
                        lastName = uiState.value.secondName,
                        gender = uiState.value.gender ?: Gender.MALE,
                    )
                )
                updateLoadingDialogVisibilityState()
                _uiState.value = _uiState.value.copy(signUpState = authState)
                Log.e("Sign Up", "Successful")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(signUpState = AuthState.Error(e))
                updateLoadingDialogVisibilityState()
                updateErrorDialogVisibilityState()
                Log.e("Sign Up", e.message ?: "Error")
            }
        }
    }

    private fun checkAllEnteredData(): Boolean {
        return uiState.value.emailErrorMessage == null &&
                uiState.value.firstNameErrorMessage == null &&
                uiState.value.secondNameErrorMessage == null &&
                uiState.value.passwordErrorMessage == null &&
                uiState.value.genderError == null &&
                uiState.value.acceptPrivacyIsChecked
    }

}