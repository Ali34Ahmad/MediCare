package com.example.medicare.presentation.navigation

import androidx.navigation.NavController

val screensNames = mapOf(
    Pair(Destination.Home, "home"),
    Pair(Destination.VaccinationAppointments, "VaccinationAppointments"),
    Pair(Destination.ClinicAppointments, "clinicAppointments"),
    Pair(Destination.Children, "children"),
)
fun getCurrentDestinationUsingName(navController: NavController): Destination {
    val route = navController.currentBackStackEntry?.destination?.route ?: ""

    return when {
        checkIfRouteContainsScreenName(route, Destination.Home) -> Destination.Home
        checkIfRouteContainsScreenName(
            route,
            Destination.VaccinationAppointments
        ) -> Destination.VaccinationAppointments

        checkIfRouteContainsScreenName(
            route,
            Destination.ClinicAppointments
        ) -> Destination.ClinicAppointments

        checkIfRouteContainsScreenName(route, Destination.Children) -> Destination.Children
        else -> Destination.Home

    }
}

fun checkIfRouteContainsScreenName(route: String, destination: Destination): Boolean {
    return route.contains(screensNames.get(destination) ?: "", ignoreCase = true)
}
