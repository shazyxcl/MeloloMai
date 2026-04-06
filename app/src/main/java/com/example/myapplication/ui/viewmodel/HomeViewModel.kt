package com.example.myapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.DramaItem
import com.example.myapplication.data.repository.MeloloRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val latestDramas: List<DramaItem>,
        val trendingDramas: List<DramaItem>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

class HomeViewModel : ViewModel() {
    private val repository = MeloloRepository()
    
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadDramas()
    }
    
    fun loadDramas() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            
            try {
                val latestResult = repository.getLatestDramas()
                val trendingResult = repository.getTrendingDramas()
                
                if (latestResult.isSuccess && trendingResult.isSuccess) {
                    _uiState.value = HomeUiState.Success(
                        latestDramas = latestResult.getOrNull() ?: emptyList(),
                        trendingDramas = trendingResult.getOrNull() ?: emptyList()
                    )
                } else {
                    _uiState.value = HomeUiState.Error(
                        latestResult.exceptionOrNull()?.message 
                            ?: trendingResult.exceptionOrNull()?.message 
                            ?: "Unknown error"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
