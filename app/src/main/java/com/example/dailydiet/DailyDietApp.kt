package com.example.dailydiet

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun DailyDietApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    Route(navController = navController)
}