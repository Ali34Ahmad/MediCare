package com.example.medicare.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dispensary.ui.composables.AppLogo
import com.example.dispensary.ui.composables.CheckBoxComponent
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldWithTwoFieldsComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
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

        Spacer(modifier = Modifier.height(Spacing.small))

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

        Spacer(modifier = Modifier.height(Spacing.small))

        OutlinedTextFieldComponent(
            title = stringResource(id = R.string.password),
            textFieldValue = uiState.value.password,
            onValueChange = {
                viewModel.updatePassword(it)
            },
            hint = R.string.password,
            errorMessage = uiState.value.passwordErrorMessage
        )

        Spacer(modifier = Modifier.height(Spacing.small))

        ChooseTab(
            title = stringResource(id = R.string.gender),
            text1 = R.string.male,
            text2 = R.string.female,
            chooseTabState = uiState.value.gender,
            onChooseChange = { chooseTabState ->
                viewModel.updateGender(chooseTabState)
            }
        )

        Spacer(modifier = Modifier.height(Spacing.small))

        CheckBoxComponent(
            checked = uiState.value.acceptPrivacyIsChecked,
            onCheckedChange = {
                viewModel.updateCheckState(it)
            },
            text1 = stringResource(id = R.string.checkbox_auth_text1),
            text2 = stringResource(id = R.string.checkbox_auth_text2),
            text3 = stringResource(id = R.string.checkbox_auth_text3),
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        ElevatedButtonComponent(
            text = R.string.sign_up,
            onClick = { viewModel.signUp() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MediCareTheme {
        SignUpScreen(viewModel = viewModel())
    }
}