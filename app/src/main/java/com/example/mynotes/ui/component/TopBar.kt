package com.example.mynotes.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mynotes.ui.AppMyNotesState

@Composable
fun TopBar(appState: AppMyNotesState) {

    TopAppBar(
        title = {
            if (appState.showTitle) {
                Text(
                    text = "My Notes",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        navigationIcon = {
            if (appState.showUpNavigation) {
                IconButton(
                    onClick = { appState.onUpClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "Go to previous Screen",
                        tint = Color.White
                    )
                }
            }
        },
        actions = {
            if (appState.showAction) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.TaskAlt,
                        contentDescription = "arrow confirm",
                        tint = Color.White
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background
    )

}