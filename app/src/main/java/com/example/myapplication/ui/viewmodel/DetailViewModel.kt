package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.DramaDetail
import com.example.myapplication.data.repository.MeloloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val drama: DramaDetail) : DetailUiState()
    data class Error(val message: String) : DetailUiState()
}

class DetailViewModel : ViewModel() {
    private val repository = MeloloRepository()
    
    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()
    
    fun loadDramaDetail(bookId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading
            
            val result = repository.getDramaDetail(bookId)
            _uiState.value = if (result.isSuccess) {
                DetailUiState.Success(result.getOrNull()!!)
            } else {
                DetailUiState.Error(result.exceptionOrNull()?.message ?: "Failed to load drama details")
            }
        }
    }
}
