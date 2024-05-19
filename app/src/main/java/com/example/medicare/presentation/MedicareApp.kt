package com.example.medicare.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medicare.presentation.children.ChildrenScreen
import com.example.medicare.presentation.clinicappointments.ClinicAppointmentsScreen
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.login.LoginScreen
import com.example.medicare.presentation.main.MainScreen
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.signup.SignUpScreen
import com.example.medicare.presentation.vaccinationappointments.VaccinationAppointmentsScreen
import kotlinx.serialization.Serializable

@Composable
fun MedicareApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.SignUp) {
        composable<Destination.SignUp> {
            SignUpScreen(
                onSignUpClick = {
                    navController.navigate(
                        Destination.Main(
                            "ALi"
                        )
                    ) {
                        popUpTo(Destination.SignUp) {
                            inclusive = true
                        }
                    }
                })
        }
        composable<Destination.Login> {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(
                        Destination.Main(
                            "ALi"
                        )
                    ) {
                        popUpTo(Destination.Login) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<Destination.Main> {
            val args = it.toRoute<Destination.Main>()
            MainScreen(
                userId = args.userId
            )
        }
    }
}

