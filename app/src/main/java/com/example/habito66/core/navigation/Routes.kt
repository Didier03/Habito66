package com.example.habito66.core.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Home : Routes("home")
    object Stats: Routes("stats")
    object Settings: Routes("settings")
    object CreateHabits: Routes("createHabits/{id}"){
        fun createRoute(id: String) = "createHabits/$id"
    }
}