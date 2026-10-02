package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImpactResultCard(uiState: ImpactUiState) {
    val cardColor = if (uiState.isDangerLevel) {
        MaterialTheme.colorScheme.errorContainer
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val progressColor = if (uiState.isDangerLevel) Color.Red else Color.Green

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = uiState.impactLevelText,
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = "Dopamina dañina generada: ${uiState.harmfulDopamineScore} pts")
            Text(text = "Dopamina saludable posible: ${uiState.healthyDopamineScore} pts")
            Text(text = "Proyección semanal: ${uiState.weeklyHarmfulHours.toInt()} hrs acumuladas")

            LinearProgressIndicator(
                progress = { (uiState.harmfulDopamineScore / 1000f).coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth(),
                color = progressColor
            )

            HorizontalDivider()

            Text(
                text = "Sugerencia de actividad:",
                style = MaterialTheme.typography.labelLarge
            )
            Text(text = uiState.suggestedActivity)
        }
    }
}