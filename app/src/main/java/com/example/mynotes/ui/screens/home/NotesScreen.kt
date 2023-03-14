package com.example.mynotes.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynotes.ui.component.Grid
import com.example.mynotes.ui.component.SearchBar
import com.example.mynotes.ui.component.ShowMessage


@Composable
fun NotesScreen(
    viewModel: NotesViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.loading) {
            CircularProgressIndicator()
        } else {
            ShowMessage(state.showMessage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Column {
            SearchBar()
            Spacer(modifier = Modifier.padding(4.dp))
            Grid(state.items)
        }
    }

}