package com.example.mynotes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar() {

    //var searchText by remember { mutableStateOf(TextFieldValue("")) }

    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(28.dp)
            )
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "icon search",
            modifier = Modifier.size(36.dp),
            tint = Color.White
        )
        
        Spacer(modifier = Modifier.padding(start = 8.dp))
        
        BasicTextField(
            value = searchText,
            onValueChange = { newText ->
                searchText = newText
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            cursorBrush = SolidColor(MaterialTheme.colors.primaryVariant),
            decorationBox = { innerTextField ->
                if (searchText.isEmpty()) {
                    Text(
                        text = "Search your notes...",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
                innerTextField()
            },
            singleLine = true
        )
        /** OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
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
        ) **/
    }
}


@Preview(device = Devices.PIXEL_2)
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(8.dp)
        ) {
            SearchBar()
        }
    }
}