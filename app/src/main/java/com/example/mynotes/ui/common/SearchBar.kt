package com.example.mynotes.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar() {

    var text by remember { mutableStateOf(TextFieldValue("")) }


    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle.Default.copy(
                color = Color.White,
                fontSize = 16.sp
            ),
            placeholder = { Text(
                text = "Search notes",
                color = Color.LightGray
            ) },
            leadingIcon = { Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = Color.White
            ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp)
                .background(MaterialTheme.colors.onSecondary)
        )
    }
}