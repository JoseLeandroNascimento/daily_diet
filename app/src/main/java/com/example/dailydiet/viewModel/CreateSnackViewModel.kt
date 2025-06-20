package com.example.dailydiet.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydiet.data.Snack
import com.example.dailydiet.data.SnackRepository
import com.example.dailydiet.model.FieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


data class FormState(
    val name: FieldState<String> = FieldState(value = ""),
    val description: FieldState<String> = FieldState(value = ""),
    val date: FieldState<String> = FieldState(value = ""),
    val time: FieldState<String> = FieldState(value = ""),
    val isInside: FieldState<Boolean?> = FieldState(value = null),
)

@HiltViewModel
class CreateSnackViewModel @Inject constructor(
    private val repo: SnackRepository
) : ViewModel() {

    var formState by mutableStateOf(FormState())
        private set

    fun nameUpdate(newName: String) {

        var error: String? = null

        if (newName.isEmpty()) {
            error = "O nome é obrigatório"
        }

        formState = formState.copy(
            name = FieldState(value = newName, error = error)
        )
    }

    fun descriptionUpdate(newDescription: String) {

        var error: String? = null

        if (newDescription.isEmpty()) {
            error = "A descrição é obrigatório"
        }

        formState = formState.copy(
            description = FieldState(value = newDescription, error = error)
        )
    }

    fun dateUpdate(newDate: String) {
        val dateContainsDigits = newDate.filter {
            it in "0123456789/"
        }

        var error: String? = null

        if (!isDataValida(newDate)) {
            error = "Formato de data inválida"
        }
        formState = formState.copy(
            date = FieldState(value = dateContainsDigits, error = error)
        )
    }

    fun timeUpdate(newTime: String) {

        val timeContainsDigits = newTime.filter {
            it in "0123456789:"
        }

        var error: String? = null

        if (!isHoraValida(newTime)) {
            error = "Formato de hora inválida"
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

    fun isDataValida(data: String): Boolean {
        return try {
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            formato.isLenient = false
            formato.parse(data)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isHoraValida(hora: String): Boolean {
        return try {
            val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
            formato.isLenient = false
            formato.parse(hora)
            true
        } catch (e: Exception) {
            false
        }
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
        viewModelScope.launch {
            repo.save(data)
        }

    }

}