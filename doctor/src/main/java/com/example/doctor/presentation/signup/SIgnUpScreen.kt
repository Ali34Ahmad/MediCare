package com.example.doctor.presentation.signup

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctor.R
import com.example.doctor.core.composables.AppLogo
import com.example.doctor.core.composables.CheckBoxComponent
import com.example.doctor.core.composables.ChooseTab
import com.example.doctor.core.composables.ChooseTabState
import com.example.doctor.core.composables.ElevatedButtonComponent
import com.example.doctor.core.composables.ErrorDialog
import com.example.doctor.core.composables.ImagePickerDialog
import com.example.doctor.core.composables.LoadingDialog
import com.example.doctor.core.composables.OutlinedTextFieldComponent
import com.example.doctor.core.composables.OutlinedTextFieldWithTwoFieldsComponent
import com.example.doctor.core.composables.SpannableTextComponent
import com.example.doctor.core.composables.TextStyle
import com.example.doctor.core.composables.TextWithMultipleStyles
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing

@Composable
fun SignUpScreen(
    onLoginClickEvent: () -> Unit,
    uiState: SignUpUiState,
    updateEmailEvent: (String) -> Unit,
    updateFirstNameEvent: (String) -> Unit,
    updateSecondNameEvent: (String) -> Unit,
    updatePasswordEvent: (String) -> Unit,
    updateSpecialityEvent: (String) -> Unit,
    updateClinicNameEvent: (String) -> Unit,
    updatePasswordVisibilityStateEvent: () -> Unit,
    updateGenderEvent: (ChooseTabState) -> Unit,
    updateCheckStateEvent: (Boolean) -> Unit,
    updateErrorDialogVisibilityState: (Boolean) -> Unit,
    onSignUpClickEvent: () -> Unit,

    navigateToScheduleScreen: () -> Unit,
    onDoctorImageSaveButtonClick: () -> Unit,
    updateDoctorImageUri: (Uri?) -> Unit,
    updateDoctorImageBitmap: (Bitmap?) -> Unit,
    onDoctorImagePickerDialogDismissRequest: () -> Unit,
    onClinicImageSaveButtonClick: () -> Unit,
    updateClinicImageUri: (Uri?) -> Unit,
    updateClinicImageBitmap: (Bitmap?) -> Unit,
    onClinicImagePickerDialogDismissRequest: () -> Unit,
) {

    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityState(false)
        },
        onConfirmClick = {
            updateErrorDialogVisibilityState(false)
        }
    )
    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
    )


    if (uiState.isSuccessful)
        navigateToScheduleScreen()

    if (uiState.showDoctorImageUploader)
        ImagePickerDialog(
            explanationText = R.string.upload_a_profile_photo,
            errorMessage = uiState.doctorErrorMessage,
            onSaveButtonClick = onDoctorImageSaveButtonClick,
            updateImageUri = updateDoctorImageUri,
            imageUri = uiState.doctorImageUri,
            updateBitmap = updateDoctorImageBitmap,
            bitmap = uiState.doctorBitmap,
            onDismissRequest = onDoctorImagePickerDialogDismissRequest,
            showProgressBar = uiState.showDoctorProgressBar,
        )
    if (uiState.showClinicImageUploader)
        ImagePickerDialog(
            explanationText = R.string.upload_clinic_photo,
            errorMessage = uiState.clinicErrorMessage,
            onSaveButtonClick = onClinicImageSaveButtonClick,
            updateImageUri = updateClinicImageUri,
            imageUri = uiState.clinicImageUri,
            updateBitmap = updateClinicImageBitmap,
            bitmap = uiState.clinicBitmap,
            onDismissRequest = onClinicImagePickerDialogDismissRequest,
            showProgressBar = uiState.showClinicProgressBar,
        )
    if (!uiState.showDoctorImageUploader && !uiState.showClinicImageUploader)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(Spacing.medium),
            verticalArrangement = Arrangement.Center
        ) {
            AppLogo(modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(Spacing.large))

            OutlinedTextFieldComponent(
                title = stringResource(id = R.string.email),
                textFieldValue = uiState.email,
                onValueChange = {
                    updateEmailEvent(it)
                },
                hint = R.string.email_hint,
                errorMessage = uiState.emailErrorMessage
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextFieldWithTwoFieldsComponent(
                title = stringResource(id = R.string.name),
                textFieldValue1 = uiState.firstName,
                textFieldValue2 = uiState.secondName,
                onValueChange1 = {
                    updateFirstNameEvent(it)
                },
                onValueChange2 = {
                    updateSecondNameEvent(it)
                },
                hint1 = R.string.first_name_hint,
                hint2 = R.string.second_name_hint,
                errorMessage1 = uiState.firstNameErrorMessage,
                errorMessage2 = uiState.secondNameErrorMessage,
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextFieldComponent(
                title = stringResource(id = R.string.speciality),
                textFieldValue = uiState.speciality,
                onValueChange = {
                    updateSpecialityEvent(it)
                },
                hint = R.string.ophthalmologist,
                errorMessage = uiState.specialityErrorMessage
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextFieldComponent(
                title = stringResource(id = R.string.clinic_name),
                textFieldValue = uiState.clinicName,
                onValueChange = {
                    updateClinicNameEvent(it)
                },
                hint = R.string.test_eye,
                errorMessage = uiState.clinicNameErrorMessage
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextFieldComponent(
                title = stringResource(id = R.string.password),
                textFieldValue = uiState.password,
                onValueChange = {
                    updatePasswordEvent(it)
                },
                hint = R.string.password,
                errorMessage = uiState.passwordErrorMessage,
                showEyeTrailingIcon = true,
                onVisibilityIconClicked = {
                    updatePasswordVisibilityStateEvent()
                },
                isPasswordVisible = uiState.isPasswordVisible,
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            ChooseTab(
                title = stringResource(id = R.string.gender),
                text1 = R.string.male,
                text2 = R.string.female,
                chooseTabState = uiState.chooseTabState,
                onChooseChange = { chooseTabState ->
                    updateGenderEvent(chooseTabState)
                },
                errorMessage = uiState.genderError
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            CheckBoxComponent(
                checked = uiState.acceptPrivacyIsChecked,
                onCheckedChange = {
                    updateCheckStateEvent(it)
                },
                text1 = stringResource(id = R.string.checkbox_auth_text1),
                text2 = stringResource(id = R.string.checkbox_auth_text2),
                text3 = stringResource(id = R.string.checkbox_auth_text3),
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            ElevatedButtonComponent(
                text = R.string.sign_up,
                onClick = {
                    onSignUpClickEvent()
                },
                modifier = Modifier.fillMaxWidth(),
                isDisabled = false
            )

            Spacer(modifier = Modifier.height(Spacing.large))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SpannableTextComponent(
                    text1 = stringResource(id = R.string.already_have_account_part1),
                    text2 = stringResource(id = R.string.already_have_account_part2),
                    onCLick = onSignUpClickEvent
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MediCareTheme {
        Surface {
            SignUpScreen(
                uiState = SignUpUiState(),
                onSignUpClickEvent = {},
                onLoginClickEvent = {},
                updateSecondNameEvent = {},
                updateCheckStateEvent = {},
                updatePasswordEvent = {},
                updateEmailEvent = {},
                updatePasswordVisibilityStateEvent = {},
                updateGenderEvent = {},
                updateSpecialityEvent={},
                updateErrorDialogVisibilityState = {},
                updateFirstNameEvent = {},
                updateClinicNameEvent = {},
                navigateToScheduleScreen = {},
                onClinicImagePickerDialogDismissRequest = {},
                onDoctorImagePickerDialogDismissRequest = {},
                onClinicImageSaveButtonClick = {},
                onDoctorImageSaveButtonClick = {},
                updateClinicImageBitmap = {},
                updateClinicImageUri = {},
                updateDoctorImageUri = {},
                updateDoctorImageBitmap = {},
            )
        }
    }
}