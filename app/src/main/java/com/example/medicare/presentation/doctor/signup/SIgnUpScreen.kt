package com.example.medicare.presentation.doctor.signup

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.CheckBoxComponent
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldWithTwoFieldsComponent
import com.example.dispensary.ui.composables.TextStyle
import com.example.dispensary.ui.composables.TextWithMultipleStyles
import com.example.medicare.R
import com.example.medicare.core.composables.AppLogo
import com.example.medicare.core.composables.ErrorDialog
import com.example.medicare.core.composables.LoadingDialog
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
    onLoginClick: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState()

    ErrorDialog(
        showDialog = uiState.value.showErrorDialog,
        onDismissRequest = {
            viewModel.updateErrorDialogVisibilityState()
        },
        onConfirmClick = {
            viewModel.updateErrorDialogVisibilityState()
        }
    )
    LoadingDialog(
        showDialog = uiState.value.showLoadingDialog,
    )

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
            textFieldValue = uiState.value.email,
            onValueChange = {
                viewModel.updateEmail(it)
            },
            hint = R.string.email_hint,
            errorMessage = uiState.value.emailErrorMessage
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldWithTwoFieldsComponent(
            title = stringResource(id = R.string.name),
            textFieldValue1 = uiState.value.firstName,
            textFieldValue2 = uiState.value.secondName,
            onValueChange1 = {
                viewModel.updateFirstName(it)
            },
            onValueChange2 = {
                viewModel.updateSecondName(it)
            },
            hint1 = R.string.first_name_hint,
            hint2 = R.string.second_name_hint,
            errorMessage1 = uiState.value.firstNameErrorMessage,
            errorMessage2 = uiState.value.secondNameErrorMessage,
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldComponent(
            title = stringResource(id = R.string.clinic_name),
            textFieldValue = uiState.value.clinicName,
            onValueChange = {
                viewModel.updateClinicName(it)
            },
            hint = R.string.test_eye,
            errorMessage = uiState.value.clinicNameErrorMessage
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldComponent(
            title = stringResource(id = R.string.password),
            textFieldValue = uiState.value.password,
            onValueChange = {
                viewModel.updatePassword(it)
            },
            hint = R.string.password,
            errorMessage = uiState.value.passwordErrorMessage,
            showEyeTrailingIcon = true,
            onVisibilityIconClicked = {
                viewModel.updatePasswordVisibilityState()
            },
            isPasswordVisible = uiState.value.isPasswordVisible,
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        ChooseTab(
            title = stringResource(id = R.string.gender),
            text1 = R.string.male,
            text2 = R.string.female,
            chooseTabState = uiState.value.chooseTabState,
            onChooseChange = { chooseTabState ->
                viewModel.updateGender(chooseTabState)
            },
            errorMessage = stringResource(id = uiState.value.genderError?:R.string.blank)
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        CheckBoxComponent(
            checked = uiState.value.acceptPrivacyIsChecked,
            onCheckedChange = {
                viewModel.updateCheckState(it)
            },
            text1 = stringResource(id = R.string.checkbox_auth_text1),
            text2 = stringResource(id = R.string.checkbox_auth_text2),
            text3 = stringResource(id = R.string.checkbox_auth_text3),
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        ElevatedButtonComponent(
            text = R.string.sign_up,
            onClick = {
                viewModel.signUp(onSignUpClick)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextWithMultipleStyles(
                text1 = stringResource(id = R.string.already_have_account_part1),
                style1 = TextStyle.Normal,
                text2 = stringResource(id = R.string.already_have_account_part2),
                style2 = TextStyle.Normal,
                color2 = MaterialTheme.colorScheme.primary,
                fontSizeSp = 16,
                modifier = Modifier.clickable { onLoginClick() }
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
                onSignUpClick = {},
                onLoginClick = {},
            )
        }
    }
}