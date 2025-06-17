package com.example.dailydiet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailydiet.R
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.GreenDark

@Composable
fun FeedbackPositiveScreen(
    modifier: Modifier = Modifier,
    onNavigationHome: () -> Unit
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Scaffold { innerPedding ->

            Column(
                modifier = Modifier
                    .padding(innerPedding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = "Continue assim!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = GreenDark
                )
                Text(
                    text = "Você continua dentro da dieta. Muito bem!",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 40.dp, top = 10.dp)
                )
                Image(
                    modifier = Modifier.size(224.dp, 288.dp),
                    painter = painterResource(id = R.drawable.diet_success),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(top = 32.dp))
                DailyDietButton(
                    onClick = onNavigationHome,
                    label = "Ir para a página inicial",
                )

            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun FeedbackPositiveLightScreen() {

    DailyDietTheme(dynamicColor = false, darkTheme = false) {
        FeedbackPositiveScreen(onNavigationHome = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun FeedbackPositiveDarkScreen() {

    DailyDietTheme(dynamicColor = false, darkTheme = true) {
        FeedbackPositiveScreen(onNavigationHome = {})
    }
}