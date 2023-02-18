package com.example.mynotes.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLink
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
                    imageVector = Icons.Default.AddLink,
                    contentDescription = "add link",
                    tint = Color.White
                )
            }
        )
        Spacer(modifier = Modifier.weight(3f))
    }
}