package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ResetButton(
    onResetClicked: () -> Unit
) {
    OutlinedButton(
        onClick = onResetClicked,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null
        )
        Text(text = " Reiniciar Calculadora")
    }
}