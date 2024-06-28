package com.example.doctor.presentation.navigation

import kotlinx.serialization.Serializable


sealed interface Destination {
    @Serializable
    object SignUp : Destination

    @Serializable
    object Login : Destination

    @Serializable
    object Schedule : Destination

    @Serializable
    object Profile : Destination

}

