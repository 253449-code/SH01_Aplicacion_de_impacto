package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NetworkSelector(
    selectedNetwork: SocialNetwork,
    onNetworkSelected: (SocialNetwork) -> Unit
) {
    Column {
        Text(
            text = "1. Selecciona la Red Social:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            SocialNetwork.entries.forEach { network ->
                FilterChip(
                    selected = network == selectedNetwork,
                    onClick = { onNetworkSelected(network) },
                    label = { Text(network.displayName) }
                )
            }
        }
    }
}