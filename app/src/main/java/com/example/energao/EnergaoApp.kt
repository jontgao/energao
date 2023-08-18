package com.example.energao

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.energao.ui.screens.EnergaoViewModel
import com.example.energao.ui.screens.HomeScreen
import com.example.energao.ui.theme.EnergaoTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergaoApp(
    viewModel: EnergaoViewModel = viewModel()
) {
    Scaffold(
        topBar = { EnergaoTopBar() }
    ) { contentPadding ->
        val uiState by viewModel.uiState.collectAsState()
        HomeScreen(
            uiState = uiState,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnergaoTopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { Image(
            painter = painterResource(R.drawable.energao_transparent),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.padding(dimensionResource(R.dimen.top_bar_padding))
        ) },
        modifier = modifier
    )
}


@Preview
@Composable
fun PreviewEnergaoApp() {
    EnergaoTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            EnergaoApp()
        }
    }
}