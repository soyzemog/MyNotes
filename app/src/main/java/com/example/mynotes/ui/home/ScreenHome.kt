package com.example.mynotes.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynotes.ui.common.Grid
import com.example.mynotes.ui.common.SearchBar


@Composable
fun ScreenHome() {
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