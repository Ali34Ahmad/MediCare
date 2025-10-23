package com.example.medicare.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.ResponsibleDoctorCardComponent
import com.example.dispensary.ui.composables.SpannableTextComponent
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.core.composables.NoListItemAvailableComponent
import com.example.medicare.core.enums.DayOfWeek
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.Gender
import com.example.medicare.core.enums.Month
import com.example.medicare.core.enums.TimeSocketState
import com.example.medicare.data.fake.appointment1
import com.example.medicare.data.fake.appointment2
import com.example.medicare.data.fake.clinic1
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.Time
import com.example.medicare.data.model.date.TimeSocket
import com.example.medicare.data.model.date.WorkDay
import com.example.medicare.data.model.user.Doctor
import com.example.medicare.presentation.bookappointment.listOfTimeSockets
import com.example.medicare.ui.composables.ClinicAppointmentHorizontalList
import com.example.medicare.ui.composables.SectionsList
import com.example.medicare.ui.composables.UpcomingVaccinationAppointmentHorizontalList
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing


@Composable
fun HomeScreen(
    navigateToBookAppointment: (String) -> Unit,
    navigateToVaccinationAppointment: () -> Unit,
    navigateToClinicsAppointment: () -> Unit,
    onNotificationClick: () -> Unit,
    onNavigateUpClick: () -> Unit,
    updateSelectedClinicEvent: (Int) -> Unit,
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    clinics: List<Clinic>,
    clinicAppointments: List<Appointment>,
    vaccinationAppointments: List<Appointment>
) {
    Scaffold(
        topBar = {
            MedicareTopAppBar(
                showNavigateUpIconButton = false,
                showNotificationIconButton = true,
                title = R.string.app_name,
                onNotificationClick = onNotificationClick,
                onNavigateUpClick = onNavigateUpClick
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                HomeScreenSection(
                    title = R.string.clinics,
                    list = {
                        SectionsList(
                            clinics = clinics,
                            modifier = Modifier.padding(vertical = Spacing.small),
                            onClick = {
                                updateSelectedClinicEvent(it)
                            },
                            currentIndex = uiState.selectedClinicIndex

                        )
                    },
                )
                ResponsibleDoctorCardComponent(
                    doctor = if (clinics.isNotEmpty()) clinics[uiState.selectedClinicIndex].responsibleDoctor else Doctor(),
                    onClick = { if (clinics.isNotEmpty()) navigateToBookAppointment(clinics[uiState.selectedClinicIndex].id) },
                    modifier = Modifier.padding(
                        horizontal = Spacing.medium,
                        vertical = Spacing.small
                    )
                )
                HomeScreenSection(
                    title = R.string.upcoming_vaccinations,
                    list = {
                        if (vaccinationAppointments.isNotEmpty())
                            UpcomingVaccinationAppointmentHorizontalList(
                                upcomingVaccinationAppointments = vaccinationAppointments,
                                modifier = Modifier.padding(vertical = Spacing.small)
                            )
                        else
                            NoListItemAvailableComponent(text=R.string.no_upcoming_vaccination_appointments)
                    },
                    showSubtext = true,
                    onSubtextClick = {
                        navigateToVaccinationAppointment()
                    },
                    numberOfUpcomingAppointments = vaccinationAppointments.size,
                    showSeeAllButton = true,
                )

                HomeScreenSection(
                    title = R.string.upcoming_appointment,
                    list = {
                        if(clinicAppointments.isNotEmpty())
                        ClinicAppointmentHorizontalList(
                            clinicsAppointments = clinicAppointments,
                            modifier = Modifier.padding(vertical = Spacing.small)
                        )
                        else
                            NoListItemAvailableComponent(text=R.string.no_upcoming_appointments)
                    },
                    onSubtextClick = {
                        navigateToClinicsAppointment()
                    },
                    showSubtext = true,
                    numberOfUpcomingAppointments = clinicAppointments.size,
                    showSeeAllButton = true,
                )

            }
        }
    }
}

@Composable
fun HomeScreenSection(
    @StringRes title: Int,
    showSubtext: Boolean = false,
    onSubtextClick: () -> Unit = {},
    numberOfUpcomingAppointments: Int = 0,
    showSeeAllButton: Boolean = false,
    modifier: Modifier = Modifier,
    list: @Composable () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = Spacing.medium,
                    end = Spacing.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = Spacing.medium,
                        end = Spacing.medium,
                    ),
            ) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.titleMedium
                )
                if (showSubtext)
                    Text(
                        text = "$numberOfUpcomingAppointments upcoming",
                        style = MaterialTheme.typography.bodySmall,
                    )
            }
            if (showSeeAllButton)
                SpannableTextComponent(
                    onCLick = onSubtextClick,
                    text1 = stringResource(id = R.string.blank),
                    text2 = stringResource(id = R.string.see_all),
                    spanStyle = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    textStyle = MaterialTheme.typography.labelLarge
                )
        }
        list()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MediCareTheme {
        Surface {
            HomeScreen(
                navigateToBookAppointment = {},
                navigateToClinicsAppointment = {},
                navigateToVaccinationAppointment = {},
                onNavigateUpClick = {},
                onNotificationClick = {},
                uiState = HomeUiState(),
                updateSelectedClinicEvent = {},
                clinics = listOf(
                    clinic1,
                    clinic1,
                    clinic1,
                    clinic1,
                    clinic1,
                    clinic1,
                ),
                clinicAppointments = listOf(
                    appointment1,
                    appointment1,
                    appointment1,
                    appointment2,
                    appointment2,
                ),
                vaccinationAppointments = listOf(
                    appointment1,
                    appointment1,
                    appointment1,
                    appointment2,
                    appointment2,
                )
            )
        }
    }
}