package com.example.doctor.presentation.schedule

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.doctor.R
import com.example.doctor.core.composables.ClinicAppointmentVerticalList
import com.example.doctor.core.composables.DatePickerButtonComponent
import com.example.doctor.core.composables.DaySocketHorizontalList
import com.example.doctor.core.composables.LoadingComponent
import com.example.doctor.core.composables.MedicareTopAppBar
import com.example.doctor.core.composables.MyDatePickerDialog
import com.example.doctor.core.composables.SpannableTextComponent
import com.example.doctor.core.toFullDate
import com.example.doctor.data.model.appointment.Appointment
import com.example.doctor.data.model.date.DaySocket
import com.example.doctor.data.model.date.FullDate
import com.example.doctor.ui.theme.MediCareTheme
import com.example.doctor.ui.theme.Spacing
import java.time.LocalDate

@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier,
    uiState: ScheduleUiState,
    clinicsAppointments: List<Appointment>,
    updateBookedDateEvent: (LocalDate) -> Unit,
    appointmentsVisitNumbers: MutableList<Int>,
) {
    MyDatePickerDialog(
        onConfirmButtonClick = { date ->
            updateBookedDateEvent(date)
            uiState.datePickerState.hide()
        },
        datePickerState = uiState.datePickerState
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
                    HomeScreenSection(title = R.string.upcoming_appointments) {
                        Column {
                            Spacer(modifier = Modifier.height(Spacing.small))
                            DatePickerButtonComponent(
                                date = uiState.bookedDate.toFullDate(),
                                onClick = {
                                    uiState.datePickerState.show()
                                },
                                modifier = Modifier.padding(horizontal = Spacing.medium),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(Spacing.medium))

                    DaySocketHorizontalList(
                        workDays = uiState.clinic.workDays,
                        selectedIndex = uiState.selectedDaySocketIndex,
                        updateSelectedIndex = updateBookedDateEvent,
                        startDayBeforeToday = 0,
                    )

                    Spacer(modifier = Modifier.height(Spacing.medium))

                    ClinicAppointmentVerticalList(
                        clinicsAppointments = clinicsAppointments,
                        appointmentsVisitNumbers = appointmentsVisitNumbers,
                    )
                }
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

@Preview
@Composable
private fun ScheduleScreenPreview() {
    MediCareTheme {
        Surface {
            ScheduleScreen(
                uiState = ScheduleUiState(),
                clinicsAppointments = emptyList(),
                updateBookedDateEvent = {},
                appointmentsVisitNumbers = mutableListOf(),
            )
        }
    }
}