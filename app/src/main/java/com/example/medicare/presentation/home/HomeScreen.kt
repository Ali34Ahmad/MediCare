package com.example.medicare.presentation.home

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.ResponsibleDoctorCardComponent
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.ui.composables.ClinicAppointment
import com.example.medicare.ui.composables.ClinicAppointmentHorizontalList
import com.example.medicare.ui.composables.SectionsList
import com.example.medicare.ui.composables.UpcomingVaccinationAppointmentHorizontalList
import com.example.medicare.ui.composables.VaccinationAppointment
import com.example.medicare.ui.theme.MediCareTheme
import com.example.medicare.ui.theme.Spacing


@Composable
fun HomeScreen(
    navigateToBookAppointment: () -> Unit = {},
    navigateToVaccinationAppointment: () -> Unit = {},
    navigateToClinicsAppointment: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HomeScreenSection(
            title = R.string.clinics,
            list = {
                SectionsList(
                    modifier = Modifier.padding(vertical = Spacing.small)
                )
            },
        )
        ResponsibleDoctorCardComponent(
            doctorName = stringResource(id = R.string.test_doctor_name),
            doctorSpecialization = stringResource(id = R.string.test_doctor_specialization),
            doctorImage = R.drawable.doctor_image,
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(horizontal = Spacing.medium, vertical = Spacing.small)
        )
        HomeScreenSection(
            title = R.string.upcoming_vaccinations,
            list = {
                UpcomingVaccinationAppointmentHorizontalList(
                    modifier = Modifier.padding(vertical = Spacing.small)
                )
            },
            showSubtext = true,
            onSubtextClick = {
                navigateToVaccinationAppointment()
            },
            numberOfUpcomingAppointments = 3,
            showSeeAllButton = true,
        )

        HomeScreenSection(
            title = R.string.upcoming_appointment,
            list = {
                ClinicAppointmentHorizontalList(
                    modifier = Modifier.padding(vertical = Spacing.small)
                )
            },
            onSubtextClick = {
                navigateToClinicsAppointment()
            },
            showSubtext = true,
            numberOfUpcomingAppointments = 3,
            showSeeAllButton = true,
        )

    }
}

@Composable
fun HomeScreenSection(
    @StringRes title: Int,
    list: @Composable () -> Unit,
    showSubtext: Boolean = false,
    onSubtextClick: () -> Unit = {},
    numberOfUpcomingAppointments: Int = 0,
    showSeeAllButton: Boolean = false,
    modifier: Modifier = Modifier,
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
            HomeScreen()
        }
    }
}