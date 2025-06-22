package com.example.dailydiet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydiet.data.Snack
import com.example.dailydiet.data.SnackRepository
import com.example.dailydiet.model.FieldState
import com.example.dailydiet.model.Response
import com.example.dailydiet.validator.DateValidator
import com.example.dailydiet.validator.DescriptionValidator
import com.example.dailydiet.validator.NameValidator
import com.example.dailydiet.validator.TimeValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.util.Calendar


data class FormState(
    val name: FieldState<String> = FieldState(value = ""),
    val description: FieldState<String> = FieldState(value = ""),
    val date: FieldState<String> = FieldState(value = ""),
    val time: FieldState<String> = FieldState(value = ""),
    val isInside: FieldState<Boolean?> = FieldState(value = null, valid = false),
    val formIsValid: Boolean = false,
    val isLoading: Boolean = false,
    val onNavigateCreateSuccess: Boolean? = null,
)

@HiltViewModel
class CreateSnackViewModel @Inject constructor(
    private val repo: SnackRepository
) : ViewModel() {

    var formState by mutableStateOf(FormState())
        private set

    fun nameUpdate(newName: String) {

        var error: String? = NameValidator.validator(newName)

        formState = formState.copy(
            name = FieldState(value = newName, error = error, valid = error == null)
        )

        updateStateForm()
    }

    fun descriptionUpdate(newDescription: String) {

        var error: String? = DescriptionValidator.validator(newDescription)

        formState = formState.copy(
            description = FieldState(value = newDescription, error = error, valid = error == null)
        )

        updateStateForm()

    }

    fun dateUpdate(newDate: String) {
        val dateContainsDigits = newDate.filter {
            it in "0123456789/"
        }
        var error: String? = DateValidator.validator(dateContainsDigits)

        formState = formState.copy(
            date = FieldState(value = dateContainsDigits, error = error, valid = error == null)
        )
        updateStateForm()

    }

    fun timeUpdate(newTime: String) {

        val timeContainsDigits = newTime.filter {
            it in "0123456789:"
        }

        val error = TimeValidator.validator(newTime)

        formState = formState.copy(
            time = FieldState(value = timeContainsDigits, error = error, valid = error == null)
        )

        updateStateForm()

    }

    fun isInsideUpdate(newIsInside: Boolean?) {

        val error: String? = newIsInside?.let {
            "Informe se está dentro da dieta"
        }

        formState = formState.copy(
            isInside = FieldState(value = newIsInside, error = error, valid = error != null)
        )
        updateStateForm()

    }

    private fun updateStateForm() {

        val formValid = with(formState) {
            name.valid &&
                    description.valid &&
                    time.valid &&
                    date.valid &&
                    isInside.valid
        }

        formState = formState.copy(
            formIsValid = formValid
        )
    }


    fun save() {

        val dateArray = formState.date.value.split("/")
        val horaArray = formState.time.value.split(":")

        val dia = dateArray[0]
        val mes = dateArray[1]
        val ano = dateArray[2]

        val hora = horaArray[0]
        val minuto = horaArray[1]

        val calendar = Calendar.getInstance()
        calendar.set(ano.toInt(), mes.toInt(), dia.toInt(), hora.toInt(), minuto.toInt())

        val data = Snack(
            name = formState.name.value,
            description = formState.description.value,
            timestamp = calendar.time.time,
            isInside = formState.isInside.value!!
        )
        formState = formState.copy(isLoading = true)
        viewModelScope.launch {
            val result = repo.save(data)

            when (result) {
                is Response.Success -> {
                    formState = formState.copy(
                        isLoading = false,
                        onNavigateCreateSuccess = data.isInside,
                    )
                }

                is Response.Error -> {
                    formState = formState.copy(isLoading = false)

                }
            }
        }

    }

}