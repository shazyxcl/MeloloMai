package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.StreamData
import com.example.myapplication.data.repository.MeloloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PlayerUiState {
    object Loading : PlayerUiState()
    data class Success(val streamData: StreamData) : PlayerUiState()
    data class Error(val message: String) : PlayerUiState()
}

class PlayerViewModel : ViewModel() {
    private val repository = MeloloRepository()
    
    private val _uiState = MutableStateFlow<PlayerUiState>(PlayerUiState.Loading)
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()
    
    fun loadStream(videoId: String) {
        viewModelScope.launch {
            _uiState.value = PlayerUiState.Loading
            
            val result = repository.getStreamUrl(videoId)
            _uiState.value = if (result.isSuccess) {
                PlayerUiState.Success(result.getOrNull()!!)
            } else {
                PlayerUiState.Error(result.exceptionOrNull()?.message ?: "Failed to load stream")
            }
        }
    }
}
