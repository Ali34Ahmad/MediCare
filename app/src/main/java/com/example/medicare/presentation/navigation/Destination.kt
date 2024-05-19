package com.example.medicare.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    object SignUp : Destination

    @Serializable
    object Login : Destination

    @Serializable
    data class Main(
        val userId: String?
    ) : Destination

    @Serializable
    object Home : Destination

    @Serializable
    object VaccinationAppointments : Destination

    @Serializable
    object ClinicAppointments : Destination

    @Serializable
    object Children : Destination

    @Serializable
    object AddChild : Destination


}
