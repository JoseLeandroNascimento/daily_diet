package com.example.dailydiet.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray100
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray600
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.RedDark
import com.example.dailydiet.ui.theme.RedLight
import com.example.dailydiet.util.percent
import com.example.dailydiet.viewModel.StatisticViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: StatisticViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Scaffold(
            containerColor = if (uiState.snackPositiveStatic >= uiState.snackNegativeStatic) {
                GreenLight
            } else {
                RedLight
            },
            topBar = {
                LargeTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (uiState.snackPositiveStatic >= uiState.snackNegativeStatic) {
                            GreenLight
                        } else {
                            RedLight
                        }
                    ),
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = if (uiState.snackPositiveStatic >= uiState.snackNegativeStatic) {
                                    GreenDark
                                } else {
                                    RedDark
                                }
                            )
                        }
                    },
                    title = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                text = uiState.snackPositiveStatic.percent(),
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Gray100
                            )
                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                style = MaterialTheme.typography.bodySmall,
                                text = "das refeições dentro da dieta",
                                color = Gray200
                            )
                        }
                    }
                )
            }
        ) {
            Surface(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)

            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                        .verticalScroll(state = rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(top = 23.dp),
                        text = "Estatísticas gerais",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleSmall,
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Gray600
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "22",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "melhor sequência de pratos dentro da dieta",
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Gray600
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = uiState.countAll.toString(),
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "refeições registradas",
                                maxLines = 3,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
                    ) {

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = GreenLight
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                Text(
                                    text = uiState.countAllSnackPositive.toString(),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = "refeições dentro da dieta",
                                    color = MaterialTheme.colorScheme.onSurface,
                                    textAlign = TextAlign.Center,
                                    maxLines = 3,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }

                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = RedLight
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = uiState.countAllSnackNegative.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 20.sp
                                )
                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = "refeições fora da dieta",
                                    textAlign = TextAlign.Center,
                                    maxLines = 3,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    overflow = TextOverflow.Ellipsis,
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun StatisticLightPreview() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        StatisticScreen(onBack = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun StatisticDarkPreview() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        StatisticScreen(onBack = {})
    }
}