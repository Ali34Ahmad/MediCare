package com.example.dispensary.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme

@Composable
fun OutlinedTextFieldComponent(
    title: String?,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    @StringRes hint: Int,
    modifier: Modifier = Modifier,
    errorMessage: String?,
    showTrailingIcon: Boolean = false,
    isRequired:Boolean=true
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = if(title==null){""}
            else{
                if (isRequired) "$title*" else title
                },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(0.dp))
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = if(!isFocused&&textFieldValue.equals("")) stringResource(id = hint) else "",
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
                if (showTrailingIcon) Icon(
                    imageVector = Icons.Outlined.Visibility,
                    contentDescription = null
                )
            },
        )
        Spacer(modifier = Modifier.height(4.dp))
        if(errorMessage!=null){
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color=MaterialTheme.colorScheme.error
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
    errorMessage1: String?,
    errorMessage2: String?
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
                showTrailingIcon = true,
                errorMessage = ""
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
                errorMessage1 = "",
                errorMessage2 = "",
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}




