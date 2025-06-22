package com.example.dailydiet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydiet.data.Snack
import com.example.dailydiet.data.SnackRepository
import com.example.dailydiet.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SnackGroupDay(
    val date: String,
    val snacksDay: List<Snack>
)

data class HomeUiState(
    var isLoading: Boolean = false,
    val success: Response.Success<Flow<List<SnackGroupDay>>> = Response.Success(data = emptyFlow()),
    val error: Response.Error = Response.Error(message = null)
)

@HiltViewModel
class HomeSnacksViewModel @Inject constructor(
    private val repo: SnackRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val response = repo.findAll()

            when (response) {
                is Response.Success -> {
                    _uiState.update {
                        it.copy(
                            success = Response.Success(data = snackGroupListFlow(response.data)),
                            isLoading = false
                        )
                    }
                }

                is Response.Error -> {

                    _uiState.update {
                        it.copy(
                            error = response,
                            isLoading = false
                        )
                    }

                }
            }
        }

    }

    fun snackGroupListFlow(datas: Flow<List<Snack>>): Flow<List<SnackGroupDay>> {
        val formatter = SimpleDateFormat("dd.MM.yy", Locale.getDefault())

        return datas.map { snacks ->
            val grouped = snacks.groupBy { snack ->
                formatter.format(Date(snack.timestamp))
            }

            grouped.map { (date, snacksOfDay) ->
                SnackGroupDay(
                    date = date,
                    snacksDay = snacksOfDay
                )
            }
        }
    }

}