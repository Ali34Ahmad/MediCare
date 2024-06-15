package com.example.medicare.presentation.main

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
import com.example.medicare.data.model.clinic.Clinic
import com.example.medicare.presentation.addchild.AddChildScreen
import com.example.medicare.presentation.bookappointment.BookAppointmentScreen
import com.example.medicare.presentation.children.ChildrenScreen
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsScreen
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.notification.NotificationScreen
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentsScreen
import com.example.medicare.ui.theme.MediCareTheme

val screenWhereToShowBottomBar = listOf(
    Destination.Home,
    Destination.VaccinationAppointments,
    Destination.ClinicAppointments,
    Destination.Children,
)


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
                showNavigateUpIconButton = !screenWhereToShowBottomBar.contains(uiState.value.currentScreen),
                title = R.string.app_name,
                onNotificationClick = {
                    viewModel.updateCurrentScreen(Destination.Notification)
                },
                onNavigateUpClick = {
                    viewModel.navigateBackButtonClicked()
                }
            )
        },
        bottomBar = {
            if (screenWhereToShowBottomBar.contains(uiState.value.currentScreen))
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
                    selectedIndex = uiState.value.selectedBottomNavBarIndex,
                    listOfOutlinedIcons = listOfOutlinedIcons,
                    listOfFilledIcons = listOfFilledIcons,
                    bottomNavItems = bottomNavItems
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
                        navigateToBookAppointment = {
                            viewModel.updateCurrentScreen(Destination.BookAppointment(""))
                        },
                        onClinicClicked={

                        }
                    )

                    is Destination.VaccinationAppointments -> VaccinationAppointmentsScreen()
                    is Destination.ClinicAppointments -> ClinicAppointmentsScreen()
                    is Destination.Children -> ChildrenScreen(onAddChildClick = {
                        viewModel.updateCurrentScreen(Destination.AddChild)
                    })

                    is Destination.AddChild -> AddChildScreen(onAddChildClick = {
                        viewModel.updateCurrentScreen(Destination.Children)
                    })

                    is Destination.Notification -> NotificationScreen(onNotificationCardClick = {})

                    is Destination.BookAppointment -> BookAppointmentScreen(
                        onBookNowButtonClick = {
                            viewModel.updateCurrentScreen(Destination.Home)
                        },
                        clinic= Clinic()
                    )

                    else -> HomeScreen(
                        navigateToVaccinationAppointment = {
                            viewModel.updateCurrentScreen(Destination.VaccinationAppointments)
                        },
                        navigateToClinicsAppointment = {
                            viewModel.updateCurrentScreen(Destination.ClinicAppointments)
                        },
                        navigateToBookAppointment = {
                            viewModel.updateCurrentScreen(Destination.BookAppointment(""))
                        },
                        onClinicClicked={

                        }
                    )
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