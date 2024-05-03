package com.example.remind.app

sealed class Screens(val route: String) {
    object Register: Screens("register") {
        object Login: Screens("login")
        object SelectType: Screens("selecttype")
    }

    object Doctor: Screens("doctor") {
        object PatienceRegister: Screens("PatienceRegister")
    }
    object Center: Screens("center") {
        object PatienceRegister: Screens("PatienceRegister")
    }
    object Patience: Screens("patience") {
        object Fitst: Screens("FirstScreen")
        object Second: Screens("SecondScreen")
        object Third: Screens("ThirdScreen")
        object Fourth: Screens("FourthScreen")
    }
}
