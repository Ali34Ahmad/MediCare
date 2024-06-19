package com.example.medicare.presentation.vaccinationappointments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.Month
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket
import com.example.medicare.ui.composables.UpcomingVaccinationAppointmentVerticalList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

@Composable
fun VaccinationAppointmentsScreen(
    onNotificationIconButtonClick: () -> Unit,
    uiState: VaccinationAppointmentUiState,
    vaccinationAppointments: List<Appointment>,
    updateSelectedFilter: (ChooseTabState) -> Unit,
) {

    Scaffold(
        topBar = {
            MedicareTopAppBar(
                showNavigateUpIconButton = false,
                showNotificationIconButton = true,
                title = R.string.app_name,
                onNotificationClick = onNotificationIconButtonClick
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column {
                ChooseTab(
                    title = null,
                    text1 = R.string.upcoming,
                    text2 = R.string.history,
                    chooseTabState = uiState.selectedFilter,
                    onChooseChange = {
                        updateSelectedFilter(it)
                    },
                    modifier = Modifier.padding(horizontal = Spacing.medium)
                )
                Spacer(modifier = Modifier.height(Spacing.small))

                UpcomingVaccinationAppointmentVerticalList(upcomingVaccinationAppointments = vaccinationAppointments)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VaccinationAppointmentsScreenPreview() {
    MediCareTheme {
        Surface {
            VaccinationAppointmentsScreen(
                onNotificationIconButtonClick = {},
                uiState = VaccinationAppointmentUiState(ChooseTabState.First),
                vaccinationAppointments = listOf(
                    Appointment(
                        id = "df",
                        clinicId = "23d",
                        userId = "Ali",
                        date = FullDate(10, Month.JUN, 2023),
                        timeSocket = TimeSocket(
                            Time(10, 10, DayPeriod.AM),
                            state = TimeSocketState.FREE
                        ),
                        vaccineId = "vaccine"
                    )
                ),
                updateSelectedFilter = {},
            )
        }
    }
}