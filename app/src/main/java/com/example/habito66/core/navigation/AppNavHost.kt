package com.example.habito66.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.habito66.presentation.habits.AddEditHabitScreen
import com.example.habito66.presentation.home.HomeScreen
import com.example.habito66.presentation.login.LoginScreen
import com.example.habito66.presentation.settings.SettingsScreen
import com.example.habito66.presentation.stats.StatsScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBottomBar = currentRoute in listOf(
        Routes.Home.route,
        Routes.Stats.route,
        Routes.Settings.route
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {

            // LOGIN
            composable(Routes.Login.route) {
                LoginScreen(
                    onLoginClick = {
                        navController.navigate(Routes.Home.route) {
                            popUpTo(Routes.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            // HOME
            composable(Routes.Home.route) {
                HomeScreen(
                    onNavigateToNewHabit = { id ->
                        navController.navigate(
                            Routes.CreateHabits.createRoute(id)
                        )
                    }
                )
            }
            //create add habit screen
            composable(
                route = Routes.CreateHabits.route,
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->

                val id = backStackEntry.arguments?.getString("id") ?: ""

                AddEditHabitScreen(
                    id = id,
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            // STATS
            composable(Routes.Stats.route) {
                StatsScreen()
            }

            // SETTINGS
            composable(Routes.Settings.route) {
                SettingsScreen()
            }

        }
    }
}
