package com.example.medicare.presentation.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
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
import com.example.medicare.R
import com.example.medicare.core.composables.MedicareTopAppBar
import com.example.medicare.presentation.addchild.AddChildScreen
import com.example.medicare.presentation.children.ChildrenScreen
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsScreen
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentsScreen
import com.example.medicare.ui.theme.MediCareTheme

@Composable
fun MainScreen(
    userId: String?,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            MedicareTopAppBar(title = R.string.app_name)
        },
        bottomBar = {
            BottomNavBarComponent(
                onItem1Click = {
                    viewModel.updateCurrentScreen(Destination.Home)
                },
                onItem2Click = {
                    viewModel.updateCurrentScreen(Destination.VaccinationAppointments)
                },
                onItem3Click = {
                    viewModel.updateCurrentScreen(Destination.ClinicAppointments)
                },
                onItem4Click = {
                    viewModel.updateCurrentScreen(Destination.Children)
                },
                selectedIndex = uiState.value.selectedBottomNavBarIndex
            )
        },
        floatingActionButton = {
            if (uiState.value.currentScreen is Destination.Children)
                FloatingActionButton(onClick = {
                    viewModel.updateCurrentScreen(Destination.AddChild)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = stringResource(id = R.string.add_child)
                    )
                }
        }
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                when (uiState.value.currentScreen) {
                    is Destination.Home -> HomeScreen(
                        navigateToVaccinationAppointment = {
                            viewModel.updateCurrentScreen(Destination.VaccinationAppointments)
                        },
                        navigateToClinicsAppointment = {
                            viewModel.updateCurrentScreen(Destination.ClinicAppointments)
                        },
                    )

                    is Destination.VaccinationAppointments -> VaccinationAppointmentsScreen()
                    is Destination.ClinicAppointments -> ClinicAppointmentsScreen()
                    is Destination.Children -> ChildrenScreen(onAddChildClick={
                        viewModel.updateCurrentScreen(Destination.AddChild)
                    })
                    is Destination.AddChild -> AddChildScreen(onAddChildClick = {})
                    else -> HomeScreen()
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
                userId = "12kduejadkkjlh2",
            )
        }
    }
}