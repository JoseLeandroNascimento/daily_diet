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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.composable.DailyDietDialog
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray600
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid
import com.example.dailydiet.ui.theme.RedLight
import com.example.dailydiet.ui.theme.RedMid
import com.example.dailydiet.viewModel.SnackViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackViewScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onNavigationEdit:(Int)-> Unit,
    viewModel: SnackViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val dateSnack by remember(uiState.success.data?.timestamp) {
        derivedStateOf {
            uiState.success.data?.timestamp?.let { timestamp ->
                SimpleDateFormat(
                    "dd/MM/yyyy 'às' HH:mm",
                    Locale.getDefault()
                ).format(Date(timestamp))
            } ?: ""
        }
    }

    var showDialogConfirmDelete by remember { mutableStateOf(false) }


    if (showDialogConfirmDelete) {
        DailyDietDialog(
            title = "Deseja realmente excluir o registro da refeição?",
            confirmLabel = "Sim, excluir",
            cancelLabel = "Cancelar",
            onConfirm = {
                viewModel.delete()
                showDialogConfirmDelete = false
                onBack()
            },
            onCancel = {
                showDialogConfirmDelete = false
            },
            onDismissRequest = {
                showDialogConfirmDelete = false
            }
        )
    }

    Scaffold(
        containerColor = if (uiState.success.data?.isInside == true) {
            GreenLight
        } else {
            RedLight
        },
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (uiState.success.data?.isInside == true) {
                        GreenLight
                    } else {
                        RedLight
                    }
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


            uiState.success.data?.let { snack ->

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 40.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = snack.name,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            modifier = Modifier.padding(top = 8.dp),
                            text = snack.description,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Justify,
                            fontSize = 16.sp
                        )

                        Text(
                            modifier = Modifier.padding(top = 24.dp, bottom = 4.dp),
                            text = "Data e hora",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = dateSnack,
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
                                        .background(
                                            color = if (snack.isInside) {
                                                GreenMid
                                            } else {
                                                RedMid
                                            },
                                            shape = CircleShape
                                        )
                                )
                                Text(
                                    text = if (snack.isInside) {
                                        "dentro da dieta"
                                    } else {
                                        "fora da dieta"
                                    },
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DailyDietButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {onNavigationEdit(snack.id)},
                            label = "Editar refeição",
                            icon = Icons.Outlined.BorderColor
                        )
                        DailyDietButton(
                            outline = true,
                            isLoading = uiState.isLoadingDelete,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { showDialogConfirmDelete = !showDialogConfirmDelete },
                            label = "Excluir refeição",
                            icon = Icons.Outlined.Delete
                        )
                    }
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
        SnackViewScreen(onBack = {}, onNavigationEdit = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackViewDarkPreview() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        SnackViewScreen(onBack = {}, onNavigationEdit = {})
    }
}