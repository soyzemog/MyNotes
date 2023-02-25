package com.example.mynotes.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconAddLink(colorIn: Color) {
    Icon(
        imageVector = Icons.Default.Language,
        contentDescription = "add link",
        modifier = Modifier.size(36.dp),
        tint = colorIn
    )

    Spacer(modifier = Modifier.padding(6.dp))

    Text(
        text = "Add URL",
        fontSize = 14.sp,
        color = colorIn
    )
}