package com.example.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianApplication
import com.example.amphibians.data.AmphibianRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY


class AmphibianViewModel(private val repo: AmphibianRepository) : ViewModel() {
    // TODO set loading enum
    var amphibianUiState: AmphibianUiState by mutableStateOf(AmphibianUiState(emptyList()))
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            // TODO surround with try catch
            amphibianUiState = AmphibianUiState(repo.getAmphibians())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as AmphibianApplication
                val repo = application.container.amphibianRepository
                AmphibianViewModel(repo)
            }
        }
    }
}
