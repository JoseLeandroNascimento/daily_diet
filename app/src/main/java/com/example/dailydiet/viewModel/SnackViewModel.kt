package com.example.dailydiet.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.dailydiet.data.Snack
import com.example.dailydiet.data.SnackRepository
import com.example.dailydiet.model.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SnackViewUiState(
    val isLoading: Boolean = false,
    val isLoadingDelete: Boolean = false,
    val success: Response.Success<Snack?> = Response.Success(data = null),
    val error: Response.Error = Response.Error(message = null)
)

@HiltViewModel
class SnackViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repo: SnackRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SnackViewUiState())
    val uiState: StateFlow<SnackViewUiState> = _uiState

    private val snackId: Int = savedStateHandle["snackId"] ?: 0

    init {

        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {


            val response = repo.finById(snackId)

            when (response) {
                is Response.Success -> {
                    _uiState.update {
                        it.copy(
                            success = response,
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

    fun delete(){

        _uiState.update {
            it.copy(
                isLoadingDelete = true
            )
        }

        viewModelScope.launch {


            repo.deleteById(snackId)

            _uiState.update {
                it.copy(
                    isLoadingDelete = false
                )
            }
        }

    }
}