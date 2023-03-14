package com.example.mynotes.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ShowMessage(message: String) {
    Text(
        text = message,
        color = Color.LightGray
    )
}