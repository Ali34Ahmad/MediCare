package com.example.medicare.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.medicare.presentation.login.LoginScreen
import com.example.medicare.presentation.main.MainScreen
import com.example.medicare.presentation.navigation.Destination
import com.example.medicare.presentation.signup.SignUpScreen

@Composable
fun MedicareApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.SignUp) {

        composable<Destination.Auth> {
            navigation<Destination.Auth>(
                startDestination = Destination.SignUp,
            ){

            }
        }

        composable<Destination.SignUp> {
            SignUpScreen(
                onSignUpClick = {
                    navController.navigate(
                        Destination.Main
                    ) {
                        popUpTo(Destination.SignUp) {
                            inclusive = true
                        }
                    }
                },
                onLoginClick = {
                    navController.navigate(
                        Destination.Login
                    )
                }
            )
        }
        composable<Destination.Login> {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(
                        Destination.Main
                    ) {
                        popUpTo(Destination.Login) {
                            inclusive = true
                        }
                    }
                }
            ) {
                navController.navigate(Destination.SignUp)
            }
        }
        composable<Destination.Main> {
            MainScreen(
            )
        }
    }
}

@Composable
inline fun <reified T:ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController):T{
    val navGraphRoute=destination.parent?.route?:return viewModel()
    val parentEntry= remember(key1 = this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}