package com.example.doctor.core.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doctor.R
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing

@Composable
fun OutlinedTextFieldComponent(
    title: String?,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    errorMessage: Int?,
    showEyeTrailingIcon: Boolean = false,
    isPasswordVisible: Boolean = false,
    isRequired: Boolean = true,
    onVisibilityIconClicked: () -> Unit = {},
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = if (title == null) {
                ""
            } else {
                if (isRequired) "$title*" else title
            },
            style = MaterialTheme.typography.titleMedium
        )
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = if (!isFocused && textFieldValue.equals("")) stringResource(id = hint) else "",
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    isFocused = focusState.isFocused
                },
            maxLines = 1,
            trailingIcon = {
                if (showEyeTrailingIcon)
                    IconButton(onClick = onVisibilityIconClicked) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = null
                        )
                    }
            },
            visualTransformation = if (isPasswordVisible||!showEyeTrailingIcon) VisualTransformation.None else PasswordVisualTransformation(),
        )

        Spacer(modifier = Modifier.height(Spacing.extraSmall))
        if (errorMessage != null) {
            Text(
                text = stringResource(errorMessage),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

    }
}

@Composable
fun OutlinedTextFieldWithTwoFieldsComponent(
    title: String?,
    textFieldValue1: String,
    textFieldValue2: String,
    onValueChange1: (String) -> Unit,
    onValueChange2: (String) -> Unit,
    @StringRes hint1: Int,
    @StringRes hint2: Int,
    modifier: Modifier = Modifier,
    errorMessage1: Int?,
    errorMessage2: Int?
) {
    Row(modifier = modifier.fillMaxWidth()) {
        OutlinedTextFieldComponent(
            title = title,
            textFieldValue = textFieldValue1,
            onValueChange = onValueChange1,
            hint = hint1,
            modifier = Modifier.weight(1f),
            errorMessage = errorMessage1,
        )
        Spacer(modifier = Modifier.weight(0.1f))
        OutlinedTextFieldComponent(
            title = null,
            textFieldValue = textFieldValue2,
            onValueChange = onValueChange2,
            hint = hint2,
            modifier = Modifier.weight(1f),
            errorMessage = errorMessage2,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview() {
    MediCareTheme {
        Surface {
            OutlinedTextFieldComponent(
                title = stringResource(id = R.string.email),
                textFieldValue = "",
                onValueChange = {},
                hint = R.string.email_hint,
                modifier = Modifier.padding(16.dp),
                showEyeTrailingIcon = true,
                errorMessage = R.string.blank
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldWithTwoFieldsComponentPreview() {
    MediCareTheme {
        Surface {
            OutlinedTextFieldWithTwoFieldsComponent(
                title = stringResource(id = R.string.name),
                textFieldValue1 = "",
                textFieldValue2 = "",
                onValueChange1 = {},
                onValueChange2 = {},
                hint1 = R.string.first_name_hint,
                hint2 = R.string.second_name_hint,
                errorMessage1 = R.string.blank,
                errorMessage2 = R.string.blank,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}




