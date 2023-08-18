package com.example.energao.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.energao.EnergaoTopBar
import com.example.energao.R
import com.example.energao.ui.theme.EnergaoTheme

@Composable
fun HomeScreen(
    uiState: EnergaoUiState,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.card_spaced_by)),
        modifier = modifier
            .padding(dimensionResource(R.dimen.home_screen_padding))
            .verticalScroll(rememberScrollState())
    ) {
        PercentOfChargeCard(
            percentOfCharge = uiState.percentOfCharge,
            hoursRemaining = uiState.hoursRemaining
        )
        BatteryHealthCard(
            batteryHealth = uiState.batteryHealth
        )
        BatteryTempAndVoltageCardsRow(
            temp = uiState.temperature,
            voltage = uiState.voltage
        )
        SavingsCard(
            savingsEnergy = uiState.savingsEnergy,
            savingsBills = uiState.savingsBills,
            savingsCO2 = uiState.savingsCO2,
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    EnergaoTheme {
        val viewModel: EnergaoViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(uiState)
    }
}
