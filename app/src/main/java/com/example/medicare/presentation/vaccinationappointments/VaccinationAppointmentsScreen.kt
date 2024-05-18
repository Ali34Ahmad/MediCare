package com.example.medicare.presentation.vaccinationappointments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.ChooseTab
import com.example.medicare.R
import com.example.medicare.ui.composables.UpcomingVaccinationAppointmentVerticalList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun VaccinationAppointmentsScreen(
    modifier: Modifier = Modifier,
    viewModel: VaccinationAppointmentViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    ChooseTab(
        title = null,
        text1 = R.string.upcoming,
        text2 = R.string.history,
        chooseTabState = uiState.value.selectedFilter,
        onChooseChange = {
            viewModel.updateSelectedFilter(it)
        },
        modifier = Modifier.padding(horizontal = Spacing.medium)
    )
    Spacer(modifier = Modifier.height(Spacing.small))

    UpcomingVaccinationAppointmentVerticalList()

}

@Preview
@Composable
private fun VaccinationAppointmentsScreenPreview() {
    MediCareTheme {
        Surface {
            VaccinationAppointmentsScreen()
        }
    }
}