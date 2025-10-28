package com.example.doctor.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.doctor.R
import com.example.doctor.core.composables.DefaultVaccineTableList
import com.example.doctor.core.composables.ClinicAppointmentVerticalList
import com.example.doctor.core.composables.ClinicInformationCardComponent
import com.example.doctor.core.composables.DatePickerButtonComponent
import com.example.doctor.core.composables.DaySocketHorizontalList
import com.example.doctor.core.composables.ElevatedButtonComponent
import com.example.doctor.core.composables.ErrorDialog
import com.example.doctor.core.composables.LoadingComponent
import com.example.doctor.core.composables.LoadingDialog
import com.example.doctor.core.composables.MedicareTopAppBar
import com.example.doctor.core.composables.MyDatePickerDialog
import com.example.doctor.core.composables.SuccessDialog
import com.example.doctor.core.toFullDate
import com.example.doctor.data.fake.clinic1
import com.example.doctor.data.fake.vaccinesClinic
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.presentation.schedule.HomeScreenSection
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing
import java.time.LocalDate

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    modifier: Modifier = Modifier,
    clinicsAppointments: List<Appointment>,
    appointmentsVisitNumbers: List<Int>,
    updateBookedDateEvent: (LocalDate) -> Unit,
    pushNotificationEvent: (Vaccine) -> Unit,
    defaultVaccineTableVaccines: List<Vaccine>,
    onAvailableVaccineListItemClick: (Int) -> Unit,
    updateErrorDialogVisibilityStateEvent:(Boolean)->Unit,
    updateSuccessDialogVisibilityStateEvent:(Boolean)->Unit,
) {
    ErrorDialog(
        showDialog = uiState.showErrorDialog,
        onDismissRequest = {
            updateErrorDialogVisibilityStateEvent(false)
        },
        onConfirmClick = {
            updateErrorDialogVisibilityStateEvent(false)
        }
    )
    LoadingDialog(
        showDialog = uiState.showLoadingDialog,
    )
    SuccessDialog(
        showDialog = uiState.showSuccessDialog,
        onDismissRequest = {
            updateSuccessDialogVisibilityStateEvent(false)
        },
        onConfirmClick = {
            updateSuccessDialogVisibilityStateEvent(false)
        }
    )


    MyDatePickerDialog(
        onConfirmButtonClick = { date ->
            updateBookedDateEvent(date)
            uiState.datePickerState.hide()
        },
        datePickerState = uiState.datePickerState,
        startDayBeforeToday = 1000
    )

    Scaffold(
        topBar = {
            MedicareTopAppBar(
                title = R.string.app_name,
            )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            if (clinicsAppointments.isEmpty()){
                LoadingComponent()
            }else{
                Column(
                    modifier = modifier
                        .fillMaxSize(),
                ) {
                    Spacer(modifier = Modifier.height(Spacing.medium))

                    ClinicInformationCardComponent(
                        clinic = uiState.clinic,
                        modifier = Modifier.padding(horizontal = Spacing.medium)
                    )

                    Spacer(modifier = Modifier.height(Spacing.small))

                    if (uiState.clinic.name == stringResource(id = R.string.vaccines)) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = Spacing.medium),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = stringResource(id = R.string.new_vaccine_available))
                                Spacer(modifier = Modifier.weight(1f))
                                ElevatedButtonComponent(
                                    text = R.string.notify_parents,
                                    onClick = {
                                        if (uiState.currentSelectedVaccineIndex != null && defaultVaccineTableVaccines.isNotEmpty())
                                            pushNotificationEvent(
                                                defaultVaccineTableVaccines[uiState.currentSelectedVaccineIndex]
                                            )
                                    },
                                    isDisabled = false
                                )
                            }
                            Spacer(modifier = Modifier.height(Spacing.medium))

                            DefaultVaccineTableList(
                                defaultVaccineTable = defaultVaccineTableVaccines,
                                selectedVaccineIndex = uiState.currentSelectedVaccineIndex,
                                onAvailableVaccineListItemClick = onAvailableVaccineListItemClick
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(Spacing.small))

                    HomeScreenSection(title = R.string.appointment_history) {
                        Column {
                            DatePickerButtonComponent(
                                date = uiState.bookedDate.toFullDate(),
                                onClick = {
                                    uiState.datePickerState.show()
                                },
                                modifier = Modifier.padding(horizontal = Spacing.medium),
                            )
                            Spacer(modifier = Modifier.height(Spacing.medium))

                            DaySocketHorizontalList(
                                workDays = uiState.clinic.workDays,
                                selectedIndex = uiState.selectedDaySocketIndex,
                                updateSelectedIndex = updateBookedDateEvent,
                                startDayBeforeToday = 60,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Spacing.medium))

                    ClinicAppointmentVerticalList(
                        clinicsAppointments = clinicsAppointments,
                        appointmentsVisitNumbers = appointmentsVisitNumbers
                    )


                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview1() {
    MediCareTheme {
        Surface {
            ProfileScreen(
                uiState = ProfileUiState(
                    clinic = clinic1,
                ),
                updateBookedDateEvent = {},
                clinicsAppointments = emptyList(),
                appointmentsVisitNumbers = emptyList(),
                pushNotificationEvent = {},
                onAvailableVaccineListItemClick = {},
                defaultVaccineTableVaccines = emptyList(),
                updateErrorDialogVisibilityStateEvent = {},
                updateSuccessDialogVisibilityStateEvent={},
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview2() {
    MediCareTheme {
        Surface {
            ProfileScreen(
                uiState = ProfileUiState(
                    clinic = vaccinesClinic,
                ),
                updateBookedDateEvent = {},
                clinicsAppointments = emptyList(),
                appointmentsVisitNumbers = emptyList(),
                pushNotificationEvent = {},
                onAvailableVaccineListItemClick = {},
                defaultVaccineTableVaccines = emptyList(),
                updateErrorDialogVisibilityStateEvent = {},
                updateSuccessDialogVisibilityStateEvent={},
            )
        }
    }
}
