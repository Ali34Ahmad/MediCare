package com.example.medicare.presentation.home

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dispensary.ui.composables.ElevatedButtonComponent
import com.example.dispensary.ui.composables.ResponsibleDoctorCardComponent
import com.example.medicare.R
import com.example.medicare.core.enums.DayOfWeek
import com.example.medicare.core.enums.DayPeriod
import com.example.medicare.core.enums.Gender
import com.example.medicare.core.enums.Month
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.data.model.date.DaySocket
import com.example.medicare.data.model.date.FullDate
import com.example.medicare.data.model.date.Time
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
    navigateToBookAppointment: () -> Unit ,
    navigateToVaccinationAppointment: () -> Unit ,
    navigateToClinicsAppointment: () -> Unit ,
    onClinicClicked: (String) -> Unit ,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()
    val clinics:List<Clinic> = viewModel.clinics.collectAsStateWithLifecycle(initialValue = emptyList()).value
    Log.v("Clinics",clinics.toString())

    val clinicAppointments:List<Appointment> =viewModel.appointments.collectAsStateWithLifecycle(initialValue = emptyList()).value
        .filter { appointment ->  appointment.vaccineId.isBlank()}

    val vaccinationAppointments:List<Appointment> =viewModel.appointments.collectAsStateWithLifecycle(initialValue = emptyList()).value
        .filter { appointment ->  appointment.vaccineId.isNotBlank()}


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HomeScreenSection(
            title = R.string.clinics,
            list = {
                SectionsList(
                    clinics= clinics,
                    modifier = Modifier.padding(vertical = Spacing.small),
                    onClick={
                        viewModel.updateSelectedClinic(it)
                        onClinicClicked(clinics[it].id)
                    },
                    currentIndex = uiState.value.selectedClinicIndex

                )
            },
        )
        ResponsibleDoctorCardComponent(
            doctor= if(clinics.isNotEmpty()) clinics[uiState.value.selectedClinicIndex].responsibleDoctor else Doctor(),
            onClick = navigateToBookAppointment,
            modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small)
        )
        HomeScreenSection(
            title = R.string.upcoming_vaccinations,
            list = {
                UpcomingVaccinationAppointmentHorizontalList(
                    upcomingVaccinationAppointments = vaccinationAppointments,
                    modifier = Modifier.padding(vertical = Spacing.small)
                )
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
                ClinicAppointmentHorizontalList(
                    clinicsAppointments = clinicAppointments,
                    modifier = Modifier.padding(vertical = Spacing.small)
                )
            },
            onSubtextClick = {
                navigateToClinicsAppointment()
            },
            showSubtext = true,
            numberOfUpcomingAppointments = clinicAppointments.size,
            showSeeAllButton = true,
        )

        ElevatedButtonComponent(
            text = R.string.add_clinic,
            onClick = { viewModel.addClinic(
                Clinic(
                    name= "Cardiac",
                    workDays = listOf(
                        WorkDay(DayOfWeek.SUN, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.MON, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.TUE, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.WED, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.THU, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.FRI, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        WorkDay(DayOfWeek.SAT, openingTime = Time(9,0,DayPeriod.AM,), closingTime = Time(3,0,DayPeriod.PM)),
                        ),
                    daySockets = listOf(
                        DaySocket(
                            date= FullDate(day=1.toString(),month=Month.JUN,year=2024),
                            timeSockets = listOfTimeSockets
                        ),
                        DaySocket(
                            date= FullDate(day=2.toString(),month=Month.JUN,year=2024),
                            timeSockets = listOfTimeSockets
                        ),
                    ),
                    responsibleDoctor = Doctor(
                        firstName="Maya",
                        lastName="Junaid",
                        speciality = "Cardiologist",
                        imageUrl = "https://firebasestorage.googleapis.com/v0/b/medicare-cfddf.appspot.com/o/images%2FOIP%20(2).jpeg?alt=media&token=de3a4f4c-367f-4ac4-a8f4-099f881dd40a",
                        gender = Gender.FEMALE
                    )
                )
            ) }
        )

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
                Text(
                    text = stringResource(id = R.string.see_all),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { onSubtextClick() }
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
                onClinicClicked = {}
            )
        }
    }
}