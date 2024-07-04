package com.example.medicare.presentation.navigation

import com.example.medicare.data.model.clinic.Clinic
import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    object SignUp : Destination

    @Serializable
    object Login : Destination

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

    @Serializable
    object Notification : Destination

    @Serializable
    data class BookAppointment(val clinicId:String) : Destination

    @Serializable
    data class VaccinationTable(val childId:String)
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

