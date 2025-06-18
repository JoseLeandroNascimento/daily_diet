package com.example.dailydiet.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.dailydiet.ui.theme.DailyDietTheme

@Composable
fun DailyDietDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    confirmLabel: String,
    cancelLabel: String,
    title: String,
) {

    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onSurface
        ) {
            Column(
                modifier = Modifier.padding(vertical = 40.dp, horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    DailyDietButton(
                        modifier = Modifier.weight(1f),
                        outline = true,
                        onClick = onCancel,
                        label = cancelLabel
                    )
                    DailyDietButton(
                        modifier = Modifier.weight(1f),
                        onClick = onConfirm,
                        label = confirmLabel
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyDietDialogLightPreview() {
    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        DailyDietDialog(
            title = "Titulo de teste",
            onCancel = {},
            onConfirm = {},
            cancelLabel = "Cancelar",
            confirmLabel = "Confirmo",
            onDismissRequest = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyDietDialogDarkPreview() {
    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        DailyDietDialog(
            title = "Titulo de teste",
            onCancel = {},
            onConfirm = {},
            cancelLabel = "Cancelar",
            confirmLabel = "Confirmo",
            onDismissRequest = {}
        )
    }
}