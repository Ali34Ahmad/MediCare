package com.example.medicare.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dispensary.ui.composables.ClinicAppointmentCardComponent
import com.example.medicare.R
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing

data class ClinicAppointment(
    val dayOfMonth: String,
    val dayOfWeek: String,
    val patientName: String,
    val appointmentTime: String,
    val doctorName: String,
    val sectionName: String,
    val remainingTime: String,
)

@Composable
fun ClinicAppointmentHorizontalList(
    clinicsAppointments: List<ClinicAppointment> = emptyList(),
    modifier: Modifier = Modifier,
) {
    val listOfUpcomingVaccinations= listOf(
        ClinicAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            doctorName = stringResource(id = R.string.test_doctor_name),
            sectionName = stringResource(id = R.string.test_eye),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        ClinicAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            doctorName = stringResource(id = R.string.test_doctor_name),
            sectionName = stringResource(id = R.string.test_eye),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),
        ClinicAppointment(
            dayOfMonth = stringResource(id = R.string.test_day_of_month),
            dayOfWeek = stringResource(id = R.string.test_day_of_week),
            patientName = stringResource(id = R.string.test_patient_name),
            appointmentTime = stringResource(id = R.string.test_time),
            doctorName = stringResource(id = R.string.test_doctor_name),
            sectionName = stringResource(id = R.string.test_eye),
            remainingTime = stringResource(id = R.string.test_remaining_time),
        ),

        )
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Spacing.medium)
    ) {
        items(listOfUpcomingVaccinations){upcomingClinicAppointment->
            ClinicAppointmentCardComponent(
                onClick = { /*TODO*/ },
                dayOfMonth = upcomingClinicAppointment.dayOfMonth,
                dayOfWeek = upcomingClinicAppointment.dayOfWeek,
                patientName = upcomingClinicAppointment.patientName,
                doctorName = upcomingClinicAppointment.doctorName,
                doctorSpecialization = upcomingClinicAppointment.sectionName,
                appointmentTime = upcomingClinicAppointment.appointmentTime,
                remainingTime =upcomingClinicAppointment.remainingTime,
                modifier= Modifier.width(300.dp)
            )
            Spacer(modifier = Modifier.width(Spacing.medium))
        }
    }
}

@Preview
@Composable
private fun ClinicAppointmentHorizontalListPreview() {
    MediCareTheme {
        Surface {
            val listOfUpcomingVaccinations= listOf(
                ClinicAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    doctorName = stringResource(id = R.string.test_doctor_name),
                    sectionName = stringResource(id = R.string.test_eye),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                ClinicAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    doctorName = stringResource(id = R.string.test_doctor_name),
                    sectionName = stringResource(id = R.string.test_eye),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),
                ClinicAppointment(
                    dayOfMonth = stringResource(id = R.string.test_day_of_month),
                    dayOfWeek = stringResource(id = R.string.test_day_of_week),
                    patientName = stringResource(id = R.string.test_patient_name),
                    appointmentTime = stringResource(id = R.string.test_time),
                    doctorName = stringResource(id = R.string.test_doctor_name),
                    sectionName = stringResource(id = R.string.test_eye),
                    remainingTime = stringResource(id = R.string.test_remaining_time),
                ),

                )
            ClinicAppointmentHorizontalList(listOfUpcomingVaccinations)
        }
    }
}