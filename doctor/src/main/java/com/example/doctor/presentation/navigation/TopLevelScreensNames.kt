package com.example.doctor.presentation.navigation

import androidx.navigation.NavController

val screensNames = mapOf(
    Pair(Destination.Schedule, "Schedule"),
    Pair(Destination.Profile, "Profile"),
)
fun getCurrentDestinationUsingName(navController: NavController): Destination {
    val route = navController.currentBackStackEntry?.destination?.route ?: ""

    return when {
        checkIfRouteContainsScreenName(route, Destination.Schedule) -> Destination.Schedule
        checkIfRouteContainsScreenName(
            route,
            Destination.Profile
        ) -> Destination.Profile

        else -> Destination.Schedule

    }
}

fun checkIfRouteContainsScreenName(route: String, destination: Destination): Boolean {
    return route.contains(screensNames.get(destination) ?: "", ignoreCase = true)
}
