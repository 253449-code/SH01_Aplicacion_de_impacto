package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun HoursSlider(
    hours: Float,
    onHoursChanged: (Float) -> Unit
) {
    // Formato visual legible para el usuario
    val hoursFormatted = when {
        hours < 1f -> "30 min"
        hours % 1f == 0f -> "${hours.toInt()} hr${if (hours > 1f) "s" else ""}"
        else -> "${hours.toInt()} hr 30 min"
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "2. Horas diarias de uso: $hoursFormatted",
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Slider(
            value = hours,
            onValueChange = { newValue ->
                // Redondear a intervalos exactos de 0.5 (30 minutos)
                val stepValue = (newValue * 2).roundToInt() / 2f
                onHoursChanged(stepValue)
            },
            valueRange = 0.5f..7f,
            steps = 12 // 12 pasos intermedios entre 0.5 y 7.0
        )
    }
}