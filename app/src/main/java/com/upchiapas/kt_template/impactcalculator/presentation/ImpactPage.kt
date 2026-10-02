package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.roundToInt

@Composable
fun ImpactPage(viewModel: ImpactViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding() // Corregir el solapamiento con la barra de estado
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Calculadora de Dopamina",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // 1. Selector de Red Social
        NetworkSelector(
            selectedNetwork = uiState.selectedNetwork,
            onNetworkSelected = { viewModel.onNetworkSelected(it) }
        )

        // 2. Control de Horas (Slider)
        HoursSlider(
            hours = uiState.dailyHours,
            onHoursChanged = { viewModel.onHoursChanged(it) }
        )

        // 3. Selector de Tipo de Contenido
        ContentTypeSelector(
            selectedContent = uiState.selectedContent,
            onContentSelected = { viewModel.onContentSelected(it) }
        )

        // 4. Tarjeta de Resultados Dinámica
        ImpactResultCard(uiState = uiState)
    }
}

// COMPONENTE 1: Selector de Red Social (Stateless)
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

// COMPONENTE 2: Slider de Horas (Stateless)
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

// COMPONENTE 3: Selector de Contenido (Stateless)
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

// COMPONENTE 4: Tarjeta de Impacto Visual (Stateless)
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