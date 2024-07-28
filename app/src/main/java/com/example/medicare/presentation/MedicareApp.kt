package com.example.medicare.presentation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.dispensary.ui.composables.ChooseTabState
import com.example.medicare.presentation.addchild.AddChildScreen
import com.example.medicare.presentation.children.ChildrenScreen
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsScreen
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.login.LoginScreen
import com.example.medicare.core.composables.MainScaffold
import com.example.medicare.core.isUpcoming
import com.example.medicare.core.navigate
import com.example.medicare.core.navigateToBookAppointment
import com.example.medicare.core.navigateToVaccinationTable
import com.example.medicare.core.navigateUp
import com.example.medicare.core.popUpToAndNavigate
import com.example.medicare.data.model.appointment.Appointment
import com.example.medicare.data.model.child.Child
import com.example.medicare.presentation.addchild.AddChildViewModel
import com.example.medicare.presentation.bookappointment.BookAppointmentScreen
import com.example.medicare.presentation.bookappointment.BookAppointmentViewModel
import com.example.medicare.presentation.children.ChildrenViewModel
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsViewModel
import com.example.medicare.presentation.home.HomeViewModel
import com.example.medicare.presentation.login.LoginViewModel
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.notification.NotificationScreen
import com.example.medicare.presentation.notification.NotificationViewModel
import com.example.medicare.presentation.signup.SignUpScreen
import com.example.medicare.presentation.signup.SignUpViewModel
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentViewModel
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentsScreen
import com.example.medicare.presentation.vaccinationtable.VaccinationTableScreen
import com.example.medicare.presentation.vaccinationtable.VaccinationTableViewModel

