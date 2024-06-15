package com.example.medicare.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    object Auth : Destination
    @Serializable
    object SignUp : Destination

    @Serializable
    object Login : Destination

    @Serializable
    object Main: Destination

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

    object Notification : Destination
    data class BookAppointment(val clinicId:String) : Destination
}


sealed interface DoctorDestination {
    @Serializable
    object SignUp : DoctorDestination

    @Serializable
    object Login : DoctorDestination

    @Serializable
    object Main: DoctorDestination

    @Serializable
    object Schedule : DoctorDestination

    @Serializable
    object Profile : DoctorDestination

}

