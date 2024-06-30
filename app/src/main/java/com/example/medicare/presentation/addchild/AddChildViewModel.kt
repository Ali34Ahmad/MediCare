package com.example.medicare.presentation.addchild

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.R
import com.example.medicare.core.enums.Gender
import com.example.medicare.core.enums.Month
import com.example.medicare.data.model.child.Child
import com.example.medicare.data.model.child.ChildNumber
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.repositories.ChildRepository
import com.example.medicare.ui.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddChildViewModel @Inject constructor(
    private val childRepository: ChildRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddChildUiState())
    val uiState = _uiState.asStateFlow()

    fun updateChildNumber(newNumber: String) {
        _uiState.value = _uiState.value.copy(
            number = newNumber,
            numberErrorMessage = null
        )
        val partsOfNumber = newNumber.split("/")
        if (partsOfNumber.size != 2) {
            _uiState.value =
                _uiState.value.copy(
                    numberErrorMessage = R.string.invalide_child_number
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

    fun updateErrorDialogVisibilityState() {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = !uiState.value.showErrorDialog)
    }

    fun updateLoadingDialogVisibilityState() {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = !uiState.value.showLoadingDialog)
    }

    fun addChild() {
        extractNumberComponents("${uiState.value.upNumber}/${uiState.value.downNumber}")
        _uiState.value =
            _uiState.value.copy(
                childFirstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.childFirstName),
                childSecondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.childSecondName),
                fatherFirstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.fatherFirstName),
                fatherSecondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.fatherSecondName),
                motherFirstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.motherFirstName),
                motherSecondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.motherSecondName),
                dateOfBirthErrorMessage = Validator.checkRequiredTextField(uiState.value.dateOfBirth),
                genderErrorMessage = Validator.checkGender(uiState.value.gender),
            )
        if (checkAllEnteredData()) {
            viewModelScope.launch {
                try {
                    updateLoadingDialogVisibilityState()
                    val year: Int = extractDateComponents(uiState.value.dateOfBirth).third
                    val month: Month =
                        getMonthByNumber(extractDateComponents(uiState.value.dateOfBirth).second)
                    val day: Int = extractDateComponents(uiState.value.dateOfBirth).first
                    childRepository.addChild(
                        Child(
                            firstName = uiState.value.childFirstName,
                            lastName = uiState.value.childSecondName,
                            birthDate = FullDate(year = year, month = month, day = day),
                            childNumber = ChildNumber(
                                uiState.value.upNumber ?: -1,
                                secondNumber = uiState.value.downNumber ?: -1
                            ),
                            gender = when (uiState.value.gender) {
                                is ChooseTabState.First -> Gender.MALE
                                is ChooseTabState.Second -> Gender.FEMALE
                                else -> throw Exception("You must let the user choose the gender")
                            },
                            father = "${uiState.value.fatherFirstName} ${uiState.value.fatherSecondName}",
                            mother = "${uiState.value.motherFirstName} ${uiState.value.motherSecondName}"
                        )
                    )
                    updateLoadingDialogVisibilityState()
                    _uiState.value=_uiState.value.copy(isAddChildSuccessful = true)
                } catch (e: Exception) {
                    updateLoadingDialogVisibilityState()
                    updateErrorDialogVisibilityState()
                    _uiState.value=_uiState.value.copy(isAddChildSuccessful = false)
                    Log.e("Add Child", e.message ?: "Error")
                }
            }
        }
    }

    private fun checkAllEnteredData(): Boolean {
        return uiState.value.numberErrorMessage == null &&
                uiState.value.childFirstNameErrorMessage == null &&
                uiState.value.childSecondNameErrorMessage == null &&
                uiState.value.fatherFirstNameErrorMessage == null &&
                uiState.value.fatherSecondNameErrorMessage == null &&
                uiState.value.motherFirstNameErrorMessage == null &&
                uiState.value.motherSecondNameErrorMessage == null &&
                uiState.value.dateOfBirthErrorMessage == null &&
                uiState.value.genderErrorMessage == null &&
                uiState.value.acceptPrivacyIsChecked
    }

    private fun extractDateComponents(dateString: String): Triple<Int, Int, Int> {
        val parts = dateString.split("/")
        if (parts.size != 3) {
            _uiState.value =
                _uiState.value.copy(dateOfBirthErrorMessage = R.string.invalid_date_value)
        }
        val year = parts[2].toInt()
        val month = parts[1].toInt()
        val day = parts[0].toInt()
        return Triple(day, month, year)
    }

    private fun extractNumberComponents(number: String): Pair<Int, Int> {
        val parts = number.split("/")
        if (parts.size != 2) {
            _uiState.value =
                _uiState.value.copy(numberErrorMessage = R.string.invalide_child_number)
        }
        val first = parts[0].toInt()
        val second = parts[1].toInt()
        return Pair(first = first, second = second)
    }


    private fun getMonthByNumber(monthNumber: Int): Month {
        if (monthNumber !in 1..12) {
            _uiState.value =
                _uiState.value.copy(dateOfBirthErrorMessage = R.string.invalid_date_value)
        }
        return Month.entries[monthNumber - 1]
    }

}