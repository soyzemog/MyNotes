package com.example.mynotes.ui.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mynotes.ui.AppMyNotesState
import com.example.mynotes.ui.screens.note.NewNoteViewModel

@Composable
fun TopBar(
    appState: AppMyNotesState,
    viewModel: NewNoteViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

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
                    onClick = {
                        viewModel.saveNote()
                    }
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