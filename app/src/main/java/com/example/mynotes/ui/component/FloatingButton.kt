package com.example.mynotes.ui.component

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.mynotes.ui.AppMyNotesState
import com.example.mynotes.ui.navigation.NavItem

@Composable
fun FloatingButton(appState: AppMyNotesState) {

    FloatingActionButton(
        onClick = { appState.onNavItemClick(NavItem.NEWNOTE) },
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            tint = Color.Black
        )
    }

}