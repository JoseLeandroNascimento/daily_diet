package com.example.dailydiet.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailydiet.R
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.data.Snack
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray100
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray400
import com.example.dailydiet.ui.theme.Gray500
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid
import com.example.dailydiet.ui.theme.RedDark
import com.example.dailydiet.ui.theme.RedLight
import com.example.dailydiet.ui.theme.RedMid
import com.example.dailydiet.util.percent
import com.example.dailydiet.viewModel.HomeSnacksViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigationStatistics: () -> Unit,
    onNavigationNewSnack: () -> Unit,
    onNavigationItem: (Int) -> Unit,
    viewModel: HomeSnacksViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState().value
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val groupedSnacks = uiState.success.data.collectAsState(initial = emptyList()).value

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.padding(horizontal = 24.dp),
                navigationIcon = {
                    Image(
                        modifier = Modifier.size(82.dp, 37.dp),
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )
                },
                title = {},
                actions = {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = CircleShape),
                        painter = painterResource(id = R.drawable.image_perfil),
                        contentDescription = null
                    )
                }
            )
        }
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {

            if (uiState.isLoading) {

                CircularProgressIndicator(
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .size(20.dp)
                )

            } else {

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    contentPadding = PaddingValues(vertical = 32.dp),
                ) {
                    item {
                        CardPercent(
                            modifier = Modifier.padding(top = 33.dp),
                            percentagePositive = uiState.snackPositiveStatic,
                            percentageNegative = uiState.snackNegativeStatic,
                            onNavigationStatistics = onNavigationStatistics
                        )
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Refeições",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            DailyDietButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = onNavigationNewSnack,
                                label = "Nova refeição",
                                icon = Icons.Default.Add
                            )
                        }
                    }

                    items(items = groupedSnacks) { groupSnack ->
                        Column {
                            Text(
                                text = groupSnack.date,
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                for (snack in groupSnack.snacksDay) {

                                    RegisterItem(
                                        snack = snack,
                                        onSelectItem = { onNavigationItem(it) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun RegisterItem(
    modifier: Modifier = Modifier,
    onSelectItem: (Int) -> Unit,
    snack: Snack
) {

    val hora = remember(snack.timestamp) {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(snack.timestamp))
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(width = 1.dp, color = Gray500),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = { onSelectItem(snack.id) })
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 14.dp,
                    horizontal = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = hora,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                VerticalDivider(
                    modifier = Modifier.height(14.dp),
                    color = Gray400,
                    thickness = 1.dp
                )
                Text(
                    text = snack.name,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        color = if (snack.isInside) {
                            GreenMid
                        } else {
                            RedMid
                        }, shape = CircleShape
                    )
            )
        }

    }
}

@Composable
fun CardPercent(
    percentagePositive: Double,
    percentageNegative: Double,
    modifier: Modifier = Modifier,
    onNavigationStatistics: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (percentagePositive >= percentageNegative) {
                    GreenLight
                } else {
                    RedLight
                }, shape = RoundedCornerShape(8.dp)
            )
            .padding(4.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = percentagePositive.percent(),
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

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onNavigationStatistics
        ) {
            Icon(
                tint = if (percentagePositive >= percentageNegative) {
                    GreenDark
                } else {
                    RedDark
                },
                imageVector = Icons.Default.ArrowOutward,
                contentDescription = null
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeLightScreenPreview() {

    DailyDietTheme(dynamicColor = false, darkTheme = false) {
        HomeScreen(onNavigationStatistics = {}, onNavigationNewSnack = {}, onNavigationItem = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeDarktScreenPreview() {
    DailyDietTheme(dynamicColor = false, darkTheme = true) {
        HomeScreen(onNavigationStatistics = {}, onNavigationNewSnack = {}, onNavigationItem = {})
    }
}