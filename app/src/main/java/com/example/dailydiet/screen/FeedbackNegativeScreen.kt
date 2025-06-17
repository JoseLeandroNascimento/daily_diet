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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailydiet.R
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.RedDark

@Composable
fun FeedbackNegativeScreen(
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
                    text = "Que pena!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = RedDark
                )
                Text(
                    text = "Você saiu da dieta dessa vez, mas continue se esforçando e não desista!",
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 40.dp, top = 10.dp),
                    textAlign = TextAlign.Center
                )
                Image(
                    modifier = Modifier.size(224.dp, 288.dp),
                    painter = painterResource(id = R.drawable.diet_failed),
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
private fun FeedbackNegativeLightScreen() {

    DailyDietTheme(dynamicColor = false, darkTheme = false) {
        FeedbackNegativeScreen(onNavigationHome = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun FeedbackNegativeDarkScreen() {

    DailyDietTheme(dynamicColor = false, darkTheme = true) {
        FeedbackNegativeScreen(onNavigationHome = {})
    }
}