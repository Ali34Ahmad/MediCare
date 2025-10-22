package com.example.doctor.presentation.login

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.doctor.core.composables.CheckBoxComponent
import com.example.doctor.core.composables.ElevatedButtonComponent
import com.example.doctor.core.composables.OutlinedTextFieldComponent
import com.example.doctor.core.composables.TextStyle
import com.example.doctor.core.composables.TextWithMultipleStyles
import com.example.doctor.R
import com.example.doctor.core.composables.AppLogo
import com.example.doctor.core.composables.ErrorDialog
import com.example.doctor.core.composables.LoadingDialog
import com.example.doctor.core.composables.SpannableTextComponent
import com.example.doctor.ui.theme.Spacing
import com.example.doctor.data.model.result.AuthState

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    navigateToScheduleScreen: () -> Unit,
    onSignUpClick: () -> Unit,
    updateErrorDialogVisibilityStateEvent: (Boolean) -> Unit,
    updateEmailEvent: (String) -> Unit,
    updatePasswordEvent: (String) -> Unit,
    updatePasswordVisibilityStateEvent: () -> Unit,
    updateCheckStateEvent: (Boolean) -> Unit,
    onLoginClick: () -> Unit,
) {

    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityStateEvent(false)
        },
        onConfirmClick = {
            updateErrorDialogVisibilityStateEvent(false)
        }
    )
    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
    )

    LaunchedEffect(uiState.authState) {
        if (uiState.authState is AuthState.Success)
            navigateToScheduleScreen()
    }


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
            textFieldValue = uiState.email,
            onValueChange = {
                updateEmailEvent(it)
            },
            hint = R.string.email_hint,
            errorMessage = uiState.emailErrorMessage
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
            imeAction = ImeAction.Done,
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
            text = R.string.log_in,
            onClick = { onLoginClick() },
            modifier = Modifier.fillMaxWidth(),
            isDisabled = false
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SpannableTextComponent(
                text1 = stringResource(id = R.string.do_not_have_account_part1),
                text2 = stringResource(id = R.string.do_not_have_account_part2),
                onCLick = onSignUpClick
            )
        }
    }
}