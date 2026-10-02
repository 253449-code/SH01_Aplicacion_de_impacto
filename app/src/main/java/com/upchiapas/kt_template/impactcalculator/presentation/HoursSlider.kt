package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun HoursSlider(
    hours: Float,
    onHoursChanged: (Float) -> Unit
) {
    Column {
        Text(
            text = "2. Horas diarias de uso: ${hours.roundToInt()} hrs",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Slider(
            value = hours,
            onValueChange = onHoursChanged,
            valueRange = 1f..12f,
            steps = 10
        )
    }
}