val screenWhereToShowBottomBar = listOf(
    Destination.Home,
    Destination.VaccinationAppointments,
    Destination.ClinicAppointments,
    Destination.Children,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicareApp(
    viewModel: MedicareAppViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val navController = rememberNavController()


    val showBottomNavBar = when {
        screenWhereToShowBottomBar.contains(uiState.value.currentDestination) -> true
        else -> false
    }
    val isChildrenScreen = when {
        navController.currentBackStackEntry?.destination?.route?.contains(
            "children",
            ignoreCase = true
        ) ?: false -> true

        else -> false
    }

    MainScaffold(
        showBottomBar = showBottomNavBar,
        showFloatingActionButton = uiState.value.showFloatingActionButton,
        onFloatingActionButtonClicked = {
            navController.navigate(Destination.AddChild, viewModel)
        },
        onHomeItemClicked = {
            navController.navigate(Destination.Home, viewModel)
        },
        onVaccinesItemClicked = {
            navController.navigate(Destination.VaccinationAppointments, viewModel)
        },
        onAppointmentItemClicked = {
            navController.navigate(Destination.ClinicAppointments, viewModel)
        },
        onChildrenItemClicked = {
            navController.navigate(Destination.Children, viewModel)
        },
        selectedIndex = uiState.value.selectedIndex
    ) {

        NavHost(navController = navController, startDestination = Destination.SignUp) {
            composable<Destination.SignUp>(
                popEnterTransition = {
                    fadeIn(tween(durationMillis = 300))
                }
            ) {
                val signUpViewModel: SignUpViewModel = hiltViewModel()
                val signUpUiState = signUpViewModel.uiState.collectAsState()
                SignUpScreen(
                    navigateToHomeScreen = {
                        navController.popUpToAndNavigate<Destination.SignUp>(
                            destination = Destination.Home,
                            viewModel = viewModel
                        )
                    },
                    onLoginClickEvent = {
                        navController.navigate(
                            Destination.Login,
                            viewModel = viewModel
                        )
                    },
                    uiState = signUpUiState.value,
                    updateEmailEvent = signUpViewModel::updateEmail,
                    updateFirstNameEvent = signUpViewModel::updateFirstName,
                    updateSecondNameEvent = signUpViewModel::updateSecondName,
                    updatePasswordEvent = signUpViewModel::updatePassword,
                    updatePasswordVisibilityStateEvent = signUpViewModel::updatePasswordVisibilityState,
                    updateGenderEvent = signUpViewModel::updateGender,
                    updateCheckStateEvent = signUpViewModel::updateCheckState,
                    updateErrorDialogVisibilityState = signUpViewModel::updateErrorDialogVisibilityState,
                    onSignUpClickEvent = signUpViewModel::signUp,
                )
            }
            composable<Destination.Login>(
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = 500,
                            delayMillis = 50,
                            easing = EaseOut,
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(
                            durationMillis = 600,
                            delayMillis = 50,
                            easing = EaseOut,
                        )
                    )
                }) {
                val loginViewModel: LoginViewModel = hiltViewModel()
                val loginUiState = loginViewModel.uiState.collectAsState()
                LoginScreen(
                    uiState = loginUiState.value,
                    navigateToHomeScreen = {
                        navController.popUpToAndNavigate<Destination.SignUp>(
                            destination = Destination.Home,
                            viewModel = viewModel
                        )
                    },
                    onLoginClick = loginViewModel::login,
                    onSignUpClick = {
                        navController.navigate(Destination.SignUp, viewModel)
                    },
                    updateCheckStateEvent = loginViewModel::updateCheckState,
                    updatePasswordEvent = loginViewModel::updatePassword,
                    updateEmailEvent = loginViewModel::updateEmail,
                    updatePasswordVisibilityStateEvent = loginViewModel::updatePasswordVisibilityState,
                    updateErrorDialogVisibilityStateEvent = loginViewModel::updateErrorDialogVisibilityState,

                    )
            }

            composable<Destination.Home> {
                val homeViewModel: HomeViewModel = hiltViewModel()
                val homeUiState = homeViewModel.uiState.collectAsState()

                val clinics = homeViewModel.clinics.collectAsState(initial = emptyList()).value
                //val clinics: List<Clinic> = emptyList()
                Log.v("Clinics", clinics.toString())

                val clinicAppointments: List<Appointment> =
                    homeViewModel.appointments.collectAsState(initial = emptyList()).value
                        .filter {
                            it.isUpcoming() && it.vaccineId.isBlank()
                        }

                val vaccinationAppointments: List<Appointment> =
                    homeViewModel.appointments.collectAsState(initial = emptyList()).value
                        .filter {
                            it.isUpcoming() && it.vaccineId.isNotBlank()
                        }

                HomeScreen(
                    uiState = homeUiState.value,
                    clinics = clinics,
                    clinicAppointments = clinicAppointments,
                    vaccinationAppointments = vaccinationAppointments,
                    navigateToBookAppointment = { clinicId ->
                        navController.navigateToBookAppointment(clinicId,viewModel)
                    },
                    navigateToVaccinationAppointment = {
                        navController.navigate(Destination.VaccinationAppointments, viewModel)
                    },
                    navigateToClinicsAppointment = {
                        navController.navigate(Destination.ClinicAppointments, viewModel)
                    },
                    onNotificationClick = {
                        navController.navigate(Destination.Notification, viewModel)
                    },
                    onNavigateUpClick = {
                        navController.navigateUp(viewModel)
                    },
                    updateSelectedClinicEvent = homeViewModel::updateSelectedClinic
                )
            }

            composable<Destination.VaccinationAppointments> {
                val vaccinationAppointmentViewModel: VaccinationAppointmentViewModel =
                    hiltViewModel()
                val vaccinationAppointmentUiState =
                    vaccinationAppointmentViewModel.uiState.collectAsState()

                val vaccinationAppointments: List<Appointment> =
                    vaccinationAppointmentViewModel.vaccinationAppointments.collectAsStateWithLifecycle(
                        initialValue = emptyList()
                    ).value
                        .filter { appointment ->
                            val isUpcoming =
                                if (vaccinationAppointmentUiState.value.selectedFilter == ChooseTabState.First)
                                    appointment.isUpcoming()
                                else
                                    !appointment.isUpcoming()
                            appointment.vaccineId.isNotBlank()&&isUpcoming
                        }

                VaccinationAppointmentsScreen(
                    onNotificationIconButtonClick = {
                        navController.navigate(Destination.Notification, viewModel)
                    },
                    uiState = vaccinationAppointmentUiState.value,
                    vaccinationAppointments = vaccinationAppointments,
                    updateSelectedFilter = vaccinationAppointmentViewModel::updateSelectedFilter
                )
            }

            composable<Destination.ClinicAppointments> {
                val clinicAppointmentsViewModel: ClinicAppointmentsViewModel = hiltViewModel()
                val clinicAppointmentUiState = clinicAppointmentsViewModel.uiState.collectAsState()
                val clinicAppointments: List<Appointment> =
                    clinicAppointmentsViewModel.appointments.collectAsStateWithLifecycle(
                        initialValue = emptyList()
                    ).value
                        .filter { appointment ->
                            val isUpcoming =
                                if (clinicAppointmentUiState.value.selectedFilter == ChooseTabState.First)
                                    appointment.isUpcoming()
                                else
                                    !appointment.isUpcoming()
                            appointment.vaccineId.isBlank()&&isUpcoming
                        }

                ClinicAppointmentsScreen(
                    uiState = clinicAppointmentUiState.value,
                    clinicAppointments = clinicAppointments,
                    onNotificationIconButtonClick = {
                        navController.navigate(Destination.Notification, viewModel)
                    },
                    updateSelectedFilter = clinicAppointmentsViewModel::updateSelectedFilter
                )
            }

            composable<Destination.Children> {
                val childrenViewModel: ChildrenViewModel = hiltViewModel()
                val childrenUiState = childrenViewModel.uiState.collectAsState()
                val children: List<Child> =
                    childrenViewModel.children.collectAsStateWithLifecycle(initialValue = emptyList()).value

                viewModel.updateFABVisibility(children.isNotEmpty()&&isChildrenScreen)

                ChildrenScreen(
                    uiState = childrenUiState.value,
                    children = children,
                    navigateToAddChildScreen = {
                        navController.navigate(Destination.AddChild, viewModel)
                    },
                    onChildCardClick = { childId ->
                        navController.navigateToVaccinationTable(childId, viewModel)
                    },
                    onNotificationButtonClick = {
                        navController.navigate(Destination.Notification, viewModel)
                    },
                    updateNoChildAddedYetState = childrenViewModel::updateNoChildAddedYetState,
                    updateLoadingDialogVisibilityState = childrenViewModel::updateLoadingDialogVisibilityState,
                    updateErrorDialogVisibilityState = childrenViewModel::updateErrorDialogVisibilityState
                )
            }

            composable<Destination.AddChild> {
                val addChildViewModel: AddChildViewModel = hiltViewModel()
                val addChildUiState = addChildViewModel.uiState.collectAsState()
                AddChildScreen(
                    navigateToChildrenScreen = {
                        navController.navigate(Destination.Children, viewModel)
                    },
                    onNavigateUpClick = {
                        navController.navigateUp(viewModel)
                    },
                    onAddChildClick = addChildViewModel::addChild,
                    updateFatherFirstNameEvent = addChildViewModel::updateFatherFirstName,
                    updateFatherSecondNameEvent = addChildViewModel::updateFatherSecondName,
                    updateMotherFirstNameEvent = addChildViewModel::updateMotherFirstName,
                    updateMotherSecondNameEvent = addChildViewModel::updateMotherSecondName,
                    updateChildNumberEvent = addChildViewModel::updateChildNumber,
                    updateChildFirstNameEvent = addChildViewModel::updateChildFirstName,
                    updateChildSecondNameEvent = addChildViewModel::updateChildSecondName,
                    updateGenderEvent = addChildViewModel::updateGender,
                    updateCheckStateEvent = addChildViewModel::updateCheckState,
                    updateErrorDialogVisibilityState = addChildViewModel::updateErrorDialogVisibilityState,
                    uiState = addChildUiState.value,
                    clearTextFieldValue=addChildViewModel::clearBirthDayTextFieldValue,
                    updateBookedDateEvent = addChildViewModel::updateDateOfBirth,
                )
            }

            composable<Destination.VaccinationTable> {
                val vaccinationTableViewModel: VaccinationTableViewModel = hiltViewModel()
                val vaccinationTableUiState = vaccinationTableViewModel.uiState.collectAsState()
                val vaccinationTable = vaccinationTableUiState.value.vaccinationTable

                val args = it.toRoute<Destination.VaccinationTable>()
                VaccinationTableScreen(
                    childId = args.childId,
                    onNavigateUpClick = {
                        navController.navigateUp(viewModel)
                    },
                    uiState = vaccinationTableUiState.value,
                    updateVaccinationTable = vaccinationTableViewModel::updateVaccinationTable,
                    vaccinationTable = vaccinationTable
                )
            }

            composable<Destination.Notification> {
                val notificationViewModel: NotificationViewModel = hiltViewModel()
                val notificationUiState = notificationViewModel.uiState.collectAsState()

                val listOfNotifications=notificationViewModel.notifications.collectAsState(initial = emptyList())

                NotificationScreen(
                    onNavigateUpClick = {
                        navController.navigateUp(viewModel)
                    },
                    uiState = notificationUiState.value,
                    listOfNotifications = listOfNotifications.value
                )
            }

            composable<Destination.BookAppointment> {
                val bookAppointmentViewModel: BookAppointmentViewModel = hiltViewModel()
                val bookAppointmentUiState = bookAppointmentViewModel.uiState.collectAsState()
                val children =
                    bookAppointmentViewModel.listOfChildren.collectAsStateWithLifecycle(initialValue = emptyList()).value
                val user=bookAppointmentViewModel.user.collectAsState(initial = null)

                val userAndChildrenNames: MutableList<String> = mutableListOf(user.value?.firstName?:"Username")
                children.forEach { child ->
                    userAndChildrenNames.add(child.firstName)
                }
                Log.v("Username",user.value?.firstName.toString())

                val args = it.toRoute<Destination.BookAppointment>()
                BookAppointmentScreen(
                    uiState = bookAppointmentUiState.value,
                    onNavigateUpClick = {
                        navController.navigateUp(viewModel)
                    },
                    clinicId = args.clinicId,
                    onBookNowButtonClick = bookAppointmentViewModel::bookAppointment,
                    updateUserAndChildrenNamesEvent = bookAppointmentViewModel::updateUserAndChildrenNames,
                    updateNamesMenuVisibilityStateEvent = bookAppointmentViewModel::updateNamesMenuVisibilityState,
                    updateClinicEvent = bookAppointmentViewModel::updateClinic,
                    updateChosenTimeSocketIndexEvent = bookAppointmentViewModel::updateChosenTimeSocketIndex,
                    updateChosenNameIndexEvent = bookAppointmentViewModel::updateChosenNameIndex,
                    updateBookedDateEvent = bookAppointmentViewModel::updateBookedDate,
                    slideToPreviousPageEvent = bookAppointmentViewModel::slideToPreviousPage,
                    slideToNextPageEvent = bookAppointmentViewModel::slideToNextPage,
                    navigateToHomeScreen = {
                        navController.navigate(Destination.Home, viewModel)
                    },
                    availableVaccines = emptyList(),
                    onAvailableVaccineListItemClick = bookAppointmentViewModel::updateCurrentSelectedIndex,
                    updateErrorDialogVisibilityState = bookAppointmentViewModel::updateErrorDialogVisibilityState,
                    userAndChildrenNames=userAndChildrenNames
                )
            }
        }
    }
}


