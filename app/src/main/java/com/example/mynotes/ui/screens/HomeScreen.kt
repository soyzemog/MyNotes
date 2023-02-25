package com.example.mynotes.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.ui.component.Grid
import com.example.mynotes.ui.component.SearchBar


@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Column {
            SearchBar()
            Spacer(modifier = Modifier.padding(4.dp))
            Grid()
        }
    }

}