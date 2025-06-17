package com.example.dailydiet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dailydiet.screen.FeedbackNegativeScreen
import com.example.dailydiet.screen.FeedbackPositiveScreen
import com.example.dailydiet.screen.HomeScreen
import com.example.dailydiet.screen.NewSnackScreen
import com.example.dailydiet.screen.SnackViewScreen
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
                },
                onNavigationItem = {
                    navController.navigate(Screen.SNACK_VIEW.route)
                }
            )
        }
        composable(Screen.STATISTIC.route) {
            StatisticScreen(onBack = {
                navController.navigateUp()
            })
        }
        composable(Screen.NEW_SNACK.route) {
            NewSnackScreen(
                onBack = {
                    navController.navigateUp()
                },
                onNavigateCreateSuccess = {
                    if (it) {
                        navController.navigate(Screen.FEEDBACK_POSITIVE.route)
                    } else {
                        navController.navigate(Screen.FEEDBACK_NEGATIVE.route)
                    }
                }
            )
        }
        composable(Screen.FEEDBACK_POSITIVE.route) {
            FeedbackPositiveScreen {
                navController.navigate(Screen.HOME.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.FEEDBACK_NEGATIVE.route) {
            FeedbackNegativeScreen {
                navController.navigate(Screen.HOME.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
            }
        }
        composable(Screen.SNACK_VIEW.route) {
            SnackViewScreen(onBack = {
                navController.navigateUp()
            })
        }
    }
}