package com.example.dailydiet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailydiet.composable.DailyDietButton
import com.example.dailydiet.composable.DailyDietToggleButton
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray500
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid
import com.example.dailydiet.ui.theme.RedLight
import com.example.dailydiet.ui.theme.RedMid
import com.example.dailydiet.viewModel.CreateSnackViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewSnackScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    viewModel: CreateSnackViewModel = hiltViewModel(),
    onNavigateCreateSuccess: (Boolean) -> Unit
) {

    val dateInteraction = remember { MutableInteractionSource() }
    val datePressed by dateInteraction.collectIsPressedAsState()
    val stateDatePicker = rememberDatePickerState()
    var showDate by remember { mutableStateOf(false) }

    LaunchedEffect(datePressed) {

        if (datePressed) {
            showDate = !showDate
        }

    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Scaffold(
            containerColor = Gray500,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Gray500
                    ),
                    title = {
                        Text(
                            text = "Nova refeição",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .padding(start = 24.dp, end = 24.dp, top = 40.dp),
                contentAlignment = Alignment.BottomCenter
            ) {


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.formState.name.value,
                        shape = RoundedCornerShape(6.dp),
                        onValueChange = {
                            viewModel.nameUpdate(it)
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Gray500,
                            focusedBorderColor = Gray500,
                        ),
                        maxLines = 1,
                        label = {
                            Text(
                                text = "Nome",
                                style = MaterialTheme.typography.titleSmall,
                                color = Gray200
                            )
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.formState.description.value,
                        shape = RoundedCornerShape(6.dp),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        ),
                        onValueChange = {
                            viewModel.descriptionUpdate(it)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Gray500,
                            focusedBorderColor = Gray500,
                        ),
                        minLines = 4,
                        maxLines = 4,
                        label = {
                            Text(
                                text = "Descrição",
                                style = MaterialTheme.typography.titleSmall,
                                color = Gray200
                            )
                        }
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { showDate = !showDate },
                            value = viewModel.formState.date.value,
                            shape = RoundedCornerShape(8.dp),
                            onValueChange = {
                                viewModel.dateUpdate(it)
                            },
                            interactionSource = dateInteraction,
                            readOnly = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Gray500,
                                focusedBorderColor = Gray500,
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            ),
                            maxLines = 1,
                            label = {
                                Text(
                                    text = "Data",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Gray200
                                )
                            }
                        )

                        if (showDate) {
                            DatePickerDialog(

                                onDismissRequest = {
                                    showDate = false
                                },
                                confirmButton = {}
                            ) {

                                DatePicker(
                                    state = stateDatePicker,
                                    colors = DatePickerDefaults.colors(
                                        containerColor = MaterialTheme.colorScheme.background
                                    ),

                                    )
                            }
                        }
                        OutlinedTextField(
                            modifier = Modifier.weight(1f),
                            value = viewModel.formState.time.value,
                            shape = RoundedCornerShape(6.dp),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next,
                                keyboardType = KeyboardType.Number
                            ),
                            onValueChange = {
                                viewModel.timeUpdate(it)
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedBorderColor = Gray500,
                                focusedBorderColor = Gray500,
                            ),
                            maxLines = 1,
                            label = {
                                Text(
                                    text = "Hora",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Gray200
                                )
                            }
                        )
                    }

                    Column(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Está dentro da dieta?",
                            style = MaterialTheme.typography.titleSmall,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            DailyDietToggleButton(
                                modifier = Modifier.weight(1f),
                                selected = viewModel.formState.isInside.value == true,
                                onSelected = {
                                    viewModel.isInsideUpdate(true)
                                },
                                focusBorderColor = GreenDark,
                                focusContainerColor = GreenLight
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .background(color = GreenMid, shape = CircleShape)
                                )
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                Text(
                                    text = "Sim",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            DailyDietToggleButton(
                                modifier = Modifier.weight(1f),
                                selected = viewModel.formState.isInside.value == false,
                                onSelected = {
                                    viewModel.isInsideUpdate(false)
                                },
                                focusBorderColor = RedMid,
                                focusContainerColor = RedLight
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .background(color = RedMid, shape = CircleShape)
                                )
                                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                                Text(
                                    text = "Não",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                    }

                }
                DailyDietButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 40.dp),
                    onClick = {
                        viewModel.save()
                    },
                    label = "Cadastrar refeição"
                )
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun NewSnackLightScreen() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        NewSnackScreen(onBack = {}, onNavigateCreateSuccess = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun NewSnackDarkScreen() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        NewSnackScreen(onBack = {}, onNavigateCreateSuccess = {})
    }
}