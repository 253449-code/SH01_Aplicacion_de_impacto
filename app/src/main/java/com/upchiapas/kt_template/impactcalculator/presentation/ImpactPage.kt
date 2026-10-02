package com.upchiapas.kt_template.impactcalculator.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ImpactPage(viewModel: ImpactViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Calculadora de Dopamina",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        // Composables invocados desde sus respectivos archivos
        NetworkSelector(
            selectedNetwork = uiState.selectedNetwork,
            onNetworkSelected = { viewModel.onNetworkSelected(it) }
        )

        HoursSlider(
            hours = uiState.dailyHours,
            onHoursChanged = { viewModel.onHoursChanged(it) }
        )

        ContentTypeSelector(
            selectedContent = uiState.selectedContent,
            onContentSelected = { viewModel.onContentSelected(it) }
        )

        WeekendToggle(
            isWeekend = uiState.isWeekend,
            onWeekendToggleChanged = { viewModel.onWeekendToggleChanged(it) }
        )

        ImpactResultCard(uiState = uiState)
    }
}