package com.example.dailydiet.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailydiet.data.SnackRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StatisticUiState(
    val snackNegativeStatic: Double = 0.0,
    val snackPositiveStatic: Double = 0.0,
    val countAllSnackPositive: Int = 0,
    val countAllSnackNegative: Int = 0,
    val countAll: Int = 0
)

@HiltViewModel
class StatisticViewModel @Inject constructor(
    private val repo: SnackRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatisticUiState())
    val uiState: StateFlow<StatisticUiState> = _uiState

    init {

        viewModelScope.launch {

            val snackNegativeStatic = repo.percentSnackNegative()
            val snackPositiveStatic = repo.percentSnackPositive()
            val countAllSnackPositive = repo.countAllSnackPositive()
            val countAllSnackNegative = repo.countAllSnackNegative()
            val countAll = repo.countAllSnack()

            _uiState.update {
                it.copy(
                    countAll = countAll,
                    snackPositiveStatic = snackPositiveStatic,
                    snackNegativeStatic = snackNegativeStatic,
                    countAllSnackNegative = countAllSnackNegative,
                    countAllSnackPositive = countAllSnackPositive,
                )
            }

        }

    }

}