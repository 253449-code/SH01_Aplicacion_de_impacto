package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WeekendToggle(
    isWeekend: Boolean,
    onWeekendToggleChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "¿Es fin de semana?",
            style = MaterialTheme.typography.titleMedium
        )
        Switch(
            checked = isWeekend,
            onCheckedChange = onWeekendToggleChanged
        )
    }
}