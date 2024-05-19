package com.example.medicare.presentation.addchild

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.CheckBoxComponent
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldComponent
import com.example.dispensary.ui.composables.OutlinedTextFieldWithTwoFieldsComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun AddChildScreen(
    modifier: Modifier = Modifier,
    onAddChildClick:()->Unit,
    viewModel: AddChildViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Spacing.medium),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextFieldComponent(
            title = stringResource(id = R.string.number),
            textFieldValue =uiState.value.number,
            onValueChange = {
                viewModel.updateNumber(it)
            },
            hint = R.string.number_hint,
            errorMessage = uiState.value.numberErrorMessage
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldWithTwoFieldsComponent(
            title = stringResource(id = R.string.name),
            textFieldValue1 = uiState.value.childFirstName,
            textFieldValue2 = uiState.value.childSecondName,
            onValueChange1 = {
                viewModel.updateChildFirstName(it)
            },
            onValueChange2 = {
                viewModel.updateChildSecondName(it)
            },
            hint1 = R.string.first_name_hint,
            hint2 = R.string.second_name_hint,
            errorMessage1 = uiState.value.childSecondNameErrorMessage,
            errorMessage2 = uiState.value.childSecondNameErrorMessage,
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldWithTwoFieldsComponent(
            title = stringResource(id = R.string.father_name),
            textFieldValue1 = uiState.value.fatherFirstName,
            textFieldValue2 = uiState.value.fatherSecondName,
            onValueChange1 = {
                viewModel.updateFatherFirstName(it)
            },
            onValueChange2 = {
                viewModel.updateFatherSecondName(it)
            },
            hint1 = R.string.first_name_hint,
            hint2 = R.string.second_name_hint,
            errorMessage1 = uiState.value.fatherFirstNameErrorMessage,
            errorMessage2 = uiState.value.fatherSecondNameErrorMessage,
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldWithTwoFieldsComponent(
            title = stringResource(id = R.string.mother_name),
            textFieldValue1 = uiState.value.motherFirstName,
            textFieldValue2 = uiState.value.motherSecondName,
            onValueChange1 = {
                viewModel.updateMotherFirstName(it)
            },
            onValueChange2 = {
                viewModel.updateMotherSecondName(it)
            },
            hint1 = R.string.first_name_hint,
            hint2 = R.string.second_name_hint,
            errorMessage1 = uiState.value.motherFirstNameErrorMessage,
            errorMessage2 = uiState.value.motherSecondNameErrorMessage,
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        OutlinedTextFieldComponent(
            title = stringResource(id = R.string.date_of_birth),
            textFieldValue = uiState.value.dateOfBirth,
            onValueChange = {
                viewModel.updateDateOfBirth(it)
            },
            hint = R.string.birthday,
            errorMessage = uiState.value.dateOfBirthErrorMessage
        )

        Spacer(modifier = Modifier.height(Spacing.medium))

        ChooseTab(
            title = stringResource(id = R.string.gender),
            text1 = R.string.male,
            text2 = R.string.female,
            chooseTabState = uiState.value.gender,
            onChooseChange = { chooseTabState ->
                viewModel.updateGender(chooseTabState)
            },
            errorMessage = uiState.value.genderErrorMessage
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
            text = R.string.add_child,
            onClick = { viewModel.addChild(onAddChildClick) },
            modifier = Modifier.fillMaxWidth()
        )
    }


}

@Preview
@Composable
private fun AddChildScreenPreview() {
    MediCareTheme {
        Surface {
            AddChildScreen(onAddChildClick={})
        }
    }
}