package com.example.dailydiet.composable

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dailydiet.ui.theme.Gray200
import com.example.dailydiet.ui.theme.Gray500

@Composable
fun DailyDietTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    errorText: String? = null,
    readOnly: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label, style = MaterialTheme.typography.titleSmall, color = Gray200)
        },
        shape = RoundedCornerShape(6.dp),
        isError = isError,
        minLines = minLines,
        maxLines = maxLines,
        supportingText = { errorText?.let { Text(text = it) } },
        interactionSource = interactionSource,
        readOnly = readOnly,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Gray500,
            focusedBorderColor = Gray500,
        ),
        modifier = modifier
    )
}