package com.example.medicare.presentation.clinicappointments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dispensary.ui.composables.ChooseTab
import com.example.medicare.R
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.ui.composables.ClinicAppointmentVerticalList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun ClinicAppointmentsScreen(
    modifier: Modifier = Modifier,
    viewModel: ClinicAppointmentsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val clinicAppointments:List<Appointment> =viewModel.appointments.collectAsStateWithLifecycle(initialValue = emptyList()).value
        .filter { appointment ->  appointment.vaccineId.isBlank()}

    

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

    ClinicAppointmentVerticalList(
        clinicsAppointments =clinicAppointments
    )
}

@Preview
@Composable
private fun ClinicAppointmentsScreenPreview() {
    MediCareTheme {
        Surface {
            ClinicAppointmentsScreen()
        }
    }
}