package com.example.medicare.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dispensary.ui.composables.ChooseTab
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.dispensary.ui.composables.VaccinationAppointmentCardComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

data class VaccinationAppointment(
    val dayOfMonth: String,
    val dayOfWeek: String,
    val patientName: String,
    val appointmentTime: String,
    val vaccineName: String,
    val remainingTime: String,
)

@Composable
fun UpcomingVaccinationAppointmentHorizontalList(
    upcomingVaccinationAppointments: List<VaccinationAppointment> = emptyList(),
    modifier: Modifier = Modifier,
) {
    val listOfUpcomingVaccinations = listOf(
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),

        )
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(listOfUpcomingVaccinations) { upcomingVaccinationAppointment ->
            VaccinationAppointmentCardComponent(
                onClick = { /*TODO*/ },
                dayOfMonth = upcomingVaccinationAppointment.dayOfMonth,
                dayOfWeek = upcomingVaccinationAppointment.dayOfWeek,
                patientName = upcomingVaccinationAppointment.patientName,
                appointmentTime = upcomingVaccinationAppointment.appointmentTime,
                vaccineName = upcomingVaccinationAppointment.vaccineName,
                remainingTime = upcomingVaccinationAppointment.remainingTime,
                modifier = Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.width(Spacing.medium))
        }
    }
}

@Composable
fun UpcomingVaccinationAppointmentVerticalList(
    upcomingVaccinationAppointments: List<VaccinationAppointment> = emptyList(),
    modifier: Modifier = Modifier,
) {
    val listOfUpcomingVaccinations = listOf(
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        VaccinationAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            vaccineName = stringResource(id = R.string.test_vaccine_name),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),

        )
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(listOfUpcomingVaccinations) { upcomingVaccinationAppointment ->
            VaccinationAppointmentCardComponent(
                onClick = { /*TODO*/ },
                dayOfMonth = upcomingVaccinationAppointment.dayOfMonth,
                dayOfWeek = upcomingVaccinationAppointment.dayOfWeek,
                patientName = upcomingVaccinationAppointment.patientName,
                appointmentTime = upcomingVaccinationAppointment.appointmentTime,
                vaccineName = upcomingVaccinationAppointment.vaccineName,
                remainingTime = upcomingVaccinationAppointment.remainingTime,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Spacing.extraSmall))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VaccinationAppointmentHorizontalListPreview() {
    MediCareTheme {
        Surface {
            val listOfUpcomingVaccinations = listOf(
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),

                )
            UpcomingVaccinationAppointmentHorizontalList(listOfUpcomingVaccinations)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VaccinationAppointmentVerticalListPreview() {
    MediCareTheme {
        Surface {
            val listOfUpcomingVaccinations = listOf(
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                VaccinationAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    vaccineName = stringResource(id = R.string.test_vaccine_name),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),

                )
            UpcomingVaccinationAppointmentVerticalList()
        }
    }
}