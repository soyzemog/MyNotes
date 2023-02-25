package com.example.mynotes.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BottomBar() {
    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background,
        cutoutShape = CircleShape
    ) {
        BottomNav()
    }
}


@Composable
fun BottomNav() {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = {  },
            icon = {
                Icon(
                    imageVector = Icons.Default.AddPhotoAlternate,
                    contentDescription = "add image",
                    tint = Color.White
                )
            }
        )

        BottomNavigationItem(
            selected = false,
            onClick = {  },
            icon = {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = "add link",
                    tint = Color.White
                )
            }
        )
        Spacer(modifier = Modifier.weight(3f))
    }
}