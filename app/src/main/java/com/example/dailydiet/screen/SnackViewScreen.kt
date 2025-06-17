package com.example.dailydiet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.BorderColor
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray600
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackViewScreen(
    modifier: Modifier = Modifier,
    onBack:()-> Unit
) {

    Scaffold(
        containerColor = GreenLight,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenLight
                ),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    Text(
                        text = "Refeição",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 18.sp
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            shape = RoundedCornerShape(
                topStart = 20.dp, topEnd = 20.dp
            ),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.BottomCenter
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp)
                ) {
                    Text(
                        text = "X-tudo",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "Sanduíche de pão integral com atum e salada de alface e tomate",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 16.sp
                    )

                    Text(
                        modifier = Modifier.padding(top = 24.dp),
                        text = "Data e hora",
                        style = MaterialTheme.typography.titleSmall,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = "12/08/2022 às 16:00",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    Box(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .background(color = Gray600, shape = CircleShape)
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .background(color = GreenMid, shape = CircleShape)
                            )
                            Text(
                                text = "dentro da dieta",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DailyDietButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        label = "Editar refeição",
                        icon = Icons.Outlined.BorderColor
                    )
                    DailyDietButton(
                        outline = true,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        label = "Excluir refeição",
                        icon = Icons.Outlined.Delete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackViewLightPreview() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        SnackViewScreen(onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackViewDarkPreview() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        SnackViewScreen(onBack = {})
    }
}