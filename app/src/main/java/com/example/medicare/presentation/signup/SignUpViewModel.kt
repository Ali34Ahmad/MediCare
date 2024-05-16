package com.example.medicare.presentation.signup





import androidx.lifecycle.ViewModel
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


interface ResourceProvider{
    fun getString(resId:Int)
}
/*
 class MyApplication : Application(), ResourceProvider {
    override fun getString(resId: Int): String {
        return resources.getString(resId)
    }
}*/

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    private val validator:Validator=Validator()


    fun updateEmail(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, emailErrorMessage = "")
    }

    fun updateFirstName(newFirstName: String) {
        _uiState.value = _uiState.value.copy(firstName = newFirstName, firstNameErrorMessage = "")
    }

    fun updateSecondName(newSecondName: String) {
        _uiState.value =
            _uiState.value.copy(secondName = newSecondName, secondNameErrorMessage = "")
    }

    fun updatePassword(newPassword: String) {
        _uiState.value =
            _uiState.value.copy(password = newPassword, passwordErrorMessage = "")
    }

    fun updateGender(newGender: ChooseTabState) {
        _uiState.value =
            _uiState.value.copy(gender = newGender)
    }

    fun updateCheckState(newState: Boolean) {
        _uiState.value =
            _uiState.value.copy(acceptPrivacyIsChecked = newState)
    }

    fun signUp() {
        _uiState.value=
            _uiState.value.copy(
                emailErrorMessage = validator.checkEmail(uiState.value.email),
                firstNameErrorMessage = validator.checkFirstName(uiState.value.firstName),
                secondNameErrorMessage = validator.checkSecondName(uiState.value.secondName),
                passwordErrorMessage = validator.checkPassword(uiState.value.password)
            )
        if(uiState.value.emailErrorMessage==null&&
            uiState.value.firstNameErrorMessage==null&&
            uiState.value.secondNameErrorMessage==null&&
            uiState.value.passwordErrorMessage==null
            ){
            submitInformation()
        }
    }
    fun submitInformation(){

    }

}