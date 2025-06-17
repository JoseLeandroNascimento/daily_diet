package com.example.dailydiet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailydiet.screen.HomeScreen
import com.example.dailydiet.screen.NewSnackScreen
import com.example.dailydiet.screen.StatisticScreen

@Composable
fun Route(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        composable(Screen.HOME.route) {
            HomeScreen(
                onNavigationStatistics = {
                    navController.navigate(Screen.STATISTIC.route)
                },
                onNavigationNewSnack = {
                    navController.navigate(Screen.NEW_SNACK.route)
                }
            )
        }
        composable(Screen.STATISTIC.route) {
            StatisticScreen(onBack = {
                navController.navigateUp()
            })
        }
        composable(Screen.NEW_SNACK.route) {
            NewSnackScreen(onBack = {
                navController.navigateUp()
            })
        }
    }
}