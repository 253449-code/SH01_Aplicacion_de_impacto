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

    // <--- Función que faltaba
    fun onWeekendToggleChanged(isWeekend: Boolean) {
        _uiState.update { it.copy(isWeekend = isWeekend) }
        calculateImpact()
    }

    private fun calculateImpact() {
        val currentState = _uiState.value

        val weekendMultiplier = if (currentState.isWeekend) 1.25f else 1.0f
        val baseScore = currentState.dailyHours * 100
        val harmfulScore = (baseScore * currentState.selectedNetwork.dopamineMultiplier * currentState.selectedContent.multiplier * weekendMultiplier).toInt()
        val healthyScore = (baseScore * 1.5f).toInt()
        val weeklyHours = currentState.dailyHours * 7f

        val isDanger = harmfulScore > 500
        val levelText = if (isDanger) "Nivel Crítico: Riesgo alto de adicción digital" else "Nivel Moderado: Consumo dentro de rangos manejables"

        val suggestion = when {
            currentState.dailyHours > 4 -> "Inviertes ${weeklyHours.toInt()}h a la semana. Podrías dominar una habilidad técnica o entrenar diariamente."
            currentState.isWeekend -> "Es fin de semana: aprovecha para hacer actividades al aire libre y desconectarte."
            else -> "Sustituye este tiempo por 30 minutos de lectura o meditación."
        }

        _uiState.update {
            it.copy(
                harmfulDopamineScore = harmfulScore,
                healthyDopamineScore = healthyScore,
                weeklyHarmfulHours = weeklyHours,
                impactLevelText = levelText,
                suggestedActivity = suggestion,
                isDangerLevel = isDanger
            )
        }
    }
}