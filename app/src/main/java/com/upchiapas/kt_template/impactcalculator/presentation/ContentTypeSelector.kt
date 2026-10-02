package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContentTypeSelector(
    selectedContent: ContentType,
    onContentSelected: (ContentType) -> Unit
) {
    Column {
        Text(
            text = "3. Tipo de contenido consumido:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            ContentType.entries.forEach { content ->
                FilterChip(
                    selected = content == selectedContent,
                    onClick = { onContentSelected(content) },
                    label = { Text(content.displayName) }
                )
            }
        }
    }
}