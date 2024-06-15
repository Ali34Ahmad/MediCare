package com.example.medicare.presentation.doctor.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dispensary.ui.composables.BottomNavBarComponent
import com.example.dispensary.ui.composables.bottomNavItems
import com.example.dispensary.ui.composables.listOfFilledIcons
import com.example.dispensary.ui.composables.listOfOutlinedIcons
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.presentation.addchild.AddChildScreen
import com.example.medicare.presentation.bookappointment.BookAppointmentScreen
import com.example.medicare.presentation.children.ChildrenScreen
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsScreen
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.navigation.DoctorDestination
import com.example.medicare.presentation.notification.NotificationScreen
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentsScreen
import com.example.medicare.ui.theme.MediCareTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            MedicareTopAppBar(
                title = R.string.app_name,
            )
        },
        bottomBar = {
                BottomNavBarComponent(
                    onItem1Click = {
                        viewModel.updateCurrentScreen(DoctorDestination.Schedule)
                    },
                    onItem2Click = {
                        viewModel.updateCurrentScreen(DoctorDestination.Profile)
                    },
                    selectedIndex = uiState.value.selectedBottomNavBarIndex,
                    listOfOutlinedIcons = listOfOutlinedIcons,
                    listOfFilledIcons = listOfFilledIcons,
                    bottomNavItems = bottomNavItems
                )
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                when (uiState.value.currentScreen) {
                    is DoctorDestination.Schedule -> {/*ScheduleScreen()*/}

                    is DoctorDestination.Profile -> {/*ProfileScreen*/}

                    else -> {/*ScheduleScreen()*/}
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MediCareTheme {
        Surface {
            MainScreen(
            )
        }
    }
}