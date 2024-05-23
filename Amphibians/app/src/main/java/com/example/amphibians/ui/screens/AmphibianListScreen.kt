package com.example.amphibians.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.AmphibianUiState

@Composable
fun AmphibianListScreen(
    amphibianUiState: AmphibianUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(items = amphibianUiState.amphibians, key = Amphibian::name) {
            AmphibianCard(it, modifier = modifier)
        }
    }
}

@Composable
fun AmphibianCard(amphibian: Amphibian, modifier: Modifier = Modifier) {
    Text(text = amphibian.name)
}
