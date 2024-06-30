package com.example.medicare.presentation.signup

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.CheckBoxComponent
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldWithTwoFieldsComponent
import com.example.dispensary.ui.composables.SpannableTextComponent
import com.example.medicare.R
import com.example.medicare.core.composables.AppLogo
import com.example.medicare.core.composables.ErrorDialog
import com.example.medicare.core.composables.LoadingDialog
import com.example.medicare.data.model.result.AuthState
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun SignUpScreen(
    navigateToHomeScreen: () -> Unit,
    uiState: SignUpUiState,
    updateErrorDialogVisibilityState:()->Unit,
    updateEmailEvent:(String)->Unit,
    updateFirstNameEvent:(String)->Unit,
    updateSecondNameEvent:(String)->Unit,
    updatePasswordEvent:(String)->Unit,
    updatePasswordVisibilityStateEvent:()->Unit,
    updateGenderEvent:(ChooseTabState)->Unit,
    updateCheckStateEvent:(Boolean)->Unit,
    onSignUpClickEvent:()->Unit,
    onLoginClickEvent: () -> Unit
) {

    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityState()
        },
        onConfirmClick = {
            updateErrorDialogVisibilityState()
        }
    )
    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
    )

    if(uiState.isSignUpSuccessful is AuthState.Success)
        navigateToHomeScreen()

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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SpannableTextComponent(
                text1 = stringResource(id = R.string.already_have_account_part1),
                text2 = stringResource(id = R.string.log_in),
                onCLick =onLoginClickEvent
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
                navigateToHomeScreen = {},
                onLoginClickEvent = {},
                uiState = SignUpUiState(
                    email = "",
                    emailErrorMessage = null,
                    firstName = ""
                ),
                onSignUpClickEvent = {},
                updateSecondNameEvent = {},
                updateEmailEvent = {},
                updateCheckStateEvent = {},
                updateErrorDialogVisibilityState = {},
                updateFirstNameEvent = {},
                updateGenderEvent = {},
                updatePasswordEvent = {},
                updatePasswordVisibilityStateEvent = {}
            )
        }
    }
}