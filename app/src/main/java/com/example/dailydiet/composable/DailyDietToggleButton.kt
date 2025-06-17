package com.example.dailydiet.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailydiet.ui.theme.DailyDietTheme
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray400
import com.example.dailydiet.ui.theme.Gray600
import com.example.dailydiet.ui.theme.GreenDark
import com.example.dailydiet.ui.theme.GreenLight
import com.example.dailydiet.ui.theme.GreenMid
import com.example.dailydiet.ui.theme.RedLight
import com.example.dailydiet.ui.theme.RedMid

@Composable
fun DailyDietToggleButton(
    modifier: Modifier = Modifier,
    selected: Boolean,
    unfocusedContainerColor: Color = Gray600,
    focusContainerColor: Color = Gray400,
    focusBorderColor: Color = Gray200,
    onSelected: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {

    Surface(
        modifier = modifier
            .clickable(onClick = onSelected),
        color = if (selected) {
            focusContainerColor
        } else {
            unfocusedContainerColor
        },
        shape = RoundedCornerShape(6.dp),
        border = if (selected) {
            BorderStroke(width = 1.dp, color = focusBorderColor)
        } else {
            null
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyDietToggleButtonLight() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = false
    ) {

        var selected by remember { mutableStateOf(false) }

        DailyDietToggleButton(
            selected = selected,
            onSelected = {
                selected = !selected
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
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyDietToggleButtonDark() {

    DailyDietTheme(
        dynamicColor = false,
        darkTheme = true
    ) {

        var selected by remember { mutableStateOf(false) }

        DailyDietToggleButton(
            selected = selected,
            onSelected = {
                selected = !selected
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