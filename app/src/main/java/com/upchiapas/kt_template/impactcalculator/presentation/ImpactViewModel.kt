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

    fun onWeekendToggleChanged(isWeekend: Boolean) {
        _uiState.update { it.copy(isWeekend = isWeekend) }
        calculateImpact()
    }

    fun onResetClicked() {
        _uiState.value = ImpactUiState()
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

        // Texto descriptivo del tiempo para las sugerencias
        val timeLabel = when {
            currentState.dailyHours == 0.5f -> "30 minutos"
            currentState.dailyHours == 1.0f -> "1 hora"
            currentState.dailyHours % 1.0f == 0f -> "${currentState.dailyHours.toInt()} horas"
            else -> "${currentState.dailyHours.toInt()} horas y media"
        }

        // Recomendaciones dinámicas según tiempo, dopamina saludable y nivel de riesgo
        val suggestion = when {
            currentState.dailyHours <= 1.0f ->
                "En $timeLabel puedes hacer ejercicio ligero o salir a caminar para obtener $healthyScore pts de dopamina saludable, cuidando tu salud cardiovascular y previniendo enfermedades."

            currentState.dailyHours <= 2.5f && !isDanger ->
                "En $timeLabel podrías leer un libro o aprender un idioma para generar $healthyScore pts de dopamina saludable, fortaleciendo tu memoria y capacidad de concentración."

            currentState.dailyHours <= 2.5f && isDanger ->
                "Consumo intenso: En $timeLabel podrías realizar una rutina de ejercicio fuerte o meditación para liberar $healthyScore pts de dopamina saludable, reduciendo el estrés y desintoxicando tu mente."

            currentState.dailyHours <= 4.5f ->
                "Inviertes $timeLabel diarios (${weeklyHours.toInt()}h/semana). En este tiempo podrías entrenar un deporte o dominar una habilidad técnica y ganar $healthyScore pts de dopamina saludable, mejorando tu rendimiento físico e intelectual."

            else ->
                "Alerta de tiempo crítico: En $timeLabel podrías avanzar proyectos personales o realizar un entrenamiento físico completo, acumulando $healthyScore pts de dopamina saludable, fortaleciendo tu autoestima y recuperando horas valiosas de vida."
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