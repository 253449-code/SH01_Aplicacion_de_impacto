package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ImpactViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ImpactUiState())
    val uiState: StateFlow<ImpactUiState> = _uiState.asStateFlow()

    init {
        calculateImpact()
    }

    fun onNetworkSelected(network: SocialNetwork) {
        _uiState.update { it.copy(selectedNetwork = network) }
        calculateImpact()
    }

    fun onHoursChanged(hours: Float) {
        _uiState.update { it.copy(dailyHours = hours) }
        calculateImpact()
    }

    fun onContentSelected(content: ContentType) {
        _uiState.update { it.copy(selectedContent = content) }
        calculateImpact()
    }

    private fun calculateImpact() {
        val currentState = _uiState.value
        val baseScore = currentState.dailyHours * 100
        val harmfulScore = (baseScore * currentState.selectedNetwork.dopamineMultiplier * currentState.selectedContent.multiplier).toInt()
        val healthyScore = (baseScore * 1.5).toInt()

        val isDanger = harmfulScore > 500
        val levelText = if (isDanger) "Nivel Crítico: Riesgo de sobrecarga" else "Nivel Moderado: Consumo estable"

        val suggestion = when {
            currentState.dailyHours > 4 -> "Usa estas ${currentState.dailyHours.toInt()}h para ejercicio físico o aprender una habilidad."
            else -> "Sustituye este tiempo por lectura o meditación."
        }

        _uiState.update {
            it.copy(
                harmfulDopamineScore = harmfulScore,
                healthyDopamineScore = healthyScore,
                impactLevelText = levelText,
                suggestedActivity = suggestion,
                isDangerLevel = isDanger
            )
        }
    }
}