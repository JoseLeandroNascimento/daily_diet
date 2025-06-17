package com.example.dailydiet.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray200

@Composable
fun DailyDietButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    enabled: Boolean = true,
    icon: ImageVector? = null,
) {

    DailyDietButton(
        modifier = modifier,
        onClick = onClick,
        label = label,
        enabled = enabled,
        icon = icon,
        outline = false
    )

}

@Composable
fun DailyDietButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    outline: Boolean = false
) {

    Button(
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(6.dp),
        border = if (outline) {
            BorderStroke(width = 1.dp, color = Gray200)
        } else {
            null
        },
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (outline) {
                MaterialTheme.colorScheme.background
            } else {
                Gray200
            },
            contentColor = if (outline) {
                Gray200
            } else {
                Color.White
            },
            disabledContainerColor = Gray200.copy(alpha = .8f),
            disabledContentColor = Color.White,
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            icon?.let {
                Icon(imageVector = it, contentDescription = null)
            }
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DailyDietButtonLight() {
    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        DailyDietButton(
            label = "Nova refeição",
            onClick = {},
            icon = Icons.Default.Add,
            outline = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyDietButtonDark() {
    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        DailyDietButton(
            label = "Nova refeição",
            onClick = {},
            icon = Icons.Default.Add
        )
    }
}