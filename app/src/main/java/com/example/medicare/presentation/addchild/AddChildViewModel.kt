package com.example.medicare.presentation.addchild

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddChildViewModel @Inject constructor(

) : ViewModel() {
    private val _uiState = MutableStateFlow(AddChildUiState())
    val uiState = _uiState.asStateFlow()

    fun updateNumber(newNumber: String) {
        _uiState.value = _uiState.value.copy(
            number = newNumber,
            numberErrorMessage = null
        )
        val partsOfNumber = newNumber.split("/")
        if (partsOfNumber.size != 2) {
            _uiState.value =
                _uiState.value.copy(
                    numberErrorMessage = "Invalid number (Child number should look like this 22/5)"
                )
            return
        }
        _uiState.value = _uiState.value.copy(
            number = newNumber,
            upNumber = partsOfNumber[0].toIntOrNull(),
            downNumber = partsOfNumber[1].toIntOrNull(),
            numberErrorMessage = null
        )
    }

    fun updateChildFirstName(newChildFirstName: String) {
        _uiState.value =
            _uiState.value.copy(
                childFirstName = newChildFirstName,
                childFirstNameErrorMessage = null
            )
    }

    fun updateChildSecondName(newChildSecondName: String) {
        _uiState.value =
            _uiState.value.copy(
                childSecondName = newChildSecondName,
                childSecondNameErrorMessage = null
            )
    }

    fun updateFatherFirstName(newFatherFirstName: String) {
        _uiState.value =
            _uiState.value.copy(
                fatherFirstName = newFatherFirstName,
                fatherFirstNameErrorMessage = null
            )
    }

    fun updateFatherSecondName(newFatherSecondName: String) {
        _uiState.value =
            _uiState.value.copy(
                fatherSecondName = newFatherSecondName,
                fatherSecondNameErrorMessage = null
            )
    }

    fun updateMotherFirstName(newMotherFirstName: String) {
        _uiState.value =
            _uiState.value.copy(
                motherFirstName = newMotherFirstName,
                motherFirstNameErrorMessage = null
            )
    }

    fun updateMotherSecondName(newMotherSecondName: String) {
        _uiState.value =
            _uiState.value.copy(
                motherSecondName = newMotherSecondName,
                motherSecondNameErrorMessage = null
            )
    }

    fun updateDateOfBirth(newDateOfBirth: String) {
        _uiState.value =
            _uiState.value.copy(dateOfBirth = newDateOfBirth, dateOfBirthErrorMessage = null)
    }

    fun updateGender(newGender: ChooseTabState) {
        _uiState.value =
            _uiState.value.copy(gender = newGender, genderErrorMessage = null)
    }

    fun updateCheckState(newState: Boolean) {
        _uiState.value =
            _uiState.value.copy(acceptPrivacyIsChecked = newState)
    }

    fun addChild(onAddChildClick:()->Unit) {
        _uiState.value =
            _uiState.value.copy(
                numberErrorMessage = Validator.checkEmail(uiState.value.number),
                childFirstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.childFirstName),
                childSecondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.childSecondName),
                fatherFirstNameErrorMessage = Validator.checkPassword(uiState.value.fatherFirstName),
                fatherSecondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.fatherSecondName),
                motherFirstNameErrorMessage =  Validator.checkRequiredTextField(uiState.value.motherFirstName),
                motherSecondNameErrorMessage =  Validator.checkRequiredTextField(uiState.value.motherSecondName),
                dateOfBirthErrorMessage =  Validator.checkRequiredTextField(uiState.value.dateOfBirth),
                genderErrorMessage =  Validator.checkGender(uiState.value.gender),
            )
        if (uiState.value.numberErrorMessage == null &&
            uiState.value.childFirstNameErrorMessage == null &&
            uiState.value.childSecondNameErrorMessage == null &&
            uiState.value.fatherFirstNameErrorMessage == null &&
            uiState.value.fatherSecondNameErrorMessage == null &&
            uiState.value.motherFirstNameErrorMessage == null &&
            uiState.value.motherSecondNameErrorMessage == null &&
            uiState.value.dateOfBirthErrorMessage == null &&
            uiState.value.genderErrorMessage == null &&
            uiState.value.acceptPrivacyIsChecked
        ) {
            viewModelScope.launch {
                try {
                }catch (e:Exception){
                    Log.e("Sign Up",e.message?:"Error")
                }
            }
            onAddChildClick()
        }
    }


}