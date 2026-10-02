package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    val statusIcon = if (uiState.isDangerLevel) Icons.Default.Warning else Icons.Default.Check
    val iconColor = if (uiState.isDangerLevel) MaterialTheme.colorScheme.error else Color(0xFF2E7D32)

    Card(
        colors = CardDefaults.cardColors(containerColor = cardColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = statusIcon,
                    contentDescription = null,
                    tint = iconColor
                )
                Text(
                    text = uiState.impactLevelText,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Text(text = "Dopamina dañina generada: ${uiState.harmfulDopamineScore} pts")
            Text(text = "Dopamina saludable posible: ${uiState.healthyDopamineScore} pts")
            Text(text = "Proyección semanal: ${uiState.weeklyHarmfulHours.toInt()} hrs acumuladas")

            LinearProgressIndicator(
                progress = { (uiState.harmfulDopamineScore / 1000f).coerceIn(0f, 1f) },
                modifier = Modifier.fillMaxWidth(),
                color = progressColor
            )

            HorizontalDivider()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "Sugerencia de actividad:",
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Text(text = uiState.suggestedActivity)
        }
    }
}