package com.example.mynotes.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mynotes.ui.component.BottomBar
import com.example.mynotes.ui.component.FloatingButton
import com.example.mynotes.ui.screens.Screen

@Composable
fun Home(onNewNoteClick: () -> Unit) {

    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "My Notes",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    backgroundColor = MaterialTheme.colors.background
                )
            },
            floatingActionButton = {
                /** if (appState.showFloatingButton) {
                    FloatingButton(appState)
                } **/
                FloatingButton(onNewNoteClick)
            },
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = true,
            bottomBar = {
                /** if (appState.showBottomBar) {
                    BottomBar()
                } **/
                BottomBar()
            },
            scaffoldState = rememberScaffoldState()
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NotesScreen()
            }
        }
    }

}