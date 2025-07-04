package com.example.dailydiet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            Screen.HOME.route,
        ) {
            HomeScreen(
                onNavigationStatistics = {
                    navController.navigate(Screen.STATISTIC.route)
                },
                onNavigationNewSnack = {
                    navController.navigate(Screen.NEW_SNACK.route)
                },
                onNavigationItem = {
                    navController.navigate("snack_view/${it}")
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
                label = "Nova refeição",
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
        composable(
            route = Screen.EDIT_SNACK.route,
            arguments = listOf(navArgument("snackId") { type = NavType.IntType })
        ) {
            NewSnackScreen(
                label= "Editar refeição",
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
        composable(
            route = Screen.SNACK_VIEW.route,
            arguments = listOf(navArgument("snackId") { type = NavType.IntType })
        ) {
            SnackViewScreen(
                onBack = {
                    navController.navigateUp()
                },
                onNavigationEdit = {
                    navController.navigate("edit_snack/${it}")
                }
            )
        }
    }
}