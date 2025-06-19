package com.example.dailydiet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dailydiet.model.FieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class FormState(
    val name: FieldState<String> = FieldState(value = ""),
    val description: FieldState<String> = FieldState(value = ""),
    val date: FieldState<String> = FieldState(value = ""),
    val time: FieldState<String> = FieldState(value = ""),
    val isInside: FieldState<Boolean?> = FieldState(value = null),
)

@HiltViewModel
class CreateSnackViewModel @Inject constructor() : ViewModel() {

    var formState by mutableStateOf(FormState())
        private set

    fun nameUpdate(newName: String) {
        formState = formState.copy(
            name = FieldState(value = newName)
        )
    }

    fun descriptionUpdate(newDescription: String) {
        formState = formState.copy(
            description = FieldState(value = newDescription)
        )
    }

    fun dateUpdate(newDate: String) {
        val dateContainsDigits = newDate.filter {
            it in "0123456789"
        }
        formState = formState.copy(
            date = FieldState(value = dateContainsDigits)
        )
    }

    fun timeUpdate(newTime: String) {

        val timeContainsDigits = newTime.filter {
            it in "0123456789"
        }
        formState = formState.copy(
            time = FieldState(value = timeContainsDigits)
        )
    }

    fun isInsideUpdate(newIsInside: Boolean) {
        formState = formState.copy(
            isInside = FieldState(value = newIsInside)
        )
    }

}