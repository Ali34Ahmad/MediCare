package com.example.doctor.presentation.signup


import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doctor.R
import com.example.doctor.Validator
import com.example.doctor.core.composables.ChooseTabState
import com.example.doctor.core.enums.DayOfWeek
import com.example.doctor.core.enums.DayPeriod
import com.example.doctor.core.enums.Gender
import com.example.doctor.core.enums.Month
import com.example.doctor.core.enums.TimeSocketState
import com.example.doctor.data.fake.listOfDaySockets
import com.example.doctor.data.fake.listOfWorkDays
import com.example.doctor.data.model.clinic.Clinic
import com.example.doctor.data.model.date.DaySocket
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.date.Time
import com.example.doctor.data.model.date.TimeSocket
import com.example.doctor.data.model.date.WorkDay
import com.example.doctor.data.model.result.AuthState
import com.example.doctor.data.model.user.Doctor
import com.example.doctor.data.model.user.User
import com.example.doctor.data.repositories.ClinicRepository
import com.example.doctor.data.repositories.DoctorRepository
import com.example.doctor.data.repositories.UserRepository
import com.example.doctor.data.services.AccountService
import com.example.doctor.data.storage.ImageUploader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val userRepository: UserRepository,
    private val imageUploader: ImageUploader,
    private val clinicRepository: ClinicRepository,
    private val doctorRepository: DoctorRepository,
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

    fun updateSpeciality(newSpeciality: String) {
        _uiState.value =
            _uiState.value.copy(speciality = newSpeciality, specialityErrorMessage = null)
    }
    fun updateClinicName(newClinicName: String) {
        _uiState.value =
            _uiState.value.copy(clinicName = newClinicName, clinicNameErrorMessage = null)
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

    fun updateErrorDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showErrorDialog = isVisible)
    }

    fun updateLoadingDialogVisibilityState(isVisible: Boolean) {
        _uiState.value =
            _uiState.value.copy(showLoadingDialog = isVisible)
    }

    private fun updateSnackBarState(isVisible: Boolean) {
        _uiState.update { it.copy(showSnackBar = isVisible) }
    }

    fun signUp() {
        _uiState.value =
            _uiState.value.copy(
                emailErrorMessage = Validator.checkEmail(uiState.value.email),
                firstNameErrorMessage = Validator.checkRequiredTextField(uiState.value.firstName),
                secondNameErrorMessage = Validator.checkRequiredTextField(uiState.value.secondName),
                clinicNameErrorMessage = Validator.checkRequiredTextField(uiState.value.clinicName),
                passwordErrorMessage = Validator.checkPassword(uiState.value.password),
                genderError = Validator.checkGender(uiState.value.chooseTabState),
                specialityErrorMessage = Validator.checkRequiredTextField(uiState.value.speciality),
            )
        if (checkAllEnterData()) {
                try {
                    viewModelScope.launch {
                        updateLoadingDialogVisibilityState(true)
                        val authState=accountService.signUp(uiState.value.email, uiState.value.password)

                        doctorRepository.addDoctor(
                            Doctor(
                                firstName = uiState.value.firstName,
                                lastName = uiState.value.secondName,
                                gender = uiState.value.gender ?: Gender.MALE,
                                speciality = uiState.value.speciality,
                                imageUrl = uiState.value.doctorImageUrl
                            )
                        )

                        updateLoadingDialogVisibilityState(false)
                        _uiState.update { it.copy(authState = AuthState.Success) }
                        updateDoctorImagePickerDialogVisibilityState(true)
                        updateSnackBarState(authState is AuthState.Error)
                    }
                } catch (e: Exception) {
                    _uiState.update { it.copy(authState = AuthState.Error(e)) }
                    updateLoadingDialogVisibilityState(false)
                    updateErrorDialogVisibilityState(true)
                    updateSnackBarState(false)
                    Log.e("Sign Up", e.message ?: "Error")
                }
        }
    }

    fun updateDoctorImageUri(uri: Uri?) {
        _uiState.update { it.copy(doctorErrorMessage = null, doctorImageUri = uri) }
    }

    fun uploadDoctorImage() {
        try {
            _uiState.update { it.copy(doctorErrorMessage = null) }
            if (uiState.value.doctorImageUri != null) {
                viewModelScope.launch {
                    updateDoctorProgressBarVisibilityState(true)
                    val imageUrl = imageUploader.uploadImage(
                        uiState.value.doctorImageUri ?: Uri.EMPTY,
                        "doctorImage"
                    )
                    _uiState.update { it.copy(doctorImageUrl = imageUrl) }
                    updateDoctorProgressBarVisibilityState(false)
                    updateDoctorImagePickerDialogVisibilityState(false)
                    updateClinicImagePickerDialogVisibilityState(true)
                }
            }
        } catch (e: Exception) {
            _uiState.update { it.copy(doctorErrorMessage = R.string.something_went_wrong) }
        }

    }

    fun updateDoctorImageBitmap(bitmap: Bitmap?) {
        _uiState.update { it.copy(doctorBitmap = bitmap, doctorErrorMessage = null) }
    }


    fun updateClinicImageUri(uri: Uri?) {
        _uiState.update { it.copy(clinicErrorMessage = null, clinicImageUri = uri) }
    }

    fun uploadClinicImage() {
        try {
            _uiState.update { it.copy(clinicErrorMessage = null) }
            if (uiState.value.doctorImageUri != null) {
                viewModelScope.launch {
                    updateClinicProgressBarVisibilityState(true)
                    val imageUrl = imageUploader.uploadImage(
                        uiState.value.clinicImageUri ?: Uri.EMPTY,
                        "clinicImage"
                    )
                    _uiState.update { it.copy(clinicImageUrl = imageUrl) }

                    Log.v("currentId",accountService.currentUserId)
                    clinicRepository.addClinic(
                        Clinic(
                            name = uiState.value.clinicName,
                            imageUrl = uiState.value.clinicImageUrl,
                            workDays = listOfWorkDays,
                            daySockets = listOfDaySockets,
                            responsibleDoctor = Doctor(
                                firstName = uiState.value.firstName,
                                lastName = uiState.value.secondName,
                                speciality = uiState.value.speciality,
                                imageUrl=uiState.value.doctorImageUrl,
                                gender = uiState.value.gender?:Gender.MALE,
                            ),
                            services = emptyList(),
                        )
                    )

                    updateClinicProgressBarVisibilityState(false)
                    updateClinicImagePickerDialogVisibilityState(false)
                    _uiState.update { it.copy(isSuccessful = true) }
                }
            }
        } catch (e: Exception) {
            updateClinicProgressBarVisibilityState(false)
            _uiState.update {
                it.copy(
                    clinicErrorMessage = R.string.something_went_wrong,
                    isSuccessful = false
                )
            }
        }

    }

    fun updateClinicImageBitmap(bitmap: Bitmap?) {
        _uiState.update { it.copy(clinicBitmap = bitmap, clinicErrorMessage = null) }
    }

    fun updateDoctorImagePickerDialogVisibilityState(isVisible: Boolean) {
        _uiState.update { it.copy(showDoctorImageUploader = isVisible) }
    }

    fun updateClinicImagePickerDialogVisibilityState(isVisible: Boolean) {
        _uiState.update { it.copy(showClinicImageUploader = isVisible) }
    }

    private fun updateDoctorProgressBarVisibilityState(isVisible: Boolean) {
        _uiState.update { it.copy(showDoctorProgressBar = isVisible) }
    }

    private fun updateClinicProgressBarVisibilityState(isVisible: Boolean) {
        _uiState.update { it.copy(showClinicProgressBar = isVisible) }
    }

    private fun checkAllEnterData(): Boolean {
        return uiState.value.emailErrorMessage == null &&
                uiState.value.firstNameErrorMessage == null &&
                uiState.value.secondNameErrorMessage == null &&
                uiState.value.passwordErrorMessage == null &&
                uiState.value.specialityErrorMessage==null &&
                uiState.value.clinicNameErrorMessage == null &&
                uiState.value.genderError == null &&
                uiState.value.acceptPrivacyIsChecked
    }
}
