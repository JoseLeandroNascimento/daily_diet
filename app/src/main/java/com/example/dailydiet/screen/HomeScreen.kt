package com.example.dailydiet.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailydiet.R
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray100
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray400
import com.example.dailydiet.ui.theme.Gray500
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigationStatistics: () -> Unit,
    onNavigationNewSnack: () -> Unit,
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background
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
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 24.dp)
            ) {
                CardPercent(
                    modifier = Modifier.padding(top = 33.dp),
                    onNavigationStatistics = onNavigationStatistics
                )
                Column(
                    modifier = Modifier.padding(top = 40.dp),
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

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    contentPadding = PaddingValues(vertical = 32.dp)
                ) {
                    items(count = 4) {
                        Column {
                            Text(
                                text = "12.08.22",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                RegisterItem()
                                RegisterItem()
                                RegisterItem()
                                RegisterItem()
                                RegisterItem()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RegisterItem(modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(width = 1.dp, color = Gray500),
        modifier = modifier.fillMaxWidth()
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
                    text = "20:00",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                VerticalDivider(
                    modifier = Modifier.height(14.dp),
                    color = Gray400,
                    thickness = 1.dp
                )
                Text(
                    text = "X-tudo",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(color = GreenMid, shape = CircleShape)
            )
        }

    }
}

@Composable
fun CardPercent(
    modifier: Modifier = Modifier,
    onNavigationStatistics: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = GreenLight, shape = RoundedCornerShape(8.dp))
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
                text = "90,86%",
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
                tint = GreenDark,
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
        HomeScreen(onNavigationStatistics = {}, onNavigationNewSnack = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeDarktScreenPreview() {
    DailyDietTheme(dynamicColor = false, darkTheme = true) {
        HomeScreen(onNavigationStatistics = {}, onNavigationNewSnack = {})
    }
}