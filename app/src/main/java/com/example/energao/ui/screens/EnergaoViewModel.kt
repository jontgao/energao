package com.example.energao.ui.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EnergaoUiState(
    val percentOfCharge: Int = 100,
    val hoursRemaining: Float = 0f,
    val batteryHealth: String = "Loading...",
    val temperature: Float = 0f,
    val voltage: Float = 0f,
    val savingsEnergy: Float = 0f,
    val savingsBills: Float = 0f,
    val savingsCO2: Float = 0f,
)

class EnergaoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EnergaoUiState())
    val uiState: StateFlow<EnergaoUiState> = _uiState.asStateFlow()


    /**
     * Setters
     */
    fun setPercentOfCharge(percentOfCharge: Int) {
        _uiState.update { currentState ->
            currentState.copy(percentOfCharge = percentOfCharge)
        }
    }
    fun setHoursRemaining(hoursRemaining: Float) {
        _uiState.update { currentState ->
            currentState.copy(hoursRemaining = hoursRemaining)
        }
    }
    fun setBatteryHealth(batteryHealth: String) {
        _uiState.update { currentState ->
            currentState.copy(batteryHealth = batteryHealth)
        }
    }
    fun setTemperature(temperature: Float) {
        _uiState.update { currentState ->
            currentState.copy(temperature = temperature)
        }
    }
    fun setVoltage(voltage: Float) {
        _uiState.update { currentState ->
            currentState.copy(voltage = voltage)
        }
    }
    fun savingsEnergy(savingsEnergy: Float) {
        _uiState.update { currentState ->
            currentState.copy(savingsEnergy = savingsEnergy)
        }
    }
    fun savingsBills(savingsBills: Float) {
        _uiState.update { currentState ->
            currentState.copy(savingsBills = savingsBills)
        }
    }
    fun savingsCO2(savingsCO2: Float) {
        _uiState.update { currentState ->
            currentState.copy(savingsCO2 = savingsCO2)
        }
    }
}