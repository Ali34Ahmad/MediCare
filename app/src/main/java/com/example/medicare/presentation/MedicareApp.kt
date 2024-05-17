package com.example.medicare.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.medicare.presentation.home.HomeScreen
import com.example.medicare.presentation.login.LoginScreen
import com.example.medicare.presentation.signup.SignUpScreen
import kotlinx.serialization.Serializable

@Composable
fun MedicareApp(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = SignUp){
        composable<SignUp> {
            SignUpScreen(
                onSignUpClick = {
                navController.navigate(Home(
                    "ALi"
                )){
                    popUpTo(SignUp){
                        inclusive=true
                    }
                }
            })
        }
        composable<Login> {
            LoginScreen(
                onLoginClick = {
                navController.navigate(Home(
                    "ALi"
                )){
                    popUpTo(Login){
                        inclusive=true
                    }
                }
            }
            )
        }
        composable<Home> {
            val args=it.toRoute<Home>()
            HomeScreen(
                userId=args.userId
                )
        }


    }
}
@Serializable
object SignUp

@Serializable
object Login

@Serializable
data class Home(
    val userId:String
)

