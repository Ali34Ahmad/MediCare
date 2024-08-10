package com.example.doctor.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doctor.core.composables.MainScaffold
import com.example.doctor.core.composables.generateDaySocketsList
import com.example.doctor.core.navigate
import com.example.doctor.core.popUpToAndNavigate
import com.example.doctor.data.model.vaccine.Vaccine
import com.example.doctor.presentation.login.LoginScreen
import com.example.doctor.presentation.login.LoginViewModel
import com.example.doctor.presentation.navigation.Destination
import com.example.doctor.presentation.profile.ProfileScreen
import com.example.doctor.presentation.profile.ProfileViewModel
import com.example.doctor.presentation.schedule.ScheduleScreen
import com.example.doctor.presentation.schedule.ScheduleViewModel
import com.example.doctor.presentation.signup.SignUpScreen
import com.example.doctor.presentation.signup.SignUpViewModel

val screenWhereToShowBottomBar = listOf(
    Destination.Schedule,
    Destination.Profile,
)


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

    MainScaffold(
        showBottomBar = showBottomNavBar,
        onScheduleItemClicked = {
            navController.navigate(Destination.Schedule, viewModel)
        },
        onProfileItemClicked = {
            navController.navigate(Destination.Profile, viewModel)
        },
        selectedIndex = uiState.value.selectedIndex
    ) {

        NavHost(navController = navController, startDestination = Destination.SignUp) {
            composable<Destination.SignUp> {
                val signUpViewModel: SignUpViewModel = hiltViewModel()
                val signUpUiState = signUpViewModel.uiState.collectAsState()
                SignUpScreen(
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
                    updateClinicNameEvent = signUpViewModel::updateClinicName,
                    onDoctorImageSaveButtonClick = signUpViewModel::uploadDoctorImage,
                    updateDoctorImageUri = signUpViewModel::updateDoctorImageUri,
                    updateDoctorImageBitmap = signUpViewModel::updateDoctorImageBitmap,
                    onClinicImageSaveButtonClick = signUpViewModel::uploadClinicImage,
                    updateClinicImageUri = signUpViewModel::updateClinicImageUri,
                    updateClinicImageBitmap = signUpViewModel::updateClinicImageBitmap,
                    updateSpecialityEvent = signUpViewModel::updateSpeciality,
                    onDoctorImagePickerDialogDismissRequest = {
                        signUpViewModel.updateDoctorImagePickerDialogVisibilityState(false)
                        signUpViewModel.updateClinicImagePickerDialogVisibilityState(true)
                    },
                    onClinicImagePickerDialogDismissRequest = {
                        signUpViewModel.updateClinicImagePickerDialogVisibilityState(false)
                    },
                    navigateToScheduleScreen = {
                        navController.navigate(Destination.Schedule, viewModel)
                    },

                    )
            }
            composable<Destination.Login> {
                val loginViewModel: LoginViewModel = hiltViewModel()
                val loginUiState = loginViewModel.uiState.collectAsState()
                LoginScreen(
                    uiState = loginUiState.value,
                    navigateToScheduleScreen = {
                        navController.popUpToAndNavigate<Destination.Login>(
                            destination = Destination.Schedule,
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

            composable<Destination.Schedule> {
                val scheduleViewModel: ScheduleViewModel = hiltViewModel()
                val scheduleUiState = scheduleViewModel.uiState.collectAsState()
                val appointments=scheduleViewModel.appointments.collectAsState(initial = emptyList())

                val appointmentsVisitNumbers= scheduleViewModel.appointmentsToNumberOfVisits

                Log.v("Visit",appointmentsVisitNumbers.toString())


                appointments.value.forEach {
                    scheduleViewModel.getNumberOfVisits(it.userId,scheduleUiState.value.clinic.id)
                }

                ScheduleScreen(
                    uiState = scheduleUiState.value,
                    clinicsAppointments = appointments.value,
                    appointmentsVisitNumbers=appointmentsVisitNumbers,
                    updateBookedDateEvent = scheduleViewModel::updateBookedDate,
                )
            }

            composable<Destination.Profile> {
                val profileViewModel: ProfileViewModel = hiltViewModel()
                val profileUiState = profileViewModel.uiState.collectAsState()
                val appointments=profileViewModel.appointments.collectAsState(initial = emptyList())
                val appointmentsVisitNumbers= profileViewModel.appointmentsToNumberOfVisits
                val defaultVaccineTable= profileViewModel.defaultVaccineTable.collectAsState(initial = emptyList())
                val defaultVaccineTableVaccines= mutableListOf<Vaccine>()

                defaultVaccineTable.value.forEach {
                    defaultVaccineTableVaccines.add(it.vaccine)
                }

                appointments.value.forEach {
                    profileViewModel.getNumberOfVisits(it.userId,profileUiState.value.clinic.id)
                }


                ProfileScreen(
                    uiState =profileUiState.value,
                    clinicsAppointments =appointments.value,
                    updateBookedDateEvent = profileViewModel::updateBookedDate,
                    appointmentsVisitNumbers=appointmentsVisitNumbers,
                    pushNotificationEvent = profileViewModel::pushNotification,
                    onAvailableVaccineListItemClick = profileViewModel::updateCurrentSelectedIndex,
                    defaultVaccineTableVaccines = defaultVaccineTableVaccines,
                    updateErrorDialogVisibilityStateEvent = profileViewModel::updateErrorDialogVisibilityState,
                    updateSuccessDialogVisibilityStateEvent=profileViewModel::updateSuccessDialogVisibilityState
                )
            }
        }
    }
}


