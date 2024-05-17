package com.example.medicare.presentation.login

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.CheckBoxComponent
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldComponent
import com.example.dispensary.ui.composables.TextStyle
import com.example.dispensary.ui.composables.TextWithMultipleStyles
import com.example.medicare.R
import com.example.medicare.core.composables.AppLogo
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    viewModel: LoginViewModel = hiltViewModel()
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
            isPasswordVisible = uiState.value.isPasswordVisible
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
            onClick = { viewModel.login(onLoginClick) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextWithMultipleStyles(
                text1 = stringResource(id = R.string.do_not_have_account_part1),
                style1 = TextStyle.Normal,
                text2 = stringResource(id = R.string.do_not_have_account_part2),
                style2 = TextStyle.Normal,
                color2 = MaterialTheme.colorScheme.primary,
                fontSizeSp = 16,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    MediCareTheme {
        Surface {
            LoginScreen()
        }
    }
}